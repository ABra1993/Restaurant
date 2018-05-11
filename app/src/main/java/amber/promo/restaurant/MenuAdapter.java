package amber.promo.restaurant;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends ArrayAdapter<MenuItem> {
    /** The following class connects the adapter to view and displays menu info. */

    private List<MenuItem> data;
    private Context context;

    public MenuAdapter(@NonNull Context context, int resource, ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        data = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.entry_menu, parent, false);
        }

        // retrieves and sets views
        ImageView menuImage = convertView.findViewById(R.id.menuImage);
        TextView menuName = convertView.findViewById(R.id.menuName);
        TextView menuPrice = convertView.findViewById(R.id.menuPrice);

        // assign image for chicken noodle soup (URL gives 404 error)
        if (data.get(position).getName().equals("Chicken Noodle Soup")) {
            data.get(position).setImageUrl("https://3.bp.blogspot.com/-xSWatW1Yxrw/WeLUnSwm1DI/AAAAAAAADms/FJ70KBtpGXkbUHIS5Z7Sod1DTZgDH7_cACLcBGAs/s1600/0000-NO-IMAGE.jpeg");
        }

        // sets item name and image
        Picasso.get().load(data.get(position).getImageUrl()).into(menuImage);
        menuName.setText(data.get(position).getName());

        // sets price in euros
        String euro = "\u20ac";
        menuPrice.setText(context.getResources().getString(R.string.priceEuro, euro, data.get(position).getPrice()));

        return convertView;
    }

}


