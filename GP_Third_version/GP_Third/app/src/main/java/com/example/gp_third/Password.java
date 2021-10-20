package com.example.gp_third;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Password extends AppCompatActivity {

    private ImageView backbttn, ksu;
    private Button done;
    private ImageButton coffee, airplane, wifi, camera, finger, heart, bag, apple, smile, rose, car, moon, tree, cloud, mosque, fire, eye, eyeoff, delete;
    private static Button caller;
    public static LinearLayout dots;

    public static String name = "", color = "", degree = "";
    private static String password = "";
    private static int counter = 0;

    private static final int MIN_SHAPES = 4;
    private static final int MAX_SHAPES = 9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        initialize();

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
                caller.setBackgroundResource(R.drawable.uncheckedbttn);
                password = "";
                counter = 0;
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
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
                        password = "";
                        counter = 0;
                    } else {
                        Register2 r = new Register2();
                        if (activity.equals("reg1")) {
                            r.setRegPass(password);
                            password = "";
                            counter = 0;
                        } else if (activity.equals("reg2")) {
                            r.setRegConfPass(password);
                            password = "";
                            counter = 0;
                        }
                    }
                    caller.setBackgroundResource(R.drawable.checkbttn);
                    finish();
                }
            }
        });



        coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "cof";
                Intent intent = new Intent(Password.this, Password2.class);
                Password2 p = new Password2();
                p.setCallerP(coffee);
                setDot();
                startActivity(intent);
            }
        });

        airplane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "air";
                Intent intent = new Intent(Password.this, Password2.class);
                Password2 p = new Password2();
                p.setCallerP(airplane);
                setDot();
                startActivity(intent);
            }
        });

        wifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "wif";
                Intent intent = new Intent(Password.this, Password2.class);
                Password2 p = new Password2();
                p.setCallerP(wifi);
                setDot();
                startActivity(intent);
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "cam";
                Intent intent = new Intent(Password.this, Password2.class);
                Password2 p = new Password2();
                p.setCallerP(camera);
                setDot();
                startActivity(intent);
            }
        });

        finger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "fin";
                Intent intent = new Intent(Password.this, Password2.class);
                Password2 p = new Password2();
                p.setCallerP(finger);
                setDot();
                startActivity(intent);
            }
        });

        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "hea";
                Intent intent = new Intent(Password.this, Password2.class);
                Password2 p = new Password2();
                p.setCallerP(heart);
                setDot();
                startActivity(intent);
            }
        });

        bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "bag";
                Intent intent = new Intent(Password.this, Password2.class);
                Password2 p = new Password2();
                p.setCallerP(bag);
                setDot();
                startActivity(intent);
            }
        });

        apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "app";
                Intent intent = new Intent(Password.this, Password2.class);
                Password2 p = new Password2();
                p.setCallerP(apple);
                setDot();
                startActivity(intent);
            }
        });

        smile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "smi";
                Intent intent = new Intent(Password.this, Password2.class);
                Password2 p = new Password2();
                p.setCallerP(smile);
                setDot();
                startActivity(intent);
            }
        });

        rose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "ros";
                Intent intent = new Intent(Password.this, Password2.class);
                Password2 p = new Password2();
                p.setCallerP(rose);
                setDot();
                startActivity(intent);
            }
        });

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "car";
                Intent intent = new Intent(Password.this, Password2.class);
                Password2 p = new Password2();
                p.setCallerP(car);
                setDot();
                startActivity(intent);
            }
        });

        moon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "moo";
                Intent intent = new Intent(Password.this, Password2.class);
                Password2 p = new Password2();
                p.setCallerP(moon);
                setDot();
                startActivity(intent);
            }
        });

        tree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "tre";
                Intent intent = new Intent(Password.this, Password2.class);
                Password2 p = new Password2();
                p.setCallerP(tree);
                setDot();
                startActivity(intent);
            }
        });

        cloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "clo";
                Intent intent = new Intent(Password.this, Password2.class);
                Password2 p = new Password2();
                p.setCallerP(cloud);
                setDot();
                startActivity(intent);

            }
        });

        mosque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "mos";
                Intent intent = new Intent(Password.this, Password2.class);
                Password2 p = new Password2();
                p.setCallerP(mosque);
                setDot();
                startActivity(intent);
            }
        });

        fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = "fir";
                Intent intent = new Intent(Password.this, Password2.class);
                Password2 p = new Password2();
                p.setCallerP(fire);
                setDot();
                startActivity(intent);
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

    private void showShape() {
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
                iv.setPadding(0, 0, 0, 0);
                dots.addView(iv);
                modPass = modPass.substring(9);
            }
        }
    }

    private void hideShapes() {
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
        if(str.equals("blu")) {
            return Color.parseColor("#0000FF"); //Blue
        } else if (str.equals("bla")) {
            return Color.parseColor("#000000"); //Black
        } else {
            return Color.parseColor("#FF0000"); //Red
        }
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

    public void setPassword() {
        if(password.equals("")) {
            password = name + degree + color;
        } else {
            password += name + degree + color;
        }
        counter++;
        System.out.println(password);
    }
    public void setDot() {
        hideShapes();
        eyeoff.setVisibility(View.VISIBLE);
        eye.setVisibility(View.INVISIBLE);
        ImageView dot = new ImageView(Password.this);
        dot.setImageResource(R.drawable.dot);
        dot.setPadding(5, 0, 5, 0);
        dots.addView(dot);
    }

    private void buttonsState(boolean b) {
        backbttn.setEnabled(b);
        done.setEnabled(b);
        ksu.setEnabled(b);
        delete.setEnabled(b);

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
        camera.setEnabled(b);
        wifi.setEnabled(b);
        coffee.setEnabled(b);
        airplane.setEnabled(b);
    }

    private void initialize() {
        backbttn = findViewById(R.id.back);
        ksu = findViewById(R.id.ksuLogo);
        done = findViewById(R.id.done);
        dots = findViewById(R.id.dots);
        delete = findViewById(R.id.delete);

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
        airplane = findViewById(R.id.airplane);
        coffee = findViewById(R.id.coffee);
        wifi = findViewById(R.id.wifi);
        camera = findViewById(R.id.camera);
    }

}
