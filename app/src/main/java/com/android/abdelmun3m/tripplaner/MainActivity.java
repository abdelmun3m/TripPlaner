package com.android.abdelmun3m.tripplaner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating AutoComplete Location
        PlaceAutocompleteFragment autocompleteFragmentStart = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment_start);

        PlaceAutocompleteFragment autocompleteFragmentEnd = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment_end);

        autocompleteFragmentStart.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                String placeName=place.getName().toString();
                Toast.makeText(MainActivity.this, ""+placeName, Toast.LENGTH_SHORT).show();
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
                String placeName=place.getName().toString();
                Toast.makeText(MainActivity.this, ""+placeName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
            }
        });


    }
}
