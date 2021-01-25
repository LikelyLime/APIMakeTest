package com.sihun.apimaketest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import VO.BookVO;
import parse.Parser;

public class MainActivity extends AppCompatActivity {

    public static EditText search;
    Button btn_search;
    ListView myListView;
    Parser parser;
    ArrayList<BookVO> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = findViewById(R.id.search);
        btn_search = findViewById(R.id.btn_search);
        myListView = findViewById(R.id.myListView);

        parser = new Parser();

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list = new ArrayList<>();
                new NaverAsync().execute();

            }
        });
    }

    class NaverAsync extends AsyncTask<Void, Void, ArrayList<BookVO>>{

        @Override
        protected ArrayList<BookVO> doInBackground(Void... voids) {

            return parser.connectNaver(list);
        }

        @Override
        protected void onPostExecute(ArrayList<BookVO> bookVOS) {
            super.onPostExecute(list);
        }
    }
}