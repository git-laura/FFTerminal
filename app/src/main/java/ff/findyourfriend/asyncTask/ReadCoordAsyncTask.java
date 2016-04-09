package ff.findyourfriend.asyncTask;

import android.os.AsyncTask;

import ff.findyourfriend.interfaces.UpdaterListener;

/**
 * Created by laura on 09/04/2016.
 */
public class ReadCoordAsyncTask extends AsyncTask<String, Double, Boolean> {
    UpdaterListener mListener;
    double coord = 40.300280;
    public ReadCoordAsyncTask(UpdaterListener mCallBackListener){
        mListener = mCallBackListener;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        while(true) {
            coord += 0.1 ;
            publishProgress(coord);
            try {
                synchronized (this) {
                    wait(1000);
                }
            } catch (InterruptedException ex) {
            }
        }
    }

    @Override
    protected void onProgressUpdate(Double... values) {
        super.onProgressUpdate(values);
        mListener.updateCoord(values[0]);
    }
}
