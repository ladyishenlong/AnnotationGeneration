package ladyishenlong.com.ioc;

public interface ViewInjector<T> {
    void inject(T t, Object source);
}
