package com.example.android.collegemanagementsystem.MongoHQ;

/**
 * Created by satyandra on 6/3/15.
 */

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class SaveAsyncTask extends AsyncTask<String, Void, Boolean> {
/*
    @Override
    protected Boolean doInBackground(MyContact... arg0) {
        try
        {
            MyContact contact = arg0[0];

            QueryBuilder qb = new QueryBuilder();

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost request = new HttpPost(qb.buildContactsSaveURL());

            StringEntity params =new StringEntity(qb.createContact(contact));
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);

            if(response.getStatusLine().getStatusCode()<205)
            {
                Log.e("true", String.valueOf(response.getStatusLine().getStatusCode()));

                return true;
            }
            else
            {
                Log.e("false", "false");
                return false;
            }
        } catch (Exception e) {
            //e.getCause();
            String val = e.getMessage();
            String val2 = val;
            return false;
        }
    }
*/

    @Override
    protected Boolean doInBackground(String... arg0) {
        try {
            QueryBuilder qb = new QueryBuilder();

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost request = new HttpPost(qb.buildContactsSaveURL());
            Log.e("chaava","{\"document\" : "+arg0[0]+", \"safe\" : true}");
            StringEntity params = new StringEntity("{\"document\" : "+arg0[0]+", \"safe\" : true}");
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);

           // if(response.getStatusLine().getStatusCode()<205)
           // {
                Log.e("true", String.valueOf(response.getStatusLine().getStatusCode()));

                return true;
           // }
            /*else
            {
                Log.e("false", "false");
                return false;
            }*/
        }catch (Exception e)
        {
            Log.e("damn error","damn error");
            return false;
        }
    }
}
