package com.plate.root.spread;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements Mylistner {
    TextView tv;
    EditText searchTxt;
    Button btn1,btn2,btn3,btn4,btn5;
    ListView listView;
    ProgressBar progressBar;
    String[] listItems;
    ArrayAdapter adapter;
    Mylistner myl;
    SingleTon singleTon;
    Boolean page1,page2,page3,page4;
    ArrayList<String> ft;
    Boolean first;
    int currentPage=0;

    private SimpleAdapter sa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar =(ProgressBar)findViewById(R.id.pr);
        progressBar.setVisibility(View.GONE);
        page1=false;
        page2=false;
        page3=false;
        page4=false;
        ft = new ArrayList<>();
        first =false;
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage=1;

                readData(MainActivity.this,R.raw.t0,"1");
                progressBar.setVisibility(View.VISIBLE);
                btn1.setVisibility(View.INVISIBLE);
                btn3.setVisibility(View.VISIBLE);
                btn2.setVisibility(View.VISIBLE);
                btn4.setVisibility(View.VISIBLE);



            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage=2;
                readData(MainActivity.this,R.raw.t1,"2");
                progressBar.setVisibility(View.VISIBLE);
                btn2.setVisibility(View.INVISIBLE);
                btn3.setVisibility(View.VISIBLE);
                btn1.setVisibility(View.VISIBLE);
                btn4.setVisibility(View.VISIBLE);



            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage=3;
                readData(MainActivity.this,R.raw.t2,"3");
                progressBar.setVisibility(View.VISIBLE);
                btn3.setVisibility(View.INVISIBLE);
                btn4.setVisibility(View.VISIBLE);
                btn2.setVisibility(View.VISIBLE);
                btn1.setVisibility(View.VISIBLE);



            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage=4;
                readData(MainActivity.this,R.raw.t3,"4");
                progressBar.setVisibility(View.VISIBLE);
                btn4.setVisibility(View.INVISIBLE);
                btn3.setVisibility(View.VISIBLE);
                btn2.setVisibility(View.VISIBLE);
                btn1.setVisibility(View.VISIBLE);


            }
        });


        singleTon = SingleTon.getInstance(getApplicationContext());
        //tv = (TextView) findViewById(R.id.tView);
        listView=(ListView)findViewById(R.id.lv);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //final String item = singleTon.getList().get(position);
                final String item1 = singleTon.getList(String.valueOf(currentPage)).get(0);


                final String isearch= (String) parent.getItemAtPosition(position);

//                final String item1 = (String) parent.getItemAtPosition(0);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View convertView = (View) inflater.inflate(R.layout.summary, null);
                alertDialog.setView(convertView);

                String realbody=null;
                ListView lv = (ListView) convertView.findViewById(R.id.alertlv);
                String[] header = item1.toUpperCase().split(",");
                String[] body =isearch.split(" ");
                for (String mbody:singleTon.getList(String.valueOf(currentPage))){
                    String[] allbody = mbody.split(",");
                    if (allbody[0].contains(body[0])){
                        realbody =mbody;
                    }
                }
                alertDialog.setTitle(body[0]);

                String[] thebody = realbody.split(",");


                ArrayList<String > ar = new ArrayList<>();
                int lent=0;
                if (thebody.length>header.length){
                    lent=header.length;
                }else {
                 lent =thebody.length;
                }

//                for (int x =0;x<lent;x++){
//                    System.out.println("Tunde issue"+x);
//
//                    String ball =header[x]+":\n"+thebody[x];
//                    ar.add(ball);
//
//                }
                HashMap<String,String> item;
                ArrayList<HashMap<String,String>> listalert = new ArrayList<HashMap<String,String>>();

                for(int i=0;i<lent;i++) {
                    item = new HashMap<String, String>();
                    item.put("line1", header[i]);
                    item.put("line2", thebody[i]);
                    listalert.add(item);
                }

                sa = new SimpleAdapter(MainActivity.this, listalert,
                        R.layout.simple,
                        new String[] { "line1","line2" },
                        new int[] {R.id.line_a, R.id.line_b});
                lv.setAdapter(sa);



//                ArrayAdapter<String> adapters = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,ar);
//                lv.setAdapter(adapters);
                alertDialog.show();

                //Toast.makeText(getApplicationContext(),item1,Toast.LENGTH_SHORT).show();
            }
        });
        searchTxt =(EditText)findViewById(R.id.search);
        searchTxt.setVisibility(View.GONE);
        searchTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String textas =searchTxt.getText().toString().toLowerCase();
                filter(textas);

            }
        });
        myl =this;

    }
    public void readData(Context context,final int r,final String pg){
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Boolean pageStatud =false;
                if (pg.equals("1")){
                    pageStatud=page1;
                }
                if (pg.equals("2")){
                    pageStatud=page2;
                }
                if (pg.equals("3")){
                    pageStatud=page3;
                }
                if (pg.equals("4")){
                    pageStatud=page4;
                }
                if (pageStatud){

                    System.out.println("Tunde loaded already  ......");

                }else{
                    System.out.println("Tunde loading file ......");

                     InputStream is =getResources().openRawResource(r);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                    try {
                        String line, alline;
                        alline = "";
                        int count = 0;
                        while ((line = reader.readLine()) != null) {
                            alline = alline + line + "#";
                            count++;
                        }
                        listItems = alline.split("#");
                        System.out.println("Tunde split down");
                        singleTon.setList(listItems, pg);
                        if (pg.equals("1")){
                            page1=true;
                        }else if(pg.equals("2")){
                            page2=true;
                        }else if(pg.equals("3")){
                            page3=true;
                        }else if(pg.equals("4")){
                            page4=true;
                        }
                    }  catch (IOException ie){
                        ie.printStackTrace();
                    }

                }
                if (first) {
                    ft.clear();
                }
                ft.addAll(singleTon.getfList(pg));
                System.out.println("Tunde singleton down");
                for (String gh:ft){
                    System.out.println("Tunde,,,,, "+gh);
                }

                    runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        searchTxt.setVisibility(View.VISIBLE);
                        if (!first) {
                            System.out.println("Tunde falsen "+first);
                            adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, ft);
                            listView.setAdapter(adapter);
                            first=true;
                        }else {

                            System.out.println("Tunde true down "+first);
                            //adapter.clear();
                            //adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, ft);


                           adapter.notifyDataSetChanged();

                        }
                        progressBar.setVisibility(View.GONE);
                        searchTxt.setText("");
                    }
                });
            }
        };


        Thread thread = new Thread(runnable);
        thread.start();

    }

    @Override
    public  void callback(String r){
        if (first) {
          //  adapter.notifyDataSetChanged();
        }
    }

    public void filter(String text){
        String ctext = text.toLowerCase(Locale.getDefault());
        ArrayList<String> lt =singleTon.getfList(String.valueOf(currentPage));

        if (ctext.length()==0){
            System.out.println("Tunde..................... ");
           // adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, lt);
            adapter.notifyDataSetChanged();

        }else{
            ft.clear();
            for (String name: lt){
                String[] names = name.toLowerCase().split(",");
                //String all = names[4]+" "+names[5]+" "+names[6];

                if (name.toLowerCase().contains(ctext)){
                    ft.add(name);
                }
            }
            System.out.println("Tunde all size  "+ft.size());
            //adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, ft);
            adapter.notifyDataSetChanged();

        }

    }
}
