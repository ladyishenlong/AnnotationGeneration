package ladyishenlong.com.ioc;

import android.app.Activity;
import android.widget.RelativeLayout;

public class LayoutBinder {
    private static final String SUFFIX = "LayoutInjector";

    //Activity 中调用的方法
    public static void bind(Activity activity, RelativeLayout baseLayout) {
        bind(activity, activity,baseLayout);
    }

    /**
     * 1.寻找对应的代理类
     * 2.调用接口提供的绑定方法
     *
     * @param host
     * @param root
     */
    @SuppressWarnings("unchecked")
    private static void bind(final Object host, final Object root,RelativeLayout baseLayout) {
        if (host == null || root == null) {
            return;
        }

        Class<?> aClass = host.getClass();
        String proxyClassFullName = aClass.getName() +"$$"+SUFFIX;    //拼接生成类的名称

        try {
            Class<?> proxyClass = Class.forName(proxyClassFullName);
            LayoutInjector layoutInjector = (LayoutInjector) proxyClass.newInstance();
            if (layoutInjector != null) {
                layoutInjector.inject(host, root,baseLayout);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
