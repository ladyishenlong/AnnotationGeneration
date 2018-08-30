package ladyishenlong.com.ioc_processor;

import com.google.auto.service.AutoService;

import java.util.Set;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import ladyishenlong.com.ioc_annotation.BindLayout;
import ladyishenlong.com.ioc_annotation.BindView;


@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_7)    //支持的源码版本
@SupportedAnnotationTypes("ladyishenlong.com.ioc_annotation.BindLayout")//注解位置
public class BindLayoutProcessor extends BaseProcessor<BindLayoutProxyInfo> {


    @Override
    protected void processAnnotation(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        printMessage("bindLayout process...");

        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindLayout.class);

        for (Element element : elements) {

            TypeElement typeElement = (TypeElement) element;
            String qualifiedName = typeElement.getQualifiedName().toString();
            BindLayoutProxyInfo proxyInfo = proxyMap.get(qualifiedName);

            if (proxyInfo == null) {
                proxyInfo = new BindLayoutProxyInfo(elementUtils, typeElement, "LayoutInjector");
                proxyMap.put(qualifiedName, proxyInfo);
            }

            BindLayout annotation = typeElement.getAnnotation(BindLayout.class);

            if(annotation!=null){
                int layoutId=annotation.value();
                proxyInfo.injectElements.put(layoutId,typeElement);
            }


        }


    }


}
