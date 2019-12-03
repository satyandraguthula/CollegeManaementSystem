package com.example.android.collegemanagementsystem;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


public class Attendance extends Activity {
    public static boolean online=false;
    private String[] mPlanetTitles={"Attendance","Update Marks"};
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    static String sendsub;
    static String sendyear;
    static String sendclass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new PlaceholderFragment()).commit();
        }
        if (isOnline())
            online=true;
        else
        online=false;
        sendsub=getIntent().getStringExtra("subject");
        sendyear=getIntent().getStringExtra("year");
        sendclass=getIntent().getStringExtra("class");
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, new ArrayList<String>(Arrays.asList(mPlanetTitles))));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String selecteditem= (String)mDrawerList.getAdapter().getItem(position);
                if(selecteditem.equalsIgnoreCase("Update Marks"))
                {
                    startActivity(new Intent(getApplicationContext(),Update_marks.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    finish();
                }
                if (selecteditem.equalsIgnoreCase("attendance"))
                {
                    mDrawerLayout.closeDrawers();
                }

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
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
        if (isOnline())
            online=true;
        else
            online=false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isOnline())
            online=true;
        else
            online=false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_attendance, menu);
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

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends android.app.Fragment {

        public PlaceholderFragment() {
        }


        Myadapter rolladapter;
        ListView listView;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_attendance, container, false);
            String[] classroom=new String[20];
            String[] Sub=new String[20];
            String[] rolllist={"112A1001","112A1002","112A1003","112A1004","112A1005","112A1006","112A1007","112A1008",
                    "112A1009","112A1010","112A1011","112A1012","112A1013","112A1014","112A1015"};
            rolladapter=new Myadapter(getActivity(),R.layout.fragment_attendance_2,R.id.attendance_textView,rolllist);
            listView=(ListView) rootView.findViewById(R.id.attendance_listView);
            listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
            listView.setAdapter(rolladapter);
            final View footerview=View.inflate(getActivity(),R.layout.fragment_attendance_button,null);
           listView.addFooterView(footerview);
            Button submitbut=( Button)footerview.findViewById(R.id.submit_button);
            submitbut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] tosend=new String[30];
                    int noitems=0;
                    SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
                    if (checkedItems != null) {
                        for (int i = 0; i < checkedItems.size(); i++) {
                            if (checkedItems.valueAt(i)) {

                                String item = rolladapter.getItem(
                                        checkedItems.keyAt(i)).toString();
                                Context context = getActivity();

                                tosend[noitems]=item;
                                noitems++;
                            }
                        }
                    }
                    if (noitems>0){
                        if (online)
                        sendJson(tosend,noitems);
                        else {
                            Toast nt = Toast.makeText(getActivity(), "no connection", Toast.LENGTH_SHORT);
                            nt.show();
                        }
                    }

                }
            });


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String name = (String) rolladapter.getItem(position);
                    View v=rolladapter.getView(position,view,listView);

                    if (listView.isItemChecked(position)&&view!=footerview) {
                        listView.setItemChecked(position, true);
                        //v.setBackgroundColor(Color.GREEN);
                        rolladapter.addit(position);
                    } else {
                        //v.setBackgroundColor(0);
                        rolladapter.remit(position);
                        listView.setItemChecked(position, false);
                        }
                    String[] tosend=new String[10];
                    int noitems=0;
                    SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
                    if (checkedItems != null) {
                        for (int i = 0; i < checkedItems.size(); i++) {
                            if (checkedItems.valueAt(i)) {

                                String item = rolladapter.getItem(
                                        checkedItems.keyAt(i)).toString();
                                Context context = getActivity();
                            }
                        }
                    }

                }
            });
            return rootView;
        }


        //HttpURLConnection urlConnection = null;
        protected void sendJson(final String stud1[], final int noitems) {
            Thread t = new Thread() {

                public void run() {

                    try {
                        String url = new String("http://3.vocal-terminal-87316.appspot.com/");
                      /*  HttpClient httpClient = new DefaultHttpClient();
                        HttpPost request = new HttpPost("http://vocal-terminal-87316.appspot.com");
                                                //request.addHeader("content-type", "application/json");*/



                        int i;

                            String post_string="";

                        for (i=0;i<noitems;i++)
                        {
                            if(i==0)
                            post_string= post_string.concat(stud1[i]);
                            else
                            post_string=post_string.concat(",").concat(stud1[i]);
                        }
                        Log.e("str",post_string);
                       // Log.e("chaava", "{\"document\" : " + post_string + ", \"safe\" : true}");
                       // String params = new String("{\"document\" : "+post_string+", \"safe\" : true}");
                        String branch="";
                        Calendar c = Calendar.getInstance();
                        System.out.println("Current time => " + c.getTime());

                        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                        String formattedDate = df.format(c.getTime());
                        formattedDate=formattedDate.replace("-",".");
                        Log.e("date",formattedDate);
                        if(sendclass.equals("C")||sendclass.equals("D"))
                            branch="CE";

                        URL builturl = new URL("http://3.vocal-terminal-87316.appspot.com/?Date="+formattedDate+"&Absentees="+post_string+"&Year="+sendyear+"&Div="+sendclass+"&Time=2.30&Lec="+sendsub.toLowerCase()+"&Branch="+branch.toLowerCase()+"&Rem=");
                       // URL builturl=new URL("http://3.vocal-terminal-87316.appspot.com/?Date=15.04.2015&Absentees=112a1001,112a1003,112a1007&Year=TE&Div=C&Time=2.30&Lec=se&Branch=ce&Rem=");
                        Log.e("tag",builturl.toString());
                        HttpURLConnection request=(HttpURLConnection)builturl.openConnection();
                        request.setRequestMethod("GET");
                        //request.connect();
                      //  Log.e("JSON string",post_string);
// idhar tm.getDeviceId() etc nikal aur 1234 daal de

// send post string to server


//grab a return string from
                      /*  URL ur1=new URL("http://vocal-terminal-87316.appspot.com");
                        HttpURLConnection urlConnection=(HttpURLConnection)ur1.openConnection();
                        urlConnection.setRequestMethod("GET");
                        urlConnection.connect();
                        Log.e("try","try");*/
                        InputStream inputStream = request.getInputStream();
                        StringBuffer buffer = new StringBuffer();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                        String line;
                        while ((line = reader.readLine()) != null) {
                            // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                            // But it does make debugging a *lot* easier if you print out the completed
                            // buffer for debugging.
                            Log.e("what i get",line);
                            buffer.append(line + "\n");
                        }

                    }catch (IOException e)
                    {
                        Log.e("shit", "io exeption");
                    }
                   /* JSONObject jobj=new JSONObject();
                    try {

                        int i;
                        for (i=0;i<noitems;i++)
                        {
                            jobj.put("Student"+String.valueOf(i+1),stud1[i]);
                        }
                    }catch (JSONException je)
                    {
                        Log.e("damn", "json exception");
                    }
                    SaveAsyncTask tsk=new SaveAsyncTask();
                    tsk.execute(jobj.toString());*/
                }
            };

            t.start();
        }
    }
}
