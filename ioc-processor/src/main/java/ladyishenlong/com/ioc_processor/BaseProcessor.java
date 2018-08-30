package ladyishenlong.com.ioc_processor;

import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * base注解解释器
 * @param <T>
 */
public abstract class BaseProcessor<T extends BaseProxyInfo> extends AbstractProcessor {

    protected Elements elementUtils; //基于元素进行操作的工具方法
    protected Filer fileCreator;     //代码创建者
    protected Messager messager;     //日志，提示者，提示错误、警告

    protected Map<String, T> proxyMap;// 储存代理类的map String是包名，T是代理类


    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        elementUtils = processingEnvironment.getElementUtils();
        fileCreator = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
        proxyMap = new HashMap<>();
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        processAnnotation(set, roundEnvironment);

        //2.生成代理类
        for (String key : proxyMap.keySet()) {
            T proxyInfo = proxyMap.get(key);
            try {
                //创建文件对象
                JavaFileObject sourceFile = fileCreator.createSourceFile(
                        proxyInfo.getProxyClassFullName(),
                        proxyInfo.getTypeElement());
                Writer writer = sourceFile.openWriter();
                writer.write(proxyInfo.generateJavaCode());     //写入文件
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }


    /**
     * 子类处理注解
     */
    protected abstract void processAnnotation(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment);

    protected void printMessage(String message){
        messager.printMessage(Diagnostic.Kind.NOTE, message);

    }



}
