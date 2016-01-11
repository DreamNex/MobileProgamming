package com.sidm.mgpgame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Dream on 11/1/2016.
 */
public class FileReading {


    public static void main(String[] args){
        try(BufferedReader br = new BufferedReader(new FileReader(("C:\\test.txt")))){
            String test;

            //change the bottom code here to be able to render check for numbers example , 1(x, y), 1(x,y)
            while((test = br.readLine()) != null){
                System.out.println(test);
            }
        }

        catch(IOException e){
            e.printStackTrace();
        }
    }
}
