package com.example.bonus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class SelectAvatar extends   AppCompatActivity{


    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.iconfinder_4_avatar_1,R.drawable.iconfinder_10_avatar_2,R.drawable.iconfinder_9_avatar_3,R.drawable.iconfinder_8_avatar_5
            ,R.drawable.iconfinder_indian_woman_hindi_avatar_6,R.drawable.iconfinder_afro_man_male_avatar_7,R.drawable.iconfinder_muslim_man_avatar_8,
            R.drawable.iconfinder_boy_male_avatar_portrait_9,R.drawable.iconfinder_girl_avatar_child_kid_10
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_avatar);

        GridView gridView = (GridView) findViewById(R.id.grid_view);

        // Instance of ImageAdapter Class
        gridView.setAdapter(new ImageAdapter(this,mThumbIds));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                for (Persona persona:Persona.dbPersone) {
                    if(persona.getUsername().equals(MainActivity.subjectUsername) && persona.getPassword().equals(MainActivity.subjectPassword)){
                        persona.imageId=mThumbIds[position];
                        Log.d("debuggo", "Cambio avatr");
                        Intent intenzione = new Intent(SelectAvatar.this, GestisciUtenti.class);
                        startActivity(intenzione);
                        finish();
                    }
                }

            }
        });
    }
}
