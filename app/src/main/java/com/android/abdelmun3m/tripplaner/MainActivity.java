package com.android.abdelmun3m.tripplaner;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    PlaceAutocompleteFragment startPlace;
    PlaceAutocompleteFragment endPlace;
    Date tripDate;
    // Time tripTime;
    EditText tripNotes;
    Boolean roundTrip;

    DatePicker datePicker;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tripsObj = new trips(tripName.toString(),startPlace,endPlace,tripDate,tripNotes.toString(),roundTrip);


        //Creating AutoComplete Location
        PlaceAutocompleteFragment autocompleteFragmentStart = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment_start);

        PlaceAutocompleteFragment autocompleteFragmentEnd = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment_end);

        autocompleteFragmentStart.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                String placeName = place.getName().toString();
                Toast.makeText(MainActivity.this, "" + placeName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
            }
        });

        autocompleteFragmentEnd.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                String placeName = place.getName().toString();
                Toast.makeText(MainActivity.this, "" + placeName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
            }
        });

        tripName = findViewById(R.id.NameEditText);
        startPlace = autocompleteFragmentStart;
        endPlace = autocompleteFragmentEnd;
        tripNotes = findViewById(R.id.CommentEditText);

        datePicker = (DatePicker) findViewById(R.id.tripDatePicker);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();


//        public void onCheckboxClicked(View view) {
//            // Is the view now checked?
//            boolean checked = ((CheckBox) view).isChecked();
//
//            // Check which checkbox was clicked
//            switch(view.getId()) {
//                case R.id.OneDirection:
//                    if (checked)
//                        roundTrip = false;
//                    break;
//                case R.id.TowDirection:
//                    if (checked)
//                        roundTrip = true;
//                    break;
//            }
//        }





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

