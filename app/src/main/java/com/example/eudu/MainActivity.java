package com.example.eudu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText user,password;
    private Button login;
    private String id=null,pass=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uidesign();
        onclick();

    }

    private void uidesign(){
        user=(EditText)findViewById(R.id.etUserName);
        password=(EditText)findViewById(R.id.etPassword);
        login=(Button)findViewById(R.id.logIn);
    }
    private void onclick(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id=user.getText().toString();
                pass=password.getText().toString();

                if(id.isEmpty()||pass.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please enter correct info", Toast.LENGTH_SHORT).show();
                }
                DatabaseReference userDatabase = FirebaseDatabase.getInstance().getReference().child("userid").child(id);
                userDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                        {
                            if(snapshot.getValue().toString().equals(pass))
                            {
                                Intent intent=new Intent(MainActivity.this,SI.class);

                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();

                            }
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Invalid userId & Password", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this, "Enter Correct info", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}