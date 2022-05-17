package edu.skku.cs.findsamedrawing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;

import edu.skku.cs.findsamedrawing.databinding.ActivityGameBinding;

public class GameActivity extends AppCompatActivity {

    ActivityGameBinding binding;
    GameViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_game);
        binding.executePendingBindings();
        viewModel = new ViewModelProvider(this).get(GameViewModel.class);
        setCardBoard();
        setChronometer();
    }
    private void setCardBoard(){
        CardBoardAdapter adapter = new CardBoardAdapter(viewModel);
        binding.gvCardBoard.setAdapter(adapter);
    }
    private void setChronometer(){
        binding.cmClock.setFormat("Time: %s");
        viewModel.stop.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean==true){
                    binding.cmClock.stop();
                }
                else{
                    binding.cmClock.start();
                }
            }
        });
    }

}