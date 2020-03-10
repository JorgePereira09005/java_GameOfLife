/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import gameoflife.GameOfLifeBoard;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Jorge
 */
public class PersonalBoard extends GameOfLifeBoard {
    
    public PersonalBoard(int width, int height) {
        super(width, height);
    }

    @Override
    public void initiateRandomCells(double d) {
        
        double rng;
        
        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 0; j < this.getHeight(); j++) {
                rng = ThreadLocalRandom.current().nextDouble(0, 1);
                if (rng < d) {
                    this.turnToLiving(i, j);
                }
            }
        }
    }

    @Override
    public boolean isAlive(int x, int y) {
        if ( x >= this.getWidth() || y >= this.getHeight() || x < 0 || y < 0 )  {
            return false;
        }
            return getBoard()[x][y];
    }
    
    @Override
    public void turnToLiving(int x, int y) {
        if (x >= 0 && y >= 0 && x <= this.getWidth() && y<=this.getHeight()) {
            getBoard()[x][y] = true;
        }
    }

    @Override
    public void turnToDead(int x, int y) {
        if (x >= 0 && y >= 0 && x <= this.getWidth() && y<=this.getHeight()) {
            getBoard()[x][y] = false;
        }
    }

    @Override
    public int getNumberOfLivingNeighbours(int x, int y) {
        int sum = 0;
               
            for (int m = x-1; m <= x + 1; m++) {
                for (int k = y-1; k <= y + 1; k++ ) {
                        if (this.isAlive(m, k)) {
                            sum++;
                        }   
                }
            }
            if (this.isAlive(x, y)) {
                sum--;
            }
        return sum;
    }

    @Override
    public void manageCell(int x, int y, int livingNeighbours) {
      
        if (this.isAlive(x, y) && livingNeighbours < 2) {
            
            this.turnToDead(x, y);
        }
        if ( this.isAlive(x, y) && (livingNeighbours == 2 || livingNeighbours == 3) ) {
            
            this.turnToLiving(x, y);
        }
        if (this.isAlive(x, y) && livingNeighbours > 3) {
            
            this.turnToDead(x, y);
        }
        if ( !this.isAlive(x, y) && livingNeighbours == 3) {
            
            this.turnToLiving(x, y);
        }
    }
    
    
    
}
