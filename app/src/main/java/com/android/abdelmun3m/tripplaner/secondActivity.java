package com.android.abdelmun3m.tripplaner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class secondActivity extends AppCompatActivity {
    DBManager dbm;

    TextView TripName;
    TextView StartPoint;
    TextView EndPoint;
    TextView Notes;

    Intent incomeIntent;
    Button addTrip;
    Button getTripData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TripName = findViewById(R.id.tripName);
        StartPoint = findViewById(R.id.startPoint);

        EndPoint = findViewById(R.id.endPoint);
        Notes = findViewById(R.id.notesTxt);
        dbm = new DBManager(this);

        addTrip = findViewById(R.id.addTripBtn);
        getTripData = findViewById(R.id.readTripBtn);

        incomeIntent = getIntent();

        /*Here I stopped*/
        TripName.setText(incomeIntent.getStringExtra(MainActivity.TripName));
        StartPoint.setText(incomeIntent.getStringExtra(MainActivity.StartPoint));
        EndPoint.setText(incomeIntent.getStringExtra(MainActivity.EndPoint));
        Notes.setText(incomeIntent.getStringExtra(MainActivity.Notes));

        getTripData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String[] results = dbm.getAllTrips();
                String[] results2 = results[0].split(",");
                TripName.setText(results2[0]);
                StartPoint.setText(results2[1]);
                EndPoint.setText(results2[2]);
                Notes.setText(results2[3]);
            }
        });



        addTrip.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dbm.insertTrip(TripName.getText().toString(),StartPoint.getText().toString(),EndPoint.getText().toString(),Notes.getText().toString());
                TripName.setText("");
              StartPoint.setText("");
                EndPoint.setText("");
                Notes.setText("");
            }
        });











    }
}
