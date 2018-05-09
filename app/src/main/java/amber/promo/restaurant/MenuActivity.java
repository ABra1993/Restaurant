package amber.promo.restaurant;

import android.app.LauncherActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {
    /** The following class displays the different menu's per category. */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // retrieves category
        Intent intent = getIntent();
        String category = intent.getExtras().getString("category");

        // gets menu's for a category
        MenuRequest menuRequest = new MenuRequest(this, category);
        menuRequest.getMenuItems(this);

        // connects listener to listView
        ListView listView = findViewById(R.id.menuList);
        listView.setOnItemClickListener(new ListItemClickListener());
    }

    @Override
    public void gotMenu(ArrayList<MenuItem> menuItem) {

        // connects adapter to listView
        ListView listView = findViewById(R.id.menuList);
        MenuAdapter adapter = new MenuAdapter(this, R.layout.entry_menu, menuItem);
        listView.setAdapter(adapter);
    }

    @Override
    public void gotMenuError(String message) {
        // creates toast
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), message, duration);
        toast.show();
    }

    // passes information to and start second activity when user clicks on item
    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MenuItem clickedMenuItem = (MenuItem) parent.getItemAtPosition(position);
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            intent.putExtra("clickedMenuItem", clickedMenuItem);
            startActivity(intent);
        }
    }
}
