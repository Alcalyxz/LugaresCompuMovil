package co.edu.udea.compumovil.gr06_20201.lab1.fragments.login;

import androidx.appcompat.app.AppCompatActivity;

import co.edu.udea.compumovil.gr06_20201.lab1.MainActivity;
import co.edu.udea.compumovil.gr06_20201.lab1.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText username,password,email;
    Button signup, signin;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle(R.string.sign_up);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        signin = (Button)findViewById(R.id.btnsignin);
        signup = (Button)findViewById(R.id.btnsignup);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String correo = email.getText().toString();

                if(user.equals("")||pass.equals("")||correo.equals("")){
                    Toast.makeText(login.this, "Please Enter All Fields", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkuser = DB.checkusername(user);
                    if(checkuser==false){
                        Boolean insert = DB.insertData(user,pass,correo);
                        if(insert==true){
                            Toast.makeText(login.this, "Registered suiccesfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(login.this, "Registration FAILED", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(login.this, "User Already Exists! please Sign in", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override

            //ESTA VUELTA ME VA A SERVIR FULL AHORA PILAS PS MK
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginAccess.class);
                startActivity(intent);
            }
        });
    }
}