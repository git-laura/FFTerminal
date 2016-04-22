package ff.findyourfriend.asyncTask;

import android.os.AsyncTask;

import ff.findyourfriend.interfaces.UpdaterListener;
import ff.findyourfriend.model.Coordenate;

/**
 * Created by laura on 09/04/2016.
 */
public class ReadCoordAsyncTask extends AsyncTask<String, Coordenate, Boolean> {
    UpdaterListener mListener;
    Coordenate coordenate = new Coordenate(40.279841, -3.787929);
    public ReadCoordAsyncTask(UpdaterListener mCallBackListener){
        mListener = mCallBackListener;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        while(true) {
            coordenate.setLatitude(coordenate.getLatitude() + 0.1);
            publishProgress(coordenate);
            try {
                synchronized (this) {
                    wait(1000);
                }
            } catch (InterruptedException ex) {
            }
        }
    }

    @Override
    protected void onProgressUpdate(Coordenate... coordenates) {
        super.onProgressUpdate(coordenates);
        mListener.updateCoordenate(coordenates[0]);
    }
}
