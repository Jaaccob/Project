package pl.kozubek.reviewgame.display;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.kozubek.reviewgame.R;
import pl.kozubek.reviewgame.adapter.AdapterDisplayReview;
import pl.kozubek.reviewgame.entity.Review;

public class DisplayReviewActivity extends AppCompatActivity {
    private static final String TAG = "DisplayReviewActivity";
    private String jsonToken;
    private Long idGame;
    private Long idUser;
    RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Review> reviews;
    private static final String jsonGameWithoutTypeUrl = "http://10.0.2.2:8080/reviews/";
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_game);

        Log.d(TAG, "onCreate: started");

        recyclerView = findViewById(R.id.reviewList);
        recyclerView.setHasFixedSize(true);

        reviews = new ArrayList<>();

        getIncomingIntent();
    }

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        Double mark = null;
        if (getIntent().hasExtra("id") &&
                getIntent().hasExtra("title") &&
                getIntent().hasExtra("imageURL") &&
                getIntent().hasExtra("author") &&
                getIntent().hasExtra("description") &&
                getIntent().hasExtra("idUser")) {
            Log.d(TAG, "getIncomingIntent: found intent extras");
            Long id = getIntent().getLongExtra("id", 0);
            idGame = id;
            idUser = getIntent().getLongExtra("idUser", 1);
            String title = getIntent().getStringExtra("title");
            String imageURL = getIntent().getStringExtra("imageURL");
            String author = getIntent().getStringExtra("author");
            String description = getIntent().getStringExtra("description");
            jsonToken = getIntent().getStringExtra("jwtToken");
            if (getIntent().hasExtra("mark")) {
                mark = getIntent().getDoubleExtra("mark", 0);
                TextView textMark = findViewById(R.id.gameMark);
                textMark.setText(df.format(mark));
            } else {
                Log.d(TAG, "getIncomingIntent: not found mark");
                extractMark(id);
            }
            setTextView(title, imageURL, author, description);
            extractGame(id);
        }
    }

    @SuppressLint("SetTextI18n")
    private void setTextView(String title, String imageURL, String author, String description) {
        Log.d(TAG, "setTextView: setting the textView");
        TextView textTitle = findViewById(R.id.gameTitle);
        textTitle.setText(title);
        TextView textAuthor = findViewById(R.id.gameAuthor);
        textAuthor.setText(author);

        ImageView image = findViewById(R.id.gameImage);
        Picasso.get().load(imageURL).into(image);
        TextView textDescription = findViewById(R.id.gameDescription);
        textDescription.setText(description);
    }

    private void extractMark(Long id) {
        Log.d(TAG, "extract: connect with api");
        RequestQueue queue = Volley.newRequestQueue(this);
        String json = "http://10.0.2.2:8080/mark/" + id;
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET,
                json,
                null,
                response -> {
                    // response
                    Log.d(TAG, String.valueOf(response));
                    Log.d(TAG, "extractMark: response " + response);
                    double mark = 0;
                    try {
                        mark = response.getDouble("mark");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    TextView textMark = findViewById(R.id.gameMark);
                    textMark.setText(df.format(mark));
                },
                error -> {
                    // TODO Auto-generated method stub
                    Log.d("ERROR", "error => " + error.toString());
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + jsonToken);
                return params;
            }
        };
        queue.add(getRequest);
    }

    private void extractGame(Long id) {
        Log.d(TAG, "extract: connect with api");
        RequestQueue queue = Volley.newRequestQueue(this);
        mAdapter = new AdapterDisplayReview(reviews, getApplicationContext(), jsonToken, idUser);
        recyclerView.setAdapter(mAdapter);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        @SuppressLint("SimpleDateFormat") JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                jsonGameWithoutTypeUrl + id,
                null,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject gameObject = response.getJSONObject(i);
                            Review review = new Review();
                            review.setNick(gameObject.getString("nick"));
                            review.setReviewMark(gameObject.getDouble("mark"));
                            review.setDate(gameObject.getString("date"));
                            review.setReviewDescription(gameObject.getString("description"));
                            reviews.add(review);
                            Log.d(TAG, review.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    mAdapter = new AdapterDisplayReview(reviews, getApplicationContext(), jsonToken, idUser);
                    recyclerView.setAdapter(mAdapter);
                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);

                }, error -> Log.d("tag", "onErrorResponse: " + error.getMessage())) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + jsonToken);
                return params;
            }
        };
        queue.add(jsonArrayRequest);
    }

    private void addFollowGame(Long idGame, Long idUser) {
        Log.d(TAG, "addFollowGame: add new follow game");

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("idGame", idGame);
            jsonBody.put("idUser", idUser);
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.PUT,
                    "http://10.0.2.2:8080/followGameAdd",
                    response -> {
                        Log.i(TAG, "stringRequest: " + response);
                    },
                    error -> {
                        Log.e("VOLLEY", error.toString());
                    }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() {
                    try {
                        return requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Authorization", "Bearer " + jsonToken);
                    return params;
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void deleteFollowGame(Long idGame, Long idUser) {
        Log.d(TAG, "deleteFollowGame: add new follow game");

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("idGame", idGame);
            jsonBody.put("idUser", idUser);
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.DELETE,
                    "http://10.0.2.2:8080/followGameDelete",
                    response -> {
                        Log.i(TAG, "stringRequest: " + response);
                    },
                    error -> {
                        Log.e("VOLLEY", error.toString());
                    }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() {
                    try {
                        return requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Authorization", "Bearer " + jsonToken);
                    return params;
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onClickCustomToggleClick(View view) {
        Toast.makeText(this, "Do you like it?", Toast.LENGTH_LONG).show();
        ToggleButton toggleButton = findViewById(R.id.follow_button);

        Log.d(TAG, "onClickCustomToggleClick " + toggleButton.isChecked());

        if (toggleButton.isChecked()) {
            addFollowGame(idGame, idUser);
        } else {
            deleteFollowGame(idGame, idUser);
        }
    }

}
