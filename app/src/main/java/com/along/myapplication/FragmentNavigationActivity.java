package com.along.myapplication;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class FragmentNavigationActivity extends Activity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, MyFragment2.Mylistener {
    private TextView textView;
    private Button button_fragment;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private EditText editText;
    private static final String TAG = "Fragment";
    private MyFragment1 myFragment1;
    private MyFragment2 myFragment2;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fragment_navigation);
        init();
    }

    private void init() {
        textView = (TextView) findViewById(R.id.textview_fragment);
        button_fragment = (Button) findViewById(R.id.button_fragment);
        radioGroup = (RadioGroup) findViewById(R.id.RadioGroupFragment);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        radioButton4 = (RadioButton) findViewById(R.id.radioButton4);
        editText = (EditText) findViewById(R.id.editTextA2F);
        fragmentManager = getFragmentManager();
        radioGroup.setOnCheckedChangeListener(this);
        radioButton1.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.radioButton: {
                break;
            }
            case R.id.radioButton2: {
                myFragment1 = new MyFragment1();
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.add(R.id.linearlayout, myFragment1, "myFragment1");
                beginTransaction.attach(myFragment1);
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                Log.d(TAG, "点了第二个按钮");
                break;
            }
            case R.id.radioButton3: {
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.remove(myFragment1);
                beginTransaction.remove(myFragment2);
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                Log.d(TAG, "点了第三个按钮");
                break;
            }
            case R.id.radioButton4: {
                myFragment2 = new MyFragment2();
                Bundle bundle = new Bundle();
                bundle.putString("name", editText.getText().toString());
                myFragment2.setArguments(bundle);
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.add(R.id.linearlayout, myFragment2, "myFragment2");
                beginTransaction.attach(myFragment2);
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                Log.d(TAG, "点了第四个按钮");
                break;
            }
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void thank(String code) {
        Toast.makeText(this, code, Toast.LENGTH_SHORT).show();
    }
}
