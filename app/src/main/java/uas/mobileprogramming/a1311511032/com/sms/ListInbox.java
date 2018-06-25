package uas.mobileprogramming.a1311511032.com.sms;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ListInbox extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap> data;
    private static LayoutInflater inflater=null;

    public ListInbox(Activity a, ArrayList<HashMap> d) {
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
            vi = inflater.inflate(R.layout.list_item, null);

        TextView first_line = (TextView)vi.findViewById(R.id.firstLine);
        TextView second_line = (TextView)vi.findViewById(R.id.secondLine);

        HashMap sms = new HashMap();
        sms = data.get(position);

        // Setting all values in listview
        first_line.setText(sms.get("address").toString());
        second_line.setText(sms.get("body").toString());
        return vi;
    }
}