package com.example.eudu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class wantToInsert extends AppCompatActivity {

    private EditText et1,et2,et3,et4,et5,et6,et7,et8,et9,et10;
    private Button btnSubmit;
    private details det;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to_insert);

        uidesign();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
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
                {
                    Toast.makeText(wantToInsert.this, "found blank input", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    det=new details(e1,e2,e3,e4,e5,e6,e7,e8,e9,e10);

                    DatabaseReference db_details = FirebaseDatabase.getInstance().getReference().child("details");
                    String key=db_details.push().getKey();

                    String datetime=getDateTime();
                    db_details.child(key).child(datetime).setValue(det);
                    Intent intent=new Intent(wantToInsert.this,qrGenerator.class);
                    intent.putExtra("val",key);
                    startActivity(intent);
                    finish();
                }
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
        btnSubmit=(Button)findViewById(R.id.button);
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}