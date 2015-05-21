package com.example.sari.bookloggerv3;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity {
//menginisiasi arraylist yang akan digunakan untuk menyimpan daftar judul buku

    //mendeklarasikan arrayadapter
    Button inputButton;
    ListView listOfBook;
    EditText inputJudulBuku, inputPengarangBuku, inputJmlHlmBuku;

    ArrayAdapter<String> adapter;

    ListBookAdapter listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputJudulBuku = (EditText) findViewById(R.id.inputJudul);
        inputPengarangBuku = (EditText) findViewById(R.id.inputPengarang);
        inputJmlHlmBuku = (EditText) findViewById(R.id.inputHalaman);
        inputButton = (Button) findViewById(R.id.btnSimpan);

        //listOfBook.setAdapter(adapter);
        listItem = new ListBookAdapter();
        ListView listItemView = (ListView) findViewById(R.id.listBuku);
        listItemView.setAdapter(listItem);


        listItemView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

                listViewBook chapter = listItem.getListViewBook(arg2);

                showDeleteDialog(chapter);

            }
        });

        Button tambah = (Button) findViewById(R.id.btnSimpan);
        tambah.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String judulBaru, pengarangBaru, halamanBaru;
        TextView inputJudul = (TextView) findViewById(R.id.inputJudul);
        TextView inputPengarang = (TextView) findViewById(R.id.inputPengarang);
        TextView inputHalaman = (TextView) findViewById(R.id.inputHalaman);
        switch (v.getId()) {
            case R.id.btnSimpan:
                judulBaru = inputJudul.getText().toString();
                pengarangBaru = inputPengarang.getText().toString();
                halamanBaru = inputHalaman.getText().toString();

                listViewBook item = new listViewBook();
                item.JudulBuku = judulBaru;
                item.PengarangBuku = pengarangBaru;
                item.JmlHlmBuku = halamanBaru;
                ListOfViewBook.add(item);

                listItem = new ListBookAdapter();
                ListView listItemView = (ListView) findViewById(R.id.listBuku);
                listItemView.setAdapter(listItem);

                break;
        }
    }

    public class listViewBook {
        String JudulBuku;
        String PengarangBuku;
        String JmlHlmBuku;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
