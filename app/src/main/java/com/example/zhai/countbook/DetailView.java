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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Activity that allows the user to view the detail for the
 * counter, increment, decrement and reset the current value
 * and choose to delete or edit the counter.
 *
 * @author zhai
 * @version 1.0
 * @since 1.0
 * Created by zhai on 9/25/17.
 */

public class DetailView extends MainActivity {

    private TextView pname;
    private TextView pcurrent;
    private TextView pdate;
    private TextView pcomment;
    private TextView pinitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        pname = (TextView) findViewById(R.id.name_detail);
        pcurrent = (TextView) findViewById(R.id.current_detail);
        pdate = (TextView) findViewById(R.id.date_detail);
        pcomment = (TextView) findViewById(R.id.comment_detail);
        pinitial = (TextView) findViewById(R.id.show_ini);

        final int position = this.getIntent().getIntExtra("counter",0);

        /**
         * Increment value
         */

        Button inc = (Button) findViewById(R.id.up);

        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Counter_list.get(position).increment();
                saveInFile();
                Intent intent = getIntent();
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
            }
        });

        /**
         * Decrement Value
         */

        Button dec = (Button) findViewById(R.id.down);

        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Counter_list.get(position).decrement();
                saveInFile();
                Intent intent = getIntent();
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
            }
        });

        /**
         * Reset value to its initial
         */

        Button reset = (Button) findViewById(R.id.reset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Counter_list.get(position).reset();
                saveInFile();
                Intent intent = getIntent();
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
            }
        });

        /**
         * Go to further edit to the counter.
         */

        Button edit = (Button) findViewById(R.id.edit);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailView.this, EditView.class);
                intent.putExtra("counter", position);
                startActivity(intent);
            }
        });

        /**
         * Delete the current counter.
         */

        Button delete = (Button) findViewById(R.id.delete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Counter_list.remove(position);
                CounterList.setAdapter(adapter);
                saveInFile();
                Intent intent = new Intent(DetailView.this, MainActivity.class);
                startActivity(intent);
            }
        });

        /**
         * Save changes and return to the main menu.
         */

        Button save = (Button) findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailView.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void onStart(){
        super.onStart();
        Intent intent = getIntent();
        int pos = intent.getIntExtra("counter",0);

        pname.setText(Counter_list.get(pos).getName());
        pcurrent.setText(String.valueOf(Counter_list.get(pos).getCurrent()));
        pdate.setText(Counter_list.get(pos).getDate());
        pcomment.setText(Counter_list.get(pos).getComment());
        pinitial.setText("Initial Value: " + String.valueOf(Counter_list.get(pos).getInitial()));

    }
}
