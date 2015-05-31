package org.sheep.androidanntationssample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Receiver;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.util.Random;

@EActivity(R.layout.activity_after)
public class AfterActivity extends Activity {

    @ViewById
    EditText etFirstNumberA;
    @ViewById
    EditText etSecondNumberA;
    @ViewById
    Button btnCalcA;

    @StringRes
    String sGj;
    @StringRes
    String sCalsIs;
    @StringRes
    String sAppToCalc;

    @AfterViews
    void initValues() {
        Random r = new Random();
        int val1 = Math.abs(r.nextInt() % 100);
        int val2 = Math.abs(r.nextInt() % 100);
        etFirstNumberA.setText(Integer.toString(val1));
        etSecondNumberA.setText(Integer.toString(val2));
    }

    @Click
    void btnCalcA() {
        Toast.makeText(this, sCalsIs, Toast.LENGTH_LONG).show();
        mediate();
    }

    @Background
    void mediate() {
        try {
            Thread.sleep(10000);
            realize();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @UiThread
    void realize() {
        Toast.makeText(this, sAppToCalc, Toast.LENGTH_SHORT).show();
    }

    @TextChange({R.id.etFirstNumberA, R.id.etSecondNumberA})
    public void onTextChanges(CharSequence charSequence, int start, int before, int count) {
        Toast.makeText(this, sGj, Toast.LENGTH_SHORT).show();
    }

    //Since AndroidAnnotations 3.2
    @Receiver(actions = "android.intent.action.PACKAGE_ADDED", dataSchemes = "package")
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getData() == null) return;
        Toast.makeText(this, "Welcome " + intent.getData().toString() + " !!", Toast.LENGTH_SHORT).show();
    }

}
