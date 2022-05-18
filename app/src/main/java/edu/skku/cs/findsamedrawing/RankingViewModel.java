package edu.skku.cs.findsamedrawing;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import edu.skku.cs.findsamedrawing.Model.Record;

public class RankingViewModel extends ViewModel {

    MutableLiveData<ArrayList<Record>> _records = new MutableLiveData<>();
    LiveData<ArrayList<Record>> records = _records;
    ConnectionUtil connectionUtil = new ConnectionUtil();

    public RankingViewModel() {
        super();
    }
}
