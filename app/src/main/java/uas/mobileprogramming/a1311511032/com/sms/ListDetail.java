package uas.mobileprogramming.a1311511032.com.sms;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ListDetail extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap> data;
    private static LayoutInflater inflater=null;

    public ListDetail(Activity a, ArrayList<HashMap> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_detail, null);

        TextView first_line = (TextView)vi.findViewById(R.id.firstLine);
        TextView second_line = (TextView)vi.findViewById(R.id.secondLine);

        HashMap sms = new HashMap();
        sms = data.get(position);

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy HH:mm");
        Date date = new Date(Long.parseLong(sms.get("date").toString()));

        first_line.setText(sms.get("body").toString());
        second_line.setText(formatter.format(date));

        return vi;
    }
}