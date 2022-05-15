package edu.skku.cs.findsamedrawing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import edu.skku.cs.findsamedrawing.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.executePendingBindings();
        skipLogin();
    }
    public void onGameStartBtnClicked(View v){
        goToGameActivity();
    }
    public void onGoToRankingBtnClicked(View v){
        goToRankingActivity();
    }
    public void onGotoInfoBtnClicked(View v){
        goToHowtoPlayActivity();
    }
    private void skipLogin(){
        Intent intent = new Intent(MainActivity.this,GameActivity.class);
        startActivity(intent);
    }
    private void goToGameActivity(){
        String username = binding.etUsername.getText().toString();
        if(username.length()==0){
            Toast.makeText(this,"Input your name",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(MainActivity.this,GameActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }
    private void goToRankingActivity(){
        Intent intent = new Intent(MainActivity.this,RankingActivity.class);
        startActivity(intent);
    }
    private void goToHowtoPlayActivity(){
        Intent intent = new Intent(MainActivity.this,HowtoPlayActivity.class);
        startActivity(intent);
    }
}