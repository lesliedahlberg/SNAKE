package com.lesliedahlberg.snake;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Created by lesliedahlberg on 13/05/16.
 */
public class Food {
    public  Array<Tile> food;
    Grid grid;
    Random r;
    int limit = 3;
    Color foodColor = new Color(0xEA9010FF);
    public Food(Grid grid){
        this.grid = grid;
        food = new Array<Tile>();
        r = new Random();
    }
    public void spawn(){
        if(food.size < limit)
            food.add(genFood());
    }
    private Tile genFood(){
        Tile food;

        food = new Tile(randomPoint(), grid, false, foodColor);
        return food;
    }

    private GridPoint2 randomPoint(){
        int x = r.nextInt(grid.gridResolution.x);
        int y = r.nextInt(grid.gridResolution.y);
        return new GridPoint2(x, y);
    }

    public void render(ShapeRenderer shapeRenderer) {
        for (Tile t : food) {
            t.render(shapeRenderer);
        }
    }

    public void reset() {
        food.clear();
    }
}
