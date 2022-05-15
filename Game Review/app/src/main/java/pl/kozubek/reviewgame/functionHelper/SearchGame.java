package pl.kozubek.reviewgame.functionHelper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import pl.kozubek.reviewgame.display.DisplayReviewActivity;
import pl.kozubek.reviewgame.entity.Game;

public class SearchGame extends AppCompatActivity {
    private static final String TAG = "SearchGame";
    private static String jsonToken = "";
    private static final String jsonURLTitle = "http://10.0.2.3:8080/singleGameTitle/";
    ProgressDialog dialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getIncomingIntent();
    }

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        if (getIntent().hasExtra("title")) {
            Log.d(TAG, "getIncomingIntent: found intent extras");
            String title = getIntent().getStringExtra("title");
            jsonToken = getIntent().getStringExtra("jwtToken");
            Log.d(TAG, "getIncomingIntent: title " + title);
            findGame(title);
        }
    }

    private void getJson(String title) {
        title = title.replace(" ", "%20");
        StringRequest request = new StringRequest(jsonURLTitle + title, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                parseJsonData(string);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Game not found", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + jsonToken);
                return params;
            }
        };
        RequestQueue rQueue = Volley.newRequestQueue(SearchGame.this);
        rQueue.add(request);
    }

    private void parseJsonData(String jsonString) {
        try {
            JSONObject object = new JSONObject(jsonString);
            Game game = new Game();
            game.setId(object.getLong("id"));
            game.setTitle(object.getString("title"));
            game.setImageURL(object.getString("imageURL"));
            game.setAuthor(object.getString("author"));
            game.setMark(object.getDouble("mark"));
            game.setDescription(object.getString("description"));
            Log.d(TAG, "parseJsonData: " + game);
            Log.d(TAG, "parseJsonData: " + object);
            Intent intent = new Intent(this, DisplayReviewActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("id", game.getId());
            intent.putExtra("title", game.getTitle());
            intent.putExtra("imageURL", game.getImageURL());
            intent.putExtra("author", game.getAuthor());
            intent.putExtra("mark", game.getMark());
            intent.putExtra("description", game.getDescription());
            intent.putExtra("jwtToken", jsonToken);
            this.startActivity(intent);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void findGame(String title) {
        getJson(title);
    }
}
