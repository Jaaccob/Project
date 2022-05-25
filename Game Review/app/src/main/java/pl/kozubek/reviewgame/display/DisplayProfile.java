package pl.kozubek.reviewgame.display;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import pl.kozubek.reviewgame.R;

public class DisplayProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "DisplayProfile";
    private static final String jsonURLUser = "http://10.0.2.2:8080/user/";
    private static String jsonToken = "";
    private static Long id;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getIncomingIntent();
        Log.d(TAG, "onCreate: started");

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

        getJson(id);
        extractUser(id);
    }

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        if (getIntent().hasExtra("jwtToken")) {
            Log.d(TAG, "getIncomingIntent: found intent extras");
            jsonToken = getIntent().getStringExtra("jwtToken");
            Log.d(TAG, "getIncomingIntent: token " + jsonToken);
            id = getIntent().getLongExtra("id", 1);
        }
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

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }


    private void getJson(Long id) {
        StringRequest request = new StringRequest(jsonURLUser + id,
                this::parseJsonData,
                volleyError -> Toast.makeText(getApplicationContext(),
                        "User not found", Toast.LENGTH_SHORT).show()) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + jsonToken);
                return params;
            }
        };
        RequestQueue rQueue = Volley.newRequestQueue(DisplayProfile.this);
        rQueue.add(request);
    }

    private void parseJsonData(String jsonString) {
        try {
            JSONObject object = new JSONObject(jsonString);
            Log.d(TAG, "parseJsonData: " + object);
            String nick = object.getString("nick");
            String email = object.getString("email");
            String firstName = object.getString("firstName");
            String lastName = object.getString("lastName");
            setTextView(nick, email, firstName, lastName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("SetTextI18n")
    private void setTextView(String nick, String email, String firstName, String lastName) {
        Log.d(TAG, "setTextView: setting the textView");
        TextView textNick = findViewById(R.id.nickTextProfile);
        textNick.setText(nick);
        TextView textPass = findViewById(R.id.emailTextProfile);
        textPass.setText(email);
        TextView textFirstName = findViewById(R.id.firstNameTextProfile);
        textFirstName.setText(firstName);
        TextView textLastName = findViewById(R.id.lastNameTextProfile);
        textLastName.setText(lastName);
    }

    private void extractUser(Long id) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String json = "http://10.0.2.2:8080/user/" + id;
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

    public void changeUser(View view) {
        Log.d(TAG, "postJson: send login and password to api");
        String jsonURL = "http://10.0.2.2:8080//change/user";
        EditText nick = findViewById(R.id.nickTextProfile);
        EditText email = findViewById(R.id.emailTextProfile);
        EditText firstName = findViewById(R.id.firstNameTextProfile);
        EditText lastName = findViewById(R.id.lastNameTextProfile);
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("id", id);
            jsonBody.put("nick", nick.getText());
            jsonBody.put("email", email.getText());
            jsonBody.put("firstName", firstName.getText());
            jsonBody.put("lastName", lastName.getText());
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.PUT, jsonURL,
                    response -> {
                        Log.i(TAG, "stringRequest: " + response);
                    },
                    error -> {
                        TextView errorView = findViewById(R.id.error);
                        if (error.toString().equals("403"))
                            errorView.setText("Invalid username or passowrd");
                        else
                            errorView.setText("More problems, check console");
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
}

