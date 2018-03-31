package com.android.abdelmun3m.tripplaner;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class AddTripActivity extends Activity
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
    TimePicker timePicker;


    private static final String TAG = AddTripActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        tripName = findViewById(R.id.NameEditText);
        tripNotes = findViewById(R.id.CommentEditText);

        datePicker = (DatePicker) findViewById(R.id.tripDatePicker);
        timePicker = findViewById(R.id.tripTimePicker);
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
                Toast.makeText(AddTripActivity.this, "" + placeName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Toast.makeText(AddTripActivity.this, status.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        endPlaceFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                String placeName = place.getName().toString();
                endPlace = place;
                Toast.makeText(AddTripActivity.this, "" + placeName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Toast.makeText(AddTripActivity.this, status.toString(), Toast.LENGTH_SHORT).show();
            }
        });


        Log.i(TAG,""+datePicker.getMaxDate());

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    tripsObj = new trips(
                            tripName.getText().toString()
                            ,startPlace.getName().toString(),
                            endPlace.getName().toString(),
                            datePicker.getDayOfMonth()+
                                    "/"+datePicker.getMonth()+"/"
                                    +datePicker.getYear(),
                           // timePicker.getHour()+":"+timePicker.getMinute(),
                            null,
                            tripNotes.getText().toString(),
                            false);


                String id = DBSingleTon.addTrip(tripsObj);
                if(id != null){
                    Toast.makeText(AddTripActivity.this, "Trip Added", Toast.LENGTH_SHORT).show();
                    //TODO GO TO Main Activity
                }
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

