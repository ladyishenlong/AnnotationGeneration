package ladyishenlong.com.ioc_processor;

import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

import ladyishenlong.com.ioc_annotation.BindView;

@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_7)    //支持的源码版本
@SupportedAnnotationTypes("ladyishenlong.com.ioc_annotation.BindView")//注解位置
public class BindViewProcessor extends BaseProcessor<BindViewProxyInfo> {


    @Override
    protected void processAnnotation(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        messager.printMessage(Diagnostic.Kind.NOTE, "bindView process...");

        //获取了注解修饰的东西（这里是view的变量）
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindView.class);

        //遍历变量
        for (Element element : elements) {

            //这里可以再加一个判断类，判断变量是否是属于view

            //将将元素转化为变量
            VariableElement variableElement = (VariableElement) element;

            //获取该变量所在的类
            TypeElement typeElement = (TypeElement) variableElement.getEnclosingElement();

            //获得该类的名称
            String qualifiedName = typeElement.getQualifiedName().toString();

            //获取map中的东西
            BindViewProxyInfo proxyInfo = proxyMap.get(qualifiedName);

            if (proxyInfo == null) {
                //将该类中被注解修饰的变量加入到 ProxyInfo 中
                proxyInfo = new BindViewProxyInfo(elementUtils, typeElement, "ViewInjector");
                proxyMap.put(qualifiedName, proxyInfo);
            }

            BindView annotation = variableElement.getAnnotation(BindView.class);

            if (annotation != null) {
                int id = annotation.value();
                proxyInfo.mInjectElements.put(id, variableElement);
            }
        }


    }
}
