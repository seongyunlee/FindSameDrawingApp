package edu.skku.cs.findsamedrawing;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import edu.skku.cs.findsamedrawing.Model.Record;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RankingViewModel extends ViewModel {

    MutableLiveData<ArrayList<Record>> _records = new MutableLiveData<>();
    LiveData<ArrayList<Record>> records = _records;
    ConnectionUtil connectionUtil = new ConnectionUtil();
    Gson gson= new GsonBuilder().create();
    public RankingViewModel() {
        super();
        getRecords();

    }


    public void set_records(ArrayList<Record> _records) {
        this._records.postValue( _records);
    }

    public void getRecords(){
        connectionUtil.getRecord(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("Connection Fail",e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String json = response.body().string();
                try {
                    JSONObject raw = new JSONObject(json);
                    JSONArray array = raw.getJSONArray("records");
                    if(array!=null){
                        ArrayList<Record> new_record = new ArrayList<>();
                        for(int i=0; i<array.length();i++){
                            Record record = gson.fromJson(array.get(i).toString(),Record.class);
                            new_record.add(record);
                        }
                        Log.d("Response",new_record.get(0).name);
                        set_records(new_record);
                    }
                }
                catch (JSONException e){
                    Log.e("JSON Error",e.toString());
                }
            }
        });
    }
}
