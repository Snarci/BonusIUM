package com.example.bonus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.Debug;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GestisciUtenti extends AppCompatActivity{
    RelativeLayout layout ;
    Button back;
    public static int counter=100;
    public static String pattern="";
    ListView list;
    SearchView filter ;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pattern="";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestisci_utenti);
        back=findViewById(R.id.backGestisciUtenti);
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //condizioni
                finish();
            }});

        filter=findViewById(R.id.filter);
        filter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                                          @Override
                                          public boolean onQueryTextSubmit(String query) {
                                              pattern=query;


                                              createList();
                                              return false;
                                          }

                                          @Override
                                          public boolean onQueryTextChange(String newText) {
                                              pattern=newText;

                                              createList();
                                              return false;
                                          }
                                      });
        filter.setQueryHint("Filtra utenti per username");
        createList();


    }
    public void createList(){
        Persona.dbPersoneFilter.clear();
        Persona.dbUsernameFilter.clear();
        for(Persona persona: Persona.dbPersone){
            if(persona.getUsername().contains(GestisciUtenti.pattern) || GestisciUtenti.pattern.equals("")) {
                Persona.dbPersoneFilter.add(persona);
                Persona.dbUsernameFilter.add(persona.getUsername());
            }
        }
        Log.d("debuggo","Qualcosamacigno size="+Persona.dbPersoneFilter.size());
        layout = (RelativeLayout) findViewById(R.id.layoutGestisciUtenti);

        ListAdapter adapter=new ListAdapter(this);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
    }

}