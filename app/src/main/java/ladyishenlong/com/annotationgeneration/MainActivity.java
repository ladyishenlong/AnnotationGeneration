package ladyishenlong.com.annotationgeneration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import ladyishenlong.com.ioc.LayoutBinder;
import ladyishenlong.com.ioc.ViewBinder;
import ladyishenlong.com.ioc_annotation.BindLayout;
import ladyishenlong.com.ioc_annotation.BindView;

/**
 * 自定义注解，自动生成代码方法的记录
 * <p>
 * 1、创建ioc-annotation javaLibrary 只是用来存放注解
 * 2、创建ioc androidLibrary 编写注解的入口
 * 3、创建ioc-processor 编写注解的解释器
 */
@BindLayout(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tv.setText("6666");
    }


    /**
     * TypeElement	一个类或接口程序元素
     * VariableElement	一个字段、enum 常量、方法或构造方法参数、局部变量或异常参数
     * ExecutableElement	某个类或接口的方法、构造方法或初始化程序（静态或实例），包括注解类型元素
     * PackageElement	一个包程序元素
     * TypeParameterElement	一般类、接口、方法或构造方法元素的泛型参数
     * */

    /**
     * etNestingKind	返回此类型元素的嵌套种类
     * getQualifiedName	返回此类型元素的完全限定名称。更准确地说，返回规范 名称。对于没有规范名称的局部类和匿名类，返回一个空名称.
     * getSuperclass	返回此类型元素的直接超类。如果此类型元素表示一个接口或者类 java.lang.Object，则返回一个种类为 NONE 的 NoType
     * getInterfaces	返回直接由此类实现或直接由此接口扩展的接口类型
     * getTypeParameters
     * */
}
