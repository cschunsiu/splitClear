package com.splitclear.cschunsiu.splitclear.util;

import android.os.AsyncTask;

public class DatabaseAsyncTask extends AsyncTask<Object,Object,Object> {

    public interface OnTaskCompleted{
        void onTaskCompleted();
    }

    private OnTaskCompleted listener;

    public DatabaseAsyncTask(OnTaskCompleted listener){
        this.listener = listener;
    }

    protected void onPostExecute(Object o){
        // Call the interface method
        if (listener != null)
            listener.onTaskCompleted();
    }

    @Override
    protected Object doInBackground(Object... params) {
        // The sleep() is just to simulate activity and delay

        return null;
    }
}