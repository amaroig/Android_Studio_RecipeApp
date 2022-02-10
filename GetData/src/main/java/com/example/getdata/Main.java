package com.example.getdata;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class Main {

    private static HttpsURLConnection connection;

    public static void main(String[] args) {
        getData();
    }




    public static void getData() {

        //TODO :Get Data from api Json format

        try {

            URL url = new URL("https://www.themealdb.com/api/json/v1/1/search.php?s=p");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            int responseCode = connection.getResponseCode();
//            System.out.println(responseCode);
            BufferedReader reader = new BufferedReader( new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer stringBuffer = new StringBuffer();
            while ((inputLine = reader.readLine()) != null){
                stringBuffer.append(inputLine);
            }
            reader.close();
//            System.out.println(stringBuffer.toString());
            JSONObject json = new JSONObject(stringBuffer.toString());
            JSONArray myMeals = json.getJSONArray("meals");

            for (int i = 0; i < myMeals.length(); i++) {
                //TODO: Get every single item i need in the meals array
            }
            //Get all the array from meals DONE
//            System.out.println(json.getJSONArray("meals"));
        }catch (Exception e) {
            System.out.println(e);
        }
        //TODO: Render just the data i need
        //TODO:Diplay Data in PrintLn

    }
}

//http://data.fixer.io/api/latest?access_key=6f443672aae4ec55e25847c5c7823bb2&format=1
//https://www.themealdb.com/api/json/v1/1/search.php?s=p
//https://jsonplaceholder.typicode.com/users