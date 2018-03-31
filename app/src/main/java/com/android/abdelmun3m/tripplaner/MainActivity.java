package com.android.abdelmun3m.tripplaner;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.util.Date;

public class MainActivity extends Activity
{
//= new trips();
    private trips tripsObj;
    EditText tripName;
    PlaceAutocompleteFragment startPlaceFragment;
    PlaceAutocompleteFragment endPlaceFragment;
    Date tripDate;
    Button addButton;
    EditText tripNotes;
    Boolean roundTrip;
    Place startPlace,endPlace;
    DatePicker datePicker;


    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tripName = findViewById(R.id.NameEditText);
        tripNotes = findViewById(R.id.CommentEditText);

        datePicker = (DatePicker) findViewById(R.id.tripDatePicker);
        addButton = findViewById(R.id.AddButton);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();


        //Creating AutoComplete Location
         startPlaceFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment_start);

         endPlaceFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment_end);

        startPlaceFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                String placeName = place.getName().toString();
                startPlace = place;
                Toast.makeText(MainActivity.this, "" + placeName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Toast.makeText(MainActivity.this, status.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        endPlaceFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                String placeName = place.getName().toString();
                endPlace = place;
                Toast.makeText(MainActivity.this, "" + placeName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Toast.makeText(MainActivity.this, status.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tripsObj = new trips(
                        tripName.getText().toString()
                        ,startPlace.getName().toString(),
                        endPlace.getName().toString(),
                        datePicker.getDayOfMonth()+"",
                        tripNotes.getText().toString(),
                        false);


                DBSingleTon.addTrip(tripsObj);
            }


        });



    }
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.OneDirection:
                if (checked)
                    roundTrip = false;
                break;
            case R.id.TowDirection:
                if (checked)
                    roundTrip = true;
                break;
        }
    }


    }

