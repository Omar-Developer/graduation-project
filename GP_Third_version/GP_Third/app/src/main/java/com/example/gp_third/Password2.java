package com.example.gp_third;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Password2 extends AppCompatActivity {

    private ImageView backbttn;
    private ImageView[] shapes;
    private TextView[] txt;
    private int[] degree = {0, 90, 180, 270};
    private int[] colores = {-16776961, 16777216, -65536};
    private static ImageButton callerP;
    private char[] characters;

    private List<String> unUsedChar;
    private String usedChar = "";
    private EditText passField;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password2);

        backbttn = findViewById(R.id.back);
        backbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Password p = new Password();
                p.dots.removeViewAt(counter);
                finish();
            }
        });

        passField = findViewById(R.id.passField);
        passField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count != 0) {
                    if ((start - before >= 0) && (!usedChar.contains(String.valueOf(s.charAt(start - before))))) {
                        Toast.makeText(Password2.this, "This character does not belong to any shape.", Toast.LENGTH_LONG).show();
                        System.out.println("String not chosen");
                        passField.setText("");
                    } else {
                        for (int i = 0; i < txt.length; i++) {
                            String str = String.valueOf(txt[i].getText());
                            //                        System.out.println("Checking if "+str+" has "+s.charAt(start-before));
                            if (start - before >= 0 && str.contains(String.valueOf(s.charAt(start - before)))) {
                                //                            System.out.println("Correct.");
                                Password p = new Password();
                                int position = i % 4;
                                if(i < 4) {
                                    p.color = "blu";
                                    if(position == 0) {
                                        p.degree = "000";
                                    } else if (position == 1) {
                                        p.degree = "090";
                                    } else if (position == 2) {
                                        p.degree = "180";
                                    } else {
                                        p.degree = "270";
                                    }
                                } else if (i < 8 && i >= 4) {
                                    p.color = "bla";
                                    if(position == 0) {
                                        p.degree = "000";
                                    } else if (position == 1) {
                                        p.degree = "090";
                                    } else if (position == 2) {
                                        p.degree = "180";
                                    } else {
                                        p.degree = "270";
                                    }
                                } else {
                                    p.color = "red";
                                    if(position == 0) {
                                        p.degree = "000";
                                    } else if (position == 1) {
                                        p.degree = "090";
                                    } else if (position == 2) {
                                        p.degree = "180";
                                    } else {
                                        p.degree = "270";
                                    }
                                }
                                p.setPassword();
                                finish();
                                break;
                            }
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });

        characters = new char[52];
        fillCharachters(characters);
        txt = new TextView[12];
        shapes = new ImageView[12];
        allViewsInTXT();
        setAllShapes(callerP);
        unUsedChar = charToList(characters);
        fillTextView(txt);

    }

    private void fillTextView(TextView[] arr) {
        List<String> list = pickRandomChar();
        for(int i = 0; i < arr.length; i++) {
            arr[i].setText(list.get(i));
        }
    }

    private List<String> pickRandomChar() {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < 12; i++) {
            list.add(getRandomElement(unUsedChar, 3));
        }
        return list;
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
//            System.out.println(list.size());
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

    private void setAllShapes(ImageButton iv) {
        int index = 0;
        String str = "";
        String s = iv.getResources().getResourceEntryName(iv.getId());
        int resID = getShape(s);
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 4; j++) {
                shapes[index].setImageResource(resID);
                shapes[index].setRotation(degree[j]);
                shapes[index].setColorFilter(colores[i]);
//                System.out.println("Shape["+index+"], Color["+colores[i]+"], degree["+degree[j]);
                index++;
            }
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

        tv = findViewById(R.id.smileView);
        iv = findViewById(R.id.smile);
        shapes[4] = iv;
        txt[4] = tv;
        tv = findViewById(R.id.roseView);
        iv = findViewById(R.id.rose);
        shapes[5] = iv;
        txt[5] = tv;
        tv = findViewById(R.id.carView);
        iv = findViewById(R.id.car);
        shapes[6] = iv;
        txt[6] = tv;
        tv = findViewById(R.id.moonView);
        iv = findViewById(R.id.moon);
        shapes[7] = iv;
        txt[7] = tv;

        tv = findViewById(R.id.treeView);
        iv = findViewById(R.id.tree);
        shapes[8] = iv;
        txt[8] = tv;
        tv = findViewById(R.id.cloudView);
        iv = findViewById(R.id.cloud);
        shapes[9] = iv;
        txt[9] = tv;
        tv = findViewById(R.id.mosqueView);
        iv = findViewById(R.id.mosque);
        shapes[10] = iv;
        txt[10] = tv;
        tv = findViewById(R.id.fireView);
        iv = findViewById(R.id.fire);
        shapes[11] = iv;
        txt[11] = tv;

    }

    private void fillCharachters(char[] arr) {
        int counter = 0;
        for(char i = 97; i <= 122; i++) {
            arr[counter++] = i;
            arr[counter++] = (char)(i - 32);
        }
    }

    private List<String> charToList(char[] arr) {
        List<String> newList = new ArrayList<>();
        for(int i = 0; i < arr.length; i++) {
            newList.add(String.valueOf(arr[i]));
//            System.out.print(newList.get(i)+", ");
        }
//        System.out.println();
        return newList;
    }

    private int getShape(String str) {
        final TypedArray shapes = getResources().obtainTypedArray(R.array.apptour);
        for(int i = 0; i < shapes.length(); i++) {
            int resID = shapes.getResourceId(i, 0);
            if(getResources().getResourceEntryName(resID).equals(str))
                return resID;
        }
        return 0;
    }

    public void setCallerP(ImageButton btn) {
        callerP = btn;
    }
}
