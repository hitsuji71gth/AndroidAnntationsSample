package org.sheep.androidanntationssample;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    @ViewById
    Button btnBefore;

    @ViewById
    Button btnAfter;

    @Click
    void btnBefore() {
        startActivity(new Intent(this, BeforeActivity.class));
    }

    @Click
    void btnAfter() {
        startActivity(new Intent(this, AfterActivity_.class));
    }

}
