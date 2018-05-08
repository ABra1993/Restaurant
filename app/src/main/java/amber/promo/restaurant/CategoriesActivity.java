package amber.promo.restaurant;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        CategoriesRequest categoriesRequest = new CategoriesRequest(this);
        categoriesRequest.getCategories(this);
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
}
