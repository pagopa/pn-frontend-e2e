package it.frontend.e2e.framework.core.presentation_binder;

public interface IBinder {
    <T> T bind(Class<T> type);
}
