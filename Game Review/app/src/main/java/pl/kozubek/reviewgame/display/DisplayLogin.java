package pl.kozubek.reviewgame.display;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Objects;

import pl.kozubek.reviewgame.R;

public class DisplayLogin extends AppCompatActivity {
    private static final String TAG = "DisplayLogin";
    private static final String jsonURL = "https://reviewgameapp.herokuapp.com/auth/token";
//    private static final String jsonURL = "http://10.0.2.2:8080/auth/token";
    private String nick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: create DisplayLogin");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void login(View view) {
        Log.d(TAG, "login: get text from login and password text");
        TextView loginText = findViewById(R.id.loginText);
        TextView passwordText = findViewById(R.id.passwordText);
        postJson(loginText.getText().toString(), passwordText.getText().toString());
    }

    public void register(View view){
        Log.d(TAG, "register: create intent DisplayRegister");
        Intent register = new Intent(this, DisplayRegister.class);
        register.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(register);
    }

    private void loginToAplication(String token) {
        Intent activityFourBestGames = new Intent(this, DisplayFourBestGames.class);
        activityFourBestGames.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Log.d(TAG, "login: sending token: " + token);
        token = token.substring(13)
                .replace("\"", "")
                .replace("}", "");
        activityFourBestGames.putExtra("jwtToken", token);
        activityFourBestGames.putExtra("nick", nick);
        startActivity(activityFourBestGames);
    }

    private void postJson(String login, String pass) {
        Log.d(TAG, "postJson: send login and password to api");
        String[] token = new String[1];
        nick = login;
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("username", login);
            jsonBody.put("password", pass);
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, jsonURL,
                    response -> {
                        Log.i(TAG, "stringRequest: " + response);
                        loginToAplication(response);
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
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    if (response != null) {
                        try {
                            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                            token[0] = json;
                            Log.d(TAG, "parseNetworkResponse: sending token " + Arrays.toString(token));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    return Response.success(token[0], HttpHeaderParser.parseCacheHeaders
                            (Objects.requireNonNull(response)));
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
