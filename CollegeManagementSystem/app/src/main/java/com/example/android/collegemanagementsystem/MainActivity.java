package com.example.android.collegemanagementsystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class MainActivity extends Activity {

    public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    /**
     * Substitute you own sender ID here. This is the project number you got
     * from the API Console, as described in "Getting Started."
     */
    String SENDER_ID = "343631909873";

    /**
     * Tag used on log messages.
     */
    static final String TAG = "GCMDemo";

    //TextView mDisplay;
    GoogleCloudMessaging gcm;
    AtomicInteger msgId = new AtomicInteger();
    SharedPreferences prefs;
    Context context;

    String regid;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        SharedPreferences pref=getApplication().getSharedPreferences("user",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("user","Teacher");
        editor.commit();
        Intent stuff=getIntent();
         if (stuff.hasExtra("test"))
        {
            Toast nt=Toast.makeText(getApplicationContext(),stuff.getStringExtra("test"),Toast.LENGTH_LONG);
            nt.show();
        }
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            android.app.Fragment mfrag=new PlaceholderFragment();
            getFragmentManager().beginTransaction()
                    .add(R.id.container, mfrag)
                    .commit();

        }
        context = getApplicationContext();
        if (checkPlayServices()) {
            gcm = GoogleCloudMessaging.getInstance(this);
            regid = getRegistrationId(context);

            if (regid.isEmpty()) {
                registerInBackground();
            }
        } else {
            Log.i(TAG, "No valid Google Play Services APK found.");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPlayServices();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }
    private void storeRegistrationId(Context context, String regId) {
        final SharedPreferences prefs = getGcmPreferences(context);
        int appVersion = getAppVersion(context);
        Log.i(TAG, "Saving regId on app version " + appVersion);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_REG_ID, regId);
        editor.putInt(PROPERTY_APP_VERSION, appVersion);
        editor.commit();
    }
    private String getRegistrationId(Context context) {
        final SharedPreferences prefs = getGcmPreferences(context);
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
        if (registrationId.isEmpty()) {
            Log.i(TAG, "Registration not found.");
            return "";
        }
        // Check if app was updated; if so, it must clear the registration ID
        // since the existing regID is not guaranteed to work with the new
        // app version.
        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) {
            Log.i(TAG, "App version changed.");
            return "";
        }
        Log.e("regid",registrationId);
        return registrationId;
    }
    private void registerInBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                    regid = gcm.register(SENDER_ID);
                    msg = "Device registered, registration ID=" + regid;
                    Log.e("stuff",msg);
                    // You should send the registration ID to your server over HTTP, so it
                    // can use GCM/HTTP or CCS to send messages to your app.
                    sendRegistrationIdToBackend();

                    // For this demo: we don't need to send it because the device will send
                    // upstream messages to a server that echo back the message using the
                    // 'from' address in the message.

                    // Persist the regID - no need to register again.
                    storeRegistrationId(context, regid);
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    // If there is an error, don't just keep trying to register.
                    // Require the user to click a button again, or perform
                    // exponential back-off.
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
             //   mDisplay.append(msg + "\n");
            }
        }.execute(null, null, null);

    }

    // Send an upstream message.
    public void onClick(final View view) {

        if (view == findViewById(R.id.submit_button)) {
            new AsyncTask<Void, Void, String>() {
                @Override
                protected String doInBackground(Void... params) {
                    String msg = "";
                    try {
                        Bundle data = new Bundle();
                        data.putString("my_message", "Hello World");
                        data.putString("my_action", "com.google.android.gcm.demo.app.ECHO_NOW");
                        String id = Integer.toString(msgId.incrementAndGet());
                        gcm.send(SENDER_ID + "@gcm.googleapis.com", id, data);
                        msg = "Sent message";
                    } catch (IOException ex) {
                        msg = "Error :" + ex.getMessage();
                    }
                    return msg;
                }

                @Override
                protected void onPostExecute(String msg) {
                   // mDisplay.append(msg + "\n");
                }
            }.execute(null, null, null);
        } else {
           // mDisplay.setText("");
        }
    }
    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }
    private SharedPreferences getGcmPreferences(Context context) {
        // This sample app persists the registration ID in shared preferences, but
        // how you store the regID in your app is up to you.
        return getSharedPreferences(MainActivity.class.getSimpleName(),
                Context.MODE_PRIVATE);
    }
    private void sendRegistrationIdToBackend() {
         //sendJson(regid,"Nothing here");
    }
    /*protected void sendJson(final String stud1, final String stud2) {
        Thread t = new Thread() {

            public void run() {*/
                  /*  HttpClient client = new DefaultHttpClient();
                    HttpResponse response;
                    JSONObject json = new JSONObject();

                    try {
                        Looper.prepare();
                        Uri builduri=new Uri.Builder()
                                .scheme("http")
                                .authority("vocal-terminal-87316.appspot.com")
                                .appendQueryParameter("Student1", stud1)
                                .appendQueryParameter("Student2", stud2)
                                .build();
                        URL url = new URL(builduri.toString());
                        urlConnection = (HttpURLConnection) url.openConnection();
                        urlConnection.setDoOutput(true);
                        urlConnection.setChunkedStreamingMode(0);
                        OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
                        urlConnection.setRequestMethod("GET");
                        urlConnection.connect();
                        int resp=urlConnection.getResponseCode();
                        int duration1 = Toast.LENGTH_SHORT;

                        Toast toast1 = Toast.makeText(getActivity(), String.valueOf(resp), duration1);
                        toast1.show();

                    } catch(Exception e) {
                        e.printStackTrace();
                        Log.e("Error", "Cannot Estabilish Connection");
                    }
                    Looper.loop();*/

              /*  MyContact contact=new MyContact();
                contact.Student1=stud1;
                contact.Student2=stud2;
                SaveAsyncTask tsk=new SaveAsyncTask();
                tsk.execute(contact);
            }
        };

        t.start();
    }*/

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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends android.app.Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            SharedPreferences preferences=getActivity().getSharedPreferences("user",Context.MODE_PRIVATE);
            String user=preferences.getString("user","none");
            if (user.equals("Teacher")){
            String menulist[]={"Attendance","Update Marks"};
            List<String> menu_items=new ArrayList<String>(Arrays.asList(menulist));
            final ArrayAdapter menuadapter=new ArrayAdapter(getActivity(),R.layout.fragment_main_2,R.id.main_textView,menu_items);
            ListView listView=(ListView) rootView.findViewById(R.id.menu_listView);
            listView.setAdapter(menuadapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String selecteditem=menuadapter.getItem(position).toString();
                    if(selecteditem.equals("Attendance")) {
                        startActivity(new Intent(getActivity(), Attendance_pre.class).putExtra(Intent.EXTRA_TEXT, selecteditem).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    }
                    if(selecteditem.equals("Update Marks"))
                        startActivity(new Intent(getActivity(),Update_marks.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }

            });}
            else if (user.equals("Student")) {
                String menulist[] = {"Check Marks", "Check Timetable","Check Attendance"};
                List<String> menu_items = new ArrayList<String>(Arrays.asList(menulist));
                final ArrayAdapter menuadapter = new ArrayAdapter(getActivity(), R.layout.fragment_main_2, R.id.main_textView, menu_items);
                ListView listView = (ListView) rootView.findViewById(R.id.menu_listView);
                listView.setAdapter(menuadapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selecteditem = menuadapter.getItem(position).toString();
                        /*if (selecteditem.equals("Attendance"))
                            startActivity(new Intent(getActivity(), Attendance.class).putExtra(Intent.EXTRA_TEXT, selecteditem));*/
                        if (selecteditem.equals("Check Marks"))
                            startActivity(new Intent(getActivity(), Subjects.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        if(selecteditem.equals("Check Timetable"))
                            startActivity(new Intent(getActivity(),TimeTable.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        if (selecteditem.equals("Check Attendance"))
                        startActivity(new Intent(getActivity(),Check_Attendance.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    }
                });
            }
            return rootView;
        }
    }
}
