package edu.skku.cs.findsamedrawing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import edu.skku.cs.findsamedrawing.databinding.ActivityGameBinding;

public class GameActivity extends AppCompatActivity {

    ActivityGameBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_game);
        binding.executePendingBindings();
        setCardBoard();
    }
    private void setCardBoard(){
        CardBoardAdapter adapter = new CardBoardAdapter();
        binding.gvCardBoard.setAdapter(adapter);
    }

}