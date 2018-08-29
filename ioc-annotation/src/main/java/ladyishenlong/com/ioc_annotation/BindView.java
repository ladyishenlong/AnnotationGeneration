package ladyishenlong.com.ioc_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)//运行时生成注解
@Target(ElementType.FIELD)//作用于变量
public @interface BindView {
     int value();
}
