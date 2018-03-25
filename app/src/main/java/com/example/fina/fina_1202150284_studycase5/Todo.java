package com.example.fina.fina_1202150284_studycase5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Todo extends AppCompatActivity {
    //mendeklarasikan variabel
    EditText td, des, prior;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        //mengakses id yang ada pada layout
         td = findViewById(R.id.todo);
         des = findViewById(R.id.desc);
         prior = findViewById(R.id.prio);
         //inisiasi database
         db = new Database(this);
    }

    //method saat tombol back diklik
    @Override
    public void onBackPressed() {
        //membuat intent baru
        startActivity(new Intent(Todo.this, Awal.class));
        this.finish(); //mengakhiri intent
    }
    //method saat tombol tambah to do diklik
    public void tambah(View view) {
        //jika semua data terisi
        if (db.inputdata(new itemTodo(td.getText().toString(), des.getText().toString(), prior.getText().toString()))){
            //akan menampilkan toast
            Toast.makeText(this, "Todo ditambahkan", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }else {
            //apabila tidak semua data diisi maka akan ditampilkan toast dibawah
            Toast.makeText(this, "Todo gagal ditambahkan", Toast.LENGTH_SHORT).show();
            //set semua edit text menjadi null
            td.setText(null);
            des.setText(null);
            prior.setText(null);
        }
    }
}