package edu.skku.cs.findsamedrawing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;

import java.util.ArrayList;

import edu.skku.cs.findsamedrawing.GameViewModel;
import edu.skku.cs.findsamedrawing.Model.Card;
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
        binding.setViewModel(viewModel);
        setCardBoard();
        setChronometer();
    }
    public void GameInitButtonClicked(View v){
        binding.btnInitGame.setEnabled(false);
        viewModel.gameStartButton();
    }
    private void setCardBoard(){
        CardBoardAdapter adapter = new CardBoardAdapter(viewModel);
        binding.gvCardBoard.setAdapter(adapter);
        viewModel.cards.observe(this, new Observer<ArrayList<Card>>() {
            @Override
            public void onChanged(ArrayList<Card> cards) {
                adapter.setCardInfo(cards);
            }
        });
    }

    private void setChronometer(){
        binding.cmClock.setFormat("Time: %s");
        viewModel.stop.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Log.d("Game Status",aBoolean.toString());
                if(aBoolean.booleanValue()==true){
                    if(viewModel.open==10){
                        binding.btnInitGame.setEnabled(true);
                    }
                    binding.cmClock.stop();

                }
                else{
                    binding.cmClock.setBase(SystemClock.elapsedRealtime());
                    binding.cmClock.start();
                }
            }
        });
    }

}