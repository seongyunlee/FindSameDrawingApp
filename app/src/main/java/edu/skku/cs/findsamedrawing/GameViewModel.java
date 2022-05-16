package edu.skku.cs.findsamedrawing;

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
    MutableLiveData<Integer> _miss = new MutableLiveData<>();
    LiveData<Integer> miss = _miss;

    GameViewModel(){
        InitializeCards();
        _stop.setValue(true);
        _miss.setValue(0);
    }
    private void InitializeCards(){
        ArrayList<Card> new_cards = new ArrayList<>();
        for(int i=0;i<20;i++){
            new_cards.add(new Card(i/5,i%5,0));
        }
        Collections.shuffle(new_cards);
    }

}
