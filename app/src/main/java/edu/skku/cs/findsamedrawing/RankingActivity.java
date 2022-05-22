package edu.skku.cs.findsamedrawing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;

import edu.skku.cs.findsamedrawing.Model.Record;
import edu.skku.cs.findsamedrawing.databinding.ActivityRankingBinding;

public class RankingActivity extends AppCompatActivity {

    ActivityRankingBinding binding;
    RankingViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_ranking);
        binding.executePendingBindings();
        viewModel = new ViewModelProvider(this).get(RankingViewModel.class);
        setRankingView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getRecords();
    }

    private void setRankingView(){
        RankingAdapter adapter = new RankingAdapter(viewModel);
        binding.rvRank.setAdapter(adapter);
        binding.rvRank.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        viewModel.records.observe(this, new Observer<ArrayList<Record>>() {
            @Override
            public void onChanged(ArrayList<Record> records) {
                adapter.setRecords(records);
            }
        });
    }
}