package com.github.thogr.bedede;

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
}
