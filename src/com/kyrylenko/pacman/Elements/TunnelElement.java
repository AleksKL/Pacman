package com.kyrylenko.pacman.Elements;


public class TunnelElement extends BaseElement{
 private boolean wasEaten;
        public TunnelElement() {
        wasEaten = false;
            charforShow = 'o';

    }
    public void EatO(){
        wasEaten = true;
        charforShow = ' ';

    }

    public boolean isEaten() {
        return wasEaten;
    }
}

