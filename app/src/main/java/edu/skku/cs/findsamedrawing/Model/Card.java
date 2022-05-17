package edu.skku.cs.findsamedrawing.Model;

public class Card {
    int color;// 0~4
    int type; //0~1

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    int state;//0 for covered

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Card(int color, int type,int state) {
        this.color = color;
        this.type = type;
        this.state =state;
    }
    public boolean isSameCard(Card card){
        if(color==card.getColor() && type==card.getType()){
            return true;
        }
        else return false;
    }
}
