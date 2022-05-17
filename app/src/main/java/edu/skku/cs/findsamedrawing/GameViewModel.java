package edu.skku.cs.findsamedrawing;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import edu.skku.cs.findsamedrawing.Model.Card;

public class GameViewModel extends ViewModel {
    MutableLiveData<ArrayList<Card>> _cards= new MutableLiveData<>();
    LiveData<ArrayList<Card>> cards = _cards;
    MutableLiveData<Boolean> _stop = new MutableLiveData<>();
    LiveData<Boolean> stop = _stop;
    int miss;
    int selected;
    int open;
    int readyForFlip;
    public GameViewModel(){
        super();
        _stop.setValue(true);
        miss=0;
        readyForFlip=-1;
        selected=-1;
    }

    private void InitializeCards(){
        ArrayList<Card> new_cards = new ArrayList<>();
        for(int i=0;i<10;i++){
            new_cards.add(new Card(i%5,i/5,0));
            new_cards.add(new Card(i%5,i/5,0));
        }
        //Collections.shuffle(new_cards);
        _cards.setValue(new_cards);
    }
    private void GameStart(){
        _stop.setValue(false);
    }
    public void gameStartButton(){
        GameStart();
        InitializeCards();
    }

    public void cardClicked(int i){
        ArrayList<Card> card_changed = _cards.getValue();
        if(readyForFlip!=-1){
            card_changed.get(selected).setState(0);
            card_changed.get(readyForFlip).setState(0);
            readyForFlip=-1;
            selected=-1;
            _cards.setValue(card_changed);
            return;
        }
        Card fliped = card_changed.get(i);
        if(fliped.getState()==1){
            return;
        }
        if(selected==-1) {
            selected=i;
            fliped.setState(1);
        }
        else{
            fliped.setState(1);
            Card card_selected = card_changed.get(selected);
            if(fliped.isSameCard(card_selected)){
                open++;
                selected=-1;
            }
            else{
                readyForFlip=i;
            }
        }
        card_changed.set(i, fliped);
        _cards.setValue(card_changed);
        checkFinish();

    }
    private void checkFinish(){
        if(open==10){
            _stop.setValue(true);
        }
    }
    public void flipOver(){
        ArrayList<Card> card_changed = _cards.getValue();
        if(readyForFlip!=-1){
            card_changed.get(selected).setState(0);
            card_changed.get(readyForFlip).setState(0);
            readyForFlip=-1;
            selected=-1;
            _cards.setValue(card_changed);
        }
    }


}
