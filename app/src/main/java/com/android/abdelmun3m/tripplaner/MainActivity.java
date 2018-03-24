package com.android.abdelmun3m.tripplaner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String TripName = "TripName";
    public static final String StartPoint = "StartPoint";
    public static final String EndPoint = "EndPoint";
    public static final String Notes = "Notes";

    EditText editTripName;
    EditText editStartPoint;

    EditText editEndPoint;
    EditText editNotes;

    Button addTrip;
    Intent intentNext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTripName = findViewById(R.id.nameTxt);
        editStartPoint = findViewById(R.id.startTxt);

        editEndPoint = findViewById(R.id.endTxt);
        editNotes = findViewById(R.id.notesTxt);

        addTrip = findViewById(R.id.addTripBtn);
        addTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentNext = new Intent( MainActivity.this, secondActivity.class);
               // intentNext.putExtra(editTripName.getText().toString(),editStartPoint.getText().toString(),editEndPoint.getText().toString(),editNotes.getText().toString());
                intentNext.putExtra(TripName,editTripName.getText().toString());
                intentNext.putExtra(StartPoint,editStartPoint.getText().toString());
                intentNext.putExtra(EndPoint,editEndPoint.getText().toString());
                intentNext.putExtra(Notes,editNotes.getText().toString());
                startActivity(intentNext);


            }
        });






  /*      TripName = findViewById(R.id.tripName);
        StartPoint = findViewById(R.id.startPoint);
        EndPoint = findViewById(R.id.endPoint);
        Notes =findViewById(R.id.notesTxt);*/

        //btnNext = findViewById(R.id.btnNext);
    }
}
