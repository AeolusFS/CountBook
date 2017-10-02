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
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Activity that allows the user to add a new counter to
 * the current counter list.
 *
 * @author zhai
 * @version 1.0
 * @since 1.0
 * Created by zhai on 9/25/17.
 */

public class AddNew extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        final Button save = (Button) findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText new_name = (EditText) findViewById(R.id.name_edit);
                EditText new_initial_value = (EditText) findViewById(R.id.initial_edit);
                EditText new_comment = (EditText) findViewById(R.id.comment_input);

                String name = new_name.getText().toString();
                String initial_value = new_initial_value.getText().toString();
                String comment = new_comment.getText().toString();

                /**
                 * Exception Handler.
                 */

                if( initial_value.length() == 0 ) {
                    new_initial_value.setError("No Initial Value");
                    return;
                }

                try {
                    int initial_test = Integer.parseInt(initial_value);
                }
                catch (Exception e){
                    new_initial_value.setError("Invalid Initial Value");
                    return;
                }

                if(Integer.parseInt(initial_value) > 10000){
                    new_initial_value.setError("Initial Value too large");
                    return;
                }

                if(Integer.parseInt(initial_value) < 0){
                    new_initial_value.setError("Negative Initial Value");
                    return;
                }

                if(name.length() == 0){
                    new_name.setError("No name");
                    return;
                }

                int initial_v2 = Integer.parseInt(initial_value);
                Counter_list.add(new TheCounter(name, initial_v2, comment));
                adapter.notifyDataSetChanged();
                saveInFile();

                Intent intent = new Intent(AddNew.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
