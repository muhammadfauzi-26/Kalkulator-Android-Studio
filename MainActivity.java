package com.example.ui_kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


// IMPORT BARU :
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
public class MainActivity extends AppCompatActivity {

    private int[] tombolNumeric = {R.id.nol, R.id.satu, R.id.dua, R.id.tiga, R.id.empat, R.id.lima,
            R.id.enam, R.id.tujuh, R.id.delapan, R.id.sembilan};

    private int[] tombolOperator = {R.id.kali, R.id.tambah, R.id.kurang, R.id.bagi};

    private TextView tampilaninput;
    private TextView tampilanhasil;
    private boolean angkaterakhir;
    private boolean kaloerror;
    private boolean setelahtitik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tampilaninput = (TextView) findViewById(R.id.tampilaninput);
        this.tampilanhasil = (TextView) findViewById(R.id.tampilanhasil);

        setNumericPadaSaatDiKlik();
        setOperatorPadaSaatDiKlik();
    }
    private void setNumericPadaSaatDiKlik() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button tombol = (Button) v;
                if (kaloerror) {
                    tampilaninput.setText(tombol.getText());
                    kaloerror = false;
                } else {
                    tampilaninput.append(tombol.getText());
                }
                angkaterakhir = true;
            }
        };
        for (int id : tombolNumeric) {
            findViewById(id).setOnClickListener(listener);
        }
    }
    private void setOperatorPadaSaatDiKlik() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (angkaterakhir && !kaloerror) {
                    Button tombol = (Button) v;
                    tampilaninput.append(tombol.getText());
                    angkaterakhir = false;
                    setelahtitik = false;
                }
            }
        };
        for (int id : tombolOperator) {
            findViewById(id).setOnClickListener(listener);
        }
        findViewById(R.id.titik).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (angkaterakhir && !kaloerror && !setelahtitik) {
                    tampilaninput.append(".");
                    angkaterakhir = false;
                    setelahtitik = true;
                }
            }
        });
        findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampilaninput.setText(" ");
                tampilanhasil.setText(" ");
                angkaterakhir = false;
                kaloerror = false;
                setelahtitik = false;
            }
        });
        findViewById(R.id.samadengan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEqual();
            }
        });
    }
    private void onEqual() {
        if (angkaterakhir && !kaloerror) {
            String txt = tampilaninput.getText().toString();
            Expression expression = new ExpressionBuilder(txt).build();
            try {
                double result = expression.evaluate();
                tampilanhasil.setText(Double.toString(result));
                setelahtitik = true;
            } catch (ArithmeticException ex) {
                tampilanhasil.setText("Error mas");
                kaloerror = true;
                angkaterakhir = false;
            }
        }
    }


}
