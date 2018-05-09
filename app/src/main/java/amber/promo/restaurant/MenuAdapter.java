package amber.promo.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends ArrayAdapter<MenuItem> {

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

        Picasso.get().load(data.get(position).getImageUrl()).into(menuImage);
        menuName.setText(data.get(position).getName());
        //menuPrice.setText(data.get(position).getPrice());

        // show price in euros
        String euro = "\u20ac";
        menuPrice.setText(context.getResources().getString(R.string.priceEuro, euro, data.get(position).getPrice()));

        return convertView;
    }
}
