package com.example.FliynProjesi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText username,password;
    Button buttonlogin;
    Button geridön;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        final GlobalClass globalveriabel=(GlobalClass)getApplicationContext();//this thing
        username = (EditText) findViewById(R.id.Username1);
        password = (EditText) findViewById(R.id.Password1);
        buttonlogin = (Button) findViewById(R.id.button3);
        geridön = (Button) findViewById(R.id.button9);
        DB= new DBHelper(this);

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalveriabel.SetUserName(username.getText().toString().trim());//and this thing
                String user= username.getText().toString();
                String pass= password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(Login.this,"Lütfen bütün alanları doldurun",Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass= DB.chechkusernamepassword(user,pass);
                    if(checkuserpass==true){
                        Toast.makeText(Login.this,"Giriş Başarılı",Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this,"Uyumsuz giriş",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        geridön.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


    }
}