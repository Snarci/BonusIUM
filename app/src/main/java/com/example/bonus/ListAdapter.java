package com.example.bonus;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter<String>  implements AdapterView.OnItemSelectedListener{

    ArrayList<String> usernames=new ArrayList<String>();
    ArrayList<Integer> avatars=new ArrayList<Integer>();

    private final Activity context;
    public static int counter=0;
    private int scostamento=0;


    public ListAdapter(Activity context){

        super(context,R.layout.container_samples,Persona.dbUsernameFilter);
        // TODO Auto-generated constructor stub
        counter=0;
        this.context=context;

        Log.d("debuggo","Qualcosamiao");

    }

    public View getView(int position,View view,ViewGroup parent) {

            Log.d("debuggo","Qualcosaasso: "+ position);
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.container_samples, null, true);

            TextView username = (TextView) rowView.findViewById(R.id.listUsername);
            ImageView avatar = (ImageView) rowView.findViewById(R.id.listAvatar);
            CustomSpinner spinner = (CustomSpinner) rowView.findViewById(R.id.listSpinner);

            username.setText(Persona.dbPersoneFilter.get(position).getUsername());
            avatar.setImageResource(Persona.dbPersoneFilter.get(position).getImageId());
            //Creazione custom spinner

            avatars.clear();
            usernames.clear();
            if (Persona.dbPersoneFilter.get(position).isAdmin) {
                usernames.add("Info Utente");
                avatars.add(R.drawable.baseline_supervised_user_circle_black_48dp);
            } else {
                usernames.add("Info Utente");
                avatars.add(R.drawable.baseline_supervised_user_circle_black_48dp);
                usernames.add("Avatar Utente");
                avatars.add(R.drawable.baseline_face_black_48dp);
                usernames.add("Rendi Admin");
                avatars.add(R.drawable.baseline_admin_panel_settings_black_48dp);

            }
            SpinnerAdapter customAdapter = new SpinnerAdapter(this.context, avatars.toArray(), usernames.toArray());
            spinner.setAdapter(customAdapter);
            spinner.setOnItemSelectedListener(this);
            //spinner.setEnabled(false);
            //semplifico lavoro utente inesperto
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //spinner.setEnabled(true);
                    spinner.performClick();

                }
            });


            return rowView;




    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

        //Toast.makeText(this.context, usernames.get(position), Toast.LENGTH_LONG).show();
        //Log.d("click", "Position: "+position);
        //Log.d("click", "Id: "+id);
        if(((CustomSpinner)arg0).counter>0 ) {



        View parent= (View) arg0.getParent();
        TextView usernameView= parent.findViewById(R.id.listUsername);

        //Log.d("click", "Parent: "+nome.getText().toString());
        Persona actualPerson=new Persona();
        for(Persona persona : Persona.dbPersoneFilter){
            if(persona.getUsername().equals(usernameView.getText().toString())){
                actualPerson=persona;
            }
        }
        //primaif qui
            if (!actualPerson.isAdmin) {
                switch (position) {
                    case 0:
                        //show info
                        for (Persona persona : Persona.dbPersoneFilter) {
                            if (persona.getUsername().equals(usernameView.getText().toString())) {

                                Intent intenzione = new Intent(context, Home.class);
                                MainActivity.subjectUsername = usernameView.getText().toString();
                                MainActivity.subjectPassword = persona.getPassword();
                                MainActivity.adminCall=true;
                                context.startActivity(intenzione);



                            }
                        }
                        break;
                    case 1:
                        //cambia avatar
                        for (Persona persona : Persona.dbPersoneFilter) {
                            if (persona.getUsername().equals(usernameView.getText().toString())) {
                                context.finish();
                                MainActivity.subjectUsername = usernameView.getText().toString();
                                MainActivity.subjectPassword = actualPerson.getPassword();
                                Intent intenzione = new Intent(context, SelectAvatar.class);
                                context.startActivity(intenzione);

                            }
                        }
                        break;
                    case 2:
                        //metti admin
                        for (Persona persona : Persona.dbPersoneFilter) {
                            if (persona.getUsername().equals(usernameView.getText().toString())) {
                                MainActivity.subjectUsername = usernameView.getText().toString();
                                MainActivity.subjectPassword = actualPerson.getPassword();
                                persona.isAdmin=true;
                                this.context.finish();
                                Intent intenzione = new Intent(context, GestisciUtenti.class);
                                context.startActivity(intenzione);

                            }
                        }
                        break;
                }
            }else{
                for (Persona persona : Persona.dbPersoneFilter) {
                    if (persona.getUsername().equals(usernameView.getText().toString())) {

                        Intent intenzione = new Intent(context, Home.class);
                        MainActivity.subjectUsername = usernameView.getText().toString();
                        MainActivity.subjectPassword = persona.getPassword();
                        MainActivity.adminCall=true;
                        context.startActivity(intenzione);


                    }
                }
            }
            //arg0.setEnabled(false);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
        Log.d("click", "Position: first");
    }


}
