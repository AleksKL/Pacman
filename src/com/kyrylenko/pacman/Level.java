package com.kyrylenko.pacman;

import com.kyrylenko.pacman.Elements.BaseElement;
import com.kyrylenko.pacman.Elements.TunnelElement;
import com.kyrylenko.pacman.Elements.WallElement;

import java.io.*;


public class Level {

    private BaseElement[][] levelArray;

    public Level(String filename) throws IOException {

        levelArray = new BaseElement[10][10];
        BufferedReader filereader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));

        for (int i = 0; i < 10 ; i++) {
            String currentFileLine = filereader.readLine();
            for (int j = 0; j <10 ; j++) {

                char currentLineElement = currentFileLine.charAt(j);
                if(currentLineElement == '#')
                    levelArray[i][j] = new WallElement();
                if(currentLineElement == 'o')
                    levelArray[i][j] = new TunnelElement();


            }

        }
    }

    public BaseElement[][] getLevelArray() {
        return levelArray;
    }
}
