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
            //CREATES CONNECTION
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            //GET RESPONSE CODE == 200
            int responseCode = connection.getResponseCode();
//            System.out.println(responseCode);
            //READ THE JSON URL DATA
            BufferedReader reader = new BufferedReader( new InputStreamReader(connection.getInputStream()));
            String inputLine;
            // STORES THE DATA IN A STRING
            StringBuffer stringBuffer = new StringBuffer();
            //RUN WHILE THE VAR INPUTLINE IS NOT EMPTY
            while ((inputLine = reader.readLine()) != null){
                //APPEND ALL THE DATA TO THE VAR INPUTLINE
                stringBuffer.append(inputLine);
            }
            //CLOSE THE CONNECTION
            reader.close();
//            System.out.println(stringBuffer.toString());
            //JSON OBJECT TO MANIPULATE THE DATA
            JSONObject json = new JSONObject(stringBuffer.toString());
            //GETTING THE ARRAY FROM MEALS[]
            JSONArray myMeals = json.getJSONArray("meals");

            //LOOPING THE ARRAY TO SEE ALL THE MEALS
            for (int i = 0; i < myMeals.length(); i++) {
                //TODO: Get every single item i need in the meals array
//                System.out.println("\n\n");
//                System.out.println(myMeals.toString());
                //Loop the entire Array from tue url Json
                JSONObject mealArray = myMeals.getJSONObject(i);
                //extract just the Meal name in the entire array
                //getting al the data from meals
                System.out.println(mealArray.get("strMeal"));


            }
            //Get all the array from meals DONE
//            System.out.println(json.getJSONArray("meals"));
        }catch (Exception e) {
            //DISPLAY ANY ERROR IF ANY
            System.out.println(e.toString());
        }
        //TODO: Render just the data i need
        //TODO:Diplay Data in PrintLn

    }
}

//http://data.fixer.io/api/latest?access_key=6f443672aae4ec55e25847c5c7823bb2&format=1
//https://www.themealdb.com/api/json/v1/1/search.php?s=p
//https://jsonplaceholder.typicode.com/users