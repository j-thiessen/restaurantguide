package com.example.jordan.groupproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    ArrayList<Restaurant> restaurantList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        String name, address, number, description, tags;

        DBHandler dbHelper = new DBHandler(this);
        Cursor c = dbHelper.getAllRestaurants();

        if(c.moveToFirst()) {

            while(!c.isAfterLast()) {
                name = c.getString(c.getColumnIndexOrThrow((RestaurantContract.Restaurants.COLUMN_NAME_NAME)));

                address = c.getString(c.getColumnIndexOrThrow((RestaurantContract.Restaurants.COLUMN_NAME_ADDRESS)));

                number = c.getString(c.getColumnIndexOrThrow((RestaurantContract.Restaurants.COLUMN_NAME_NUMBER)));

                description = c.getString(c.getColumnIndexOrThrow((RestaurantContract.Restaurants.COLUMN_NAME_DESCRIPTION)));

                tags = c.getString(c.getColumnIndexOrThrow((RestaurantContract.Restaurants.COLUMN_NAME_TAGS)));

                Restaurant rest = new Restaurant();
                rest.setName(name);
                rest.setAddress(address);
                rest.setNumber(number);
                rest.setDescription(description);
                rest.setTags(tags);
                rest.setId(c.getInt(c.getColumnIndexOrThrow((RestaurantContract.Restaurants._ID))));
                restaurantList.add(rest);
                c.moveToNext();
            }
        }

        final ListView lvItems = (ListView) findViewById(R.id.lvAll);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                long itemValue = ((Restaurant)lvItems.getItemAtPosition(itemPosition)).getId();
                Intent intent = new Intent(ViewActivity.this, SingleActivity.class);
                intent.putExtra("restaurant_id", itemValue);
                startActivity(intent);
            }
        });

        lvItems.setAdapter(new RestaurantAdapter(this, restaurantList));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
