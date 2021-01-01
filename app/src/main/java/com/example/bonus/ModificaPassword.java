package com.example.bonus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModificaPassword extends AppCompatActivity {
Button back,confirm;
EditText oldPassword,newPassword,newPasswordConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_password);

        oldPassword=findViewById(R.id.oldPasswordEditText);
        newPassword=findViewById(R.id.newPasswordEditText);
        newPasswordConfirm=findViewById(R.id.newConfirmPasswordEditText);

        back=findViewById(R.id.backCambioPassword);
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //condizioni
                GestisciUtenti.pattern="";
                finish();
            }});
        confirm=findViewById(R.id.confermaCambioPassword);
        confirm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //condizioni
                GestisciUtenti.pattern="";
                if(!oldPassword.getText().toString().equals(MainActivity.loggedPassword)){
                    oldPassword.setError("Password errata");
                }
                if(!newPassword.getText().toString().equals(newPasswordConfirm.getText().toString())){
                    newPassword.setError("Password non combaciano");
                    newPasswordConfirm.setError("Password non combaciano");
                }

                //caso ok
                if(oldPassword.getText().toString().equals(MainActivity.loggedPassword) && newPassword.getText().toString().equals(newPasswordConfirm.getText().toString())){
                    if(!oldPassword.getText().toString().equals(newPassword.getText().toString())){
                        for(Persona persona: Persona.dbPersone){
                            if(persona.getUsername().equals(MainActivity.loggedUsername)){
                                persona.password=newPassword.getText().toString();
                                finish();
                            }
                        }

                    }else{
                        newPasswordConfirm.setError("Nuove password uguali alla precedente");
                        newPassword.setError("Nuove password uguali alla precedente");
                    }


                }
                //Intent intenzione = new Intent(ModificaPassword.this, MainActivity.class);


                //startActivity(intenzione);
            }});
    }
}