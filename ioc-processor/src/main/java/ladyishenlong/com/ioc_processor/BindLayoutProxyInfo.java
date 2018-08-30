package ladyishenlong.com.ioc_processor;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

public class BindLayoutProxyInfo extends BaseProxyInfo {

    public Map<Integer, TypeElement> injectElements = new HashMap<>();    //变量列表 view 的id 和变量


    public BindLayoutProxyInfo(Elements elementUtils, TypeElement typeElement, String SUFFIX) {
        super(elementUtils, typeElement, SUFFIX);
    }


    @Override
    protected void headfile(StringBuilder stringBuilder) {
        super.headfile(stringBuilder);
        stringBuilder.append("import android.widget.RelativeLayout;\n");
    }

    @Override
    protected void generateMethod(StringBuilder stringBuilder) {

        for (Integer integer : injectElements.keySet()) {
            stringBuilder.append("" +
                    "" +
                    "" +
                    "    @Override\n" +
                    "    public void inject(MainActivity mainActivity, Object source , RelativeLayout baseLayout) {\n" +


                    " //在这里可以生成代码  \n " +
                    "        mainActivity.setContentView("+ integer+");      \n" +
                    "    }");
        }



    }

}
