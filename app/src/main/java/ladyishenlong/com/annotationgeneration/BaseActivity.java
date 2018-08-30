package ladyishenlong.com.annotationgeneration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import ladyishenlong.com.ioc.LayoutBinder;
import ladyishenlong.com.ioc.ViewBinder;

public class BaseActivity extends AppCompatActivity {


    RelativeLayout baseLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        baseLayout=(RelativeLayout)findViewById(R.id.baseLayout);

        LayoutBinder.bind(this,baseLayout);
        ViewBinder.bind(this);
    }
}
