package com.example.diman_calc;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.io.Serializable;

abstract class Meth
{
    //returned index of i and g true-column//
    public static int Fun(String s, boolean check) {
        String a;
        int o = 0;
        if (check) {
            for (int k = 0; k < s.length(); k++) {
                if (s.charAt(k) == 't') {
                    o = k;
                    break;
                }
            }
            a = s.substring(o + 1, s.length());
            return Integer.parseInt(a);
        } else {
            for (int k = 0; k < s.length(); k++) {
                if (s.charAt(k) == 'e') {
                    o = k;
                    break;
                }
            }
            a = s.substring(0, o);
            return Integer.parseInt(a);
        }
    }
}
class ButOper {
    Button[] masBut;

    ButOper(boolean check,Context cont,LinearLayout lay) {
    if(check) {
    masBut=new Button[5];
        for(int i=0;i<5;i++) {
            masBut[i]=new Button(cont);
            masBut[i].setText("Ch"+i);
            lay.addView(masBut[i]);
        }
    }
    else {
        masBut=new Button[3];
        for(int i=0;i<3;i++) {
            masBut[i]=new Button(cont);
            masBut[i].setText("Ch"+i);
            lay.addView(masBut[i]);
        }
    }
    }
}
class TableObj implements View.OnFocusChangeListener {
    Context context;
    TableLayout TableLay;
    TableRow TableRowMas[];
    EditText EditMas[][];
    private int i, j;

    public TableObj(int i, int j, Context context) //construct
    {
        this.context = context;
        TableLay = new TableLayout(this.context);
        this.i = i;
        this.j = j;
        CreateTableRowObj();
        createEdit();
    }

    public void CreateTableRowObj()//add Row in Table
    {
        TableRowMas = new TableRow[i];

        for (int k = 0; k < i; k++) {
            TableRowMas[k] = new TableRow(context);
            TableLay.addView(TableRowMas[k]);
        }


    }

    public void createEdit() {
        EditMas = new EditText[i][j];
        for (int k = 0; k < i; k++) {

            for (int l = 0; l < j; l++) {
                EditMas[k][l] = new EditText(context);
                //EditMas[k][l].setTextSize(2,0);
                EditMas[k][l].setTag(k + "edit" + l);
                EditMas[k][l].setOnFocusChangeListener(this);
                EditMas[k][l].setHint("a[" + k + "][" + l + "]");
                EditMas[k][l].setHintTextColor(Color.GRAY);
                EditMas[k][l].setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

                TableRowMas[k].addView(EditMas[k][l]);
            }

        }

    }



    @Override
    public void onFocusChange(View v, boolean hasFocus) {
if(hasFocus)
{
    //new Rows
    if(Meth.Fun(String.valueOf(v.getTag()),false)==i-1)
    {
        TableRow[] TableRowMas2=new TableRow[i+1];
        EditText[][] EditMas2=new EditText[i+1][j];
        for(int k=0;k<i;k++)
        {
            TableRowMas2[k]=TableRowMas[k];
            for(int l=0;l<j;l++)
            {
                EditMas2[k][l]=EditMas[k][l];
            }
        }
        TableRowMas=TableRowMas2;
        EditMas=EditMas2;
        TableRowMas[i]=new TableRow(context);
        TableLay.addView(TableRowMas[i]);
        for(int l=0;l<j;l++)
        {
            EditMas[i][l]=new EditText(context);
            EditMas[i][l].setTag(i+"edit"+l);
            EditMas[i][l].setOnFocusChangeListener(this);
            EditMas[i][l].setHint("a[" + i + "][" + l + "]");
            EditMas[i][l].setHintTextColor(Color.GRAY);
            EditMas[i][l].setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            TableRowMas[i].addView(EditMas[i][l]);
        }
        i++;
    }
    //new columns
    if(Meth.Fun(String.valueOf(v.getTag()),true)==j-1)
    {
     EditText[][] EditMas2=new EditText[i][j+1];
     for(int k=0;k<i;k++)
     {
         for(int l=0;l<j;l++)
         {
             EditMas2[k][l]=EditMas[k][l];
         }
     }
     EditMas=EditMas2;
        for(int k=0;k<i;k++)
        {
            EditMas[k][j]=new EditText(context);
            EditMas[k][j].setOnFocusChangeListener(this);
            EditMas[k][j].setTag(k+"edit"+j);
            EditMas[k][j].setHintTextColor(Color.GRAY);
            EditMas[k][j].setHint("a[" + k + "][" + j + "]");
            EditMas[k][j].setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            TableRowMas[k].addView(EditMas[k][j]);
        }

        j++;
    }

}
    }

}
////////////////////////////////////////////////////////////////////////////////////////
public class MainActivity extends AppCompatActivity {
    LinearLayout linlay;
    TableObj t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linlay = (LinearLayout) findViewById(R.id.LinLay);
        TableObj t1 = new TableObj(2, 2, this);
        linlay.addView(t1.TableLay);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
