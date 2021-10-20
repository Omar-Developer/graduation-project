package com.example.gpproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
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

public class Register2 extends AppCompatActivity {

    private ImageView backbttn;
    private Button enterPass;
    private Button confirmPass;
    private Button done;
    private static String regPass = "", regConfPass = "";
    private EditText username;

    INodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        initialize();

        backbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regPass = "";
                regConfPass = "";
                finish();
            }
        });

        enterPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register2.this, Password.class);
                intent.putExtra("activity", "reg1");
                Password p = new Password();
                p.setCaller(enterPass);
                startActivity(intent);
            }
        });

        confirmPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register2.this, Password.class);
                intent.putExtra("activity", "reg2");
                Password p = new Password();
                p.setCaller(confirmPass);
                startActivity(intent);
            }
        });

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(INodeJS.class);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(username.getText().toString().trim())) {
                    Toast.makeText(Register2.this, "You must enter your username.", Toast.LENGTH_SHORT).show();
                    username.setHintTextColor(Color.parseColor("#F44244"));
                } else {
                    if (regPass.equals("") && regConfPass.equals("")) {
                        Toast.makeText(Register2.this, "Please, enter your password.", Toast.LENGTH_SHORT).show();
                        enterPass.setBackgroundResource(R.drawable.uncheckedbttn);
                        confirmPass.setBackgroundResource(R.drawable.uncheckedbttn);
                    } else if (!regPass.equals(regConfPass)) {
                        Toast.makeText(Register2.this, "You must enter matching passwords.", Toast.LENGTH_SHORT).show();
                        regPass = "";
                        regConfPass = "";
                        enterPass.setBackgroundResource(R.drawable.uncheckedbttn);
                        confirmPass.setBackgroundResource(R.drawable.uncheckedbttn);
                    } else {
                        registerUser(username.getText().toString(), regPass);
                    }
                }
            }
        });
    }

    private void registerUser(String email, String password) {
        buttonsState(false);
        final RelativeLayout pb = findViewById(R.id.progress);
        pb.setVisibility(View.VISIBLE);
        pb.bringToFront();
        compositeDisposable.add(myAPI.registerUser(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (s.contains("Username")) {
                            pb.setVisibility(View.GONE);
                            buttonsState(true);
                            Toast.makeText(Register2.this, "Username already taken", Toast.LENGTH_SHORT).show();
                            username.setText("");
                            username.setHintTextColor(Color.parseColor("#F44244"));
                        } else if (s.contains("Successful")) {
                            pb.setVisibility(View.GONE);
                            buttonsState(true);
                            Toast.makeText(Register2.this, "Register Success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register2.this, Login.class);
                            finish();
                            startActivity(intent);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        pb.setVisibility(View.GONE);
                        buttonsState(true);
                        Toast.makeText(Register2.this, "Sorry, Error happened during registration.", Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    public void setRegPass(String str) {
        regPass = str;
    }

    public void setRegConfPass(String str) {
        regConfPass = str;
    }

    private void initialize() {
        backbttn = findViewById(R.id.back);
        enterPass = findViewById(R.id.enterPass);
        confirmPass = findViewById(R.id.confirmPass);
        done = findViewById(R.id.done);
        username = findViewById(R.id.username);
    }

    private void buttonsState(boolean b) {
        backbttn.setEnabled(b);
        enterPass.setEnabled(b);
        confirmPass.setEnabled(b);
        done.setEnabled(b);
        username.setEnabled(b);
    }
}
