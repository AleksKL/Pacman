package com.kyrylenko.pacman.Elements;


public class PacmanElement extends BaseElement {
private boolean alive = true;
   public PacmanElement(){
        charforShow = 'C';
    }

    public boolean isAlive() {
        return alive;
    }
    public void die(){
        alive=false;
        charforShow = 'X';
    }
}
