package com.example.franonwheels.Util;

public class CustomException extends Exception{

    public CustomException(String message){
        super(message);
    }

    public static CustomException userExists(){
        return new CustomException("User with same credentials already exists");
    }
}
