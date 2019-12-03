package com.example.android.collegemanagementsystem.MongoHQ;

import com.example.android.collegemanagementsystem.MyContact;

public class QueryBuilder {

    /**
     * Specify your database name here
     * @return
     */
    public String getDatabaseName() {
        return "miniproject";
    }

    /**
     * Specify your MongoLab API here
     * @return
     */
    public String getApiKey() {
        return "2rz9ZQKGPTIXIjasEgQd5kpJALIrvnIu";
    }

    /**
     * This constructs the URL that allows you to manage your database,
     * collections and documents
     * @return
     */
    public String getBaseUrl()
    {
        return "https://api.mongolab.com/api/1/databases/"+getDatabaseName()+"/collections/";
    }

    /**
     * Completes the formating of your URL and adds your API key at the end
     * @return
     */
    public String docApiKeyUrl()
    {
        return "?apiKey="+getApiKey();
    }

    /**
     * Returns the docs101 collection
     * @return
     */
    public String documentRequest()
    {
        return "docs101";
    }

    /**
     * Builds a complete URL using the methods specified above
     * @return
     */
    public String buildContactsSaveURL()
    {
        return getBaseUrl()+documentRequest()+docApiKeyUrl();
    }

    /**
     * Formats the contact details for MongoHQ Posting
     * @param contact: Details of the person
     * @return
     */
    public String createContact(MyContact contact)
    {
        return String
                .format("{\"document\"  : {\"Student1\": \"%s\", "
                                + "\"Student2\": \"%s\"}, \"safe\" : true}",
                        contact.Student1, contact.Student2);
    }



}