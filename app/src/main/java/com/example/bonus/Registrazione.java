package com.example.bonus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Registrazione extends AppCompatActivity {
    TextView eta;
    Persona persona;
    EditText username,password,confermaPassword,citta;
    Button registarti,back;
    String dataToString;
    Calendar date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);
        eta=findViewById(R.id.dataNascita);
        username=findViewById(R.id.usernameEditText);
        password=findViewById(R.id.passwordEditText);
        confermaPassword=findViewById(R.id.passwordConfirmEditText);
        citta=findViewById(R.id.cittaEditText);
        registarti=findViewById(R.id.register);
        back=findViewById(R.id.backRegister);
        persona= new Persona();
        persona.dataDiNascita=null;
        registarti.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(generalError()){
                    fillPerson();
                    Intent intent = new Intent(Registrazione.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    //debug dopo
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
               finish();
            }
        });
        eta.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    eta.setError(null);
                    showDialog();
                }
            }
        });
        eta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eta.setError(null);
                showDialog();
            }
        });
    }
    void showDialog() {
        DialogFragment newFragment = DatePickerFrag.newInstance();
        newFragment.show(getSupportFragmentManager(), "dialog");
    }

    public void doPositiveClick(Calendar date) {
        SimpleDateFormat format= new SimpleDateFormat("dd/MM/YYYY");
        eta.setText(format.format(date.getTime()));
        dataToString=format.format(date.getTime()).toString();



    }

    public void doNegativeClick(Dialog dialog) {
        // Do stuff here.
        dialog.dismiss();
    }
    public void updateDataNascita(Calendar date){

    }

    //Check empty
    public boolean usernameCheck(String username){
        this.username.setError("vuoto");
        return username.length() != 0 && username != null;
    }
    public boolean passwordCheck(String password){
        if(password.length() == 0)
        this.password.setError("vuoto");
        return password.length() != 0 && password != null;
    }
    public boolean confermaPasswordCheck(String confermaPassword){
        if(confermaPassword.length() == 0)
        this.confermaPassword.setError("vuoto");
        return confermaPassword.length() != 0 && confermaPassword != null;
    }
    public boolean cittaCheck(String citta){
        this.citta.setError("vuoto");
        return citta.length() != 0 && citta != null;
    }
    public void equalPasswordCheck(String password, String confermaPassword){
            if(!password.equals(confermaPassword) && ((confermaPassword.length() != 0) && password.length() != 0)){

                this.password.setError("Diverse");
                this.confermaPassword.setError("Diverse");
            }else{
                if(passwordCheck(password))this.password.setError(null);
                if(confermaPasswordCheck(confermaPassword))this.confermaPassword.setError(null);
            }







    }

    public boolean generalError(){
        boolean correct=true;
        if(usernameCheck(username.getText().toString()))username.setError(null);
        if(cittaCheck(citta.getText().toString()))citta.setError(null);
        eta.setError(null);
        equalPasswordCheck(password.getText().toString(),confermaPassword.getText().toString());

        if(username.getError()!=null || password.getError()!=null || confermaPassword.getError()!=null || citta.getError()!=null || eta.getError()!=null ){
            correct=false;
        }
        for(Persona persona: Persona.dbPersone){
            if(persona.getUsername().equals(username.getText().toString())){
                correct=false;
                username.setError("Username già presente");
            }
        }
        if(eta.getText().toString().equals("")){
            correct=false;
            eta.setError("Vuoto");
        }
        return correct;

    }
    public void fillPerson(){

        Persona addPerson= new Persona(username.getText().toString(),password.getText().toString(),citta.getText().toString(),dataToString,false,false);
    }



}