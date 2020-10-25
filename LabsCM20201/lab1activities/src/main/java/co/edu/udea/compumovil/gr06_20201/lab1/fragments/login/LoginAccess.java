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

public class LoginAccess extends AppCompatActivity {
    EditText username, password;
    Button btnLogin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_access);
        this.setTitle(R.string.sign_in);

        username = (EditText)findViewById(R.id.username1);
        password = (EditText)findViewById(R.id.password1);
        btnLogin = (Button)findViewById(R.id.btnsignin1);
        DB = new DBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals("")){
                    Toast.makeText(LoginAccess.this, "Please enter All fields", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkuserpass = DB.checkusernamepassword(user,pass);
                    if(checkuserpass==true){
                        Toast.makeText(LoginAccess.this, "Sign in Succesfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginAccess.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
                    
            }
        });
    }
}