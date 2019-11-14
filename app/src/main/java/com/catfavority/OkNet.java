package com.catfavority;

import android.os.AsyncTask;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OkNet {

    private static final OkNet Instance = new OkNet();

    public static OkNet getInstance() {
        return Instance;
    }

    public void GetaDatasync(String url, final AsyncCallback asyncCallback) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                return GetDataHttp(strings[0]);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (!TextUtils.isEmpty(s)) {
                    asyncCallback.onSucceedData(s);
                }

            }
        }.execute(url);
    }

    public String GetDataHttp(String url) {
        HttpURLConnection httpURLConnection = null;
        try {
            URL url1 = new URL(url);
            httpURLConnection = (HttpURLConnection) url1.openConnection();
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                InputStream inputStream = httpURLConnection.getInputStream();


                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));//写入reader
                StringBuilder response = new StringBuilder();
                String line;
                while((line = reader.readLine()) != null){
                    response.append(line);
                }

                return response.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void PostDataAsync(final String url, final String postbody, final AsyncCallback callback) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                return PostDataHttp(url,postbody);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (!TextUtils.isEmpty(s)){
                    callback.onSucceedData(s);
                }else{
                    callback.onFailed(503,"未请求到");
                }
            }
        }.execute(url,postbody);
    }

    private String PostDataHttp(String url, String body) {
        HttpURLConnection httpURLConnection = null;
        try {
            URL url1 = new URL(url);
            httpURLConnection = (HttpURLConnection) url1.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.getOutputStream().write(body.getBytes());

            if (httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
                InputStream inputStream = httpURLConnection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));//写入reader
                StringBuilder response = new StringBuilder();
                String line;
                while((line = reader.readLine()) != null){
                    response.append(line);
                }
                return response.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public interface AsyncCallback {
        void onSucceedData(String result);
        void onFailed(int i, String error);

    }
}
