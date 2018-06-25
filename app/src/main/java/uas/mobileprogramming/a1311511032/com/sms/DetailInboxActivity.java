package uas.mobileprogramming.a1311511032.com.sms;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class DetailInboxActivity extends AppCompatActivity {

    private Toolbar toolbar;
    ListView list;
    ListDetail adapterDetail;

    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_inbox);

        Bundle b = getIntent().getExtras();
        address = b.getString("address");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(address);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        list = (ListView) findViewById(R.id.listview);
        adapterDetail = new ListDetail(this, getSMSInbox());
        list.setAdapter(adapterDetail);

        // Click event for single list row
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
    public ArrayList<HashMap> getSMSInbox(){
        ArrayList<HashMap> sms = new ArrayList<HashMap>();
        if(ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.READ_SMS") == PackageManager.PERMISSION_GRANTED) {
            Uri uriSMSURI = Uri.parse("content://sms/inbox");
            String[] reqCols = {"_id", "thread_id", "address", "body", "date"};
            Cursor cur = getContentResolver().query(uriSMSURI, reqCols, "address='" + address + "'", null, null);

            while (cur != null && cur.moveToNext()) {
                String thread_id = cur.getString(cur.getColumnIndex("thread_id"));
                String address = cur.getString(cur.getColumnIndex("address"));
                String body = cur.getString(cur.getColumnIndex("body"));
                String date = cur.getString(cur.getColumnIndex("date"));

                HashMap hashMap = new HashMap();
                hashMap.put("thread_id", thread_id);
                hashMap.put("address", address);
                hashMap.put("body", body);
                hashMap.put("date", date);

                sms.add(hashMap);
            }
            if (cur != null) {
                cur.close();
            }
            Iterator<HashMap> iterator = sms.iterator();
            while (iterator.hasNext()) {
                HashMap hm = iterator.next();
                System.out.println(hm.get("address"));
            }
        } else {
            ActivityCompat.requestPermissions(DetailInboxActivity.this, new String[]{"android.permission.READ_SMS"}, 100);
        }
        return sms;
    }
}
