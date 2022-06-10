package com.example.FliynProjesi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;

public class HomeActivity extends AppCompatActivity {
    TextView kullaniciisim1;
    TextView gösterpara;
    TextView moppar;
    EditText para;
    Button button;
    Button button2;
    DBHelper DB;//kilit dott
    ProgressBar progressBar1;
    CheckBox çek;
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DB= new DBHelper(this); //KİLİT DOT
        para=(EditText) findViewById(R.id.paratxt);
        button=(Button) findViewById(R.id.button4);
        button2=(Button) findViewById(R.id.button6);
        progressBar1=(ProgressBar) findViewById(R.id.progressBar);
        çek=(CheckBox) findViewById(R.id.checkBox);
        //there is
        final GlobalClass globalveriabel=(GlobalClass)getApplicationContext();
        kullaniciisim1=(TextView)findViewById(R.id.kullaniciisim);
        gösterpara=(TextView)findViewById(R.id.showpara);
        moppar=(TextView)findViewById(R.id.şopar);
        kullaniciisim1.setText(globalveriabel.GetUsername().toString());
        //hep bu arada iş var aşşağı doğru
        //String kee= kullaniciisim.toString();//this thing not working
        //String soap=globalveriabel.GetUsername();
        String kişi=globalveriabel.GetUsername();
        Cursor rock= DB.bombaparahesap(kişi);


        Cursor duck= DB.bombaparahesap("Anonim Bağışçı");



        if (rock.moveToFirst() ) {

        gösterpara.setText(rock.getString(rock.getColumnIndex("para")));
            progressBar1.setProgress(Integer.parseInt(gösterpara.getText().toString()));
        }else {
            gösterpara.setText("Data Bulunamadı!?!");
        }
        rock.close();
        //ROCKLEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
        if (duck.moveToFirst() ) {
            moppar.setText(duck.getString(duck.getColumnIndex("para")));
        }else {
            Toast.makeText(HomeActivity.this, "OH NO anonim yok", Toast.LENGTH_SHORT).show();
        }
        rock.close();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (para.getText().length()==0){
                    Toast.makeText(HomeActivity.this, "Bağış Miktarı Girmediniz", Toast.LENGTH_SHORT).show();
                }else {
                    //içeri
                    int money= Integer.parseInt(gösterpara.getText().toString());
                    int gani= Integer.parseInt(moppar.getText().toString());
                    String soap=globalveriabel.GetUsername();
                    int papa= Integer.valueOf(String.valueOf(para.getText()));
                    int TOPx=money+papa;
                    int TOPxA=gani+papa;
                    String Anonim="Anonim Bağışçı";
                    //dıştan
                    if(çek.isChecked()){
                        if(papa>0){
                            Boolean insert =DB.updateData(Anonim,TOPxA);
                            if (insert == true) {
                                Toast.makeText(HomeActivity.this, "Bağış başarılı  isim:"+Anonim+" stabilmoney:"+papa+" textmoney:"+gani+"", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(HomeActivity.this, "Bağış BAŞARISIZ", Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(HomeActivity.this, "Lütfen 0 dan daha büyük bir değer giriniz", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    if(papa>0){
                    Boolean insert =DB.updateData(soap,TOPx);
                    if (insert == true) {
                        Toast.makeText(HomeActivity.this, "Bağış başarılı  isim:"+soap+" stabilmoney:"+papa+" textmoney:"+money+"", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(HomeActivity.this, "Bağış BAŞARISIZ", Toast.LENGTH_SHORT).show();
                    }

                }else {
                        Toast.makeText(HomeActivity.this, "Lütfen 0 dan daha büyük bir değer giriniz", Toast.LENGTH_SHORT).show();
                    }
                    }
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),MainActivity3.class);
                startActivity(intent);
            }
        });
    }
    }
