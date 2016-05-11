package com.along.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileActivity extends AppCompatActivity {

    private Button readAndWhite;
    private TextView textToWhite;
    private EditText textToRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        setTitle("文件写入、读取");
        readAndWhite = (Button) findViewById(R.id.buttonFile);
        textToRead = (EditText) findViewById(R.id.editTextFile);
        textToWhite = (TextView) findViewById(R.id.textViewFile);
        readAndWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    WriteFiles(textToRead.getText().toString());
                    textToWhite.setText(readFiles());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void WriteFiles(String content) throws IOException {
        FileOutputStream fos = openFileOutput("text.txt", MODE_PRIVATE);
        fos.write(content.getBytes());
        fos.close();
    }

    public String readFiles() throws IOException {
        String content = null;
        FileInputStream fis = openFileInput("text.txt");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = fis.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        content = baos.toString();
        fis.close();
        baos.close();
        return content;
    }
}
