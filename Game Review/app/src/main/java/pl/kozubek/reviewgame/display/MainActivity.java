package pl.kozubek.reviewgame.display;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import pl.kozubek.reviewgame.R;
import pl.kozubek.reviewgame.adapter.Adapter;
import pl.kozubek.reviewgame.entity.Game;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "AllGame";
    private static String jsonToken = "";
    private static Long id;

    RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Game> games;
    private static final String jsonGameWithoutTypeUrl = "https://reviewgameapp.herokuapp.com/gamesWithoutType";
//    private static final String jsonGameWithoutTypeUrl = "http://10.0.2.2:8080/gamesWithoutType";

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_game);
        getIncomingIntent();


        recyclerView = findViewById(R.id.gameList);
        recyclerView.setHasFixedSize(true);


        games = new ArrayList<>();
        extractGame();
        PackageManager pm = getPackageManager();
        pm.setComponentEnabledSetting(new ComponentName(this, MainActivity.class),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        navigationView.bringToFront();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar
                , R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.four_best_games);
        extractUser(id);
    }

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        if (getIntent().hasExtra("jwtToken")) {
            Log.d(TAG, "getIncomingIntent: found intent extras");
            jsonToken = getIntent().getStringExtra("jwtToken");
            Log.d(TAG, "getIncomingIntent: token " + jsonToken);
            id = getIntent().getLongExtra("id",1);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    private void extractGame() {
        RequestQueue queue = Volley.newRequestQueue(this);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                jsonGameWithoutTypeUrl,
                null,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject gameObject = response.getJSONObject(i);
                            Game game = new Game();
                            game.setId((long) gameObject.getInt("id"));
                            game.setImageURL(gameObject.getString("imageURL"));
                            game.setTitle(gameObject.getString("title"));
                            game.setAuthor(gameObject.getString("author"));
                            game.setDescription(gameObject.getString("description"));
                            game.setMark(gameObject.getDouble("mark"));

                            games.add(game);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    games = games.stream()
                            .sorted(Comparator
                                    .comparingDouble(Game::getMark)
                                    .reversed())
                            .collect(Collectors.toList());
                    mAdapter = new Adapter(getApplicationContext(), games, jsonToken, id);
                    recyclerView.setAdapter(mAdapter);
                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);

                }, error -> Log.d("tag", "onErrorResponse: " + error.getMessage())
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + jsonToken);
                return params;
            }
        };


        queue.add(jsonArrayRequest);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.all_games:
                Intent all_games = new Intent(this, MainActivity.class);
                all_games.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                all_games.putExtra("jwtToken", jsonToken);
                all_games.putExtra("id", id);
                startActivity(all_games);
                break;
            case R.id.four_best_games:
                Intent four_best_games = new Intent(this, DisplayFourBestGames.class);
                four_best_games.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                four_best_games.putExtra("jwtToken", jsonToken);
                four_best_games.putExtra("id", id);
                startActivity(four_best_games);
                break;
            case R.id.profile:
                Intent profile = new Intent(this, DisplayProfile.class);
                profile.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                profile.putExtra("jwtToken", jsonToken);
                profile.putExtra("id", id);
                startActivity(profile);
                break;
            case R.id.follow_game:
                Intent followGame = new Intent(this, DisplayFollowedGames.class);
                followGame.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                followGame.putExtra("jwtToken", jsonToken);
                followGame.putExtra("id", id);
                startActivity(followGame);
                break;
            case R.id.followed_user:
                Intent followUser = new Intent(this, DisplayFollowedUsers.class);
                followUser.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                followUser.putExtra("jwtToken", jsonToken);
                followUser.putExtra("id", id);
                startActivity(followUser);
                break;
            case R.id.logout:
                Intent login = new Intent(this, DisplayLogin.class);
                login.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(login);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void extractUser(Long id) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String json = "https://reviewgameapp.herokuapp.com/user/" + id;
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET,
                json,
                null,
                response -> {
                    // response
                    Log.d(TAG, String.valueOf(response));
                    Log.d(TAG, "exctractUser: response " + response);

                    TextView user = findViewById(R.id.userID);
                    TextView email = findViewById(R.id.userEmail);
                    try {
                        user.setText("Nick: " + response.getString("nick"));
                        email.setText("Email: " + response.getString("email"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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


}