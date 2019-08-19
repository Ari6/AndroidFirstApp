  package com.ntlts.first;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.KeyEventDispatcher;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ntlts.first.TermDB.TermEntry;

  public class MainActivity extends AppCompatActivity
          implements MyAdapter.OnTermClickListener{
    public static final String EXTRA_MESSAGE = "com.ntlts.first.MESSAGE";
    public TextView textView;
    TermDBHelper dbHelper = new TermDBHelper(this);
    RecyclerView listView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter mAdapter;
    List<String> itemIds = new ArrayList<>();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //format
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //end
        SQLiteDatabase termDb = dbHelper.getWritableDatabase();
        listView = (RecyclerView) findViewById(R.id.listView);
        layoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(RecyclerView.VERTICAL);
        listView.setLayoutManager(layoutManager);
        //List<String> list = Arrays.asList("One", "Two", "Three");

        String[] projection = {
                BaseColumns._ID,
                TermEntry.COLUMN_NAME_TITLE,
                TermEntry.COLUMN_NAME_START_DATE
        };

        Cursor cursor = termDb.query(
                TermEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        while (cursor.moveToNext()) {
            String itemId = cursor.getString(cursor.getColumnIndexOrThrow(TermEntry.COLUMN_NAME_TITLE));
            itemIds.add(itemId);
        }
        cursor.close();
        System.out.println("DB size" + itemIds.size());
        if(itemIds.size() == 0) {
            itemIds = Arrays.asList("None");
        }
        mAdapter = new MyAdapter(itemIds, this);
        listView.setAdapter(mAdapter);

    }

    public void sendMessage(View view){
        //code here
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void changeMessage(View view){
        textView = (TextView) findViewById(R.id.textView2);
        textView.setText(R.string.pressed_button);
        //setContentView(view);
    }
    public void createOnClick(View view){
        ContentValues values = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        values.put(TermEntry.COLUMN_NAME_TITLE, "Test1");
        values.put(TermEntry.COLUMN_NAME_START_DATE, "2019-08-18 12:10:35");
        int i = listView.getAdapter().getItemCount();
        long newRowId = db.insert(TermEntry.TABLE_NAME, null, values);
        System.out.println("Database inserted");

        String[] projection = {
                BaseColumns._ID,
                TermEntry.COLUMN_NAME_TITLE,
                TermEntry.COLUMN_NAME_START_DATE
        };

        Cursor cursor = db.query(
                TermEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        List<String> itemIds = new ArrayList<>();
        while (cursor.moveToNext()) {
            String itemId = cursor.getString(cursor.getColumnIndexOrThrow(TermEntry.COLUMN_NAME_TITLE));
            itemIds.add(itemId);
        }
        cursor.close();
        listView.setAdapter(new MyAdapter(itemIds,this));
    }

      @Override
      public void onClick(int position) {
        Intent intent = new Intent(this, SecondList.class);
        intent.putExtra("com.ntlts.first.EXTRA_NUMBER", position);
        startActivity(intent);
      }

  }
