package dd.app.computall.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import dd.app.computall.activities.Login;
import dd.app.computall.choose.Choose;
import dd.app.computall.R;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Sync sync = new Sync(SplashScreen.this);
        sync.execute();

        Thread sleepThread = new Thread(){
            public void run(){
                try{
                    //sleeps for 3 seconds
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    //after it sleeps for 3 seconds it opens up the choose class
                    Intent intent = new Intent(SplashScreen.this, Login.class);
                    startActivity(intent);
                }
            }
        };
        sleepThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}