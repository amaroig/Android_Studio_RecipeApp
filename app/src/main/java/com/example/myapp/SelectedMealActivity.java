package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SelectedMealActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_meal);
        TextView MealNameTextView = (TextView) findViewById(R.id.MealNameText);

        Intent intent = getIntent();
        //getting data from mainActivity File
        String mealName = intent.getStringExtra(MainActivity.MEAL_NAME);

        //setting the data For the textView
        MealNameTextView.setText(mealName);




    }
}