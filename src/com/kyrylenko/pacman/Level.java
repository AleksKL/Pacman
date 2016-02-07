package com.kyrylenko.pacman;

import com.kyrylenko.pacman.Elements.*;

import java.io.*;


public class Level {

    private BaseElement[][] levelArray;
    int sizeX;
    int sizeY;
    private PointXY pacPosition;
    private PointXY ghost1Position;
    private PointXY ghost2Position;


    public Level(String filename) throws IOException {

        sizeX = 30;
        sizeY = 30;
        levelArray = new BaseElement[sizeX][sizeY];
        BufferedReader filereader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));

        for (int i = 0; i < sizeX; i++) {
            String currentFileLine = filereader.readLine();
            for (int j = 0; j < sizeY; j++) {

                char currentLineElement = currentFileLine.charAt(j);
                if (currentLineElement == '#')
                    levelArray[i][j] = new WallElement();
                if (currentLineElement == 'o')
                    levelArray[i][j] = new TunnelElement();
                if (currentLineElement == 'E')
                    levelArray[i][j] = new ExitElement();
                if (currentLineElement == 'C') {
                    levelArray[i][j] = new PacmanElement();
                    pacPosition = new PointXY(i, j);
                }
                if (currentLineElement == 'W') {
                    levelArray[i][j] = new GhostElement();
                    ghost1Position = new PointXY(i, j);
                }
                if (currentLineElement == 'Y') {
                    levelArray[i][j] = new GhostElement();
                    ghost2Position = new PointXY(i, j);
                }


            }

        }

    }


    public BaseElement[][] getLevelArray() {
        return levelArray;
    }

    public BaseElement getElement(PointXY pointxy) {

        return levelArray[pointxy.getX()][pointxy.getY()];
    }

    public boolean checkBounds(PointXY pointxy) {

        return (pointxy.getX() >= 0 && pointxy.getY() >= 0 && pointxy.getX() < sizeX && pointxy.getY() < sizeY);
    }

    public void setElement(BaseElement element, PointXY pointxy) {
        levelArray[pointxy.getX()][pointxy.getY()] = element;
    }

    public PointXY getPacPosition() {
        return pacPosition;
    }

    public PointXY getGhost1Position() {
        return ghost1Position;
    }
    public PointXY getGhost2Position() {
        return ghost2Position;
    }

    public void print() {
        for (int i = 0; i <sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {

                levelArray[i][j].show();
                System.out.print(" ");

            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

}
