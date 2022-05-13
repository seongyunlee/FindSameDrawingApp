package edu.skku.cs.findsamedrawing.Model;

public class Card {
    int color;
    int type;

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

    public Card(int color, int type) {
        this.color = color;
        this.type = type;
    }
}
