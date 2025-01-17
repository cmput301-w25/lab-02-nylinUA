/*
    CMPUT 301

    Idk about Version information, lets say 0.5 or something

    Today is Jan.17, 2025

    See the license.md file about copyright man


    !!!How this works!!!
    1. Enter the name of the city into the textbox near the bottom, and then click "Add" button
       below to add the city to the list

    2. Tap a city name to select it then press "Delete" to remove the city from the listview.
 */


package com.example.listycity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;


    StringBuffer cityUserInput = new StringBuffer();
    int lastClickedCityPosition = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //Lab demo code: create a List of cities to be displayed

        cityList = findViewById(R.id.city_list);

        String[] cities = {"Edmonton", "Paris", "London", "Shanghai" };

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this,R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        //------------------------Lab participation code:-----------------------------------------

        ConstraintLayout myScreen = (ConstraintLayout) findViewById(R.id.main);
        myScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SCREEN", "Does this do anything???");
            }
        });


        //Section for deleting items in the list
        //1. Create a listener for the delete button
        Button buttonDelete = (Button) findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Log.d("BUTTONS", "User tapped the Delete button");

                //Only delete the item if its position exists within the size of the list of cities
                if (lastClickedCityPosition != -1 && lastClickedCityPosition <= dataList.size()-1){
                    dataList.remove(lastClickedCityPosition);
                    //cityAdapter.remove(String.valueOf(lastClickedCityPosition));
                    cityAdapter.notifyDataSetChanged();
                    //Log.d("BUTTONS", "bruhh" + lastClickedCityPosition);
                    lastClickedCityPosition = -1;
                }

            }
        });
        //2. add listener to the ListView, cityList
        ListView citiesListView = (ListView) cityList;
        citiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String clickedItemIds = cityAdapter.getItem(position);
                Log.d("LISTVIEWS", "User tapped the List item no." + position + ", " + clickedItemIds);
                lastClickedCityPosition = position;
            }
        });



        //add a listener to the add button
        TextInputEditText cityInput = (TextInputEditText) findViewById(R.id.city_input_edit_text);

        Button buttonAdd = (Button) findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Log.d("BUTTONS", "User tapped the Add button");

                cityUserInput.append(cityInput.getText());

                //Log.d("BUTTONS", cityUserInput.toString());

                if (cityUserInput.length() > 0) {

                    dataList.add(cityUserInput.toString());
                    cityAdapter.notifyDataSetChanged();
                    cityUserInput.setLength(0);

                    lastClickedCityPosition = -1;
                }

            }
        });







    }
}