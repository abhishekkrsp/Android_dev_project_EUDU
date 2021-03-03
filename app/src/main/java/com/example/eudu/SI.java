package com.example.eudu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blikoon.qrcodescanner.QrCodeActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SI extends AppCompatActivity {
    private Button btnScan,btnInsert;
    private static final int REQUEST_CODE_QR_SCAN = 101;
    private final String LOGTAG ="ScanYourQR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_i);

        uidesign();;

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SI.this,QrCodeActivity.class);
                startActivityForResult( i,REQUEST_CODE_QR_SCAN);
            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(SI.this,wantToInsert.class);
                startActivity(s);
            }
        });
    }
    @Override
    protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode != Activity.RESULT_OK)
        {
            Log.d(LOGTAG,"GOT POOR RESULT");
            if(data==null)
                return;

            String result = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if( result!=null)
            {
                AlertDialog alertDialog = new AlertDialog.Builder(SI.this).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("QR Code could not be scanned");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            return;

        }
        if(requestCode == REQUEST_CODE_QR_SCAN)
        {

            Log.d(LOGTAG,"GOT RESULT");
            if(data==null)
                return;

            String result = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");


            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("details").child(result);
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists())
                    {
                        Intent intent = new Intent(SI.this, RUDe.class);
                        intent.putExtra("val",result);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(SI.this, "data not found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(SI.this, "error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void uidesign(){
        btnScan = (Button)findViewById(R.id.btnScan);
        btnInsert=(Button)findViewById(R.id.btnInsert);
    }
}







//    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("details").child(result);
//            mDatabase.addValueEventListener(new ValueEventListener() {
//@Override
//public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//        if(dataSnapshot.exists())
//        {
//        Intent intent = new Intent(SI.this, RUDe.class);
//        intent.putExtra("val",result);
//        startActivity(intent);
//        }
//        else
//        {
//        Toast.makeText(SI.this, "data not found", Toast.LENGTH_SHORT).show();
//        }
//        }
//
//@Override
//public void onCancelled(@NonNull DatabaseError error) {
//        Toast.makeText(SI.this, "error", Toast.LENGTH_SHORT).show();
//        }
//        });





