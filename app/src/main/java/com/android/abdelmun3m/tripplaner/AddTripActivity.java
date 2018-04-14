package com.android.abdelmun3m.tripplaner;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.util.ArrayList;
import java.util.Calendar;

public class AddTripActivity extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener
{
//= new trips();
    private trips tripsObj;
    EditText tripName;
    PlaceAutocompleteFragment startPlaceFragment;
    PlaceAutocompleteFragment endPlaceFragment;
    Button addButton;
    EditText tripNotes;
    Boolean roundTrip;
    CustomPlace startPlace,endPlace;
    ImageView btnDate ;
    TextView tvDate;
    TextView tvTime;
    ImageView btnTime ;
    TimePicker time;
    DatePicker date;
    Spinner spinnerDirection;
    EditText etAddNewNote;
    ImageView btnAddNote;
    RecyclerView lvNote;
    NoteAdapter notesAdapter;
    boolean oneDirection;
    boolean edit;
    trips editTrip;

    private static final String TAG = AddTripActivity.class.getSimpleName();
    public static final String EDIT_TRIP_KEY ="edit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(DBSingleTon.GET_LOGGED_USER(this) == null){
            finish();
        }
        setContentView(R.layout.activity_add_trip);


        Intent intent  = getIntent();
         edit= false;


        tripName = findViewById(R.id.NameEditText);
        addButton = findViewById(R.id.AddButton);
        btnDate =  findViewById(R.id.ibtn_date);
        tvDate = findViewById(R.id.tv_date);
        tvTime = findViewById(R.id.tv_time);
        btnTime = findViewById(R.id.ibtn_time);
        spinnerDirection = findViewById(R.id.spinner_direction);
        etAddNewNote = findViewById(R.id.et_new_note);
        btnAddNote = findViewById(R.id.btn_add_note);
        lvNote = findViewById(R.id.rv_notes);
        btnAddNote.setOnClickListener(this);
        btnDate.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        addButton.setOnClickListener(this);
        spinnerDirection.setOnItemSelectedListener(this);
        startPlaceFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment_start);

        endPlaceFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment_end);

        notesAdapter = new NoteAdapter(this);
        RecyclerView.LayoutManager manger = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        lvNote.setLayoutManager(manger);
        lvNote.setHasFixedSize(true);
        lvNote.setAdapter(notesAdapter);
        //Creating AutoComplete Location


        if(intent.hasExtra(EDIT_TRIP_KEY)){
            editTrip= intent.getParcelableExtra(EDIT_TRIP_KEY);
            edit = true;
            tripName.setText(editTrip.getTripName());
            addButton.setText("Save");
            tvDate.setText(editTrip.getTripDate());
            tvTime.setText(editTrip.getTripTime());
            spinnerDirection.setSelection(editTrip.getRoundTrip()? 1:0);
            notesAdapter.setNotes(editTrip.tripNotes);;
            startPlace = editTrip.getStartPlace();
            startPlaceFragment.setText(startPlace.getName());
            endPlace = editTrip.getEndPlace();
            endPlaceFragment.setText(endPlace.getName());
        }




        startPlaceFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                String placeName = place.getName().toString();
                startPlace = new CustomPlace(place.getId(),place.getLatLng().latitude,place.getLatLng().longitude,place.getName().toString());
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
                endPlace =  new CustomPlace(place.getId(),place.getLatLng().latitude,place.getLatLng().longitude,place.getName().toString());
                Toast.makeText(AddTripActivity.this, "" + placeName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Toast.makeText(AddTripActivity.this, status.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.ibtn_date: showDateDialog() ;break;
            case R.id.ibtn_time:showTimeDialog();break;
            case R.id.AddButton:addTrip();break;
            case R.id.btn_add_note:addNewNote();break;
            default:break;
        }
    }


    private void addNewNote() {

        String note = etAddNewNote.getText().toString().trim();
        if(!note.equals("")){
            notesAdapter.addNewNote(note);
            etAddNewNote.setText("");
        }
    }

    private void showDateDialog() {
        Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                AddTripActivity.this.date = datePicker;
                String date = datePicker.getDayOfMonth()+"/"+datePicker.getMonth()+"/"+datePicker.getYear();
                tvDate.setText(date);


            }
        },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dialog.show();



    }

    private void showTimeDialog(){

        TimePickerDialog dialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                AddTripActivity.this.time = timePicker;
                tvTime.setText(i +":"+i1);
                //Toast.makeText(AddTripActivity.this, i +":"+i1 , Toast.LENGTH_SHORT).show();
            }
        },
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),
                true
                );

        dialog.show();
    }

    private void addTrip(){

        if(tripName.getText().toString().equals("")){
            Toast.makeText(this, "Please Add Trip Name", Toast.LENGTH_LONG).show();
        }else if(startPlace == null){
            Toast.makeText(this, "Please Add Start Place", Toast.LENGTH_LONG).show();
        }else if(endPlace == null){
            Toast.makeText(this, "Please Add End Place", Toast.LENGTH_LONG).show();
        }else if(tvDate.getText().toString().trim().equals("")){
            Toast.makeText(this, "Please Add Trip Date", Toast.LENGTH_LONG).show();
        }else if(tvTime.getText().toString().trim().equals("")){
            Toast.makeText(this, "Please Add Trip Time", Toast.LENGTH_LONG).show();
        }else{

            tripsObj = new trips(
                    tripName.getText().toString(),
                    startPlace,
                    endPlace,
                    tvDate.getText().toString(),
                    // timePicker.getHour()+":"+timePicker.getMinute(),
                    tvTime.getText().toString(),
                    notesAdapter.getNotes(),
                    false);

            if(!edit){

                String id = DBSingleTon.ADD_TRIP(tripsObj);
                if(id != null){
                    Toast.makeText(AddTripActivity.this, "Trip Added", Toast.LENGTH_SHORT).show();
                    //TODO GO TO Main Activity
                    startActivity(new Intent(AddTripActivity.this,MainActivity.class));
                }
            }else{
                DBSingleTon.GET_TRIP_REFERENCE().child(editTrip.id).setValue(tripsObj);
                Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddTripActivity.this,MainActivity.class));
            }

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        oneDirection = (i==0)?true:false;
        Toast.makeText(this, ""+i, Toast.LENGTH_SHORT).show();
        //TODO Add Direction to trip object
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        oneDirection = true;
    }
}

