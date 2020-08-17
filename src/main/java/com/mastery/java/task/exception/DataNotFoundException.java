package com.mastery.java.task.exception;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException() {
        super("Data not found.");
    }

}
