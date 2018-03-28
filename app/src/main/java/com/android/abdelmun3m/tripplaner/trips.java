package com.android.abdelmun3m.tripplaner;

import android.text.format.Time;

import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;

import java.util.Date;

/**
 * Created by Asmaa on 3/28/2018.
 */

public class trips {

    String tripName;
    PlaceAutocompleteFragment startPlace;
    PlaceAutocompleteFragment endPlace;
    Date tripDate;
   // Time tripTime;
    String tripNotes;
    Boolean roundTrip;



    public trips(String tripName, PlaceAutocompleteFragment startPlace, PlaceAutocompleteFragment endPlace, Date tripDate, String tripNotes, Boolean roundTrip) {
        this.tripName = tripName;
        this.startPlace = startPlace;
        this.endPlace = endPlace;
        this.tripDate = tripDate;
        this.tripNotes = tripNotes;
        this.roundTrip = roundTrip;

    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public PlaceAutocompleteFragment getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(PlaceAutocompleteFragment startPlace) {
        this.startPlace = startPlace;
    }

    public PlaceAutocompleteFragment getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(PlaceAutocompleteFragment endPlace) {
        this.endPlace = endPlace;
    }

    public Date getTripDate() {
        return tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }

    public String getTripNotes() {
        return tripNotes;
    }

    public void setTripNotes(String tripNotes) {
        this.tripNotes = tripNotes;
    }

    public Boolean getRoundTrip() {
        return roundTrip;
    }

    public void setRoundTrip(Boolean roundTrip) {
        this.roundTrip = roundTrip;
    }

    @Override
    public String toString() {
        return tripName;
    }
}
