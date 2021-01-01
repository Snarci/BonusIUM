package com.example.bonus;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Persona  {



    public String username;
    public String password;
    public String citta;
    public String dataDiNascita;
    public boolean isAdmin;



    public int imageId = R.drawable.iconfinder_people_4781;
    public static ArrayList<String> dbUsername= new ArrayList<String>();
    public static ArrayList<Integer> dbAvatar= new ArrayList<Integer>();
    public static ArrayList<Persona> dbPersone = new ArrayList<Persona>();
    public static ArrayList<Persona> dbPersoneFilter = new ArrayList<Persona>();
    public static ArrayList<String> dbUsernameFilter = new ArrayList<String>();
    public Persona(){
        this.username="";
        this.password="";

    }


    public Persona(String username, String password,  String citta, String eta,boolean isAdmin,boolean justCreate){
        this.username=username;
        this.password=password;
        this.citta=citta;
        this.dataDiNascita=eta;
        this.isAdmin=isAdmin;
        if(!justCreate){
            dbUsername.add(username);
            dbAvatar.add(R.drawable.addmim);
            dbPersone.add(new Persona(username,password,citta,eta,isAdmin,true));
            Log.d("debuggo", "personeasd:: "+Persona.dbPersone);
        }

    }
    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(String dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
/*
    public File createFile(){
        try {
            File myObj = new File(filename);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                return myObj;
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            return null;
        }
        return null;
    }
    public void writeFile(String msg){
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(msg);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String readFile(){
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
*/
}