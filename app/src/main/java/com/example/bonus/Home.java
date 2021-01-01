package com.example.bonus;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    public TextView username,password,citta,data,benvenuto,ituoidati,modificaPassword,home;
    public Button gestisciUtenti,logout,back;
    public ImageView imageAdmin,imageAvatar;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        imageAvatar=findViewById(R.id.avatarHome);
        benvenuto=findViewById(R.id.benvenuto);
        username=findViewById(R.id.usernameShow);
        password=findViewById(R.id.passwordShow);
        citta=findViewById(R.id.cittaShow);
        data=findViewById(R.id.dataShow);
        logout=findViewById(R.id.logout);
        home=findViewById(R.id.home);
        ituoidati=findViewById(R.id.ituoidati);
        modificaPassword=findViewById(R.id.changePassword);
        modificaPassword.setPaintFlags(modificaPassword.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        modificaPassword.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                Intent intenzione = new Intent(Home.this,ModificaPassword.class);
                finish();
                startActivity(intenzione);


            }});
        logout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //condizioni
                MainActivity.subjectUsername="";
                MainActivity.subjectPassword="";
                MainActivity.loggedPassword="";
                MainActivity.loggedUsername="";
                MainActivity.context.finish();
                Intent intenzione = new Intent(Home.this,MainActivity.class);
                finish();
                startActivity(intenzione);


            }});
        imageAdmin=findViewById(R.id.adminImage);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //condizioni
                GestisciUtenti.pattern="";
                finish();
            }});
        gestisciUtenti=findViewById(R.id.gestisciUtenti);
        gestisciUtenti.setVisibility(View.GONE);
        gestisciUtenti.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                Intent intent = new Intent(Home.this, GestisciUtenti.class);
                startActivity(intent);
            }});
        String selectUsername,selectPassword;
        if(MainActivity.adminCall){

            selectUsername=MainActivity.subjectUsername;
            selectPassword=MainActivity.subjectPassword;
        }else{
            selectUsername=MainActivity.loggedUsername;
            selectPassword=MainActivity.loggedPassword;
        }
        Log.d("debuggo", "Stringhe "+selectUsername +"  dd  "+selectPassword);
        for (Persona persona:Persona.dbPersone) {
            if(persona.getUsername().equals(selectUsername) && persona.getPassword().equals(selectPassword)){
                username.setText(persona.getUsername());
                password.setText(persona.getPassword());
                citta.setText(persona.getCitta());
                data.setText(persona.getDataDiNascita().toString());
                imageAvatar.setImageResource(persona.imageId);
               if(!MainActivity.adminCall) benvenuto.setText("Benvenuto/a "+persona.getUsername()+"!");
                if(persona.isAdmin) {
                    imageAdmin.setImageResource(R.drawable.baseline_admin_panel_settings_black_48dp);
                    gestisciUtenti.setVisibility(View.VISIBLE);
                }
                if(MainActivity.adminCall){
                    home.setText("");
                    gestisciUtenti.setVisibility(View.GONE);
                    logout.setVisibility(View.GONE);
                    modificaPassword.setVisibility(View.GONE);
                    ituoidati.setText("Dati utente");
                }
            }else{
                //Debug dopo
            }
        }

    }


}