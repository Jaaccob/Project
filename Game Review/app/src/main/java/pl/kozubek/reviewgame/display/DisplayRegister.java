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
import java.util.Objects;

import pl.kozubek.reviewgame.R;

public class DisplayRegister extends AppCompatActivity {
    private static final String TAG = "DisplayReviewActivity";
    private static final String jsonURL = "http://10.0.2.2:8080/auth/register";
    private static final int PASSWORD_LENGTH = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }

    public void checkCorrectPassword(View view) {
        Log.d(TAG, "checkCorrectPassword: checking if the password is correct");
        TextView login = findViewById(R.id.nickTextRegister);
        TextView password = findViewById(R.id.passwordTextRegister);
        TextView confirmPassword = findViewById(R.id.passwordConfirmTextRegister);

        String passOne = password.getText().toString();
        String passTwo = confirmPassword.getText().toString();
        if (!passOne.equals(passTwo)) {
            TextView error = findViewById(R.id.errorRegister);
            error.setText("Passwords are't the same");
        } else if (!isValidPassword(passTwo)) {
            TextView error = findViewById(R.id.errorRegister);
            error.setText("Invalid password, min 6 characters and min 1 special characters");
        } else {
            postJson(login.getText().toString(), password.getText().toString());
        }
    }

    private void postJson(String login, String pass) {
        Log.d(TAG, "postJson: send login and password to api");
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("username", login);
            jsonBody.put("password", pass);
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, jsonURL,
                    response -> {
                        Log.i(TAG, "stringRequest: " + response);
                        loginToAplication();
                    },
                    error -> {
                        Log.d(TAG, "eroor: " + error.toString());
                        TextView errorView = findViewById(R.id.errorRegister);
                        if (error.networkResponse.statusCode == 500)
                            errorView.setText("Invalid username or passowrd");
                        if (error.networkResponse.statusCode == 403) {
                            errorView.setText("Username already exist in database");
                        } else
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
                    String stringResponse = null;
                    if (response != null) {
                        stringResponse = String.valueOf(response.statusCode);
                    }
                    return Response.success(stringResponse, HttpHeaderParser.parseCacheHeaders
                            (Objects.requireNonNull(response)));
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loginToAplication() {
        Intent login = new Intent(this, DisplayLogin.class);
        login.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(login);
    }

    private static boolean isValidPassword(String password) {

        if (password.length() < PASSWORD_LENGTH) return false;

        int charCount = 0;
        int numCount = 0;
        int specCount = 0;
        int bigCount = 0;
        for (int i = 0; i < password.length(); i++) {

            char ch = password.charAt(i);
            if (isBigChar(ch)) bigCount++;
            else if (isNumeric(ch)) numCount++;
            else if (isLetter(ch)) charCount++;
            else if (isSpecialChar(ch)) specCount++;
            else return false;
        }
        return (charCount >= 2 && numCount >= 2 && specCount >= 1 && bigCount >= 1);
    }

    private static boolean isLetter(char ch) {
        ch = Character.toUpperCase(ch);
        return (ch >= 'A' && ch <= 'Z');
    }

    private static boolean isNumeric(char ch) {
        return (ch >= '0' && ch <= '9');
    }

    private static boolean isSpecialChar(char ch) {
        return (ch >= '!' && ch <= '/') || (ch >= ':' && ch <= '@');
    }

    private static boolean isBigChar(char ch) {
        return (ch >= 'A' && ch <= 'Z');
    }
}
