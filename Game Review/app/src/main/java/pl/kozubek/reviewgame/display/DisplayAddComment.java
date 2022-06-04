package pl.kozubek.reviewgame.display;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import pl.kozubek.reviewgame.R;

public class DisplayAddComment extends AppCompatActivity {

    private static final String TAG = "DisplayAddComment";
//    private static final String jsonAddCommentUrl = "http://10.0.2.2:8080/addReview";
    private static final String jsonAddCommentUrl = "https://reviewgameapp.herokuapp.com/addReview";

    private Long idUser;
    private Long idGame;
    private String jsonToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        getIncomingIntent();
    }

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        String image = null, title = null;
        if (getIntent().hasExtra("idUser")) {
            image = getIntent().getStringExtra("image");
            title = getIntent().getStringExtra("title");
            idUser = getIntent().getLongExtra("idUser", 1);
            idGame = getIntent().getLongExtra("idGame", 1);
            jsonToken = getIntent().getStringExtra("jsonToken");
        }
        setContent(title, image);
    }

    private void setContent(String title, String image) {
        ImageView imageView = findViewById(R.id.review_image);
        TextView textView = findViewById(R.id.follow_game_title);
        Picasso.get().load(image).into(imageView);
        textView.setText(title);
    }

    public void addComment(View view) {
        TextView mark = findViewById(R.id.mark_review);
        TextView description = findViewById(R.id.description_review);
        if (mark.length() > 0 && description.length() > 0) {
            if (Double.parseDouble(mark.getText().toString()) >= 0 && Double.parseDouble(mark.getText().toString()) <= 5) {
                sendComment(Double.parseDouble(mark.getText().toString()), description.getText().toString());
            } else {
                TextView errorMark = findViewById(R.id.error_mark);
                errorMark.setText("The possible setting is from 0 to 5");
            }
        } else {
            TextView errorMark = findViewById(R.id.error_mark);
            errorMark.setText("The possible setting is from 0 to 5");
            TextView errorDesc = findViewById(R.id.error_description);
            errorDesc.setText("Description can't be empty");
        }

    }

    private void sendComment(Double mark, String description) {
        Log.d(TAG, "sendComment: add new comment");
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("idUser", idUser);
            jsonBody.put("idGame", idGame);
            jsonBody.put("mark", mark);
            jsonBody.put("date", LocalDate.now());
            jsonBody.put("description", description);
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    jsonAddCommentUrl,
                    response -> {
                        Log.i(TAG, "stringRequest: " + response);
                        finish();
                    },
                    error -> {
                        Log.d(TAG, "error: " + error.toString());
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
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String stringResponse = null;
                    if (response != null) {
                        stringResponse = String.valueOf(response.statusCode);
                    }
                    return Response.success(stringResponse, HttpHeaderParser.parseCacheHeaders
                            (Objects.requireNonNull(response)));
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
}
