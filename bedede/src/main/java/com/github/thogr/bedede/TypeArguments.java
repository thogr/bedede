package com.github.thogr.bedede;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

final class TypeArguments<T> {

    private final Object obj;

    private TypeArguments(final Object obj) {
        this.obj = obj;
    }

    static <T> Class<T> typeArgument(final Object obj, final int index) {
        final TypeArguments<T> args = new TypeArguments<T>(obj);
        return args.get(index);
    }

    static <T> Class<T> typeArgument(final Object obj) {
        return typeArgument(obj, 0);
    }

    private Type[] getActualTypeArguments(final Class<?> cClass) {
        return getGenericType(cClass).getActualTypeArguments();
    }

    private ParameterizedType getGenericType(final Type cClass) {
        if (cClass instanceof ParameterizedType) {
            return (ParameterizedType) cClass;
        } else if (cClass instanceof Class) {
            final Type superclass = getGenericSuperClass(cClass);
            return getGenericType(superclass);
        } else {
            throw new IllegalArgumentException("Not a ParameterizedType:" + cClass);
        }
    }

    private Type getGenericSuperClass(final Type cClass) {
        if (cClass instanceof ParameterizedType){
            final ParameterizedType parameterizedType = (ParameterizedType) cClass;
            final Class<?> raw = (Class<?>) parameterizedType.getRawType();
            return raw.getGenericSuperclass();
        }
        return ((Class<?>) cClass).getGenericSuperclass();
    }

    @SuppressWarnings("unchecked")
    Class<T> get(final int i) {
       final Class<? extends Object> clazz = obj.getClass();
       final Type[] args = getActualTypeArguments(clazz);
       final Type t = args[i];
       if (t instanceof TypeVariable<?>) {
           final TypeVariable<?> v = (TypeVariable<?>) t;
           System.err.println("Class: " + clazz.getName());
           System.err.println("TypeVariable: " + v);
           System.err.println("TypeName: " + v.getTypeName());
       }

       return (Class<T>) t;
    }


 // getting the SerializedLambda
    static SerializedLambda getSerializedLambda(final Object function) {
        if (function == null || !(function instanceof java.io.Serializable)) {
            throw new IllegalArgumentException();
        }

        for (Class<?> clazz = function.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            try {
                final Method replaceMethod = clazz.getDeclaredMethod("writeReplace");
                replaceMethod.setAccessible(true);
                final Object serializedForm = replaceMethod.invoke(function);

                if (serializedForm instanceof SerializedLambda) {
                    return (SerializedLambda) serializedForm;
                }
            }
            catch (final NoSuchMethodError e) {
                // fall through the loop and try the next class
            }
            catch (final Throwable t) {
                throw new RuntimeException("Error while extracting serialized lambda", t);
            }
        }

        throw new RuntimeException("writeReplace method not found");
    }

 // getting the synthetic static lambda method
    static Method getLambdaMethod(final SerializedLambda lambda) throws Exception {
        final String implClassName = lambda.getImplClass().replace('/', '.');
        final Class<?> implClass = Class.forName(implClassName);

        final String lambdaName = lambda.getImplMethodName();

        for (final Method m : implClass.getDeclaredMethods()) {
            if (m.getName().equals(lambdaName)) {
                return m;
            }
        }

        throw new Exception("Lambda Method not found");
    }

}
