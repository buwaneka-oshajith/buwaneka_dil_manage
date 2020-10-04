package com.example.diliverymanage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diliverymanage.Model.Drivers;
import com.example.diliverymanage.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class driverlogin extends AppCompatActivity {

    private EditText inputNumber,inputPassword;
    private Button Driver_loginbtn;
    private ProgressDialog LoadingBar;

    private String parentDbname = "Drivers";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driverlogin);

        Driver_loginbtn = (Button) findViewById(R.id.driver_login_btn);
        inputNumber = (EditText) findViewById(R.id.driverlogin_phone_no);
        inputPassword = (EditText) findViewById(R.id.driverlogin_password);
        LoadingBar = new ProgressDialog(this);

        Driver_loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginDriver();

            }

            private void LoginDriver() {

                String phone = inputNumber.getText().toString();
                String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(phone))
                {
                    Toast.makeText(driverlogin.this, "Plese Enter your Phone Number", Toast.LENGTH_SHORT).show();;
                }
                else if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(driverlogin.this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
                }
                else{
                    LoadingBar.setTitle("Logging Account");
                    LoadingBar.setMessage("Please wait while we are checking the credentials");
                    LoadingBar.setCanceledOnTouchOutside(false);
                    LoadingBar.show();

                    Allowaccesstodriver(phone, password);
                }
            }

            private void Allowaccesstodriver(final String phone, final String password) {

                final DatabaseReference RootRef;
                RootRef = FirebaseDatabase.getInstance().getReference();

                RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(parentDbname).child(phone).exists())
                        {
                            Drivers driverdata = snapshot.child(parentDbname).child(phone).getValue(Drivers.class);

                            if (driverdata.getPhone().equals(phone))
                            {
                                if (driverdata.getPassword().equals(password))
                                {
                                    Toast.makeText(driverlogin.this, "Login Successfully...", Toast.LENGTH_SHORT).show();
                                    LoadingBar.dismiss();

                                    Intent intent = new Intent(driverlogin.this, Driver_home.class);
                                    Prevalent.currentdriver = driverdata;
                                    startActivity(intent);

                                }
                                else
                                {
                                    LoadingBar.dismiss();
                                    Toast.makeText(driverlogin.this, "Password is Incorrect", Toast.LENGTH_SHORT).show();
                                }

                            }


                        }
                        else
                        {
                            Toast.makeText(driverlogin.this, "Account with this "+phone+ "number do not Exists", Toast.LENGTH_SHORT).show();
                            LoadingBar.dismiss();
                            Toast.makeText(driverlogin.this, "You need to create a new account", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}