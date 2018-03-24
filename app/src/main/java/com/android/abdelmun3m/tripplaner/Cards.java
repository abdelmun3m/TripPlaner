package com.android.abdelmun3m.tripplaner;

/**
 * Created by lamiaa on 23/03/18.
 */

public class Cards {
   private int id;
    private String tripName;
    private double srtpoint;
    private double endpoint;
    private String dateTime;
    private String tripstatus;
    private String rootStatus;

    public Cards(int id ,String tripName, double srtpoint, double endpoint, String dateTime, String tripstatus, String rootStatus) {
        this.id=id;
        this.tripName = tripName;
        this.srtpoint = srtpoint;
        this.endpoint = endpoint;
        this.dateTime = dateTime;
        this.tripstatus = tripstatus;
        this.rootStatus = rootStatus;
    }

    public int getId() {
        return id;
    }

    public String getTripName() {
        return tripName;
    }

    public double getSrtpoint() {
        return srtpoint;
    }

    public double getEndpoint() {
        return endpoint;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getTripstatus() {
        return tripstatus;
    }

    public String getRootStatus() {
        return rootStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public void setSrtpoint(double srtpoint) {
        this.srtpoint = srtpoint;
    }

    public void setEndpoint(double endpoint) {
        this.endpoint = endpoint;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setTripstatus(String tripstatus) {
        this.tripstatus = tripstatus;
    }

    public void setRootStatus(String rootStatus) {
        this.rootStatus = rootStatus;
    }
}
