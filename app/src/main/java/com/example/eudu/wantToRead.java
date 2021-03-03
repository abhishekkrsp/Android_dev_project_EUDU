package com.example.eudu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.eudu.adapter.RecyclerViewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class wantToRead extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private  ArrayList<details> detlist;
    private ArrayList<String> datelist;

    String idValue;
    details det;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to_read);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent =getIntent();
        idValue =intent.getStringExtra("val");

        detlist=new ArrayList<>();
        datelist=new ArrayList<>();

        Toast.makeText(wantToRead.this, idValue, Toast.LENGTH_SHORT).show();
        getrqst();
    }


    public void getrqst(){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("details").child(idValue);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                detlist.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    datelist.add(snapshot.getKey().toString());
                    det = snapshot.getValue(details.class);
                    detlist.add(det);

            }
                    recyclerViewAdapter = new RecyclerViewAdapter(wantToRead.this,detlist,datelist);
                    recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(wantToRead.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}




//        company.setText("Company's Name :- "+det.getCompany());
//        rpm.setText("RPM :- "+det.getRpm());
//        powerRating.setText("Power Rating :- "+det.getPowerRating());
//        volts.setText("Volts :- "+det.getVolts());
//        amps.setText("Amps :- "+det.getAmps());
//        encl.setText("ENCL :- "+det.getEncl());
//        inscl.setText("INS.CL :- "+det.getInscl());
//        manufactureYear.setText("Manufacturing Year :- "+det.getManufactureYear());
//        ambTemp.setText("AmbTemp :- "+det.getAmbTemp());
//        duty.setText("Duty :- "+det.getDuty());

//        company=(TextView)findViewById(R.id.textView11);
//        rpm=(TextView)findViewById(R.id.textView14);
//        powerRating=(TextView)findViewById(R.id.textView15);
//        volts=(TextView)findViewById(R.id.textView16);
//        amps=(TextView)findViewById(R.id.textView17);
//        encl=(TextView)findViewById(R.id.textView18);
//        inscl=(TextView)findViewById(R.id.textView19);
//        manufactureYear=(TextView)findViewById(R.id.textView20);
//        ambTemp=(TextView)findViewById(R.id.textView21);
//        duty=(TextView)findViewById(R.id.textView22);