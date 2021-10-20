package com.example.gpproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.gpproject.Retrofit.INodeJS;
import com.example.gpproject.Retrofit.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity {

    private ImageView backbttn;
    private Button enterPass, login;
    private EditText username;
    private static String pass = "" ;
    private static final String TAG = "Login";

    INodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        backbttn = findViewById(R.id.back);
        enterPass = findViewById(R.id.enterPass);
        login = findViewById(R.id.login);
        username = findViewById(R.id.username);

        enterPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Password.class);
                intent.putExtra("activity", "Login");
                Password p = new Password();
                p.setCaller(enterPass);
                startActivity(intent);
            }
        });

        backbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass = "";
                finish();
            }
        });

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(INodeJS.class);
        login.setOnClickListener(   new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(username.getText().toString().trim())) {
                    username.setHintTextColor(Color.parseColor("#F44244"));
                    Toast.makeText(Login.this, "You must enter your username.", Toast.LENGTH_SHORT).show();
                    Toast.makeText(Login.this, "You must fill the fields.", Toast.LENGTH_SHORT).show();
                }
                else if (pass.equals("")) {
                    Toast.makeText(Login.this, "Please, enter your password.", Toast.LENGTH_SHORT).show();
                    enterPass.setBackgroundResource(R.drawable.uncheckedbttn);
                } else {
                    System.out.println("Processing the given data.");
                    loginUser(username.getText().toString(), pass);
                    pass = "";
                }
            }
        });
    }

    private void loginUser(String email, String password) {
        try {
            buttonsState(false);
            final RelativeLayout pb = findViewById(R.id.progress);
            pb.setVisibility(View.VISIBLE);
            pb.bringToFront();
            compositeDisposable.add(myAPI.loginUser(email, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                            if (s.contains("Success")) {
                                pb.setVisibility(View.GONE);
                                buttonsState(true);
                                Toast.makeText(Login.this, "Login Success", Toast.LENGTH_SHORT).show();
                                System.out.println("Login Success.");
                            }
                            else {
                                pb.setVisibility(View.GONE);
                                buttonsState(true);
                                Toast.makeText(Login.this, "Login Failed!. Make sure you entered the right username and password", Toast.LENGTH_LONG).show();
                                System.out.println("Login Failed.");
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            pb.setVisibility(View.GONE);
                            buttonsState(true);
                            Toast.makeText(Login.this, "Sorry, Error happened during Login.", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, throwable.getMessage(), throwable);
                        }
                    }));
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void setPass(String str) {
        this.pass = str;
    }

    private void buttonsState(boolean b) {
        backbttn.setEnabled(b);
        enterPass.setEnabled(b);
        login.setEnabled(b);
        username.setEnabled(b);
    }
}
