package com.github.thogr.bedede;


public class TestStateFactory implements StateFactory {

    private Object currentView;
   // private final BddExecutor bdd;

    public TestStateFactory(final ConditionVerifier controller) {
     //   this.bdd = new BddExecutorImpl(this, controller);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T createState(final Class<T> viewClass) {
        try {
            //setCurrentView(viewClass.getConstructor(BddExecutor.class).newInstance(bdd));
            return (T) getCurrentView();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object getCurrentView() {
        return currentView;
    }

    void setCurrentView(final Object currentView) {
        this.currentView = currentView;
    }

    public void resetCurrentView() {
        this.currentView = null;
    }
}
