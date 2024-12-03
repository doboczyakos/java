package com.example.eloadas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import mnbSoap.MNBArfolyamServiceSoap;
import mnbSoap.MNBArfolyamServiceSoapImpl;

public class mnbSoap{



    MNBArfolyamServiceSoapImpl impl;
    MNBArfolyamServiceSoap service;
    String filename;

    mnbSoap() {
        this.impl = new MNBArfolyamServiceSoapImpl();
        this.service = impl.getCustomBindingMNBArfolyamServiceSoap();
        this.filename="MNB.txt";
    }

    public String letoltes1(){
        String adatok="";
        try {
            adatok=service.getCurrentExchangeRates();
        }catch (Exception e){
        }
        writeToFile(adatok);
        String result=adatok;
        return result;
    }

    public String letoltes2(String currency,String startDate,String endDate){
        String adatok="";
        try {
            adatok=service.getExchangeRates(startDate,endDate,currency);
        }catch (Exception e){
        }
        String result=adatok;
        writeToFile(adatok);
        return result;
    }

    void clearFile(){
        try {
            //File file=new File("c:\\adatok",filename);
            FileWriter myWriter = new FileWriter(new File("c:\\adatok\\",filename));
            myWriter.write("");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void writeToFile(String adat){
        clearFile();
        try {
            FileWriter myWriter = new FileWriter("c:\\adatok\\"+filename);
            myWriter.write(adat);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}
