package ladyishenlong.com.ioc_processor;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

public class BindViewProxyInfo extends BaseProxyInfo {

    public Map<Integer, VariableElement> mInjectElements = new HashMap<>();    //变量列表 view 的id 和变量

    public BindViewProxyInfo(Elements elementUtils, TypeElement typeElement, String SUFFIX) {
        super(elementUtils, typeElement, SUFFIX);
    }



    @Override
    public void generateMethod(final StringBuilder stringBuilder) {
        if (stringBuilder == null) {
            return;
        }
        stringBuilder.append("@Override\n")
                .append("public void inject(").append(typeElement.getQualifiedName()).append(" host, Object object )").append("{\n");

        for (Integer id : mInjectElements.keySet()) {
            VariableElement variableElement = mInjectElements.get(id);
            String name = variableElement.getSimpleName().toString();
            String type = variableElement.asType().toString();
            stringBuilder.append("if(object instanceof android.app.Activity)").append("{\n")
                    .append("host.").append(name).append(" = ")
                    .append("(").append(type).append(")((android.app.Activity)object).findViewById(").append(id).append(");")
                    .append("\n}\n")
                    .append("else").append("{\n")
                    .append("host.").append(name).append(" = ")
                    .append("(").append(type).append(")((android.view.View)object).findViewById(").append(id).append(");")
                    .append("\n}\n");
        }
        stringBuilder.append("\n}\n");
    }



}
