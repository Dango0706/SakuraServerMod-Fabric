package me.tuanzi.draw;

public enum DrawRarity {
    NORMAL(10,1),
    GREAT(5,1),
    RARE(3,1),
    UNRARE(2,1),

    ;

    private int weight;
    private int color;

    DrawRarity(int weight, int color) {
        this.weight = weight;
        this.color = color;
    }



    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
