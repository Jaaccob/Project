package pl.kozubek.reviewgame.display;

import android.annotation.SuppressLint;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import pl.kozubek.reviewgame.R;
import pl.kozubek.reviewgame.adapter.Adapter;
import pl.kozubek.reviewgame.adapter.AdapterDisplayReview;
import pl.kozubek.reviewgame.entity.Game;
import pl.kozubek.reviewgame.entity.Review;

public class DisplayReviewActivity extends AppCompatActivity {
    private static final String TAG = "DisplayReviewActivity";
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
        if (getIntent().hasExtra("id") &&
                getIntent().hasExtra("title") &&
                getIntent().hasExtra("imageURL") &&
                getIntent().hasExtra("author") &&
                getIntent().hasExtra("mark") &&
                getIntent().hasExtra("description")) {
            Log.d(TAG, "getIncomingIntent: found intent extras");
            Long id = getIntent().getLongExtra("id", 0);
            String title = getIntent().getStringExtra("title");
            String imageURL = getIntent().getStringExtra("imageURL");
            String author = getIntent().getStringExtra("author");
            String description = getIntent().getStringExtra("description");
            Double mark = getIntent().getDoubleExtra("mark", 0);


            setTextView(id, title, imageURL, author, mark, description);
            extractGame(id);
        }
    }

    @SuppressLint("SetTextI18n")
    private void setTextView(Long id, String title, String imageURL, String author, Double mark, String description) {
        Log.d(TAG, "setTextView: setting the textView");
        TextView textTitle = findViewById(R.id.gameTitle);
        textTitle.setText(title);
        TextView textAuthor = findViewById(R.id.gameAuthor);
        textAuthor.setText(author);
        TextView textMark = findViewById(R.id.gameMark);
        textMark.setText(df.format(mark));
        ImageView image = findViewById(R.id.gameImage);
        Picasso.get().load(imageURL).into(image);
        TextView textDescription = findViewById(R.id.gameDescription);
        textDescription.setText(description);
    }

    private void extractGame(Long id) {
        Log.d(TAG, "extract: connect with api");
        RequestQueue queue = Volley.newRequestQueue(this);
        mAdapter = new AdapterDisplayReview(reviews, getApplicationContext());
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

                    mAdapter = new AdapterDisplayReview(reviews, getApplicationContext());
                    recyclerView.setAdapter(mAdapter);
                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);

                }, error -> Log.d("tag", "onErrorResponse: " + error.getMessage()));
        queue.add(jsonArrayRequest);
    }

}
