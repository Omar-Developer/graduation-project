package com.example.gpproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

public class Password extends AppCompatActivity {

    private ImageView backbttn, preview, ksu;
    private Button done, submitShape, deleteShape, blue, red, black, yellow;
    private static Button caller;
    private ImageButton finger, heart, bag, apple, smile, rose, car, moon, tree, cloud, mosque, fire, eye, eyeoff;
    private ImageButton rotateRight, rotateLeft;

    private String name = "fin", color = "bla", password = "";
    private int index = 0, counter = 0;
    private int[] degree = {0, 90, 180, 270};

    private static final int MIN_SHAPES = 4;
    private static final int MAX_SHAPES = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        initialize(); //map each component into its correct id.
        setClr(getResources().getColor(R.color.black)); //set the color of all icons to black.

        ksu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RelativeLayout l = findViewById(R.id.tip);
                final View help, help11, help22, help33;
                Button next = findViewById(R.id.next0);
                Button next1 = findViewById(R.id.next1);
                Button next2 = findViewById(R.id.next2);
                Button next3 = findViewById(R.id.next3);
                help = findViewById(R.id.help);
                help11 = findViewById(R.id.help1);
                help22 = findViewById(R.id.help2);
                help33 = findViewById(R.id.help3);

                l.setVisibility(View.VISIBLE);
                l.bringToFront();
                buttonsState(false);
                ImageButton ib = findViewById(R.id.close);
                ImageButton ib1 = findViewById(R.id.close1);
                ImageButton ib2 = findViewById(R.id.close2);
                ImageButton ib3 = findViewById(R.id.close3);
                ib.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonsState(true);
                        l.setVisibility(View.GONE);
                    }
                });

                ib1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonsState(true);
                        l.setVisibility(View.GONE);
                    }
                });

                ib2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonsState(true);
                        l.setVisibility(View.GONE);
                    }
                });

                ib3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonsState(true);
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
                        help22.setVisibility(View.INVISIBLE);
                        help33.setVisibility(View.VISIBLE);
                    }
                });

                next3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonsState(true);
                        l.setVisibility(View.GONE);
                        help.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        backbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = "";
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
                caller.setBackgroundResource(R.drawable.uncheckedbttn);
                finish();
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

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = "blu";
                int new_color = getResources().getColor(R.color.blue);
                setClr(new_color);
            }
        });

        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = "bla";
                int new_color = getResources().getColor(R.color.black);
                setClr(new_color);
            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color="red";
                int new_color = getResources().getColor(R.color.red);
                setClr(new_color);
            }
        });

        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color="yel";
                int new_color = getResources().getColor(R.color.yellow);
                setClr(new_color);
            }
        });

        rotateRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index == 3)
                    index = 0;
                else
                    index++;
                rotate();
            }
        });

        rotateLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index == 0)
                    index = 3;
                else
                    index--;
                rotate();
            }
        });

        finger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "fin";
                preview.setImageResource(R.drawable.finger);
                preview.setTag(R.drawable.finger);
                preview.setRotation(degree[index]);
//                int new_color = getResources().getColor(R.color.white);
//                preview.setColorFilter(new_color);
                if (color.equals("blu")) {
                    int new_color = getResources().getColor(R.color.blue);
                    preview.setColorFilter(new_color);
                }
                else if (color.equals("red")) {
                    int new_color = getResources().getColor(R.color.red);
                    preview.setColorFilter(new_color);
                }
                else if (color.equals("yel")) {
                    int new_color = getResources().getColor(R.color.yellow);
                    preview.setColorFilter(new_color);
                }
                else {

                }
            }
        });

        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "hea";
                preview.setImageResource(R.drawable.heart);
                preview.setTag(R.drawable.heart);
                preview.setRotation(degree[index]);
//                int new_color = getResources().getColor(R.color.white);
//                preview.setColorFilter(new_color);
                if (color.equals("blu")) {
                    int new_color = getResources().getColor(R.color.blue);
                    preview.setColorFilter(new_color);
                }
                else if (color.equals("red")) {
                    int new_color = getResources().getColor(R.color.red);
                    preview.setColorFilter(new_color);
                }
                else if (color.equals("yel")) {
                    int new_color = getResources().getColor(R.color.yellow);
                    preview.setColorFilter(new_color);
                }
                else {

                }
            }
        });

        bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "bag";
                preview.setImageResource(R.drawable.bag);
                preview.setTag(R.drawable.bag);
                preview.setRotation(degree[index]);
//                int new_color = getResources().getColor(R.color.white);
//                preview.setColorFilter(new_color);
                if (color.equals("blu")) {
                    int new_color = getResources().getColor(R.color.blue);
                    preview.setColorFilter(new_color);
                }
                else if (color.equals("red")) {
                    int new_color = getResources().getColor(R.color.red);
                    preview.setColorFilter(new_color);
                }
                else if (color.equals("yel")) {
                    int new_color = getResources().getColor(R.color.yellow);
                    preview.setColorFilter(new_color);
                }
                else {

                }
            }
        });

        apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "app";
                preview.setImageResource(R.drawable.apple);
                preview.setTag(R.drawable.apple);
                preview.setRotation(degree[index]);
//                int new_color = getResources().getColor(R.color.white);
//                preview.setColorFilter(new_color);
                if (color.equals("blu")) {
                    int new_color = getResources().getColor(R.color.blue);
                    preview.setColorFilter(new_color);
                }
                else if (color.equals("red")) {
                    int new_color = getResources().getColor(R.color.red);
                    preview.setColorFilter(new_color);
                }
                else if (color.equals("yel")) {
                    int new_color = getResources().getColor(R.color.yellow);
                    preview.setColorFilter(new_color);
                }
                else {

                }
            }
        });

        smile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "smi";
                preview.setImageResource(R.drawable.smile);
                preview.setTag(R.drawable.smile);
                preview.setRotation(degree[index]);
//                int new_color = getResources().getColor(R.color.white);
//                preview.setColorFilter(new_color);
                if (color.equals("blu")) {
                    int new_color = getResources().getColor(R.color.blue);
                    preview.setColorFilter(new_color);
                }
                else if (color.equals("red")) {
                    int new_color = getResources().getColor(R.color.red);
                    preview.setColorFilter(new_color);
                }
                else if (color.equals("yel")) {
                    int new_color = getResources().getColor(R.color.yellow);
                    preview.setColorFilter(new_color);
                }
                else {

                }
            }
        });

        rose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "ros";
                preview.setImageResource(R.drawable.rose);
                preview.setTag(R.drawable.rose);
                preview.setRotation(degree[index]);
//                int new_color = getResources().getColor(R.color.white);
//                preview.setColorFilter(new_color);
                if (color.equals("blu")) {
                    int new_color = getResources().getColor(R.color.blue);
                    preview.setColorFilter(new_color);
                }
                else if (color.equals("red")) {
                    int new_color = getResources().getColor(R.color.red);
                    preview.setColorFilter(new_color);
                }
                else if (color.equals("yel")) {
                    int new_color = getResources().getColor(R.color.yellow);
                    preview.setColorFilter(new_color);
                }
                else {

                }
            }
        });

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "car";
                preview.setImageResource(R.drawable.car);
                preview.setTag(R.drawable.car);
                preview.setRotation(degree[index]);
//                int new_color = getResources().getColor(R.color.white);
//                preview.setColorFilter(new_color);
                if (color.equals("blu")) {
                    int new_color = getResources().getColor(R.color.blue);
                    preview.setColorFilter(new_color);
                }
                else if (color.equals("red")) {
                    int new_color = getResources().getColor(R.color.red);
                    preview.setColorFilter(new_color);
                }
                else if (color.equals("yel")) {
                    int new_color = getResources().getColor(R.color.yellow);
                    preview.setColorFilter(new_color);
                }
                else {

                }
            }
        });

        moon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "moo";
                preview.setImageResource(R.drawable.moon);
                preview.setTag(R.drawable.moon);
                preview.setRotation(degree[index]);
//                int new_color = getResources().getColor(R.color.white);
//                preview.setColorFilter(new_color);
                if (color.equals("blu")) {
                    int new_color = getResources().getColor(R.color.blue);
                    preview.setColorFilter(new_color);
                }
                else if (color.equals("red")) {
                    int new_color = getResources().getColor(R.color.red);
                    preview.setColorFilter(new_color);
                }
                else if (color.equals("yel")) {
                    int new_color = getResources().getColor(R.color.yellow);
                    preview.setColorFilter(new_color);
                }
                else {

                }
            }
        });

        tree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "tre";
                preview.setImageResource(R.drawable.tree);
                preview.setTag(R.drawable.tree);
                preview.setRotation(degree[index]);
//                int new_color = getResources().getColor(R.color.white);
//                preview.setColorFilter(new_color);
                if (color.equals("blu")) {
                    int new_color = getResources().getColor(R.color.blue);
                    preview.setColorFilter(new_color);
                }
                else if (color.equals("red")) {
                    int new_color = getResources().getColor(R.color.red);
                    preview.setColorFilter(new_color);
                }
                else if (color.equals("yel")) {
                    int new_color = getResources().getColor(R.color.yellow);
                    preview.setColorFilter(new_color);
                }
                else {

                }
            }
        });

        cloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "clo";
                preview.setImageResource(R.drawable.cloud);
                preview.setTag(R.drawable.cloud);
                preview.setRotation(degree[index]);
//                int new_color = getResources().getColor(R.color.white);
//                preview.setColorFilter(new_color);
                if (color.equals("blu")) {
                    int new_color = getResources().getColor(R.color.blue);
                    preview.setColorFilter(new_color);
                }
                else if (color.equals("red")) {
                    int new_color = getResources().getColor(R.color.red);
                    preview.setColorFilter(new_color);
                }
                else if (color.equals("yel")) {
                    int new_color = getResources().getColor(R.color.yellow);
                    preview.setColorFilter(new_color);
                }
                else {

                }
            }
        });

        mosque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "mos";
                preview.setImageResource(R.drawable.mosque);
                preview.setTag(R.drawable.mosque);
                preview.setRotation(degree[index]);
//                int new_color = getResources().getColor(R.color.white);
//                preview.setColorFilter(new_color);
                if (color.equals("blu")) {
                    int new_color = getResources().getColor(R.color.blue);
                    preview.setColorFilter(new_color);
                }
                else if (color.equals("red")) {
                    int new_color = getResources().getColor(R.color.red);
                    preview.setColorFilter(new_color);
                }
                else if (color.equals("yel")) {
                    int new_color = getResources().getColor(R.color.yellow);
                    preview.setColorFilter(new_color);
                }
                else {

                }
            }
        });

        fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "fir";
                preview.setImageResource(R.drawable.fire);
                preview.setTag(R.drawable.fire);
                preview.setRotation(degree[index]);
//                int new_color = getResources().getColor(R.color.white);
//                preview.setColorFilter(new_color);
                if (color.equals("blu")) {
                    int new_color = getResources().getColor(R.color.blue);
                    preview.setColorFilter(new_color);
                }
                else if (color.equals("red")) {
                    int new_color = getResources().getColor(R.color.red);
                    preview.setColorFilter(new_color);
                }
                else if (color.equals("yel")) {
                    int new_color = getResources().getColor(R.color.yellow);
                    preview.setColorFilter(new_color);
                }
                else {

                }
            }
        });

        submitShape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < MAX_SHAPES) {
                    if (password == null)
                        password = name + String.format("%03d", degree[index]) + color;
                    else
                        password = password + name + String.format("%03d", degree[index]) + color;

                    if (eyeoff.getVisibility() == View.INVISIBLE) {
                        int new_color;
                        ImageView iv = new ImageView(getApplicationContext());
                        iv.setImageDrawable(preview.getDrawable());
                        iv.setRotation(degree[index]);
                        if (color.equals("blu")) {
                            new_color = getResources().getColor(R.color.blue);
                            iv.setColorFilter(new_color);
                        }
                        else if (color.equals("red")) {
                            new_color = getResources().getColor(R.color.red);
                            iv.setColorFilter(new_color);
                        }
                        else if (color.equals("yel")) {
                            new_color = getResources().getColor(R.color.yellow);
                            iv.setColorFilter(new_color);
                        }
                        else {
//                            new_color = getResources().getColor(R.color.black);
//                            iv.setColorFilter(new_color);
                        }
                        iv.setPadding(5, 0, 5, 0);
                        LinearLayout dots = findViewById(R.id.dots);
                        dots.addView(iv);
                    }
                    else {
                        ImageView dot = new ImageView(getApplicationContext());
                        dot.setImageResource(R.drawable.dot);
                        dot.setPadding(5, 0, 5, 0);
                        LinearLayout dots = findViewById(R.id.dots);
                        dots.addView(dot);
                    }
                    generateRandomShape();
                    counter++;
                }
                else {
                    Toast.makeText(Password.this, "Maximum shapes reached.", Toast.LENGTH_SHORT).show();
                    System.out.println("Error: maximum shapes reached.");
                }
            }
        });

        deleteShape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!password.equals("")) && counter != 0) {
                    password = password.substring(0, password.length()-9);
                    counter--;

                    LinearLayout dots = findViewById(R.id.dots);
                    dots.removeViewAt(counter);
                }
                else {
                    Toast.makeText(Password.this, "Nothing to delete.", Toast.LENGTH_SHORT).show();
                    System.out.println("Error: no shapes to delete.");
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

    private void generateRandomShape() {
        final TypedArray shapes = getResources().obtainTypedArray(R.array.apptour);
        final TypedArray colors = getResources().obtainTypedArray(R.array.clr);

        final Random rand = new Random();
        final int rndInts = rand.nextInt(shapes.length());
        final int resIDs = shapes.getResourceId(rndInts, 0);
        preview.setImageResource(resIDs);
        name = getResources().getResourceEntryName(resIDs).substring(0, 3);


        final int rndIntc = rand.nextInt(colors.length());
        final int resIDc = colors.getColor(rndIntc, 0);
        setClr(resIDc);
        if(resIDc == Color.parseColor("#000000")) {
            color = "bla";
        }
        else if (resIDc == Color.parseColor("#0000FF")) {
            color = "blu";
        }
        else if (resIDc == Color.parseColor("#FF0000")) {
            color = "red";
        }
        else {
            color = "yel";
        }

        index = rand.nextInt(4);
        rotate();
    }

    private void rotate() {
        finger.setRotation(degree[index]);
        heart.setRotation(degree[index]);
        bag.setRotation(degree[index]);
        apple.setRotation(degree[index]);
        smile.setRotation(degree[index]);
        rose.setRotation(degree[index]);
        car.setRotation(degree[index]);
        moon.setRotation(degree[index]);
        tree.setRotation(degree[index]);
        cloud.setRotation(degree[index]);
        mosque.setRotation(degree[index]);
        fire.setRotation(degree[index]);
        preview.setRotation(degree[index]);
    }

    private void setClr(int new_color) {
        finger.setColorFilter(new_color);
        heart.setColorFilter(new_color);
        bag.setColorFilter(new_color);
        apple.setColorFilter(new_color);
        smile.setColorFilter(new_color);
        rose.setColorFilter(new_color);
        car.setColorFilter(new_color);
        moon.setColorFilter(new_color);
        tree.setColorFilter(new_color);
        cloud.setColorFilter(new_color);
        mosque.setColorFilter(new_color);
        fire.setColorFilter(new_color);
        preview.setColorFilter(new_color);
    }

    private void showShape() {
        LinearLayout dots = findViewById(R.id.dots);
        dots.removeAllViews();
        if ((!password.equals("")) && counter != 0) {
            String modPass = password;
            for(int i = 0; i < counter; i++) {
                ImageView iv = new ImageView(getApplicationContext());
                String tempName = modPass.substring(0,3);
                String tempRot = modPass.substring(3, 6);
                String tempClr = modPass.substring(6, 9);

                iv.setImageResource(getShape(tempName));
                iv.setRotation(Integer.valueOf(tempRot.replaceFirst("^0+(?!$)", "")));
                iv.setColorFilter(getClr(tempClr));
                iv.setPadding(5, 0, 5, 0);
                dots.addView(iv);
                modPass = modPass.substring(9);

            }
        }
    }

    private void hideShapes() {
        LinearLayout dots = findViewById(R.id.dots);
        dots.removeAllViews();
        if ((!password.equals("")) && counter != 0) {
            for(int i = 0; i < counter; i++) {
                ImageView dot = new ImageView(getApplicationContext());
                dot.setImageResource(R.drawable.dot);
                dot.setPadding(5, 0, 5, 0);
                dots.addView(dot);
            }
        }
    }

    private int getClr(String str) {
        if(str.equals("blu"))
            return Color.parseColor("#0000FF");
        else if (str.equals("bla"))
            return Color.parseColor("#000000");
        else if (str.equals("red"))
            return Color.parseColor("#FF0000");
        else
            return Color.parseColor("#FFB642");
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

    private void buttonsState(boolean b) {
        backbttn.setEnabled(b);
        done.setEnabled(b);
        submitShape.setEnabled(b);
        deleteShape.setEnabled(b);
        ksu.setEnabled(b);

        blue.setEnabled(b);
        red.setEnabled(b);
        black.setEnabled(b);
        yellow.setEnabled(b);

        finger.setEnabled(b);
        heart.setEnabled(b);
        bag.setEnabled(b);
        apple.setEnabled(b);
        smile.setEnabled(b);
        rose.setEnabled(b);
        car.setEnabled(b);
        moon.setEnabled(b);
        tree.setEnabled(b);
        cloud.setEnabled(b);
        mosque.setEnabled(b);
        fire.setEnabled(b);
        eye.setEnabled(b);
        eyeoff.setEnabled(b);
        rotateRight.setEnabled(b);
        rotateLeft.setEnabled(b);
    }

    public void setCaller(Button btn) {
        caller = btn;
    }

    private void initialize() {
        backbttn = findViewById(R.id.back);
        ksu = findViewById(R.id.ksuLogo);
        preview = findViewById(R.id.preview);
        done = findViewById(R.id.done);
        submitShape = findViewById(R.id.submitShape);
        deleteShape = findViewById(R.id.deleteShape);

        blue = findViewById(R.id.bluebutton);
        black = findViewById(R.id.blackbutton);
        red = findViewById(R.id.redbutton);
        yellow = findViewById(R.id.yellowbutton);

        rotateLeft = findViewById(R.id.rotateLeft);
        rotateRight = findViewById(R.id.rotateRight);

        finger = findViewById(R.id.finger);
        heart = findViewById(R.id.heart);
        bag = findViewById(R.id.bag);
        apple = findViewById(R.id.apple);
        smile = findViewById(R.id.smile);
        rose = findViewById(R.id.rose);
        car = findViewById(R.id.car);
        moon = findViewById(R.id.moon);
        tree = findViewById(R.id.tree);
        cloud = findViewById(R.id.cloud);
        mosque = findViewById(R.id.mosque);
        fire = findViewById(R.id.fire);
        eye = findViewById(R.id.eye);
        eyeoff = findViewById(R.id.eye_off);
    }
}
