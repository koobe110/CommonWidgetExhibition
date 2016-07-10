package com.along.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class CommonWidgetActivity extends AppCompatActivity implements View.OnClickListener{
    private Button button1;
    private Button button2;
    private Button button3;
    private Button buttonToSec;
    private Calendar cal;
    private int year;
    private int month;
    private int day;

//    private AutoCompleteTextView autoCompleteTextView;
    private MultiAutoCompleteTextView multiAutoCompleteTextView;
    private String[] res = {"beijing1", "beijing2", "beijing3", "shanghai1", "shanghai2"};
    private UserDefinedToken tokenizer;
    private ToggleButton toggleButton;
    private ImageView imageView;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private RadioGroup radioGroup;
    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        requestWindowFeature(Window.FEATURE_PROGRESS);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        setContentView(R.layout.activity_first);
        new DatePickerDialog(CommonWidgetActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                setTitle(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            }
        }, year, month, day).show();
        setProgressBarVisibility(true);
        setProgressBarIndeterminateVisibility(true);
        setProgress(600);//设置带进度条的进度

        UserDefinedToken tokenizer = new UserDefinedToken();
        tokenizer.terminateToken(", ");
//        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, res);
//        autoCompleteTextView.setAdapter(adapter);

        multiAutoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView);
        multiAutoCompleteTextView.setAdapter(adapter);
        multiAutoCompleteTextView.setTokenizer(tokenizer);
//        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
//        System.out.println(new MultiAutoCompleteTextView.CommaTokenizer().terminateToken("beijing1, beijing2, shanghai"));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        button1 = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button1.setOnClickListener(this);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(this);
        result = (TextView) findViewById(R.id.result);
        buttonToSec = (Button) findViewById(R.id.button5);
        buttonToSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1to2 = new Intent(CommonWidgetActivity.this, ListViewActivity.class);
                startActivityForResult(intent1to2, 1);
            }
        });







        imageView = (ImageView) findViewById(R.id.imageView);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                imageView.setImageResource(isChecked?R.drawable.lighton:R.drawable.lightoff);
            }
        });
        checkBox1 = (CheckBox) findViewById(R.id.checkBox);
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i("tag", "go");
                if(isChecked){
                    String text = checkBox1.getText().toString();
                    Log.i("tag", text);
                }
            }
        });
        radioGroup = (RadioGroup) findViewById(R.id.RadioGroupID);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton3:
                        Log.i("tag", "花生");
                        break;
                    case R.id.radioButton2:
                        Log.i("tag", "瓜子");
                        break;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2){
            result.setText(data.getStringExtra("回传的数据"));
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(CommonWidgetActivity.this, "外部类实现信息弹出", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(CommonWidgetActivity.this, "弹出信息", Toast.LENGTH_SHORT).show();
    }

}
