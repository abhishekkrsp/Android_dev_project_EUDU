package com.example.eudu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RUDe extends AppCompatActivity {

    private Button btnRead,btnUpdate,btnDelete,btnAnalyse,btnAdd;
    private String idValue;
    private details det=new details();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r_u_de);
        uidesign();

        Intent intent =getIntent();
        idValue =intent.getStringExtra("val");

        getrqst();

        Onclickbuttons();
    }

    private void uidesign(){
        btnRead=(Button)findViewById(R.id.btnRead);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        btnAnalyse=(Button)findViewById(R.id.analyse);
        btnAdd=(Button)findViewById(R.id.btnadd);
    }
    private void Onclickbuttons(){

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RUDe.this,wantToRead.class);
                intent.putExtra("val",idValue);
                startActivity(intent);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RUDe.this,add.class);
                intent.putExtra("val",idValue);
                startActivity(intent);
            }
        });

        btnAnalyse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RUDe.this,Analysis.class);
                intent.putExtra("val",det);
                startActivity(intent);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog alertDialog = new AlertDialog.Builder(RUDe.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Do You want to delete?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {

                                DatabaseReference db_employee= FirebaseDatabase.getInstance().getReference().child("details").child(idValue);
                                db_employee.removeValue();
                                dialog.dismiss();
                                finish();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

            }
        });
    }
    public void getrqst(){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("details").child(idValue);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                det = dataSnapshot.getValue(details.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RUDe.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}