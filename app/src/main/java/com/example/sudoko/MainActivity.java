package com.example.sudoko;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TableLayout table;
        table=(TableLayout)findViewById(R.id.tableLayout);
        TableRow tablerow[] = new TableRow[9];
        for(int i=0;i<9;i++) {
            tablerow[i] = new TableRow(this);
            table.addView(tablerow[i]);
        }

        Button[][] button = new Button[9][9];
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
                    /*if(b[i][j]==b[k][j]){
                        c=1;
                        j--;
                        break;
                    }*/
                }
                if(c==1)
                    continue;

                button[i][j] = new Button(this);
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.0f);
                layoutParams.setMargins(0, 0, 0, 0);
                button[i][j].setLayoutParams(layoutParams);
                tablerow[i].addView(button[i][j]);
                button[i][j].setText(Integer.toString(b[i][j]));
            }
        }


    }

}
