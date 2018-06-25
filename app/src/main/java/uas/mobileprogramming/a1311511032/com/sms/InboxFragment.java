package uas.mobileprogramming.a1311511032.com.sms;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class InboxFragment extends Fragment{

    ListView list;
    ListInbox adapterInbox;

    public InboxFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ConstraintLayout constraintLayout = (ConstraintLayout)inflater.inflate(R.layout.inbox_fragment, container, false);

        final ArrayList<HashMap> dataInbox = getSMS();
        list = (ListView) constraintLayout.findViewById(R.id.listview);
        adapterInbox = new ListInbox(this.getActivity(), dataInbox);
        list.setAdapter(adapterInbox);

        // Click event for single list row
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap data = dataInbox.get(position);
                Intent myIntent = new Intent(getActivity(), DetailInboxActivity.class);
                myIntent.putExtra("address", data.get("address").toString());
                startActivity(myIntent);
            }
        });
        return constraintLayout;
    }

    public ArrayList<HashMap> getSMS(){
        ArrayList<HashMap> sms = new ArrayList<HashMap>();
        Uri uriSMSURI = Uri.parse("content://sms/inbox");
        String[] reqCols = {"_id", "thread_id", "address", "body", "date"};
        Cursor cur = getActivity().getApplicationContext().getContentResolver().query(uriSMSURI, reqCols, null, null, null);

        List<String> thread = new ArrayList<String>();
        while (cur != null && cur.moveToNext()) {
            String thread_id = cur.getString(cur.getColumnIndex("thread_id"));
            String address = cur.getString(cur.getColumnIndex("address"));
            String body = cur.getString(cur.getColumnIndex("body"));
            String date = cur.getString(cur.getColumnIndex("date"));
            if (!thread.contains(thread_id)) {
                thread.add(thread_id);
                HashMap hashMap = new HashMap();
                hashMap.put("thread_id", thread_id);
                hashMap.put("address", address);
                hashMap.put("body", body);
                hashMap.put("date", date);

                sms.add(hashMap);
            }
        }
        if (cur != null) {
            cur.close();
        }
        Iterator<HashMap> iterator = sms.iterator();
        while (iterator.hasNext()) {
            HashMap hm = iterator.next();
            System.out.println(hm.get("thread_id"));
        }
        return sms;
    }
}