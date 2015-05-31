package org.sheep.androidanntationssample;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;


public class BeforeActivity extends Activity implements TextWatcher {

    EditText etFirstNumberB;
    EditText etSecondNumberB;
    Button btnCalcB;

    BroadcastReceiver brInstallation;

    String sGj;
    String sCalsIs;
    String sAppToCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before);

        sGj = getResources().getString(R.string.sGj);
        sCalsIs = getResources().getString(R.string.sCalsIs);
        sAppToCalc = getResources().getString(R.string.sAppToCalc);

        etFirstNumberB = (EditText)findViewById(R.id.etFirstNumberB);
        etSecondNumberB = (EditText)findViewById(R.id.etSecondNumberB);
        btnCalcB = (Button)findViewById(R.id.btnCalcB);

        etFirstNumberB.addTextChangedListener(this);
        etSecondNumberB.addTextChangedListener(this);

        btnCalcB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BeforeActivity.this, sCalsIs, Toast.LENGTH_LONG).show();
                MyAsyncTask task = new MyAsyncTask();
                task.execute();
            }
        });

        brInstallation = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent == null || intent.getData() == null) return;
                Toast.makeText(BeforeActivity.this, "Welcome " + intent.getData().toString() + " !!", Toast.LENGTH_SHORT).show();
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.PACKAGE_ADDED");
        filter.addDataScheme("package");
        registerReceiver(brInstallation, filter);

        initValues();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(brInstallation);
        super.onDestroy();
    }

    void initValues() {
        Random r = new Random();
        int val1 = Math.abs(r.nextInt() % 100);
        int val2 = Math.abs(r.nextInt() % 100);
        etFirstNumberB.setText(Integer.toString(val1));
        etSecondNumberB.setText(Integer.toString(val2));
    }

    class MyAsyncTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            Toast.makeText(BeforeActivity.this, sAppToCalc, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        Toast.makeText(this, sGj, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void afterTextChanged(Editable editable) {}
}
