package com.example.jordan.groupproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
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


    public void addBtn(View v) {
        String name, address, number,description,tags;

        name = ((EditText)findViewById(R.id.editName)).getText().toString();
        address = ((EditText)findViewById(R.id.editAddress)).getText().toString() + ", " + ((EditText)findViewById(R.id.editPC)).getText().toString();
        number = ((EditText)findViewById(R.id.editNumber)).getText().toString();
        description =  ((EditText)findViewById(R.id.editDescription)).getText().toString();
        tags =  ((EditText)findViewById(R.id.editTags)).getText().toString();


        DBHandler dbHelper = new DBHandler(this);
        dbHelper.addRestaurant(name, address, number,description,tags);

    }
}
