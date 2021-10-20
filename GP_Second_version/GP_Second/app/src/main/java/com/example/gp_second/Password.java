package com.example.gp_second;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Password extends AppCompatActivity {

    private ImageView backbttn, ksu;
    private ImageView[] shapes;
    private TextView[] txt;
    private Button done;
    private static Button caller;
    private ImageButton eye, eyeoff;
    private char[] characters;
    private String password = "";
    private int counter = 0;
    private List<String> unUsedChar;
    private String usedChar = "";
    private EditText passField;

    private static final int MIN_SHAPES = 8;
    private static final int MAX_SHAPES = 255;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        initialize();

        ksu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RelativeLayout l = findViewById(R.id.tip);
                final View help, help11, help22;
                Button next = findViewById(R.id.next0);
                Button next1 = findViewById(R.id.next1);
                Button next2 = findViewById(R.id.next2);
                help = findViewById(R.id.help);
                help11 = findViewById(R.id.help1);
                help22 = findViewById(R.id.help2);

                l.setVisibility(View.VISIBLE);
                l.bringToFront();
                passField.setEnabled(false);
                done.setEnabled(false);
                ImageButton ib = findViewById(R.id.close);
                ImageButton ib1 = findViewById(R.id.close1);
                ImageButton ib2 = findViewById(R.id.close2);
                ib.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        passField.setEnabled(true);
                        done.setEnabled(true);
                        l.setVisibility(View.GONE);
                    }
                });

                ib1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        passField.setEnabled(true);
                        done.setEnabled(true);
                        l.setVisibility(View.GONE);
                    }
                });

                ib2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        passField.setEnabled(true);
                        done.setEnabled(true);
                        l.setVisibility(View.GONE);
                    }
                });

                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        help.setVisibility(View.INVISIBLE);
                        help11.setVisibility(View.VISIBLE);
                    }
                });

                next1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        help11.setVisibility(View.INVISIBLE);
                        help22.setVisibility(View.VISIBLE);
                    }
                });

                next2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        passField.setEnabled(true);
                        done.setEnabled(true);
                        l.setVisibility(View.GONE);
                        help.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        backbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caller.setBackgroundResource(R.drawable.uncheckedbttn);
                finish();
            }
        });


        passField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count != 0) {
                    if (start - before >= 0 && !usedChar.contains(String.valueOf(s.charAt(start - before)))) {
                        Toast.makeText(Password.this, "This character does not belong to any shape.", Toast.LENGTH_LONG).show();
                        passField.setText(passField.getText().toString().substring(0, passField.getText().length()-1));
                        passField.setSelection(passField.length());
                        System.out.println("String not chosen");
                    } else {
                        for (int i = 0; i < txt.length; i++) {
                            String str = String.valueOf(txt[i].getText());
                            if (start - before >= 0 && str.contains(String.valueOf(s.charAt(start - before)))) {
                                if (password == "") {
                                    password = shapes[i].getResources().getResourceEntryName(shapes[i].getId()).substring(0, 3);
                                } else {
                                    password += shapes[i].getResources().getResourceEntryName(shapes[i].getId()).substring(0, 3);
                                }
                                counter++;
                                System.out.println("Password: " + password);
                                break;
                            }
                        }
                    }
                } else {
                    if(!password.equals("")) {
                        if(start != s.length()) {
                            Toast.makeText(Password.this, "Last shape DELETED: you can only delete from last.", Toast.LENGTH_LONG).show();
                            passField.setSelection(s.length());
                        }
                        password = password.substring(0, password.length() - 3);
                        counter--;
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter < MIN_SHAPES) {
                    Toast.makeText(Password.this, "You must submit at least "+MIN_SHAPES+" shapes.", Toast.LENGTH_SHORT).show();
                    System.out.println("Error: you must submit at least "+MIN_SHAPES+" shapes.");
                }
                else {
                    Intent intent = getIntent();
                    String activity = intent.getStringExtra("activity");
                    if (activity.equals("Login")) {
                        Login l = new Login();
                        l.setPass(password);
                    } else {
                        Register2 r = new Register2();
                        if (activity.equals("reg1")) {
                            r.setRegPass(password);
                        } else if (activity.equals("reg2")) {
                            r.setRegConfPass(password);
                        }
                    }
                    caller.setBackgroundResource(R.drawable.checkbttn);
                    finish();
                }
            }
        });


        eyeoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eyeoff.setVisibility(View.INVISIBLE);
                eye.setVisibility(View.VISIBLE);
                showShape();
            }
        });


        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eye.setVisibility(View.INVISIBLE);
                eyeoff.setVisibility(View.VISIBLE);
                hideShapes();
            }
        });
    }

    private void initialize() {
        characters = new char[52];
        fillCharachters(characters);
        txt = new TextView[15];
        shapes = new ImageView[15];
        allViewsInTXT();
        unUsedChar = charToList(characters);
        fillTextView(txt);

        ksu = findViewById(R.id.ksuLogo);
        backbttn = findViewById(R.id.back);
        passField = findViewById(R.id.passField);
        done = findViewById(R.id.done);
        eyeoff = findViewById(R.id.eye_off);
        eye = findViewById(R.id.eye);
    }

    private void showShape() {
        LinearLayout dots = findViewById(R.id.dots);
        dots.removeAllViews();
        if ((!password.equals("")) && counter != 0) {
            String modPass = password;
            for(int i = 0; i < counter; i++) {
                ImageView iv = new ImageView(getApplicationContext());
                String tempName = modPass.substring(0,3);
                iv.setImageResource(getShape(tempName));
                iv.setPadding(5, 0, 5, 0);
                dots.addView(iv);
                modPass = modPass.substring(3);

            }
        }
    }

    private void hideShapes() {
            LinearLayout dots = findViewById(R.id.dots);
            dots.removeAllViews();
            EditText ev = passField;
            dots.addView(ev);
    }

    private int getShape(String str) {
        final TypedArray shapes = getResources().obtainTypedArray(R.array.apptour);
        for(int i = 0; i < shapes.length(); i++) {
            int resID = shapes.getResourceId(i, 0);
            if(getResources().getResourceEntryName(resID).substring(0, 3).equals(str))
                return resID;
        }
        return 0;
    }

    public void setCaller(Button btn) {
        caller = btn;
    }

    private void fillTextView(TextView[] arr) {
        List<String> list = pickRandomChar();
        for(int i = 0; i < arr.length; i++) {
            arr[i].setText(list.get(i));
        }
    }

    private List<String> pickRandomChar() {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < 15; i++) {
            list.add(getRandomElement(unUsedChar, 3));
        }
        return list;
    }

    private void fillCharachters(char[] arr) {
        int counter = 0;
        for(char i = 97; i <= 122; i++) {
            arr[counter++] = i;
            arr[counter++] = (char)(i - 32);
        }
    }

    private void allViewsInTXT() {
        TextView tv = findViewById(R.id.fingerView);
        ImageView iv = findViewById(R.id.finger);
        shapes[0] = iv;
        txt[0] = tv;
        tv = findViewById(R.id.heartView);
        iv = findViewById(R.id.heart);
        shapes[1] = iv;
        txt[1] = tv;
        tv = findViewById(R.id.bagView);
        iv = findViewById(R.id.bag);
        shapes[2] = iv;
        txt[2] = tv;
        tv = findViewById(R.id.appleView);
        iv = findViewById(R.id.apple);
        shapes[3] = iv;
        txt[3] = tv;
        tv = findViewById(R.id.airplaneView);
        iv = findViewById(R.id.airplane);
        shapes[4] = iv;
        txt[4] = tv;

        tv = findViewById(R.id.smileView);
        iv = findViewById(R.id.smile);
        shapes[5] = iv;
        txt[5] = tv;
        tv = findViewById(R.id.roseView);
        iv = findViewById(R.id.rose);
        shapes[6] = iv;
        txt[6] = tv;
        tv = findViewById(R.id.carView);
        iv = findViewById(R.id.car);
        shapes[7] = iv;
        txt[7] = tv;
        tv = findViewById(R.id.moonView);
        iv = findViewById(R.id.moon);
        shapes[8] = iv;
        txt[8] = tv;
        tv = findViewById(R.id.cameraView);
        iv = findViewById(R.id.camera);
        shapes[9] = iv;
        txt[9] = tv;

        tv = findViewById(R.id.treeView);
        iv = findViewById(R.id.tree);
        shapes[10] = iv;
        txt[10] = tv;
        tv = findViewById(R.id.cloudView);
        iv = findViewById(R.id.cloud);
        shapes[11] = iv;
        txt[11] = tv;
        tv = findViewById(R.id.mosqueView);
        iv = findViewById(R.id.mosque);
        shapes[12] = iv;
        txt[12] = tv;
        tv = findViewById(R.id.fireView);
        iv = findViewById(R.id.fire);
        shapes[13] = iv;
        txt[13] = tv;
        tv = findViewById(R.id.coffeeView);
        iv = findViewById(R.id.coffee);
        shapes[14] = iv;
        txt[14] = tv;
    }

    private List<String> charToList(char[] arr) {
        List<String> newList = new ArrayList<>();
        for(int i = 0; i < arr.length; i++) {
            newList.add(String.valueOf(arr[i]));
        }
        System.out.println();
        return newList;
    }

    private String getRandomElement(List<String> list, int totalItems) {
        Random rand = new Random();

        // create a temporary list for storing
        // selected element
        String str = "";
        List<String> newList = new ArrayList<>();
        for (int i = 0; i < totalItems; i++) {

            // take a raundom index between 0 to size
            // of given List
            System.out.println(list.size());
            if(list.size() <= 0) break;
            int randomIndex = rand.nextInt(list.size());

            // add element in temporary list
            str += list.get(randomIndex);
            usedChar += list.get(randomIndex);
            // Remove selected element from orginal list
            list.remove(randomIndex);
        }
        return str;
    }
}
