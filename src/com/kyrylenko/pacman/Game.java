package com.kyrylenko.pacman;


import com.kyrylenko.pacman.Elements.BaseElement;
import com.kyrylenko.pacman.Elements.GhostElement;
import com.kyrylenko.pacman.Elements.PacmanElement;
import com.kyrylenko.pacman.Elements.TunnelElement;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class Game {

    private BaseElement[][] levelArray;
    private PacmanElement pac = new PacmanElement();
    private GhostElement ghost1 = new GhostElement();
    //private GhostElement ghost2 = new GhostElement();
    private int pacPositionX;
    private int pacPositionY;
    private int ghost1PositionX;
    private int ghost1PositionY;
   // private int ghost2PositionX;
    //private int ghost2PositionY;
    private int score = 0;
    public Game() {
        Level level = null;
        try {
            level = new Level("E:/1.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        levelArray = level.getLevelArray();
        pacPositionX = 0;
        pacPositionY = 1;
        levelArray[pacPositionX][pacPositionY] = pac;
        ghost1PositionX = 0;
        ghost1PositionY = 8;
        levelArray[ghost1PositionX][ghost1PositionY] = ghost1;
       // ghost2PositionX = 5;
      //  ghost2PositionY = 8;
       // levelArray[ghost2PositionX][ghost2PositionY] = ghost2;
    }

    public void print() {
        System.out.println("Score "+score);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                levelArray[i][j].show();
                System.out.print(" ");

            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public void run() {
        //Создаем объект "наблюдатель за клавиатурой" и стартуем его.
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();


        while (true) {
            int newX = pacPositionX;
            int newY = pacPositionY;

            //"наблюдатель" содержит события о нажатии клавиш?
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();
                //Если равно символу 'q' - выйти из игры.
                if (event.getKeyChar() == 'q') return;

                //Если "стрелка влево" - сдвинуть фигурку влево
                if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                    newY--;
                }

                //Если "стрелка вправо" - сдвинуть фигурку вправо
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                    newY++;
                }

                //Если "стрелка вверх" - сдвинуть фигурку вверх
                else if (event.getKeyCode() == KeyEvent.VK_UP) {
                    newX--;
                }

                //Если "стрелка вниз" - сдвинуть фигурку вниз
                else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                    newX++;
                }

            }

            movePac(newX, newY); // двигаем пакмена
            print();        //отображаем текущее состояние игры
            sleep();        //пауза между ходами
        }


    }

    public void sleep() {
        try {

            Thread.sleep(10);
        } catch (InterruptedException e) {
        }
    }
 public void movePac(int newX, int newY){

     if (newX>=0 && newY>=0 && newX<10 && newY<10) {

         BaseElement elem = levelArray[newX][newY];
          if (elem instanceof TunnelElement) {
              TunnelElement tunel = (TunnelElement) elem;
              if (!tunel.isEaten()){
                  tunel.EatO();
                  score +=10;
              }
             levelArray[newX][newY] = pac;
             levelArray[pacPositionX][pacPositionY] = elem;
             pacPositionX = newX;
             pacPositionY = newY;
         }
     }


 }

    public void moveGhost(){
        double currentDistance = calculateDistance(ghost1PositionX,ghost1PositionY);


    }

    public double calculateDistance(int newGhostX, int newGhostY){
        double distanceX = pacPositionX - newGhostX;
        double distanceY = pacPositionY - newGhostY;
        return Math.sqrt(distanceX*distanceX+distanceY*distanceY);

    }
    public static void main(String[] args) throws IOException {

        Game game = new Game();

        game.run();
    }
}
