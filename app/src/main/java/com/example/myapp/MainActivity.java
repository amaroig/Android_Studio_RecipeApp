package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.myapp.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<String> userList;
    ProgressDialog progressDialog;
    ArrayAdapter<String> listAdapter;

    Handler mainHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        startUSerList();

        new fetchData().start();
    }

    private void startUSerList() {
        userList = new ArrayList<>();
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, userList);
        binding.userList.setAdapter(listAdapter);

    }

    class fetchData extends Thread{

        @Override
        public void run() {

            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    //MAKE PROGRESS bAR
                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("Fetching Meals");
//                    progressDialog.setCancelMessage(false);
                    progressDialog.show();
                }
            });


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
                userList.clear();

                //LOOPING THE ARRAY TO SEE ALL THE MEALS
                for (int i = 0; i < myMeals.length(); i++) {
                    //TODO: Get every single item i need in the meals array
//                System.out.println("\n\n");
//                System.out.println(myMeals.toString());
                    //Loop the entire Array from tue url Json
                    JSONObject mealArray = myMeals.getJSONObject(i);
                    //extract just the Meal name in the entire array
                    //getting al the data from meals
                    String mealName = (String) mealArray.get("strMeal");
                    System.out.println(mealName);
                    userList.add(mealName);




                }
                //Get all the array from meals DONE
//            System.out.println(json.getJSONArray("meals"));
            }catch (Exception e) {
                //DISPLAY ANY ERROR IF ANY
                System.out.println(e.toString());
            }

            mainHandler.post(new Runnable() {
                @Override
                public void run() {

                    if(progressDialog.isShowing()) {
                        progressDialog.dismiss();
                        listAdapter.notifyDataSetChanged();
                    }

                }
            });
        }
    }
}