package com.example.sudoko;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Random;

import static android.view.View.VISIBLE;


public class MainActivity extends AppCompatActivity {
    int row=0;
    int col=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TableLayout table2;
        table2=(TableLayout)findViewById(R.id.tableLayout2);

        // 스도쿠 버튼(9*9) 생성
        TableLayout table;
        table=(TableLayout)findViewById(R.id.tableLayout);
        TableRow tablerow[] = new TableRow[9];
        for(int i=0;i<9;i++) {
            tablerow[i] = new TableRow(this);
            table.addView(tablerow[i]);
        }

        final Button[][] button = new Button[9][9];
        int b[][]= new int[9][9];
        for(int i=0;i<9;i++) {
            for (int j = 0; j < 9; j++) {
                int c=0;
                Random r=new Random();
                b[i][j]=r.nextInt(9)+1;
                for(int k=0;k<j;k++){
                    if(b[i][j]==b[i][k]) {
                        c=1;
                        j--;
                        break;
                    }
                }
                if(c==1)
                    continue;

                button[i][j] = new Button(this);
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.0f);
                layoutParams.setMargins(5, 5, 5, 5);
                button[i][j].setLayoutParams(layoutParams);
                tablerow[i].addView(button[i][j]);
                button[i][j].setText(Integer.toString(b[i][j]));

                final Button dd= button[i][j];
                dd.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        table2.setBackgroundResource(android.R.color.background_light);

                        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                            dd.setBackgroundColor(Color.TRANSPARENT);
                        } else if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                            dd.setBackgroundColor(Color.LTGRAY);
                        }

                        return false;
                    }
                });

                final int finalI = i;
                final int finalJ = j;
                button[i][j].setOnClickListener(new View.OnClickListener() { public void onClick(View view) {
                    row= finalI;
                    col= finalJ;
                    table2.setVisibility(VISIBLE); }
                });

            }

        }
        // 숫자 변경판 생
        TableRow tablerow2[] = new TableRow[4];
        final Button button2[][]=new Button[4][3];
        for(int i=0;i<4;i++){
            tablerow2[i]=new TableRow(this);
            table2.addView(tablerow2[i]);
        }
        for(int q=0;q<4;q++){
            for(int w=0;w<3;w++){
                button2[q][w] = new Button(this);
                TableRow.LayoutParams layoutParams2 = new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.0f);
                layoutParams2.setMargins(0, 0, 0, 0);
                button2[q][w].setLayoutParams(layoutParams2);
                tablerow2[q].addView(button2[q][w]);
                if(q<3)
                    button2[q][w].setText(Integer.toString(q*3+w+1));
                final int finalQ = q;
                final int finalW = w;
                button2[q][w].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        button[row][col].setText(Integer.toString(finalQ *3+ finalW +1));
                        table2.setVisibility(View.GONE);
                    }
                });
            }
        }
        button2[3][0].setText("삭제");

    }

}
