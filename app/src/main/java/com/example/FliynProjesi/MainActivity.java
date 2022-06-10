package com.example.FliynProjesi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username,password,repassword;
    Button signin,signup;
    DBHelper DB;
    public Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GlobalClass globalveriabel=(GlobalClass)getApplicationContext();//this thing
        username= (EditText) findViewById(R.id.Username);
        password= (EditText) findViewById(R.id.Password);
        repassword= (EditText) findViewById(R.id.RePassword);
        signup =(Button) findViewById(R.id.button);
        signin=(Button) findViewById(R.id.button2);
        DB= new DBHelper(this);//SSTAİMPORTANTREA

        Boolean checkuser=DB.chechusername("Anonim Bağışçı");
        if(checkuser==false){
            Boolean insert = DB.insertdata("Anonim Bağışçı","15766320017",0);
            //Boolean insert = DB.insertdata("Anonim Bağışçı",0017,para);
            if(insert==true){
                Toast.makeText(MainActivity.this,"Welcome Star light",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this,"Another Time",Toast.LENGTH_SHORT).show();
            }
        }

        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                globalveriabel.SetUserName(username.getText().toString().trim());//and this thing
                String user= username.getText().toString();
                String pass=password.getText().toString();
                String repass=repassword.getText().toString();
                int para = 0;

                if(user.equals("")|| pass.equals("")||repass.equals(""))
                    Toast.makeText(MainActivity.this,"Bütün alanları doldurunuz",Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser=DB.chechusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertdata(user,pass,para);
                            //Boolean insert = DB.insertdata("Anonim Bağışçı",0017,para);
                            if(insert==true){
                                Toast.makeText(MainActivity.this,"Başarı ile kayıt oldunuz",Toast.LENGTH_SHORT).show();
                                Intent intent= new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this,"Kayıt olunamadı,Girdiğiniz bilgiler Uyuşmuyor",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(MainActivity.this,"Kullanıcı zaten kayıtlı",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MainActivity.this,"Şifreler uyuşmuyor",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
            });


    }
}