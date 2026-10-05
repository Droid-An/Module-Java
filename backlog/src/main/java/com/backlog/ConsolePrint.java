package com.backlog;

public class ConsolePrint implements PresentationLayer {

    @Override
    public void printSuccess(String s) {
        System.out.println(s);
    }
}
