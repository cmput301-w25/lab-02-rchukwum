package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // Declare the variables
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText cityInput;
    Button addCityButton, deleteCityButton;
    String selectedCity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the ListView and other UI elements
        cityList = findViewById(R.id.city_list);
        cityInput = findViewById(R.id.city_input);
        addCityButton = findViewById(R.id.add_city_button);
        deleteCityButton = findViewById(R.id.delete_city_button);

        // Initial list of cities
        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        // Initialize data list and add cities
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        // Set up the ArrayAdapter for the ListView
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        cityList.setAdapter(cityAdapter);

        // Add city functionality
        addCityButton.setOnClickListener(v -> {
            String newCity = cityInput.getText().toString().trim();
            if (!newCity.isEmpty()) {
                // Add the new city to the list
                dataList.add(newCity);

                // Notify the adapter that the data has changed so that the ListView is updated
                cityAdapter.notifyDataSetChanged();

                // Clear the input field
                cityInput.setText("");

                // Show a Toast message
                Toast.makeText(MainActivity.this, "City added!", Toast.LENGTH_SHORT).show();
            } else {
                // Show a Toast if the input field is empty
                Toast.makeText(MainActivity.this, "Please enter a city name", Toast.LENGTH_SHORT).show();
            }
        });

        // Delete city functionality
        deleteCityButton.setOnClickListener(v -> {
            if (selectedCity != null) {
                // Remove the selected city from the list
                dataList.remove(selectedCity);

                // Notify the adapter that the data has changed so the ListView is updated
                cityAdapter.notifyDataSetChanged();

                // Reset the selected city after deletion
                selectedCity = null;

                // Show a Toast message
                Toast.makeText(MainActivity.this, "City deleted!", Toast.LENGTH_SHORT).show();
            } else {
                // Show a Toast if no city is selected
                Toast.makeText(MainActivity.this, "Please select a city to delete", Toast.LENGTH_SHORT).show();
            }
        });

        // Set an item click listener on the ListView to select a city for deletion
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            // Store the selected city
            selectedCity = dataList.get(position);

            // Show a Toast with the selected city
            Toast.makeText(MainActivity.this, "Selected: " + selectedCity, Toast.LENGTH_SHORT).show();
        });
    }
}
