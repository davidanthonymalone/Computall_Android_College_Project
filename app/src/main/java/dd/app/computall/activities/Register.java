package dd.app.computall.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dd.app.computall.R;
import dd.app.computall.choose.Choose;

/**
 * Created by User on 09/04/2016.
 */
public class Register extends AppCompatActivity {
    private boolean mIsBackButtonPressed;
    private SharedPreferences settings;

        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            settings = getSharedPreferences("loginPrefs", 0);
        }

        public void register(View v){
                CharSequence username = ((TextView) findViewById(R.id.registerUsername)).getText();
                CharSequence password = ((EditText) findViewById(R.id.registerPassword)).getText();
                CharSequence vp = ((EditText) findViewById(R.id.verifypass)).getText();


            if(username.length() <= 0 || password.length() <=0) {
                Toast.makeText(this, "You must enter an email and password", Toast.LENGTH_SHORT).show();
            }
            else if(vp.toString().equals(password.toString())==false)
            {
                Toast.makeText(this, "Password and verify password must be the same", Toast.LENGTH_SHORT).show();

            }
            else if (vp.toString().equals(password.toString())==true)
            {

                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("loggedin", true);
                editor.putString("username", username.toString());
                editor.putString("password", password.toString());
                editor.commit();

                startHomeScreen();
                this.finish();
            }
        }

    public void startHomeScreen() {
        Intent intent = new Intent(Register.this, Choose.class);
        Register.this.startActivity(intent);
    }

}
