package edu.skku.cs.findsamedrawing;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.transition.Visibility;

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
        int[] colors = {Color.parseColor("#FF6125"),Color.parseColor("#FFFF54"),Color.parseColor("#9FCE63"),Color.parseColor("#EF85F9"),Color.parseColor("#F5C242")};
        if(view==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_card,viewGroup,false);

        }
        ImageButton button = view.findViewById(R.id.ib_card);
        if(cardInfo.get(i).getState()==1) {
            Drawable d;
            if (cardInfo.get(i).getType() == 0) {
                d = ContextCompat.getDrawable(context, R.drawable.ic_close);

            } else {
                d = ContextCompat.getDrawable(context, R.drawable.ic_glass);
            }
            d.setTint(colors[cardInfo.get(i).getColor()]);
            button.setBackground(d);
        }
        else{
            button.setBackgroundColor(Color.parseColor("#00ABAB"));
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.cardClicked(i);
            }
        });

        return view;
    }
    public void setCardInfo(ArrayList<Card> cards) {
        cardInfo = cards;
        Log.d("card changed", Integer.toString(cardInfo.size()));
        notifyDataSetChanged();
    }
}
