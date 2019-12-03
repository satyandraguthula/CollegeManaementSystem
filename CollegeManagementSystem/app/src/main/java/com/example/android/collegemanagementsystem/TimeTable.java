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
import com.example.android.collegemanagementsystem.data.TableStructure;

import java.util.ArrayList;
import java.util.Arrays;


public class TimeTable extends Activity {
    private String[] mPlanetTitles={"Check Marks", "Check Timetable","Check Attendance"};
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layouttt);
        mDrawerList = (ListView) findViewById(R.id.left_drawertt);
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
                    mDrawerLayout.closeDrawers();
                if(selecteditem.equals("Check Attendance"))
                    startActivity(new Intent(getApplicationContext(), Check_Attendance.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
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
        String type[]=new String[20];
        String names[]=new String[20];
        String teach[]=new String[20];
        int Monday[]=new int[10];
        int Tuesday[]=new int[10];
        int Wednesday[]=new int[10];
        int Thursday[]=new int[10];
        int Friday[]=new int[10];
        int temp;
        TableCreation helper=new TableCreation(this);
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor=db.query(TableStructure.SubjectEntry.TableName,null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            int i = 0;
            int Subjectindex = cursor.getColumnIndex(TableStructure.SubjectEntry.Column_subject);
            int Subjecttype = cursor.getColumnIndex(TableStructure.SubjectEntry.Column_type);
            int idofsub = cursor.getColumnIndex(TableStructure.SubjectEntry._ID);
            int idteach=cursor.getColumnIndex(TableStructure.SubjectEntry.Column_teacher);
            //List listitems=new ArrayList();
            while (i < cursor.getCount()) {
                if (cursor.getString(Subjectindex) != null) {
                    temp=cursor.getInt(idofsub);
                    names[temp - 1] = cursor.getString(Subjectindex);
                    type[temp - 1] = cursor.getString(Subjecttype);
                    cursor.moveToNext();
                    Log.e(names[i],type[i]);
                    i++;
                }

            }

            Cursor cursor2=db.query(TableStructure.TTable.TableName,null,null,null,null,null,null);
            if(cursor2.moveToFirst())
            {

                int day=cursor2.getColumnIndex(TableStructure.TTable.Day);
                int slot1=cursor2.getColumnIndex(TableStructure.TTable.Slot1);
                int slot2=cursor2.getColumnIndex(TableStructure.TTable.Slot2);
                int slot3=cursor2.getColumnIndex(TableStructure.TTable.Slot3);
                int slot4=cursor2.getColumnIndex(TableStructure.TTable.Slot4);
                int slot5=cursor2.getColumnIndex(TableStructure.TTable.Slot5);
                int slot6=cursor2.getColumnIndex(TableStructure.TTable.Slot6);
                int slot7=cursor2.getColumnIndex(TableStructure.TTable.Slot7);
                int slot8=cursor2.getColumnIndex(TableStructure.TTable.Slot8);
                if (cursor2.getString(day).equals("Monday"))
                {
                    Monday[1]=0;
                    Monday[2]=0;
                    Monday[3]=0;
                    Monday[4]=0;
                    Monday[5]=0;
                    Monday[6]=0;
                    Monday[7]=0;
                    Monday[8]=0;
                    if(!cursor2.isNull(slot1))
                    {
                        Monday[1]=cursor2.getInt(slot1);
                    }
                    if(!cursor2.isNull(slot2))
                    {
                        Monday[2]=cursor2.getInt(slot2);
                    }
                    if(!cursor2.isNull(slot3))
                    {
                        Monday[3]=cursor2.getInt(slot3);
                    }
                    if(!cursor2.isNull(slot4))
                    {
                        Monday[4]=cursor2.getInt(slot4);
                    }
                    if(!cursor2.isNull(slot5))
                    {
                        Monday[5]=cursor2.getInt(slot5);
                    }
                    if(!cursor2.isNull(slot6))
                    {
                        Monday[6]=cursor2.getInt(slot6);
                    }
                    if(!cursor2.isNull(slot7))
                    {
                        Monday[7]=cursor2.getInt(slot7);
                    }
                    if(!cursor2.isNull(slot8))
                    {
                        Monday[8]=cursor2.getInt(slot8);
                    }
                }
                else if (cursor2.getString(day).equals("Tuesday"))

                {
                    Tuesday[1]=0;
                    Tuesday[2]=0;
                    Tuesday[3]=0;
                    Tuesday[4]=0;
                    Tuesday[5]=0;
                    Tuesday[6]=0;
                    Tuesday[7]=0;
                    Tuesday[8]=0;


                    if(!cursor2.isNull(slot1))
                    {
                        Tuesday[1]=cursor2.getInt(slot1);
                    }
                    if(!cursor2.isNull(slot2))
                    {
                        Tuesday[2]=cursor2.getInt(slot2);
                    }
                    if(!cursor2.isNull(slot3))
                    {
                        Tuesday[3]=cursor2.getInt(slot3);
                    }
                    if(!cursor2.isNull(slot4))
                    {
                        Tuesday[4]=cursor2.getInt(slot4);
                    }
                    if(!cursor2.isNull(slot5))
                    {
                        Tuesday[5]=cursor2.getInt(slot5);
                    }
                    if(!cursor2.isNull(slot6))
                    {
                        Tuesday[6]=cursor2.getInt(slot6);
                    }
                    if(!cursor2.isNull(slot7))
                    {
                        Tuesday[7]=cursor2.getInt(slot7);
                    }
                    if(!cursor2.isNull(slot8))
                    {
                        Tuesday[8]=cursor2.getInt(slot8);
                    }
                }
                else if (cursor2.getString(day).equals("Wednesday"))

                {
                    Wednesday[1]=0;
                    Wednesday[2]=0;
                    Wednesday[3]=0;
                    Wednesday[4]=0;
                    Wednesday[5]=0;
                    Wednesday[6]=0;
                    Wednesday[7]=0;
                    Wednesday[8]=0;
                    if(!cursor2.isNull(slot1))
                    {
                        Wednesday[1]=cursor2.getInt(slot1);
                    }
                    if(!cursor2.isNull(slot2))
                    {
                        Wednesday[2]=cursor2.getInt(slot2);
                    }
                    if(!cursor2.isNull(slot3))
                    {
                        Wednesday[3]=cursor2.getInt(slot3);
                    }
                    if(!cursor2.isNull(slot4))
                    {
                        Wednesday[4]=cursor2.getInt(slot4);
                    }
                    if(!cursor2.isNull(slot5))
                    {
                        Wednesday[5]=cursor2.getInt(slot5);
                    }
                    if(!cursor2.isNull(slot6))
                    {
                        Wednesday[6]=cursor2.getInt(slot6);
                    }
                    if(!cursor2.isNull(slot7))
                    {
                        Wednesday[7]=cursor2.getInt(slot7);
                    }
                    if(!cursor2.isNull(slot8))
                    {
                        Wednesday[8]=cursor2.getInt(slot8);
                    }
                }
                else if (cursor2.getString(day).equals("Thursday"))

                {
                    Thursday[1]=0;
                    Thursday[2]=0;
                    Thursday[3]=0;
                    Thursday[4]=0;
                    Thursday[5]=0;
                    Thursday[6]=0;
                    Thursday[7]=0;
                    Thursday[8]=0;
                    if(!cursor2.isNull(slot1))
                    {
                        Thursday[1]=cursor2.getInt(slot1);
                    }
                    if(!cursor2.isNull(slot2))
                    {
                        Thursday[2]=cursor2.getInt(slot2);
                    }
                    if(!cursor2.isNull(slot3))
                    {
                        Thursday[3]=cursor2.getInt(slot3);
                    }
                    if(!cursor2.isNull(slot4))
                    {
                        Thursday[4]=cursor2.getInt(slot4);
                    }
                    if(!cursor2.isNull(slot5))
                    {
                        Thursday[5]=cursor2.getInt(slot5);
                    }
                    if(!cursor2.isNull(slot6))
                    {
                        Thursday[6]=cursor2.getInt(slot6);
                    }
                    if(!cursor2.isNull(slot7))
                    {
                        Thursday[7]=cursor2.getInt(slot7);
                    }
                    if(!cursor2.isNull(slot8))
                    {
                        Thursday[8]=cursor2.getInt(slot8);
                    }
                }
                else if (cursor2.getString(day).equals("Friday"))

                {
                    Friday[1]=0;
                    Friday[2]=0;
                    Friday[3]=0;
                    Friday[4]=0;
                    Friday[5]=0;
                    Friday[6]=0;
                    Friday[7]=0;
                    Friday[8]=0;
                    if(!cursor2.isNull(slot1))
                    {
                        Friday[1]=cursor2.getInt(slot1);
                    }
                    if(!cursor2.isNull(slot2))
                    {
                        Friday[2]=cursor2.getInt(slot2);
                    }
                    if(!cursor2.isNull(slot3))
                    {
                        Friday[3]=cursor2.getInt(slot3);
                    }
                    if(!cursor2.isNull(slot4))
                    {
                        Friday[4]=cursor2.getInt(slot4);
                    }
                    if(!cursor2.isNull(slot5))
                    {
                        Friday[5]=cursor2.getInt(slot5);
                    }
                    if(!cursor2.isNull(slot6))
                    {
                        Friday[6]=cursor2.getInt(slot6);
                    }
                    if(!cursor2.isNull(slot7))
                    {
                        Friday[7]=cursor2.getInt(slot7);
                    }
                    if(!cursor2.isNull(slot8))
                    {
                        Friday[8]=cursor2.getInt(slot8);
                    }
                }
                while (cursor2.moveToNext())
                {
                    if (cursor2.getString(day).equals("Monday"))
                    {
                        Monday[1]=0;
                        Monday[2]=0;
                        Monday[3]=0;
                        Monday[4]=0;
                        Monday[5]=0;
                        Monday[6]=0;
                        Monday[7]=0;
                        Monday[8]=0;
                        if(!cursor2.isNull(slot1))
                        {
                            Monday[1]=cursor2.getInt(slot1);
                        }
                        if(!cursor2.isNull(slot2))
                        {
                            Monday[2]=cursor2.getInt(slot2);
                        }
                        if(!cursor2.isNull(slot3))
                        {
                            Monday[3]=cursor2.getInt(slot3);
                        }
                        if(!cursor2.isNull(slot4))
                        {
                            Monday[4]=cursor2.getInt(slot4);
                        }
                        if(!cursor2.isNull(slot5))
                        {
                            Monday[5]=cursor2.getInt(slot5);
                        }
                        if(!cursor2.isNull(slot6))
                        {
                            Monday[6]=cursor2.getInt(slot6);
                        }
                        if(!cursor2.isNull(slot7))
                        {
                            Monday[7]=cursor2.getInt(slot7);
                        }
                        if(!cursor2.isNull(slot8))
                        {
                            Monday[8]=cursor2.getInt(slot8);
                        }
                    }
                    else if (cursor2.getString(day).equals("Tuesday"))

                    {
                        Tuesday[1]=0;
                        Tuesday[2]=0;
                        Tuesday[3]=0;
                        Tuesday[4]=0;
                        Tuesday[5]=0;
                        Tuesday[6]=0;
                        Tuesday[7]=0;
                        Tuesday[8]=0;


                        if(!cursor2.isNull(slot1))
                        {
                            Tuesday[1]=cursor2.getInt(slot1);
                        }
                        if(!cursor2.isNull(slot2))
                        {
                            Tuesday[2]=cursor2.getInt(slot2);
                        }
                        if(!cursor2.isNull(slot3))
                        {
                            Tuesday[3]=cursor2.getInt(slot3);
                        }
                        if(!cursor2.isNull(slot4))
                        {
                            Tuesday[4]=cursor2.getInt(slot4);
                        }
                        if(!cursor2.isNull(slot5))
                        {
                            Tuesday[5]=cursor2.getInt(slot5);
                        }
                        if(!cursor2.isNull(slot6))
                        {
                            Tuesday[6]=cursor2.getInt(slot6);
                        }
                        if(!cursor2.isNull(slot7))
                        {
                            Tuesday[7]=cursor2.getInt(slot7);
                        }
                        if(!cursor2.isNull(slot8))
                        {
                            Tuesday[8]=cursor2.getInt(slot8);
                        }
                    }
                    else if (cursor2.getString(day).equals("Wednesday"))

                    {
                        Wednesday[1]=0;
                        Wednesday[2]=0;
                        Wednesday[3]=0;
                        Wednesday[4]=0;
                        Wednesday[5]=0;
                        Wednesday[6]=0;
                        Wednesday[7]=0;
                        Wednesday[8]=0;
                        if(!cursor2.isNull(slot1))
                        {
                            Wednesday[1]=cursor2.getInt(slot1);
                        }
                        if(!cursor2.isNull(slot2))
                        {
                            Wednesday[2]=cursor2.getInt(slot2);
                        }
                        if(!cursor2.isNull(slot3))
                        {
                            Wednesday[3]=cursor2.getInt(slot3);
                        }
                        if(!cursor2.isNull(slot4))
                        {
                            Wednesday[4]=cursor2.getInt(slot4);
                        }
                        if(!cursor2.isNull(slot5))
                        {
                            Wednesday[5]=cursor2.getInt(slot5);
                        }
                        if(!cursor2.isNull(slot6))
                        {
                            Wednesday[6]=cursor2.getInt(slot6);
                        }
                        if(!cursor2.isNull(slot7))
                        {
                            Wednesday[7]=cursor2.getInt(slot7);
                        }
                        if(!cursor2.isNull(slot8))
                        {
                            Wednesday[8]=cursor2.getInt(slot8);
                        }
                    }
                    else if (cursor2.getString(day).equals("Thursday"))

                    {
                        Thursday[1]=0;
                        Thursday[2]=0;
                        Thursday[3]=0;
                        Thursday[4]=0;
                        Thursday[5]=0;
                        Thursday[6]=0;
                        Thursday[7]=0;
                        Thursday[8]=0;
                        if(!cursor2.isNull(slot1))
                        {
                            Thursday[1]=cursor2.getInt(slot1);
                        }
                        if(!cursor2.isNull(slot2))
                        {
                            Thursday[2]=cursor2.getInt(slot2);
                        }
                        if(!cursor2.isNull(slot3))
                        {
                            Thursday[3]=cursor2.getInt(slot3);
                        }
                        if(!cursor2.isNull(slot4))
                        {
                            Thursday[4]=cursor2.getInt(slot4);
                        }
                        if(!cursor2.isNull(slot5))
                        {
                            Thursday[5]=cursor2.getInt(slot5);
                        }
                        if(!cursor2.isNull(slot6))
                        {
                            Thursday[6]=cursor2.getInt(slot6);
                        }
                        if(!cursor2.isNull(slot7))
                        {
                            Thursday[7]=cursor2.getInt(slot7);
                        }
                        if(!cursor2.isNull(slot8))
                        {
                            Thursday[8]=cursor2.getInt(slot8);
                        }
                    }
                    else if (cursor2.getString(day).equals("Friday"))

                    {
                        Friday[1]=0;
                        Friday[2]=0;
                        Friday[3]=0;
                        Friday[4]=0;
                        Friday[5]=0;
                        Friday[6]=0;
                        Friday[7]=0;
                        Friday[8]=0;
                        if(!cursor2.isNull(slot1))
                        {
                            Friday[1]=cursor2.getInt(slot1);
                        }
                        if(!cursor2.isNull(slot2))
                        {
                            Friday[2]=cursor2.getInt(slot2);
                        }
                        if(!cursor2.isNull(slot3))
                        {
                            Friday[3]=cursor2.getInt(slot3);
                        }
                        if(!cursor2.isNull(slot4))
                        {
                            Friday[4]=cursor2.getInt(slot4);
                        }
                        if(!cursor2.isNull(slot5))
                        {
                            Friday[5]=cursor2.getInt(slot5);
                        }
                        if(!cursor2.isNull(slot6))
                        {
                            Friday[6]=cursor2.getInt(slot6);
                        }
                        if(!cursor2.isNull(slot7))
                        {
                            Friday[7]=cursor2.getInt(slot7);
                        }
                        if(!cursor2.isNull(slot8))
                        {
                            Friday[8]=cursor2.getInt(slot8);
                        }
                    }
                    else
                    {
                        Log.e("Wrong day",cursor2.getString(day));
                        break;
                    }
                }
            }
            db.close();
            TableLayout t1=(TableLayout)findViewById(R.id.tt_table_layout1);
            TableRow tr[]=new TableRow[10];
            TextView tv1[]=new TextView[10];
            TextView tv2[]=new TextView[10];
            TextView tv3[]=new TextView[10];
            TextView tv4[]=new TextView[10];
            TextView tv5[]=new TextView[10];
            int j=1;
            TableRow row1=new TableRow(this);
            if(Monday.length>0)
            {
                TextView v1=new TextView(this,null,android.R.id.text1);
                v1.setText("Monday");
                row1.addView(v1);
            }
            if(Tuesday.length>0)
            {
                TextView v1=new TextView(this,null,android.R.id.text1);
                v1.setText("Tuesday");
                row1.addView(v1);
            }
             if(Wednesday.length>0)
            {
                TextView v1=new TextView(this,null,android.R.id.text1);
                v1.setText("Wednesday");
                row1.addView(v1);
            }
            if(Thursday.length>0)
            {
                TextView v1=new TextView(this,null,android.R.id.text1);
                v1.setText("Thursday");
                row1.addView(v1);
            }
            if(Friday.length>0)
            {
                TextView v1=new TextView(this,null,android.R.id.text1);
                v1.setText("Friday");
                row1.addView(v1);
            }
            t1.setStretchAllColumns(true);
            t1.addView(row1);
            while (j<=8) {
                tr[j]=new TableRow(this);
                if(Monday[j]>0) {
                    tv1[j - 1] = new TextView(this, null, android.R.id.text1);
                    tv1[j - 1].setText(names[Monday[j] - 1]);
                    tr[j].addView(tv1[j-1]);
                }
                else
                {
                    tv1[j - 1] = new TextView(this, null, android.R.id.text1);
                    tv1[j - 1].setText("-");
                    tr[j].addView(tv1[j-1]);
                }
                if(Tuesday[j]>0) {
                    tv2[j - 1] = new TextView(this, null, android.R.id.text1);
                    tv2[j - 1].setText(names[Tuesday[j] - 1]);
                    tr[j].addView(tv2[j-1]);
                }
                else
                {
                    tv2[j - 1] = new TextView(this, null,android.R.id.text1);
                    tv2[j - 1].setText("-");
                    tr[j].addView(tv2[j-1]);
                }
                if(Wednesday[j]>0) {
                    tv3[j - 1] = new TextView(this, null, android.R.id.text1);
                    tv3[j - 1].setText(names[Wednesday[j] - 1]);
                    tr[j].addView(tv3[j-1]);
                }
                else
                {
                    tv3[j - 1] = new TextView(this, null, android.R.id.text1);
                    tv3[j - 1].setText("-");
                    tr[j].addView(tv3[j-1]);
                }
                if (Thursday[j]>0)
                {
                    tv4[j-1]=new TextView(this,null,android.R.id.text1);
                tv4[j-1].setText(names[Thursday[j]-1]);
                    tr[j].addView(tv4[j-1]);
                }
                else
                {
                    tv4[j - 1] = new TextView(this, null,android.R.id.text1);
                    tv4[j - 1].setText("-");
                    tr[j].addView(tv4[j-1]);
                }
                if (Friday[j]>0) {
                    tv5[j - 1] = new TextView(this, null,android.R.id.text1);
                    tv5[j - 1].setText(names[Friday[j] - 1]);
                    tr[j].addView(tv5[j-1]);
                }
                else
                {
                    tv5[j - 1] = new TextView(this, null, android.R.id.text1);
                    tv5[j - 1].setText("-");
                    tr[j].addView(tv5[j-1]);
                }
                t1.addView(tr[j]);
                j++;
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_time_table, menu);
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
