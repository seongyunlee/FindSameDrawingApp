package edu.skku.cs.findsamedrawing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import edu.skku.cs.findsamedrawing.GameViewModel;
import edu.skku.cs.findsamedrawing.Model.Card;
import edu.skku.cs.findsamedrawing.databinding.ActivityGameBinding;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GameActivity extends AppCompatActivity {

    ActivityGameBinding binding;
    GameViewModel viewModel;
    ConnectionUtil connectionUtil = new ConnectionUtil();
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_game);
        binding.executePendingBindings();
        Intent intent = getIntent();
        username = intent.getStringExtra("name");
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
                        Log.d("Result",binding.cmClock.getText().toString().split("Time: ")[1]);
                        sendNewRecord(binding.cmClock.getText().toString().split("Time: ")[1]);
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
    private void sendNewRecord(String time){
        if(username==null){
            username="test";
        }
        connectionUtil.addRecord(username, time, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("Connection failure",e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                GameActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(GameActivity.this,"Send record Successfully",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}