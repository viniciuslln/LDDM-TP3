package com.example.vinih.tp3;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vinih on 04/05/2015.
 */
public class LatLong {
    double Lat;
    double Long;
    long time;
    private long id;
    String mdate;

    public LatLong(double lat, double aLong, long time) {
        Lat = lat;
        Long = aLong;
        this.time = time;
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date(time);
        mdate = format.format(date);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public LatLong() {
    }

    public double getLat() {
        return Lat;
    }

    public double getLong() {
        return Long;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public void setLong(double aLong) {
        Long = aLong;
    }

    @Override
    public String toString() {

        return ("\nLat: "+Lat+"Lon: "+Long+" - "+mdate);
    }


    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }
}
