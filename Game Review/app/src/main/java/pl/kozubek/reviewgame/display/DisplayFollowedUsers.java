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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.kozubek.reviewgame.R;
import pl.kozubek.reviewgame.adapter.AdapterDisplayFollowedUsers;
import pl.kozubek.reviewgame.entity.User;

public class DisplayFollowedUsers extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "DisplayFollowedUsers";
    private String jsonToken;
    private static Long id;

    RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<User> users;
    private static final String jsonGameWithTypeUrl = "http://10.0.2.2:8080/followedUsers/";

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followed_users);
        getIncomingIntent();

        recyclerView = findViewById(R.id.followUserList);
        recyclerView.setHasFixedSize(true);

        users = new ArrayList<>();


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
        extractUser();
        extractUser(id);
    }

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        if (getIntent().hasExtra("id")) {
            Log.d(TAG, "getIncomingIntent: found intent extras");
            id = getIntent().getLongExtra("id", 0);
            jsonToken = getIntent().getStringExtra("jwtToken");
            extractUser(id);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    private void extractUser() {
        Log.d(TAG, "extractUser: extract followed game by " + id);
        RequestQueue queue = Volley.newRequestQueue(this);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                jsonGameWithTypeUrl + id,
                null,
                response -> {
                    Log.d(TAG, "jsonArrayRequest: extract followed game by " + id);
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject gameObject = response.getJSONObject(i);
                            Log.d(TAG, "respone: " + gameObject);
                            User user = new User();
                            user.setId((long) gameObject.getInt("id"));
                            user.setImageURL(gameObject.getString("imageURL"));
                            user.setNick(gameObject.getString("nick"));
                            user.setEmail(gameObject.getString("email"));
                            user.setFirstName(gameObject.getString("firstName"));
                            user.setLastName(gameObject.getString("lastName"));
                            Log.d(TAG, "extractGame: " + user);
                            users.add(user);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mAdapter = new AdapterDisplayFollowedUsers(getApplicationContext(), users, jsonToken, id);
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
        String json = "http://10.0.2.2:8080/user/" + id;
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET,
                json,
                null,
                response -> {
                    // response
                    Log.d(TAG, "extractUser: " + response);

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
