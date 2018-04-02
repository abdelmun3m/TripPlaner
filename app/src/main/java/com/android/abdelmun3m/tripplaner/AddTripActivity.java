package com.android.abdelmun3m.tripplaner;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddTripActivity extends Activity implements View.OnClickListener
{
//= new trips();
    private trips tripsObj;
    EditText tripName;
    PlaceAutocompleteFragment startPlaceFragment;
    PlaceAutocompleteFragment endPlaceFragment;
    Button addButton;
    EditText tripNotes;
    Boolean roundTrip;
    Place startPlace,endPlace;
    Button btnDate ;
    Button btnTime ;
    TimePicker time;
    DatePicker date;



    private static final String TAG = AddTripActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(DBSingleTon.GET_LOGGED_USER(this) == null){
            finish();
        }
        setContentView(R.layout.activity_add_trip);

        tripName = findViewById(R.id.NameEditText);
        tripNotes = findViewById(R.id.CommentEditText);
        addButton = findViewById(R.id.AddButton);
        btnDate = findViewById(R.id.btn_date);
        btnTime = findViewById(R.id.btn_time);
        btnDate.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        addButton.setOnClickListener(this);

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


    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.btn_date: showDateDialog() ;break;
            case R.id.btn_time:showTimeDialog();break;
            case R.id.AddButton:addTrip();break;
            default:;
        }
    }

    private void showDateDialog() {
        Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                AddTripActivity.this.date = datePicker;
            }
        },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dialog.show();



    }

    private void showTimeDialog(){

        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                AddTripActivity.this.time = timePicker;

            }
        },
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),
                true
                );

        dialog.show();
    }

    private void addTrip(){

        tripsObj = new trips(
                tripName.getText().toString(),
                startPlace,
                endPlace,
                date.getDayOfMonth()+
                        "/"+date.getMonth()+"/"
                        +date.getYear(),
                // timePicker.getHour()+":"+timePicker.getMinute(),
                null,
                tripNotes.getText().toString(),
                false);

        String id = DBSingleTon.addTrip(tripsObj);
        if(id != null){
            Toast.makeText(AddTripActivity.this, "Trip Added", Toast.LENGTH_SHORT).show();
            //TODO GO TO Main Activity
            Intent i = new Intent(AddTripActivity.this,MainActivity.class);
            AddTripActivity.this.startActivity(i);
        }
    }
}

