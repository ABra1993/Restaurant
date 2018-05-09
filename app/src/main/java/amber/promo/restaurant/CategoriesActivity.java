package amber.promo.restaurant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {
    /** The following class creates an activity showing the categories of food. */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // gets categories
        CategoriesRequest categoriesRequest = new CategoriesRequest(this);
        categoriesRequest.getCategories(this);

        // connects listener to ListView
        ListView listView = findViewById(R.id.categoryList);
        listView.setOnItemClickListener(new ListItemClickListener());
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {

        // connects adapter to listView
        ListView listView = findViewById(R.id.categoryList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, R.layout.entry_categories, R.id.category, categories);
        listView.setAdapter(adapter);
    }

    @Override
    public void gotCategoriesError(String message) {
        // creates toast
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), message, duration);
        toast.show();
    }

    // passes information to and start MenuActivity when user clicks on item
    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String category = (String) parent.getItemAtPosition(position);
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("category", category);
            startActivity(intent);
        }
    }
}
