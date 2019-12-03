package com.example.android.collegemanagementsystem;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.android.collegemanagementsystem.data.TableCreation;

import java.util.ArrayList;
import java.util.Arrays;


public class Check_Attendance extends Activity {

    private String[] mPlanetTitles={"Check Marks", "Check Timetable","Check Attendance"};
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check__attendance);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layoutca);
        mDrawerList = (ListView) findViewById(R.id.left_drawerca);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, new ArrayList<String>(Arrays.asList(mPlanetTitles))));
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selecteditem= (String)mDrawerList.getAdapter().getItem(position);

                if (selecteditem.equals("Check Marks"))
                    startActivity(new Intent(getApplicationContext(), Subjects.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                if(selecteditem.equals("Check Timetable"))
                    startActivity(new Intent(getApplicationContext(), TimeTable.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                if (selecteditem.equals("Check Attendance"))
                    mDrawerLayout.closeDrawers();
            }
        });

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(getTitle());
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(getTitle());
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        TableLayout t1=(TableLayout)findViewById(R.id.marks_table_layoutatt);
        TableCreation helper=new TableCreation(this);

        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor=db.query("se",null,null,null,null,null,null);
        if (cursor.moveToFirst())
        {
            int i=0;
            int dat=cursor.getColumnIndex("date");
            Log.e("dt",String.valueOf(dat));
            int tim=cursor.getColumnIndex("Time");
            Log.e("ty",String.valueOf(tim));
            int stat=cursor.getColumnIndex("status");
            Log.e("sts",String.valueOf(stat));
            //List listitems=new ArrayList();
            String date[]=new String[20];
            String time[]=new String[20];
            String status[]=new String[20];
            do
            {
                //if (cursor.getString(Subjectindex)!=null)
                //listitems.add(cursor.getString(Subjectindex));
                //Log.e("error", cursor.getString(Subjectindex)+String.valueOf(i));
                date[i]=cursor.getString(dat);
                time[i]=cursor.getString(tim);
                status[i]=cursor.getString(stat);
                i++;

            }while (cursor.moveToNext());
                int j=0;
            int k=0;

                /*ArrayAdapter subadapter=new ArrayAdapter(getActivity(),R.layout.fragment_attendance_2,R.id.attendance_textView,listitems);
                ListView lv=(ListView)rootView.findViewById(R.id.listView_Subjects);
                lv.setAdapter(subadapter);*/
                TableRow tr[]=new TableRow[20];
                //int subid=cursor2.getInt(id);
                LayoutInflater lf=getLayoutInflater();
                TableRow row=(TableRow)lf.inflate(R.layout.tablerow,t1,false);
                TextView tv=(TextView)row.findViewById(R.id.txtview1);
                TableRow rowtop=(TableRow)lf.inflate(R.layout.tablerow,t1,false);
                TextView tvtop=(TextView)rowtop.findViewById(R.id.txtview1);
                tvtop.setText("  ");
                TextView tvtop1=(TextView)rowtop.findViewById(R.id.txtview2);
                tvtop1.setText("SE");
            tvtop1.setGravity(Gravity.CENTER);
            TextView tvtop2=(TextView)rowtop.findViewById(R.id.txtview1);
            tvtop2.setText("  ");
            t1.addView(rowtop);
                tv.setText("Date");
                tv.setGravity(Gravity.CENTER);

                TextView tv1=(TextView)row.findViewById(R.id.txtview2);
                tv1.setText("Time");
                tv1.setGravity(Gravity.CENTER);

                TextView tv2=(TextView)row.findViewById(R.id.txtview3);
                tv2.setText("Absent");
                tv2.setGravity(Gravity.CENTER);

                t1.addView(row);
                TextView temp[]=new TextView[20];
                TextView temp1[]=new TextView[20];
                TextView temp2[]=new TextView[20];
                while (j<i) {
                        tr[k]= (TableRow)lf.inflate(R.layout.tablerow, t1, false);
                        temp[k]=(TextView)tr[k].findViewById(R.id.txtview1);
                        temp[k].setText(date[j]);
                        temp[k].setGravity(Gravity.CENTER);
                            temp1[k]=(TextView)tr[k].findViewById(R.id.txtview2);
                            temp1[k].setText(time[j]);
                            temp1[k].setGravity(Gravity.CENTER);
                            temp2[k]=(TextView)tr[k].findViewById(R.id.txtview3);
                            temp2[k].setText(status[j]);
                            temp2[k].setGravity(Gravity.CENTER);
                        t1.addView(tr[k++]);
                    j++;
                }

            }
        Cursor cursor2=db.query("spcc",null,null,null,null,null,null);
        if (cursor2.moveToFirst())
        {
            int i=0;
            int dat=cursor2.getColumnIndex("date");
            Log.e("dt",String.valueOf(dat));
            int tim=cursor2.getColumnIndex("Time");
            Log.e("ty",String.valueOf(tim));
            int stat=cursor2.getColumnIndex("status");
            Log.e("sts",String.valueOf(stat));
            //List listitems=new ArrayList();
            String date[]=new String[20];
            String time[]=new String[20];
            String status[]=new String[20];
            do
            {
                //if (cursor.getString(Subjectindex)!=null)
                //listitems.add(cursor.getString(Subjectindex));
                //Log.e("error", cursor.getString(Subjectindex)+String.valueOf(i));
                date[i]=cursor2.getString(dat);
                time[i]=cursor2.getString(tim);
                status[i]=cursor2.getString(stat);
                i++;

            }while (cursor2.moveToNext());

                /*ArrayAdapter subadapter=new ArrayAdapter(getActivity(),R.layout.fragment_attendance_2,R.id.attendance_textView,listitems);
                ListView lv=(ListView)rootView.findViewById(R.id.listView_Subjects);
                lv.setAdapter(subadapter);*/
            TableRow tr[]=new TableRow[20];
            //int subid=cursor2.getInt(id);
            int j=0;
            int k=0;
            LayoutInflater lf=getLayoutInflater();
            TableRow row=(TableRow)lf.inflate(R.layout.tablerow,t1,false);
            TextView tv=(TextView)row.findViewById(R.id.txtview1);
            TableRow rowtop=(TableRow)lf.inflate(R.layout.tablerow,t1,false);
            TextView tvtop=(TextView)rowtop.findViewById(R.id.txtview1);
            tvtop.setText("  ");
            TextView tvtop1=(TextView)rowtop.findViewById(R.id.txtview2);
            tvtop1.setText("SPCC");
            tvtop1.setGravity(Gravity.CENTER);
            TextView tvtop2=(TextView)rowtop.findViewById(R.id.txtview1);
            tvtop2.setText("  ");
            t1.addView(rowtop);
            tv.setText("Date");
            tv.setGravity(Gravity.CENTER);

            TextView tv1=(TextView)row.findViewById(R.id.txtview2);
            tv1.setText("Time");
            tv1.setGravity(Gravity.CENTER);

            TextView tv2=(TextView)row.findViewById(R.id.txtview3);
            tv2.setText("Absent");
            tv2.setGravity(Gravity.CENTER);

            t1.addView(row);
            TextView temp[]=new TextView[20];
            TextView temp1[]=new TextView[20];
            TextView temp2[]=new TextView[20];
            while (j<i) {
                tr[k]= (TableRow)lf.inflate(R.layout.tablerow, t1, false);
                temp[k]=(TextView)tr[k].findViewById(R.id.txtview1);
                temp[k].setText(date[j]);
                temp[k].setGravity(Gravity.CENTER);
                temp1[k]=(TextView)tr[k].findViewById(R.id.txtview2);
                temp1[k].setText(time[j]);
                temp1[k].setGravity(Gravity.CENTER);
                temp2[k]=(TextView)tr[k].findViewById(R.id.txtview3);
                temp2[k].setText(status[j]);
                temp2[k].setGravity(Gravity.CENTER);
                t1.addView(tr[k++]);
                j++;
            }

        }
        db.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_check__attendance, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
