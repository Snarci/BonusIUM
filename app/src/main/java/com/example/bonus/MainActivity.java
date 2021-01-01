package com.example.bonus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    Button submit;
    TextView gotoRegister;
    public static boolean first=true;
    static String loggedUsername="admin";
    static String loggedPassword="admin";
    static String subjectUsername;
    static String subjectPassword;
    static Activity context;
    static boolean adminCall=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        Log.d("debuggo", "Init");
        //Creazione persone testing + admin
        if(Persona.dbPersone.size()==0){
            new Persona("admin","admin","","",true,false);
            new Persona("mario","rossi","Pompu","",false,false);
            new Persona("manu","massi","Pirri","",false,false);
            new Persona("mario1","rossi","Pompu","",false,false);
            new Persona("manu1","massi","Pirri","",false,false);
            new Persona("mario2","rossi","Pompu","",false,false);
            new Persona("manu2","massi","Pirri","",false,false);
            /*
            new Persona("mario3","rossi","Pompu","",false,false);
            new Persona("manu3","massi","Pirri","",false,false);
            new Persona("mario4","rossi","Pompu","",false,false);
            new Persona("manu4","massi","Pirri","",false,false);
            new Persona("mario5","rossi","Pompu","",false,false);
            new Persona("manu5","massi","Pirri","",false,false);

             */

        }

        username=findViewById(R.id.usernameEditText);
        password=findViewById(R.id.passwordEditText);
        username.setText(loggedUsername);
        password.setText(loggedPassword);
        submit=findViewById(R.id.accediButton);
        submit.setOnClickListener(new View.OnClickListener() {
            boolean usernameOk=false;
            boolean passwordOk=false;
            @Override
            public void onClick(View v) {
                usernameOk=false;
                passwordOk=false;
                if(generalError()){
                    Log.d("debuggo", "Primo layer");
                    //vai home
                    for (Persona persona:Persona.dbPersone) {
                        if(persona.getUsername().equals(username.getText().toString())){
                            usernameOk=true;
                        }
                        if(persona.getPassword().equals(password.getText().toString())){
                            passwordOk=true;
                        }
                        if(persona.getUsername().equals(username.getText().toString()) && persona.getPassword().equals(password.getText().toString())){

                            Intent intenzione = new Intent(MainActivity.this, Home.class);
                             loggedUsername=username.getText().toString();
                             loggedPassword=password.getText().toString();
                             adminCall=false;

                             startActivity(intenzione);



                        }

                    }
                    if(usernameOk && !passwordOk){
                        password.setError("Password errata");
                    }else if(!usernameOk){
                        username.setError("Username non presente");
                    }
                //login errato
                }else{
                    //debug qualcosa qui

                }
            }
        });

        //vai a registrazione
        gotoRegister=findViewById(R.id.gotoRegister);
        gotoRegister.setPaintFlags(gotoRegister.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        gotoRegister.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Registrazione.class);
                Log.d("debuggo", "==== layer");

                startActivity(intent);

            }
        });



    }

    //Check empty
    public boolean usernameCheck(String username){
        this.username.setError("vuoto");
        return username.length() != 0 && username != null;
    }
    public boolean passwordCheck(String password){
        this.password.setError("vuoto");
        return password.length() != 0 && password != null;
    }
    public boolean generalError(){
        boolean correct=true;
        if(usernameCheck(username.getText().toString())){username.setError(null);}else{correct=false;}
        if(passwordCheck(password.getText().toString())){password.setError(null);}else{correct=false;}
        return correct;
    }
}