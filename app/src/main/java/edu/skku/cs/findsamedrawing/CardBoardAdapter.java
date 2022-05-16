package edu.skku.cs.findsamedrawing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import edu.skku.cs.findsamedrawing.Model.Card;

public class CardBoardAdapter extends BaseAdapter {
    ArrayList<Card> cardInfo = new ArrayList<>();
    GameViewModel viewModel;
    Context context;

    CardBoardAdapter(GameViewModel viewModel){
        this.viewModel = viewModel;
    }

    @Override
    public int getCount() {
        return cardInfo.size();
    }

    @Override
    public Object getItem(int i) {
        return cardInfo.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        context = viewGroup.getContext();
        if(view==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_card,viewGroup,false);
        }
        return view;
    }
}
