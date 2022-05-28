package pl.kozubek.reviewgame.display;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.kozubek.reviewgame.R;
import pl.kozubek.reviewgame.adapter.Adapter;
import pl.kozubek.reviewgame.adapter.AdapterDisplayFollowedUsers;
import pl.kozubek.reviewgame.entity.Game;
import pl.kozubek.reviewgame.entity.User;
import pl.kozubek.reviewgame.functionHelper.CustomHurlStack;

public class DisplayUserProfile extends AppCompatActivity {

    private final static String TAG = "DisplayUserProfile";
    private String jsonToken;
    private Long id;
    private static Long idUser;

    private List<User> users;
    private List<Game> games;

    RecyclerView recyclerViewUser;
    RecyclerView recyclerViewGames;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManagerUser;
    private RecyclerView.LayoutManager layoutManagerGames;

    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        getIncomingIntent();

        recyclerViewUser = findViewById(R.id.followUserListProfile);
        recyclerViewUser.setHasFixedSize(true);
        users = new ArrayList<>();

        recyclerViewGames = findViewById(R.id.followGameListProfile);
        recyclerViewGames.setHasFixedSize(true);
        games = new ArrayList<>();

        extractUser(id);
        extractGames(id);
    }

    @SuppressLint("SetTextI18n")
    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        if (getIntent().hasExtra("jwtToken")) {
            Log.d(TAG, "getIncomingIntent: found intent extras");
            jsonToken = getIntent().getStringExtra("jwtToken");
            id = getIntent().getLongExtra("id",1);
            idUser = getIntent().getLongExtra("idUser", 1);
            TextView textView = findViewById(R.id.userID);
            textView.setText("User: " + getIntent().getStringExtra("nick"));
        }
    }

    private void extractUser(Long idUser) {
        Log.d(TAG, "extractUser: download list of follwed user");
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        mAdapter = new AdapterDisplayFollowedUsers(getApplicationContext(), users, jsonToken, idUser);
        recyclerViewUser.setAdapter(mAdapter);
        layoutManagerUser = new LinearLayoutManager(getApplicationContext());
        recyclerViewUser.setLayoutManager(layoutManagerUser);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                "http://10.0.2.2:8080/followedUsers/" + idUser,
                null,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject JsonUser = response.getJSONObject(i);
                            User user = new User();
                            user.setId(JsonUser.getLong("id"));
                            user.setNick(JsonUser.getString("nick"));
                            user.setEmail(JsonUser.getString("email"));
                            user.setImageURL(JsonUser.getString("imageURL"));
                            user.setFirstName(JsonUser.getString("firstName"));
                            user.setLastName(JsonUser.getString("lastName"));
                            users.add(user);
                            Log.d(TAG, "extractUser: user " + user);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mAdapter = new AdapterDisplayFollowedUsers(getApplicationContext(), users, jsonToken, idUser);
                    recyclerViewUser.setAdapter(mAdapter);
                    layoutManagerUser = new LinearLayoutManager(getApplicationContext());
                    recyclerViewUser.setLayoutManager(layoutManagerUser);
                },
                error -> {
                    Log.e(TAG, "extractUser: error " + error.getMessage());
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + jsonToken);
                return params;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }

    private void extractGames(Long idUser) {
        Log.d(TAG, "extractGames: download list of games user");
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        mAdapter = new Adapter(getApplicationContext(), games, jsonToken, idUser);
        recyclerViewGames.setAdapter(mAdapter);
        layoutManagerGames = new LinearLayoutManager(getApplicationContext());
        recyclerViewGames.setLayoutManager(layoutManagerGames);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                "http://10.0.2.2:8080/folllowGamesWithoutType/" + idUser,
                null,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject JsonObject = response.getJSONObject(i);
                            Game game = new Game();
                            game.setId(JsonObject.getLong("id"));
                            game.setTitle(JsonObject.getString("title"));
                            game.setAuthor(JsonObject.getString("author"));
                            game.setDescription(JsonObject.getString("description"));
                            game.setMark(JsonObject.getDouble("mark"));
                            game.setImageURL(JsonObject.getString("imageURL"));
                            games.add(game);
                            Log.d(TAG, "extractGame: game " + game);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mAdapter = new Adapter(getApplicationContext(), games, jsonToken, idUser);
                    recyclerViewGames.setAdapter(mAdapter);
                    layoutManagerGames = new LinearLayoutManager(getApplicationContext());
                    recyclerViewGames.setLayoutManager(layoutManagerGames);
                },
                error -> {
                    Log.e(TAG, "extractGames: error " + error.getMessage());
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + jsonToken);
                return params;
            }
        };
        requestQueue.add(jsonArrayRequest);
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
                    "http://10.0.2.2:8080/followUserAdd",
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
                    return requestBody.getBytes(StandardCharsets.UTF_8);
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
            CustomHurlStack customHurlStack = new CustomHurlStack();
            RequestQueue requestQueue = Volley.newRequestQueue(this, customHurlStack);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("idGame", idGame);
            jsonBody.put("idUser", idUser);
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.DELETE,
                    "http://10.0.2.2:8080/followUserDelete",
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
                    return requestBody.getBytes(StandardCharsets.UTF_8);
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
        ToggleButton toggleButton = findViewById(R.id.follow_button_user);

        Log.d(TAG, "onClickCustomToggleClick " + toggleButton.isChecked());

//        if (toggleButton.isChecked()) {
//            addFollowGame(idGame, idUser);
//        } else {
//            deleteFollowGame(idGame, idUser);
//        }
    }

}
