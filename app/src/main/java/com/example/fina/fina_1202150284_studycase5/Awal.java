package com.example.fina.fina_1202150284_studycase5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class Awal extends AppCompatActivity {
    //mendeklarasikan variabel
    Database db;
    RecyclerView rv;
    adapter adapter;
    ArrayList<itemTodo> listitem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awal); //memberikan akses recyclerview yang ada di layout
        rv = findViewById(R.id.rv_list); //membuat arraylist baru
        listitem = new ArrayList<>(); //membuat database baru
        db = new Database(this);//memanggil method getAllItem
        db.getAllItem(listitem); //inisialisasi shared preferences
        SharedPreferences shp = this.getApplicationContext().getSharedPreferences("shp", 0);

        int warna = shp.getInt("background", R.color.putih); //membuat adapter baru
        adapter = new adapter(this, listitem, warna); //menghindari perubahan yang tidak diperlukan saat menambahkan/menghapus item pada recycler view
        rv.setHasFixedSize(true); //menentukan layoutnya linear
        rv.setLayoutManager(new LinearLayoutManager(this)); //inisiasi adapter untuk recycler view
        rv.setAdapter(adapter); //menjalankan method geser
        geser();
    }

    //membuat method untuk menghapus item pada todolist
    public void geser() {
        //membuat touch helper baru untuk recycler view
        ItemTouchHelper.SimpleCallback sc = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int posisi = viewHolder.getAdapterPosition();
            itemTodo now = adapter.getItem(posisi);
            //apabila todolist digeser ke kanan atau kiri maka data akan terhapus
            if (direction==ItemTouchHelper.LEFT||direction==ItemTouchHelper.RIGHT){
                if (db.hapusdata(now.getTodo())){
                    adapter.removeitem(posisi);
                }
            }
            }
        };
        //menentukan itemtouchhelper untuk recycler view
        ItemTouchHelper helper =  new ItemTouchHelper(sc);
        helper.attachToRecyclerView(rv);
    }
    //saat menu pada activity dibuat
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.main, menu);
       return true;
    }
    //method ketika item dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //mendapatkan id dari item
        int id = item.getItemId();
        //jika id yang dipilih setting
        if (id==R.id.setting){
            startActivity(new Intent(Awal.this, Setting.class)); //membuat intent baru dari awal ke setting

            finish();//mengakhiri activity setelah intent dijalankan
        }
        return true;
    }
    //method ketika tombol + diklik
    public void masuk(View view) {
        //membuat intent baru
        startActivity(new Intent(Awal.this, Todo.class));
        //mengakhiri activity setelah intent dijalankan
        finish();
    }
}
