/*
 * MainActivity
 *
 * Version 1.0
 *
 * October 1st, 2017
 *
 * Copyright (c) 2017 Yuhao Zhai, CMPUT301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code of Student Behavior at University of Alberta.
 * You can find a copy of the license in this project. Otherwise please contact zhai@ualberta.ca
 */

package com.example.zhai.countbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.ArrayList;

/**
 * Activity that displays the list of counters, and allows
 * the users to add new counter or choose to edit or see
 * details of a particular counter.
 *
 * @author zhai
 * @version 1.0
 * @since 1.0
 * Created by zhai on 9/25/17.
 */

public class MainActivity extends AppCompatActivity {

    public static final String FILENAME = "file.sav";
    public EditText name;
    public ListView CounterList;
    public List<TheCounter> Counter_list = new ArrayList<>();
    public ArrayAdapter<TheCounter> adapter;
    public TextView CountersNo;
    int Item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button add = (Button) findViewById(R.id.add);

        CounterList = (ListView) findViewById(R.id.CounterList);
        CountersNo = (TextView) findViewById(R.id.CountersNo);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNew.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(CounterList);
        CounterList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                Item=position;
                return false;
            }
        });
    }



    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<TheCounter>(this, R.layout.list_item, Counter_list);
        CounterList.setAdapter(adapter);
        CountersNo.setText(String.valueOf(CounterList.getAdapter().getCount())+" Counters.");

    }

    /**
     * Create a menu that allows the user to choose to edit or see
     * details of a particular counter.
     *
     * @param menu
     * @param v
     * @param menuInfo
     */

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Options");
        menu.add(0, v.getId(), 0, "details");
        menu.add(0, v.getId(), 0, "edit");
    }

    /**
     * Handle the user's choose.
     *
     * @param item
     * @return
     */

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        if (item.getTitle().equals("details")) {
            Intent godetail = new Intent(MainActivity.this, DetailView.class);
            godetail.putExtra("counter", position);
            startActivity(godetail);
        }
        else if (item.getTitle().equals("edit")) {
            Intent goedit = new Intent(MainActivity.this, EditView.class);
            goedit.putExtra("counter", position);
            startActivity(goedit);
        }

        else {
            return false;
        }
        return true;
    }

    /**
     * loadFromFile
     *
     * reference https://github.com/watts1/lonelyTwitter
     */

    public void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();
            Counter_list = gson.fromJson(in, new TypeToken<ArrayList<TheCounter>>() {
            }.getType());

            fis.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            Counter_list = new ArrayList<TheCounter>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    /**
     * saveInFile
     *
     * reference https://github.com/watts1/lonelyTwitter
     */

    public void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();

            gson.toJson(Counter_list, writer);

            writer.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}

