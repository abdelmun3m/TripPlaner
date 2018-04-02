package com.android.abdelmun3m.tripplaner;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{


    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button buttonLogout;
    private DatabaseReference databaseReference;
    private EditText editTextName,editTextAddress;
    private Button buttonSaveInformation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        firebaseAuth =FirebaseAuth.getInstance();


        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,LoginActivty.class));
        }

        databaseReference= FirebaseDatabase.getInstance().getReference();

        editTextName=(EditText) findViewById(R.id.editTextName);
        editTextAddress=(EditText) findViewById(R.id.editTextAddress);
        buttonSaveInformation=(Button) findViewById(R.id.buttonSaveInformation);

        FirebaseUser user=firebaseAuth.getCurrentUser();

        //Intialization Views
        textViewUserEmail= (TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout=(Button) findViewById(R.id.buttonLogout);
        textViewUserEmail.setText("Welcome"+user.getEmail());

        //Adding Listener To Button
        buttonLogout.setOnClickListener(this);
        buttonSaveInformation.setOnClickListener(this);

    }

    private void saveUserInformation(){

        String name=editTextName.getText().toString().trim();
        String address=editTextAddress.getText().toString().trim();

        saveUserInformation saveInfo=new saveUserInformation(name , address);

        FirebaseUser  user= firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(saveInfo);

        Toast.makeText(this,"Information Save Successfully",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View view) {

        if(view == buttonLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,LoginActivty.class));
        }

        if(view == buttonSaveInformation){

            saveUserInformation();
        }

    }
}
