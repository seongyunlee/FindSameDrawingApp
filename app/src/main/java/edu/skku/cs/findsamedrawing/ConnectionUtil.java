package edu.skku.cs.findsamedrawing;

import android.util.Log;

import com.google.gson.Gson;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class ConnectionUtil {
    final String URL = "https://p3o0i3ipc0.execute-api.ap-northeast-2.amazonaws.com/dev/";
    OkHttpClient client = new OkHttpClient();


    private void sendQuery(Request req, Callback callback){
        client.newCall(req).enqueue(callback);
    }
    public void addRecord(String username,String time,Callback callback){
        Record model = new Record(username,time);
        Gson gson = new Gson();
        String json = gson.toJson(model,Record.class);
        Request req = new Request.Builder().url(URL+"addrecord").post(RequestBody.create(json, MediaType.parse("application/json"))).build();
        sendQuery(req,callback);
    }
    public void getRecord(Callback callback){
        Request req = new Request.Builder().url(URL+"getrecord").build();
        sendQuery(req,callback);
    }


    class Record{
        String name;
        String time;

        public Record(String name, String time) {
            this.name = name;
            this.time = time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

}
