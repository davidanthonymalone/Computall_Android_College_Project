package dd.app.computall.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import dd.app.computall.R;
import dd.app.computall.choose.Choose;

/**
 * Created by User on 09/04/2016.
 */
public class Login extends AppCompatActivity
{

    private Button usrreg;
    private Button usrlog;
    private boolean BackPressed;
    private SharedPreferences loginsettings;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        usrreg = (Button) findViewById(R.id.registerhere);
        usrlog = (Button) findViewById(R.id.btnLogin);


        loginsettings = getSharedPreferences("loginPrefs", 0);
        if (loginsettings.getBoolean("loggedin", false))
            startHomeScreen();
        setContentView(R.layout.activity_login);

    }

    public void register(View v)
    {
        startActivity(new Intent(this, Register.class));
    }

    public void startHomeScreen()
    {
        Intent intent = new Intent(Login.this, Choose.class);
        Login.this.startActivity(intent);
    }


    @Override
    public void onBackPressed()
    {

        super.onBackPressed();

        BackPressed = true;

    }


    public void Login(View v)
    {

        CharSequence username = ((TextView) findViewById(R.id.loginUsername)).getText();
        CharSequence password = ((TextView) findViewById(R.id.loginPassword)).getText();

        String validUsername = loginsettings.getString("username", "");
        String validPassword = loginsettings.getString("password", "");

        if (username.length() <= 0 || password.length() <= 0)
        {

            Toast.makeText(this, "You must enter an email and password", Toast.LENGTH_SHORT).show();

        }
        else if (!username.toString().matches(validUsername) || !password.toString().matches(validPassword))
        {
            Toast.makeText(this, "Unable to validate your email & password", Toast.LENGTH_SHORT).show();
        }
        else if (!BackPressed)
        {
            SharedPreferences.Editor editor = loginsettings.edit();
            editor.putBoolean("loggedin", true);
            editor.commit();

            startHomeScreen();
            this.finish();
        }
    }


    public void onExitPressed(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this); //Creates AlertDialog when Item is pressed
        builder.setTitle("Exit Application");
        builder.setMessage("Are you sure you want to exit the app.");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()


                {
                //When positive button (yes) is clicked run code in this method
                    public void onClick(DialogInterface dialog, int choose)
                    {
                        finish();
                    }

                }
        );


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int choose)
                    {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
}





