package com.along.myapplication;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContentProviderActivity extends AppCompatActivity {
    private static final String TAG = "ContentProviderActivity";
    private TextView textViewContentProvider;
    private Button readContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        setTitle("ContentProvider");
        textViewContentProvider = (TextView) findViewById(R.id.textViewContentProvider);
        readContent = (Button) findViewById(R.id.buttonContentProvider);
        readContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver contentResolver = getContentResolver();
                Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME}, null, null, null);
                if (cursor != null) {
                    textViewContentProvider.setText("");
                    int contentCount = 0;
                    while (cursor.moveToNext()&&contentCount<30) {
                        int id = cursor.getInt(cursor.getColumnIndex("_ID"));
                        String name = cursor.getString(cursor.getColumnIndex("DISPLAY_NAME"));
                        textViewContentProvider.append(name + "");
                        Cursor cursor2 = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER}, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id, null, null);
                        if (cursor2 != null) {
                            while (cursor2.moveToNext()){
                                String phoneNumber = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                textViewContentProvider.append(phoneNumber+"\n");
                            }
                        }
                        cursor2.close();
                        contentCount++;
                    }
                    cursor.close();
                }
            }
        });

    }
}
