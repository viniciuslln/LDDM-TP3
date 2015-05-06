package com.example.vinih.tp3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vinih on 05/05/2015.
 */
public class MyAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<LatLong> list = new ArrayList<LatLong>();
    private Context context;
    private LatLongDB db;


    public  MyAdapter(ArrayList<LatLong> list, Context context, LatLongDB db) {
        this.list = list;
        this.context = context;
        this.db = db;
    }

    @Override
    public long getItemId(int pos) {
        return list.get(pos).getId();
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    public void add(LatLong ll){
        list.add(ll);
    }

    public void clear(){
        list.clear();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.mylist, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.textViewList);
        listItemText.setText(list.get(position).toString());

        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button)view.findViewById(R.id.buttonDelete);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                db.mDelete(list.get(position));
                list.remove(position);
                notifyDataSetChanged();
            }
        });



        return view;
    }
}