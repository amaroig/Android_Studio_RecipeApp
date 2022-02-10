package com.example.myapp;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;

//Food App
//TODO:fetch data from api!
//TODO: display data


public class FoodData {
    private String itemName;
    private String itemDescription;
    private Integer Id;
    private String youTubeLink;
    private String itemImage;


    public FoodData(String itemName, String itemDescription, String itemImage, String youTubeLink, Integer Id){
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemImage = itemImage;
        this.Id = Id;
        this.youTubeLink = youTubeLink;
    }

    public void main() {
        try {
            URL u = new URL("https://www.themealdb.com/api/json/v1/1/search.php?s=p");
            HttpsURLConnection hr = (HttpsURLConnection) u.openConnection();
            // System.out.println(hr.getResponseCode());
            if(hr.getResponseCode() == 200) {
                InputStream im = hr.getInputStream();
                StringBuffer sb = new StringBuffer();
                BufferedReader br = new BufferedReader(new InputStreamReader(im));
                String line = br.readLine();

                while(line != null) {
                    System.out.println(line);
                    line = br.readLine();
                    //TODO: Get espesific Data
                    JSONObject myResponse = new JSONObject(line.toString());
                    System.out.println("Base + "  + myResponse.getString("base"));


                }


            }

        } catch (Exception e) {
            System.out.println(e);

        }

        
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public Integer getId() {
        return Id;
    }

    public String getYouTubeLink() {
        return youTubeLink;
    }

    public String getItemImage() {
        return itemImage;
    }
}
