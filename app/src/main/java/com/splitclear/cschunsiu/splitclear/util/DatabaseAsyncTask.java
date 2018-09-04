package com.splitclear.cschunsiu.splitclear.util;

import android.os.AsyncTask;

public abstract class DatabaseAsyncTask extends AsyncTask<Void,Void, Object> {
    public AsyncResponse respond = null;

    public abstract void processFinish(Object output);

    public interface AsyncResponse{
        void processFinish(Object output);
    }

    public DatabaseAsyncTask(AsyncResponse respond){
        this.respond = respond;
    }
    @Override
    protected Object doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPostExecute(Object result) {
        respond.processFinish(result);
    }
}
