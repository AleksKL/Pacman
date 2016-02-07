package com.kyrylenko.pacman;


public class PointXY {
    private  int x;
    private  int y;

    public PointXY(int x, int y) {
        this.x = x;
        this.y = y;
}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public PointXY moveUP(){
        return new PointXY(x-1,y);
    }
    public PointXY moveDOWN(){
        return new PointXY(x+1,y);
    }
    public PointXY moveRIGHT(){
        return new PointXY(x,y+1);
    }
    public PointXY moveLEFT(){
        return new PointXY(x,y-1);
    }

    public double getDistance(PointXY pointXY) {
        double distanceX = pointXY.getX() - x;
        double distanceY = pointXY.getY() - y;
        return Math.sqrt(distanceX * distanceX + distanceY * distanceY);

    }
}
