package com.example.vinih.tp3;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
//import android.widget.MyAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    SensorAccelerometer SM;
    LatLongDB db;
    ListView lv;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listView);

        db = new LatLongDB(this, (ListView) findViewById(R.id.listView));
        db.open();
        adapter = new MyAdapter(db.busca(), this, db);
        lv.setAdapter(adapter);

        SM = new SensorAccelerometer(this, db, adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        SM.resume();
        db.open();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





    @Override
    protected void onPause() {
        super.onPause();
        SM.unregisterSensor();
        db.close();

    }

    public void getLoc(View view) {
        GPSLoc mGPS = new GPSLoc(this);

        if(mGPS.canGetLocation ){

            mGPS.getLocation();

            LatLong ll = new LatLong(mGPS.getLatitude(), mGPS.getLongitude(), mGPS.location.getTime());
            ll = db.insert(ll);
            adapter.add(ll);
            adapter.notifyDataSetChanged();


        }else{
            Toast.makeText(this, "Unabletofind", Toast.LENGTH_SHORT).show();
            System.out.println("Unable");
        }
    }

    public void limpa(View view) {
        db.limpa();
        adapter.clear();
        adapter.notifyDataSetChanged();
    }
}