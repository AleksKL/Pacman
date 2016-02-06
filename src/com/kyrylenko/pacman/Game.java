package com.kyrylenko.pacman;


import com.kyrylenko.pacman.Elements.BaseElement;
import com.kyrylenko.pacman.Elements.PacmanElement;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class Game {

    BaseElement[][] levelArray;
    private PacmanElement pac = new PacmanElement();
    int pacPositionX;
    int pacPositionY;

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
    }

    public void print() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                levelArray[i][j].show();
                System.out.print(" ");

            }
            System.out.println();
        }
    }

    public void run() {
        //Создаем объект "наблюдатель за клавиатурой" и стартуем его.
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();


        while (true) {
            //"наблюдатель" содержит события о нажатии клавиш?
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();
                //Если равно символу 'q' - выйти из игры.
                if (event.getKeyChar() == 'q') return;

                //Если "стрелка влево" - сдвинуть фигурку влево
                if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                }

                //Если "стрелка вправо" - сдвинуть фигурку вправо
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                }

                //Если "стрелка вверх" - сдвинуть фигурку вверх
                else if (event.getKeyCode() == KeyEvent.VK_UP) {
                }

                //Если "стрелка вниз" - сдвинуть фигурку вниз
                else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                }

            }


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

    public static void main(String[] args) throws IOException {

        Game game = new Game();

        game.print();
    }
}
