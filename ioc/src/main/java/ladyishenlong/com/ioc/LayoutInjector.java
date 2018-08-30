package ladyishenlong.com.ioc;

import android.widget.RelativeLayout;

public interface LayoutInjector<T> {
    void inject(T t, Object source,RelativeLayout baseLayout);
}
