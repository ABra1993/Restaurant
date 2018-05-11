package amber.promo.restaurant;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.security.auth.callback.Callback;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    /** The following class downloads categories of food using JSON from un url. */

    // initializes interface
    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    // initializes properties...
    private Context context;
    private ArrayList<String> categories = new ArrayList<>();
    private Callback inputActivity;

    // initializes constructor...
    public CategoriesRequest(Context context) {
        this.context = context;
    }

    // downloads categories of food using JSON
    public void getCategories(Callback activity) {
        inputActivity = activity;

        // instantiates the RequestQueue
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://resto.mprog.nl/categories";

        // stores API data from a JSON object
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
            JSONArray categoriesArray = response.getJSONArray("categories");
            for (int i = 0; i < categoriesArray.length(); i++) {
                categories.add(categoriesArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // calls gotCategories from Callback interface
        inputActivity.gotCategories(categories);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        // calls gotCategoriesError from Callback interface
        inputActivity.gotCategoriesError(error.getMessage());
    }
}
