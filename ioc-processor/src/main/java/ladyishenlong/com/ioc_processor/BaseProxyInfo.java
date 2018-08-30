package ladyishenlong.com.ioc_processor;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

/**
 * base代理类
 */
public abstract class BaseProxyInfo {

    protected String SUFFIX;  //生成类中需要实现的接口名称
    protected TypeElement typeElement;    //类信息
    protected String packageName;    //包名
    protected String proxyClassName;    //注解生成的代理类名称 (注解所在类名)$$(实现的接口名)

    public BaseProxyInfo(final Elements elementUtils, final TypeElement typeElement, String SUFFIX) {
        this.SUFFIX = SUFFIX;
        this.typeElement = typeElement;
        PackageElement packageElement = elementUtils.getPackageOf(typeElement);
        packageName = packageElement.getQualifiedName().toString();
        String className = getClassName(typeElement, packageName);
        proxyClassName = className + "$$" + SUFFIX;
    }


    private String getClassName(final TypeElement typeElement, final String packageName) {
        int packageLength = packageName.length() + 1;
        return typeElement.getQualifiedName().toString().substring(packageLength).replace('.', '$');
    }


    /**
     * 生成java文件的内容
     *
     * @return
     */
    public String generateJavaCode() {
        StringBuilder stringBuilder = new StringBuilder();
        //stringBuilder 中不要再使用 + 拼接字符串
        //生成类
        stringBuilder.append("// 自动生成 禁止修改!\n")
                .append("package ").append(packageName).append(";\n\n")
                .append("import ladyishenlong.com.ioc.*;\n\n");
        headfile(stringBuilder);
        stringBuilder.append("public class ").append(proxyClassName).append(" implements ").append(SUFFIX).append("<").append(typeElement.getQualifiedName()).append(">").append("{\n");
        generateMethod(stringBuilder);
        stringBuilder.append("\n}\n");
        return stringBuilder.toString();
    }

    /**
     * 生成类中的方法
     */
    protected abstract void generateMethod(StringBuilder stringBuilder);

    /**
     * 添加头文件
     */
    protected  void headfile(StringBuilder stringBuilder){

    }


    /**
     * 代理类的全名
     *
     * @return
     */
    public String getProxyClassFullName() {
        return packageName + "." + proxyClassName;
    }

    /**
     * 获得类
     *
     * @return
     */
    public TypeElement getTypeElement() {
        return typeElement;
    }

}
