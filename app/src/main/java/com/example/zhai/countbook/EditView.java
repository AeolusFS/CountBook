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

/**
 * Activity that allows the user to do further editing
 * on a counter.
 *
 * @author zhai
 * @version 1.0
 * @since 1.0
 * Created by zhai on 9/25/17.
 */

package com.example.zhai.countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditView extends MainActivity {

    private TextView pname;
    private TextView pinitial;
    private TextView pcurrent;
    private TextView pcomment;

    int new_iniString = 0;
    int new_curString = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_view);

        pname = (EditText) findViewById(R.id.name_edit);
        pinitial = (EditText) findViewById(R.id.initial_edit);
        pcurrent = (EditText) findViewById(R.id.current_edit);
        pcomment = (EditText) findViewById(R.id.comment_edit);

        final int ed_position = this.getIntent().getIntExtra("counter",0);

        Button save = (Button) findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String new_name = pname.getText().toString();
                String new_initial = pinitial.getText().toString();
                String new_comment = pcomment.getText().toString();
                String new_current = pcurrent.getText().toString();

                /**
                 * Exception Handler.
                 */

                if( new_initial.length() == 0 ) {
                    pinitial.setError("No Initial Value");
                    return;
                }

                try {
                    new_iniString = Integer.parseInt(new_initial);
                }
                catch (Exception e){
                    pinitial.setError("Invalid Initial Value");
                    return;
                }

                if(Integer.parseInt(new_initial) > 10000){
                    pinitial.setError("Initial Value too large");
                    return;
                }

                if(Integer.parseInt(new_initial) < 0){
                    pinitial.setError("Negative Initial Value");
                    return;
                }

                if( new_current.length() == 0 ) {
                    pcurrent.setError("No Current Value");
                    return;
                }

                try {
                    new_curString = Integer.parseInt(new_current);
                }
                catch (Exception e){
                    pcurrent.setError("Invalid Current Value");
                    return;
                }

                if(Integer.parseInt(new_current) > 10000){
                    pcurrent.setError("Current Value too large");
                    return;
                }

                if(Integer.parseInt(new_current) < 0){
                    pcurrent.setError("Negative Current Value");
                    return;
                }

                if(new_name.length() == 0){
                    pname.setError("No name");
                    return;
                }

                Counter_list.get(ed_position).setName(new_name);
                Counter_list.get(ed_position).setInitial(new_iniString);
                Counter_list.get(ed_position).setComment(new_comment);
                Counter_list.get(ed_position).setCurrent(new_curString);

                saveInFile();
                Intent intent = new Intent(EditView.this, DetailView.class);
                intent.putExtra("counter",ed_position );
                startActivity(intent);
            }
        });
    }

    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        int ed_pos = intent.getIntExtra("counter", 0);

        pname.setText(Counter_list.get(ed_pos).getName());
        pinitial.setText(String.valueOf(Counter_list.get(ed_pos).getInitial()));
        pcurrent.setText(String.valueOf(Counter_list.get(ed_pos).getCurrent()));
        pcomment.setText(Counter_list.get(ed_pos).getComment());
    }
}
