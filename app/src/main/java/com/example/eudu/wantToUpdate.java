package com.example.eudu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class wantToUpdate extends AppCompatActivity {

    private EditText et1,et2,et3,et4,et5,et6,et7,et8,et9,et10;
    private Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to_update);

        uidesign();
        Intent come=getIntent();
        details det=(details)come.getSerializableExtra("classval");
        String idValue=come.getStringExtra("val");
        et1.setText(det.getCompany());
        et2.setText(det.getRpm());
        et3.setText(det.getPowerRating());
        et4.setText(det.getVolts());
        et5.setText(det.getAmps());
        et6.setText(det.getEncl());
        et7.setText(det.getInscl());
        et8.setText(det.getManufactureYear());
        et9.setText(det.getAmbTemp());
        et10.setText(det.getDuty());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e1=et1.getText().toString();
                String e2=et2.getText().toString();
                String e3=et3.getText().toString();
                String e4=et4.getText().toString();
                String e5=et5.getText().toString();
                String e6=et6.getText().toString();
                String e7=et7.getText().toString();
                String e8=et8.getText().toString();
                String e9=et9.getText().toString();
                String e10=et10.getText().toString();
                if(e1.isEmpty()||e2.isEmpty()||e3.isEmpty()||e4.isEmpty()||e5.isEmpty()||e6.isEmpty()||e7.isEmpty()||e8.isEmpty()||e9.isEmpty()||e10.isEmpty())
                return;
                det.setCompany(e1);
                det.setRpm(e2);
                det.setPowerRating(e3);
                det.setVolts(e4);
                det.setAmps(e5);
                det.setEncl(e6);
                det.setInscl(e7);
                det.setManufactureYear(e8);
                det.setAmbTemp(e9);
                det.setDuty(e10);
                DatabaseReference db_details = FirebaseDatabase.getInstance().getReference().child("details");
                db_details.child(idValue).setValue(det);
                AlertDialog alertDialog = new AlertDialog.Builder(wantToUpdate.this).create();
                alertDialog.setTitle("Success");
                alertDialog.setMessage("Updated successfully");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                finish();
                            }
                        });
                alertDialog.show();
            }
        });

    }
    private void uidesign(){
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        et3=(EditText)findViewById(R.id.et3);
        et4=(EditText)findViewById(R.id.et4);
        et5=(EditText)findViewById(R.id.et5);
        et6=(EditText)findViewById(R.id.et6);
        et7=(EditText)findViewById(R.id.et7);
        et8=(EditText)findViewById(R.id.et8);
        et9=(EditText)findViewById(R.id.et9);
        et10=(EditText)findViewById(R.id.et10);
        btnUpdate=(Button)findViewById(R.id.button);
    }
}