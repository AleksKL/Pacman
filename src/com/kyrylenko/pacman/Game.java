package com.kyrylenko.pacman;


import com.kyrylenko.pacman.Elements.*;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class Game {

    private Level level;
    private PacmanElement pac = new PacmanElement();
    private GhostElement ghost1 = new GhostElement();
    private GhostElement ghost2 = new GhostElement();
    private PointXY pacPosition;
    private PointXY ghost1Position;
    private PointXY ghost2Position;
    private boolean win = false;

    private int score = 0;

    public Game() {
        try {
            level = new Level("E:/1.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        pacPosition = level.getPacPosition();
        ghost1Position = level.getGhost1Position();
        ghost2Position = level.getGhost2Position();
    }

    public void run() {
        //Создаем объект "наблюдатель за клавиатурой" и стартуем его.
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();


        while (pac.isAlive() && !win) {
            PointXY newPosition = pacPosition;

            //"наблюдатель" содержит события о нажатии клавиш?
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();
                //Если равно символу 'q' - выйти из игры.
                if (event.getKeyChar() == 'q') return;

                //Если "стрелка влево" - сдвинуть фигурку влево
                if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                    newPosition = newPosition.moveLEFT();
                }

                //Если "стрелка вправо" - сдвинуть фигурку вправо
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                    newPosition = newPosition.moveRIGHT();
                }

                //Если "стрелка вверх" - сдвинуть фигурку вверх
                else if (event.getKeyCode() == KeyEvent.VK_UP) {
                    newPosition = newPosition.moveUP();
                }

                //Если "стрелка вниз" - сдвинуть фигурку вниз
                else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                    newPosition = newPosition.moveDOWN();
                }
            }
            if (score > 20) {
                moveGhost1(followPac(ghost1Position));
               // moveGhost2(followPac(ghost2Position));
            }
            movePac(newPosition); // двигаем пакмена
            level.print();        //отображаем текущее состояние игры
            sleep();        //пауза между ходами
        }
        if (win) {
            System.out.println("Congradulate!! Pac Win");
        } else
            System.out.println("Game over");
    }

    public void sleep() {
        try {

            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }

    public void movePac(PointXY pointXY) {
        if (canBeMoved(pointXY)) {
            BaseElement element = level.getElement(pointXY);
            if (element instanceof GhostElement) {
                pac.die();
            } else if (element instanceof TunnelElement) {
                TunnelElement tunel = (TunnelElement) level.getElement(pointXY);
                if (!tunel.isEaten()) {
                    tunel.EatO();
                    score += 10;
                }
                if (tunel instanceof ExitElement) {
                    win = true;
                }
            }
            level.setElement(pac, pointXY);
            level.setElement(element, pacPosition);
            pacPosition = pointXY;
        }
    }

    public void moveGhost1(PointXY pointXY) {
        if (canBeMoved(pointXY)) {
            BaseElement element = level.getElement(pointXY);
            if (element instanceof PacmanElement) {
                pac.die();
            }
            level.setElement(ghost1, pointXY);
            level.setElement(element, ghost1Position);
            ghost1Position = pointXY;
        }
    }

    public void moveGhost2(PointXY pointXY) {
        if (canBeMoved(pointXY)) {
            BaseElement element = level.getElement(pointXY);
            if (element instanceof PacmanElement) {
                pac.die();
            }
            level.setElement(ghost2, pointXY);
            level.setElement(element, ghost2Position);
            ghost2Position = pointXY;
        }
    }

    public PointXY followPac(PointXY ghostPosition) {
        ArrayList<PointXY> possiblePositions = new ArrayList<PointXY>();
        possiblePositions.add(ghostPosition.moveUP());
        possiblePositions.add(ghostPosition.moveDOWN());
        possiblePositions.add(ghostPosition.moveLEFT());
        possiblePositions.add(ghostPosition.moveRIGHT());

        TreeMap<Double, PointXY> poinSortedByDistance = new TreeMap<Double, PointXY>();
        for (PointXY pointXY : possiblePositions) {
            if (canBeMoved(pointXY)) {
                poinSortedByDistance.put(pointXY.getDistance(pacPosition), pointXY);
            }
        }
        return poinSortedByDistance.firstEntry().getValue();
    }

    public boolean canBeMoved(PointXY pointXY) {

        if (level.checkBounds(pointXY)) {

            BaseElement elem = level.getElement(pointXY);
            return !(elem instanceof WallElement);
        }
        return false;
    }


    public static void main(String[] args) throws IOException {

        Game game = new Game();

        game.run();
    }
}
