package amber.promo.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MenuItemActivity extends AppCompatActivity {
    /** Sets up activity showing menu items. */

    MenuItem currentMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        // retrieves views
        ImageView menuItemImage = findViewById(R.id.menuItemImage);
        TextView menuItemName = findViewById(R.id.menuItemName);
        TextView menuItemCategory = findViewById(R.id.menuItemCategory);
        TextView menuItemDescription = findViewById(R.id.menuItemDescription);
        TextView menuItemPrice = findViewById(R.id.menuItemPrice);

        // retrieves info previous activity
        Intent intent = getIntent();
        MenuItem retrievedMenuItem = (MenuItem) intent.getSerializableExtra("clickedMenuItem");
        currentMenuItem = retrievedMenuItem;

        // assign image for chicken noodle soup (URL gives 404 error)
        if (retrievedMenuItem.getName().equals("Chicken Noodle Soup")) {
            retrievedMenuItem.setImageUrl("https://3.bp.blogspot.com/-xSWatW1Yxrw/WeLUnSwm1DI/AAAAAAAADms/FJ70KBtpGXkbUHIS5Z7Sod1DTZgDH7_cACLcBGAs/s1600/0000-NO-IMAGE.jpeg");
        }

        // sets item name and image
        Picasso.get().load(retrievedMenuItem.getImageUrl()).into(menuItemImage);

        // sets views
        menuItemName.setText(retrievedMenuItem.getName());
        menuItemCategory.setText(retrievedMenuItem.getCategory());
        menuItemDescription.setText(retrievedMenuItem.getDescription());

        // shows price in euros
        String euro = "\u20ac";
        menuItemPrice.setText(getString(R.string.priceEuro, euro, retrievedMenuItem.getPrice()));
    }
}

