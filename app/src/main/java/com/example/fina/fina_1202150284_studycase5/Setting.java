package com.example.fina.fina_1202150284_studycase5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Setting extends AppCompatActivity {
    //mendeklarasikan variabel
    int wrn;
    TextView color;
    AlertDialog.Builder alert;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //membuat alert dialog baru namanya alert
        alert = new AlertDialog.Builder(this);
        //inisialisasi shared preferences
        SharedPreferences shp = getApplicationContext().getSharedPreferences("shp", 0);
        edit = shp.edit();
        //mengakses textview pada layout
        color = findViewById(R.id.warna);
        //set warna dengan warna yang sudah ditentukan
        wrn = shp.getInt("background", R.color.putih);

        color.setText(getWarna(wrn));
    }
    //method saat pilihan pada menu dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() ==android.R.id.home){
            this.onBackPressed(); //menjalankan method on back pressed
        }
        return true;
    }
    //saat tombol back diklik
    @Override
    public void onBackPressed() {
        //membuat intent baru
        startActivity(new Intent(Setting.this, Awal.class));
        //mengakhiri activity
        finish();
    }
    //mendapatkan string warna yang digunakan untuk mengubah warna
    public String getWarna(int i){
        if (i==R.color.biru){
            return "Biru";
        }else if (i==R.color.merah){
            return "Merah";
        }else if (i==R.color.hijau){
            return "Hijau";
        }else{
            return "Putih";
        }
    }
    //mendapatkan id dari warna yang digunakan
    public int getIntColor(int i){
        if (i==R.color.biru){
            return R.id.btn_blue;
        }else if (i==R.color.merah){
            return R.id.btn_red;
        }else if (i==R.color.hijau){
            return R.id.btn_green;
        }else {
            return R.id.btn_white;
        }
    }

    public void pencet(View view) {
        //mengubah title pada alertnya
        alert.setTitle("Choose Color");
        //membuat view baru
        View v = getLayoutInflater().inflate(R.layout.pilihwarna,null);
        alert.setView(v); //menampilkan view yang telah dibuat

        //mengakses radio grup pada layout
        final RadioGroup rg = v.findViewById(R.id.rg);
        rg.check(getIntColor(wrn));

        //saat menekan OK pada alert dialog
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
              int cek = rg.getCheckedRadioButtonId();
              //mendapatkan id radio button yang dipilih
              switch (cek){
                  case R.id.btn_blue:
                    wrn = R.color.biru;
                  break;
                  case R.id.btn_green:
                      wrn = R.color.hijau;
                      break;
                  case R.id.btn_red:
                      wrn = R.color.merah;
                      break;
                  case R.id.btn_white:
                      wrn = R.color.putih;
                      break;
              }
              //set warnanya menjadi id yang dipilih
              color.setText(getWarna(wrn));
              //menaruh shared preferences
              edit.putInt("background", wrn);
              //commit shared preferences
              edit.commit();
            }
        });
        //saat menekan cancel di alert dialog
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });alert.create().show(); //membuat dan menampilkan alert
    }
}
