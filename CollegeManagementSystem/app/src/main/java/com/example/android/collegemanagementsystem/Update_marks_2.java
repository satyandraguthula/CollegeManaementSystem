package com.example.android.collegemanagementsystem;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;


public class Update_marks_2 extends Activity {

    static String sendsub;
    static String sendyear;
    static String sendclass;
    static String sendexam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sendsub=getIntent().getStringExtra("subject");
        sendyear=getIntent().getStringExtra("year");
        sendclass=getIntent().getStringExtra("class");
        sendexam=getIntent().getStringExtra("exam");
        setContentView(R.layout.activity_update_marks_2);

        LinearLayout l1=(LinearLayout)findViewById(R.id.Linearlayout_mark);
        TextView tv[]=new TextView[20];
        final EditText ev[]=new EditText[20];
        int i;
        for(i=0;i<15;i++)
        {
            tv[i]=new TextView(getApplicationContext());
            if(i<9)
            tv[i].setText("112a100"+String.valueOf(i+1));
            else
                tv[i].setText("112a10" + String.valueOf(i + 1));
            ev[i]=new EditText(getApplicationContext());
            ev[i].setInputType(InputType.TYPE_CLASS_NUMBER);
            l1.addView(tv[i]);
            l1.addView(ev[i]);
        }

        Button buton=new Button(getApplication());
        buton.setText("send");
        l1.addView(buton);
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String value[]=new String[20];
                int j;
                for(j=0;j<15;j++)
                    value[j]=ev[j].getText().toString();
                sendJson(value);
            }
        });
    }
    protected void sendJson(final String stud1[]) {
        Thread t = new Thread() {

            public void run() {

                try {
                    String url = new String("http://3.vocal-terminal-87316.appspot.com/");
                      /*  HttpClient httpClient = new DefaultHttpClient();
                        HttpPost request = new HttpPost("http://vocal-terminal-87316.appspot.com");
                                                //request.addHeader("content-type", "application/json");*/



                    int i;

                    String post_string="";

                    for (i=0;i<15;i++)
                    {
                        if(!stud1[i].equals("")) {
                            if (i == 0)
                                post_string = post_string.concat("112a1001:").concat(stud1[i]);
                            else {
                                if(i<9)
                                post_string = post_string.concat(",").concat("112a100"+String.valueOf(i+1)+":").concat(stud1[i]);
                                else
                                    post_string=post_string.concat(",").concat("112a10"+String.valueOf(i+1)+":").concat(stud1[i]);
                            }
                        }
                    }
                    Log.e("str", post_string);
                    // Log.e("chaava", "{\"document\" : " + post_string + ", \"safe\" : true}");
                    // String params = new String("{\"document\" : "+post_string+", \"safe\" : true}");
                    String branch="";
                    Calendar c = Calendar.getInstance();
                    System.out.println("Current time => " + c.getTime());
;
                    if(sendclass.equals("C")||sendclass.equals("D"))
                        branch="CE";

                    URL builturl = new URL("http://3.vocal-terminal-87316.appspot.com/Marks?Sub="+sendsub.toUpperCase()+"&Branch="+branch+"&Year="+sendyear.toUpperCase()+"&Exam="+sendexam+"&Div="+sendclass.toUpperCase()+"&Marks="+post_string);
                    // URL builturl=new URL("http://3.vocal-terminal-87316.appspot.com/?Date=15.04.2015&Absentees=112a1001,112a1003,112a1007&Year=TE&Div=C&Time=2.30&Lec=se&Branch=ce&Rem=");
                    Log.e("tag",builturl.toString());
                    HttpURLConnection request=(HttpURLConnection)builturl.openConnection();
                    request.setRequestMethod("GET");

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update_marks_2, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
       // mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
       /* if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }*/

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
