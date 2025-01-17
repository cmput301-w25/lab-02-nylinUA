package com.example.listycity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
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


        //create a List of cities to be displayed

        //ListView cityList = (ListView) findViewById(R.id.city_list);
        cityList = findViewById(R.id.city_list);

        String[] cities = {"Edmonton", "Paris", "London", "Shanghai" };

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this,R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);


        /*
        Button buttonAdd = (Button) findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Add button");
            }
        });

         */

        TextInputEditText cityInput = (TextInputEditText) findViewById(R.id.city_input_edit_text);


        Button buttonAdd = (Button) findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Add button");

                cityUserInput.append(cityInput.getText());

                Log.d("BUTTONS", cityUserInput.toString());

                dataList.add(cityUserInput.toString());
                cityAdapter.notifyDataSetChanged();
                cityUserInput.setLength(0);

            }
        });



        Button buttonDelete = (Button) findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Delete button");
                if (lastClickedCityPosition != -1 && lastClickedCityPosition <= cities.length-1){
                    dataList.remove(lastClickedCityPosition);
                    //cityAdapter.remove(String.valueOf(lastClickedCityPosition));
                    cityAdapter.notifyDataSetChanged();
                    Log.d("BUTTONS", "bruhh" + lastClickedCityPosition);
                    lastClickedCityPosition = -1;
                }

            }
        });



        ListView citiesListView = (ListView) cityList;

        citiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String clickedItemIds = cityAdapter.getItem(position);
                Log.d("LISTVIEWS", "User tapped the List item no." + position + ", " + clickedItemIds);
                lastClickedCityPosition = position;
            }
        });








    }
}