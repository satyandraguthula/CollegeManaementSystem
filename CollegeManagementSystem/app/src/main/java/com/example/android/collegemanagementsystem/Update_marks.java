package com.example.android.collegemanagementsystem;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.android.collegemanagementsystem.data.TableCreation;
import com.example.android.collegemanagementsystem.data.TableStructure;

import java.util.ArrayList;
import java.util.List;


public class Update_marks extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_marks);
        final Spinner year=(Spinner)findViewById(R.id.spinner_yearm);
        final Spinner classr=(Spinner)findViewById(R.id.spinner_classm);
        final Spinner subr=(Spinner)findViewById(R.id.spinner_subm);
        final Spinner exam=(Spinner)findViewById(R.id.spinner_exam);
        final Button send=(Button)findViewById(R.id.select_buttonm);
        send.setVisibility(View.INVISIBLE);
        year.setVisibility(View.INVISIBLE);
        classr.setVisibility(View.INVISIBLE);
        subr.setVisibility(View.INVISIBLE);
        exam.setVisibility(View.INVISIBLE);
        TableCreation helper=new TableCreation(this);
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor=db.query(TableStructure.Teacsub.TableName,null,null,null,null,null,null);
        if (cursor.moveToFirst())
        {
            int i=0;
            final String[] classn=new String[20];
            final String[] subn=new String[20];
            final String[] yearn=new String[20];
            int Subjectindex=cursor.getColumnIndex(TableStructure.Teacsub.TSubject);
            int tclass=cursor.getColumnIndex(TableStructure.Teacsub.Tclass);
            int tyear=cursor.getColumnIndex(TableStructure.Teacsub.Year);
            do {
                classn[i]=cursor.getString(tclass);
                subn[i]=cursor.getString(Subjectindex);
                yearn[i]=cursor.getString(tyear);
                Log.e("gh", classn[i]);
                i++;
            }while (cursor.moveToNext());
            boolean searchf[]=new boolean[5];
            String[] yeartemp={"FE","SE","TE","BE"};
            String[] yearval=new String[4];
            final int it=i;
            int j=0;
            for (j=0;j<i;j++)
            {
                Log.e("gtdtedt",yearn[j]);
                if (yearn[j].equals(yeartemp[0]))
                    searchf[0]=true;
                else if (yearn[j].equals(yeartemp[1]))
                    searchf[1]=true;
                else if(yearn[j].equals(yeartemp[2]))
                    searchf[2]=true;
                else if (yearn[j].equals(yeartemp[3]))
                    searchf[3]=true;
            }
            int k=0;
            List<String> yar=new ArrayList<>();
            for (j=0;j<4;j++)
            {
                Log.e("qwert",yeartemp[j]);
                if (searchf[j]==true)
                {
                    yar.add(yeartemp[j]);
                    yearval[k]=yeartemp[j];
                    Log.e("gfsd",yearval[k]);
                    k++;
                }
            }
            final ArrayAdapter<String> classadap=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_text);
            final ArrayAdapter<String> subadap=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_text);
            final ArrayAdapter<String> examadap=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_text);
            final ArrayAdapter<String> yearadap=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_text,yar);
            year.setAdapter(yearadap);
            year.setVisibility(View.VISIBLE);
            year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String temp=(String)yearadap.getItem(position);
                    classadap.clear();
                    int j,k;
                    boolean repeat=false;
                    String[] check=new String[20];
                    for(j=0;j<it;j++) {
                        if(yearn[j].equals(temp))
                        {
                            check[j]=classn[j];
                            for (k=0;k<j;k++)
                                if (check[j].equals(check[k]))
                                    repeat=true;
                            if (!repeat)
                                classadap.add(classn[j]);
                            repeat=false;
                        }
                    }
                    classr.setAdapter(classadap);
                    classr.setVisibility(View.VISIBLE);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            classr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String temp=(String)classadap.getItem(position);
                    subadap.clear();
                    int j;
                    for(j=0;j<it;j++) {
                        if(classn[j].equals(temp))
                            subadap.add(subn[j]);
                    }
                    subr.setAdapter(subadap);
                    subr.setVisibility(View.VISIBLE);
                    examadap.clear();
                    examadap.add("UT1");
                    examadap.add("UT2");
                    exam.setAdapter(examadap);
                    exam.setVisibility(View.VISIBLE);
                    send.setVisibility(View.VISIBLE);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String sendSub=subr.getSelectedItem().toString();
                    String sendClass=classr.getSelectedItem().toString();
                    String sendYear=year.getSelectedItem().toString();
                    String sendexam=exam.getSelectedItem().toString();
                    startActivity(new Intent(getApplicationContext(),Update_marks_2.class).putExtra("year",sendYear)
                            .putExtra("class",sendClass).putExtra("subject",sendSub).putExtra("exam",sendexam));
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update_marks, menu);
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
}
