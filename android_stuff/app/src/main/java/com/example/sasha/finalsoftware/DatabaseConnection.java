package com.example.sasha.finalsoftware;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * addUser
 * getuUers
 * getSingle
 * prefixSearch
 */

public class DatabaseConnection {

    private static String URL = "http://cknutson.org:12114";
    private static DatabaseConnection instance;
    private static Context ctx;
    public static ArrayList<CensusName> names = new ArrayList<>();
    public static List<User> userSet = new ArrayList<>();


    public static void addUser(final GoogleSignInAccount account) {
        AuthenticatedUser.getInstance().userInfoUpdated = false;
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // All your networking logic
                // should be here
                try {
                    boolean isInDatabase = false;
                    //Set admin status to false by default
//                    AuthenticatedUser.getInstance().isAdmin = false;

                    //Hit /getUsers endpoints
                    URL serverURL = new URL(URL + "/getUsers");
//                    System.out.println("Url address: " + serverURL.toString());
                    HttpURLConnection myConnection = (HttpURLConnection) serverURL.openConnection();
                    myConnection.setRequestMethod("GET");
                    myConnection.setRequestProperty("Content-Type", "application/json");
//                    myConnection.setDoOutput(true);
                    System.out.println("RESPONSE CODE: " + myConnection.getResponseCode());
                    //Read Response
                    if (myConnection.getResponseCode() == 200) {
                        //If response is successful, process results here
//                        System.out.println("[DB] Connection: " + myConnection.getResponseMessage());
                        BufferedReader in = new BufferedReader(new InputStreamReader(myConnection.getInputStream()));
                        String str;
                        String inputLine = "";
                        String outputLine = "";
                        StringBuffer content = new StringBuffer();
                        Gson gson = new Gson();
                        while ((str = in.readLine()) != null) {
                            inputLine += str;
                        }
//                        System.out.println("Input json in string from: \n" + inputLine);
                        in.close();
                        Type listtype = new TypeToken<List<User>>(){}.getType();
                        userSet = gson.fromJson(inputLine, listtype);

                        for (int i = 0; i < userSet.size(); i++){
//                            System.out.println("Id on database: " + userSet.get(i)._id + ". Account id: " + account.getId());
                            if(userSet.get(i)._id.equals(account.getId())){
                                System.out.println("Should be setting isInDatabase to true");
                                isInDatabase = true;
                            }
                        }

                    } else {
                        // Error handling code goes here if call not successful
                        System.out.println("[DB] Error" + myConnection.getResponseMessage());
                    }

                    //Disconnect from /getUsers endpoint
//                    System.out.println("Disconnected from endpoint");
                    myConnection.disconnect();


                    //Variable should be set from earlier, and if user isn't in DB, add them.
//                    System.out.println("isInDatabase value: " + isInDatabase);
                    if (!isInDatabase) {
                        serverURL = new URL(URL + "/addUser");
                        myConnection = (HttpURLConnection) serverURL.openConnection();
                        //myConnection.setRequestProperty("getCollection", "application/vnd.github.v3+json");
                        myConnection.setRequestMethod("POST");
                        myConnection.setRequestProperty("Content-Type", "application/json");

                        //Create request body here
                        String requestBodyJSON = "";
                        try {
                            requestBodyJSON = new JSONObject()
                                    .put("_id", account.getId())
                                    .put("name", account.getDisplayName())
                                    .put("email", account.getEmail())
                                    .toString();
                        } catch (Exception e) {
                            //whatever
                        }

                        myConnection.setDoOutput(true);

                        DataOutputStream out = new DataOutputStream(myConnection.getOutputStream());
                        out.writeBytes(requestBodyJSON);
                        out.flush();
                        out.close();

                        if (myConnection.getResponseCode() == 200) {
                            //Process response
//                            Log.i("[DB] Connection: ", myConnection.getResponseMessage());
                            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(myConnection.getInputStream()));
                            String inputLine;
                            StringBuffer content = new StringBuffer();
                            while ((inputLine = in.readLine()) != null) {
                                content.append(inputLine);
                            }
                            in.close();
//                            Log.i("[DB] Response: ", content.toString());

                        } else {
                            // Error handling code goes here
                            Log.i("[DB]", "Error");
                        }
                        myConnection.disconnect();
                    }
                    else{
                        System.out.println(AuthenticatedUser.getInstance().account.getDisplayName());
                    }
                    AuthenticatedUser.getInstance().userInfoUpdated = true;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Log.i("[DB] Auth display name", account.getDisplayName());
        Log.i("[DB] Auth email", account.getEmail());
        Log.i("[DB] Auth ID", account.getId());

    }


    private static ArrayList<CensusName> search(final String endpoint, final String name, final int start, final int end){
        names = new ArrayList<>();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //Hit /getUsers endpoints
                    URL serverURL = new URL(URL + endpoint);
                    System.out.println("Url address: " + serverURL.toString());
                    HttpURLConnection myConnection = (HttpURLConnection) serverURL.openConnection();
                    myConnection.setRequestMethod("POST");
                    myConnection.setRequestProperty("Content-Type", "application/json");

                    //Create request body here
                    String requestBodyJSON = "";
                    try {
                        requestBodyJSON = new JSONObject()
                                .put("name", name)
                                .put("start", start)
                                .put("end", end)
                                .toString();
                    } catch (Exception e) {
                        //whatever
                    }
                    myConnection.setDoOutput(true);
                    DataOutputStream out = new DataOutputStream(myConnection.getOutputStream());
                    out.writeBytes(requestBodyJSON);
                    out.flush();
                    out.close();
                    //Read Response
                    if (myConnection.getResponseCode() == 200) {
                        //If response is successful, process results here
//                        System.out.println("[DB] Connection: " + myConnection.getResponseMessage());
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(myConnection.getInputStream()));
                        String str;
                        String inputLine = "";
                        String outputLine = "";
                        StringBuffer content = new StringBuffer();
                        Gson gson = new Gson();
                        while ((str = in.readLine()) != null) {
                            inputLine += str;
                        }
                        System.out.println("Input json in string from: \n" + inputLine);
                        in.close();
                        Type listtype = new TypeToken<List<CensusName>>(){}.getType();
                        names = gson.fromJson(inputLine, listtype);
                        if (names.size()==0){
                            names.add(new CensusName("0","empty", "0", "empty"));
                        }

                    } else {
                        // Error handling code goes here if call not successful
                        System.out.println("[DB] Error" + myConnection.getResponseMessage());
                    }

                    //Disconnect from /getUsers endpoint
//                    System.out.println("Disconnected from endpoint");
                    myConnection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    System.out.println(e.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println(e.toString());
                }
            }
        });
        while(names.isEmpty()){
            System.out.println("userSet isn't ready yet, SIZE : " + names.size());
            try{Thread.sleep(200);}catch(Exception e){};
        }
        return names;
    }
    public static ArrayList<CensusName> singleSearch(final String name, final int start, final int end){
        return search("/getSingle", name, start, end);
    }
    public static ArrayList<CensusName> wildcardSearch(final String name, final int start, final int end){
        return search("/wildcardSearch", name, start, end);
    }

    public static synchronized DatabaseConnection getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseConnection(context);
        }
        return instance;
    }

    private DatabaseConnection(Context context) {
        ctx = context;
    }

}
