package amber.promo.restaurant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    // initializes interface
    public interface Callback {
        void gotMenu(ArrayList<MenuItem> menuItem);
        void gotMenuError(String message);
    }

    // initializes properties...
    private Context context;
    private ArrayList<MenuItem> menuItem = new ArrayList<>();
    private Callback inputActivity;
    private String category;

    // initialize constructor...
    public MenuRequest(Context context, String category) {
        this.context = context;
        this.category = category;
    }

    public void getMenuItems(Callback activity) {
        inputActivity = activity;

        // instantiates the RequestQueue
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://resto.mprog.nl/menu";

        // stores API data in a JSON object
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                this, this);

        // adds the request to the RequestQueue
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {

        // fills ArrayList from the JSON object
        try {
            // stores items JSON array in an ArrayList
            JSONArray menuArray = response.getJSONArray("items");
            for (int i = 0; i < menuArray.length(); i++) {
                JSONObject menuObject = menuArray.getJSONObject(i);
                if (menuObject.getString("category").equals(category)) {
                    // retrieves objects
                    String description = menuObject.getString("description");
                    String imageUrl = menuObject.getString("image_url");
                    String name = menuObject.getString("name");
                    String price = menuObject.getString("price");

                    // stores objects in arrayList
                    MenuItem item =  new MenuItem(name, description, imageUrl, category, price);
                    menuItem.add(item);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        inputActivity.gotMenu(menuItem);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        // calls gotMenuError from Callback interface
        inputActivity.gotMenuError(error.getMessage());
    }
}
