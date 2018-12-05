package com.sy.patrick;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference lab9;
    EditText Fullname, Age, Gender;
    ArrayList<String> keyList;
    int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseDatabase.getInstance();
        lab9 = db.getReference("lab9");
        Fullname = findViewById(R.id.Fullname);
        Age = findViewById(R.id.Age);
        Gender = findViewById(R.id.Gender);
        keyList = new ArrayList<>();
    }

  /* @Override
   protected void onStart(){
        super.onStart();
        lab9.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ss : dataSnapshot.getChildren()){
                    keyList.add(ss.getKey());
                }
                Toast.makeText(MainActivity.this, keyList.get(0),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    } */

    public void saveRecord (View v){
        try {
            String fname = Fullname.getText().toString().trim();
            int age = Integer.parseInt(Age.getText().toString().trim());
            String gender = Gender.getText().toString().trim();
            String key = lab9.push().getKey();
            Inputs in = new Inputs(fname, age, gender);
            lab9.child(key).setValue(in);
            Toast.makeText(this, "Record Inserted...", Toast.LENGTH_LONG).show();

            lab9.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ss : dataSnapshot.getChildren()) {
                        keyList.add(ss.getKey());
                    }
                    Toast.makeText(MainActivity.this, keyList.get(0), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }catch(NumberFormatException e){
            Toast.makeText(this, "Error, try again", Toast.LENGTH_LONG).show();
        }
    }

    /*public void searchRecord(View v)
    {
        try {

            String fname = Fullname.getText().toString().trim();
            lab9.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }catch(NumberFormatException e){

        }
    }*/

}
