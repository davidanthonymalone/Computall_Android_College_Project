package dd.app.computall.splash;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import dd.app.computall.activities.Login;

public class Sync extends AsyncTask<Void, Integer, String> {
    Context context;
    ProgressDialog progressDialog;

    Sync(Context context){
        this.context = context;
    }
    @Override
    protected String doInBackground(Void... params) {
        int i = 0;
        synchronized (this)
        {
            while(i <10){
                try {
                    wait(1500);
                    i++;
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Loading finished";
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Loading");
        progressDialog.setMax(10);
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();

    }

    @Override
    protected void onPostExecute(String avoid) {

    }

    @Override
    protected void onProgressUpdate(Integer... vsoid){
        int progress = vsoid[0];
        progressDialog.setProgress(progress);
}



}