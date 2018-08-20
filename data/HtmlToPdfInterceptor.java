package com.jusfoun.jxsd.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HtmlToPdfInterceptor extends Thread {


    private InputStream is;

    public HtmlToPdfInterceptor(InputStream is) {
        this.is = is;
    }

    public void run() {
        try {
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

