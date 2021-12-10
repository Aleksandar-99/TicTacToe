package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] dugmici = new Button[3][3];
    private boolean Igrac1NaRedu = true;
    private int brojRunde;
    private int Igrac1Poeni;
    private int Igrac2Poeni;
    private TextView textViewIgrac1;
    private TextView textViewIgrac2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewIgrac1 = findViewById(R.id.text_view_p1);
        textViewIgrac2 = findViewById(R.id.text_view_p2);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                dugmici[i][j] = findViewById(resID);
                dugmici[i][j].setOnClickListener(this);
            }
        }
        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetujIgru();
            }
        });
    }
    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (Igrac1NaRedu) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }
        brojRunde++;
        if (Provera()) {
            if (Igrac1NaRedu) {
                Igrac1Pobedjuje();
            } else {
                Igrac2pobedjuje();
            }
        } else if (brojRunde== 9) {
            nereseno();
        } else {
            Igrac1NaRedu = !Igrac1NaRedu;
        }
    }
    private boolean Provera() {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = dugmici[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }
        return false;
    }
    private void Igrac1Pobedjuje() {
        Igrac1Poeni++;
        Toast.makeText(this, "Igrac 1 Pobedjuje", Toast.LENGTH_SHORT).show();
        Poeni();
        ResetujIgru();
    }
    private void Igrac2pobedjuje() {
        Igrac2Poeni++;
        Toast.makeText(this, "Igrac 2 pobedjuje!", Toast.LENGTH_SHORT).show();
        Poeni();
        ResetujIgru();
    }
    private void nereseno() {
        Toast.makeText(this, "Nereseno!", Toast.LENGTH_SHORT).show();
        ResetujIgru();
    }
    private void ResetujIgru() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                dugmici[i][j].setText("");
            }
            brojRunde = 0;
            Igrac1NaRedu = true;
        }
    }
    private void Poeni() {
        textViewIgrac1.setText("Igrac 1: " + Igrac1Poeni);
        textViewIgrac2.setText("Igrac 2: " + Igrac2Poeni);
    }


}