package com.example.vinih.tp3;

/**
 * Created by Vinih on 04/05/2015.
 */
public class LatLong {
    double Lat;
    double Long;

    public LatLong(double lat, double aLong) {
        Lat = lat;
        Long = aLong;
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
        return ("\nLat: "+Lat+"Lon: "+Long+"\n");
    }


}
