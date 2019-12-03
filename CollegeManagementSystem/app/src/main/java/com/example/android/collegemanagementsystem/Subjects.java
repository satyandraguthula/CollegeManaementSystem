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
import com.example.android.collegemanagementsystem.data.TableStructure;

import java.util.ArrayList;
import java.util.Arrays;


public class Subjects extends Activity {
    private String[] mPlanetTitles1={"Check Marks", "Check Timetable","Check Attendance"};
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layoutsub);
        mDrawerList = (ListView) findViewById(R.id.left_drawersub);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // Set the adapter for the list view


            mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                    R.layout.drawer_list_item, new ArrayList<String>(Arrays.asList(mPlanetTitles1))));

        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selecteditem= (String)mDrawerList.getAdapter().getItem(position);
                if (selecteditem.equals("Check Marks"))
                    mDrawerLayout.closeDrawers();
                if(selecteditem.equals("Check Timetable"))
                    startActivity(new Intent(getApplicationContext(),TimeTable.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                if(selecteditem.equals("Check Attendance"))
                    startActivity(new Intent(getApplicationContext(),Check_Attendance.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
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

        TableCreation helper=new TableCreation(this);
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor=db.query(TableStructure.SubjectEntry.TableName,null,null,null,null,null,null);
        if (cursor.moveToFirst())
        {
            int i=0;
            int Subjectindex=cursor.getColumnIndex(TableStructure.SubjectEntry.Column_subject);
            int idofsub=cursor.getColumnIndex(TableStructure.SubjectEntry._ID);
            //List listitems=new ArrayList();
            String ans[]=new String[20];
            int idarray[]=new int[20];
            while (i<cursor.getCount())
            {
                //if (cursor.getString(Subjectindex)!=null)
                //listitems.add(cursor.getString(Subjectindex));
                //Log.e("error", cursor.getString(Subjectindex)+String.valueOf(i));
                ans[i]=cursor.getString(Subjectindex);
                idarray[i]=cursor.getInt(idofsub);
                cursor.moveToNext();
                i++;

            }

                /*ArrayAdapter subadapter=new ArrayAdapter(getActivity(),R.layout.fragment_attendance_2,R.id.attendance_textView,listitems);
                ListView lv=(ListView)rootView.findViewById(R.id.listView_Subjects);
                lv.setAdapter(subadapter);*/
            Cursor cursor2=db.query(TableStructure.Marks.TableName,null,null,null,null,null,null);
            Log.e("count", String.valueOf(cursor2.getCount()));
            if (cursor2.moveToFirst()) {
                TableLayout t1=(TableLayout)findViewById(R.id.marks_table_layout1);
                TableRow tr[]=new TableRow[6];
                int id=cursor2.getColumnIndex(TableStructure.Marks._ID);
                int ut1=cursor2.getColumnIndex(TableStructure.Marks.Column_ut1);
                int ut2=cursor2.getColumnIndex(TableStructure.Marks.Column_ut2);
                t1.isColumnStretchable(id);
                t1.isColumnStretchable(ut1);
                t1.isColumnStretchable(ut2);
                int subid=cursor2.getInt(id);
                int j=0;
                int k=0;
                LayoutInflater lf=getLayoutInflater();
                TableRow row=(TableRow)lf.inflate(R.layout.tablerow,t1,false);
                TextView tv=(TextView)row.findViewById(R.id.txtview1);
                tv.setText("Subject");
                tv.setGravity(Gravity.CENTER);
                tv.setPadding(10,10,10,10);
                TextView tv1=(TextView)row.findViewById(R.id.txtview2);
                tv1.setText("UT1");
                tv1.setGravity(Gravity.CENTER);
                tv1.setPadding(10,10,10,10);
                TextView tv2=(TextView)row.findViewById(R.id.txtview3);
                tv2.setText("UT2");
                tv2.setGravity(Gravity.CENTER);
                tv2.setPadding(10,10,10,10);
                t1.addView(row);
                Log.e("stupid","stuff");
                TextView temp[]=new TextView[20];
                TextView temp1[]=new TextView[20];
                TextView temp2[]=new TextView[20];
                while (j<i) {
                    if (idarray[j] == subid) {
                        tr[k]= (TableRow)lf.inflate(R.layout.tablerow, t1, false);
                        Log.e("stuff", ans[j]);
                        temp[k]=(TextView)tr[k].findViewById(R.id.txtview1);
                        temp[k].setText(ans[j]);
                        temp[k].setGravity(Gravity.CENTER);
                        if (cursor2.getString(ut1)!=null) {
                            temp1[k]=(TextView)tr[k].findViewById(R.id.txtview2);
                            temp1[k].setText(cursor2.getString(ut1));
                            temp1[k].setGravity(Gravity.CENTER);
                        }
                        if (cursor2.getString(ut2)!=null) {
                            temp2[k]=(TextView)tr[k].findViewById(R.id.txtview3);
                            temp2[k].setText(cursor2.getString(ut2));
                            temp2[k].setGravity(Gravity.CENTER);
                        }
                        t1.addView(tr[k++]);
                    }
                    j++;
                }
                while (cursor2.moveToNext())
                {
                    j=0;
                    subid=cursor2.getInt(id);
                    while (j<i) {

                        if (idarray[j] == subid) {
                            Log.e("stuff2",ans[j]);
                            tr[k]=(TableRow)lf.inflate(R.layout.tablerow, t1, false);
                            temp[k]=(TextView)tr[k].findViewById(R.id.txtview1);
                            temp[k].setText(ans[j]);
                            temp[k].setGravity(Gravity.CENTER);
                            if (cursor2.getString(ut1)!=null) {
                                temp1[k]=(TextView)tr[k].findViewById(R.id.txtview2);
                                temp1[k].setText(cursor2.getString(ut1));
                                temp1[k].setGravity(Gravity.CENTER);
                            }
                            if (cursor2.getString(ut2)!=null) {
                                temp2[k]=(TextView)tr[k].findViewById(R.id.txtview3);
                                temp2[k].setText(cursor2.getString(ut2));
                                temp2[k].setGravity(Gravity.CENTER);
                            }
                            t1.addView(tr[k++]);
                            break;
                        }
                        j++;
                    }
                }
            }
        }
        db.close();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_subjects, menu);
        return true;
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

    /**
     * A placeholder fragment containing a simple view.
     */

}
