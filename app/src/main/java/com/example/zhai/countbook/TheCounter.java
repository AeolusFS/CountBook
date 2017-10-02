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

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a counter object.
 *
 * @author zhai
 * @version 1.0
 * @since 1.0
 * Created by zhai on 9/25/17.
 */

public class TheCounter {

    private String name;
    private Date dateS;
    private int initial;
    private int current;
    private String comment;
    private String date;

    /**
     * Create a new counter object.
     *
     * @param name
     * @param initial
     * @param comment
     */

    public TheCounter(String name, int initial, String comment){
        this.name = name;
        this.dateS = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.date = dateFormat.format(dateS);
        this.initial = initial;
        this.current = initial;
        this.comment = comment;
    }

    /**
     * Return a string representing name.
     *
     * @return
     */

    public String getName(){
        return this.name;
    }

    /**
     * Return a string representing date
     * @return
     */

    public String getDate() { return this.date; }

    /**
     * Return a string representing initial value.
     *
     * @return
     */

    public int getInitial(){
        return this.initial;
    }

    /**
     * Return a string representing current value.
     *
     * @return
     */

    public int getCurrent(){
        return this.current;
    }

    /**
     * Return a string representing comment.
     *
     * @return
     */

    public String getComment(){
        return this.comment;
    }

    /**
     * A method that change the name.
     */

    public void setName(String name) { this.name = name; }

    /**
     * A method that change the initial value.
     */

    public void setInitial(int initial){
        this.initial = initial;
    }

    /**
     * A method that change the current value.
     */

    public void setCurrent(int current){
        this.current = current;
    }

    /**
     * A method that change the comment.
     */

    public void setComment(String comment){
        this.comment = comment;
    }

    /**
     * A method that increment the current value by 1.
     */

    public void increment(){
        this.current ++;
    }

    /**
     * A method that decrement the current value by 1.
     */

    public void decrement(){
        this.current --;
    }

    /**
     * A method that reset the current value to the initial value.
     */

    public void reset(){
        this.current = this.initial;
    }

    /**
     * Formatting counter's information to a string output.
     *
     * @return
     */

    @Override
    public String toString(){
        return dateS.toString() + " | " + name + " | " + current;
    }

}
