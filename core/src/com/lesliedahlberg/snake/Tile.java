package com.lesliedahlberg.snake;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.FloatFrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;

import static com.lesliedahlberg.snake.Tile.orientation.*;


/**
 * Created by lesliedahlberg on 12/05/16.
 */
public class Tile {
    private Grid grid;
    public GridPoint2 index;
    private Color color;
    public enum orientation {U,D,L,R,N};
    boolean pause;

    public Tile(GridPoint2 index, Grid grid, boolean pause, Color color){
        this.index = index;
        this.grid = grid;
        this.color = color;
        this.pause = pause;
    }
    public void render(ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(color);
        GridPoint2 point = grid.grid[index.x][index.y];
        shapeRenderer.rect(point.x, point.y, grid.tileSize.x, grid.tileSize.y);
    }
    public void move(orientation o){
        if(pause){
            pause = false;
        }else{
            switch (o){
                case L:
                    if(index.x <= 0)
                        index.x = grid.gridResolution.x - 1;
                    else
                        index.x -= 1;
                    break;
                case R:
                    if(index.x >= grid.gridResolution.x - 1)
                        index.x = 0;
                    else
                        index.x += 1;
                    break;
                case U:
                    if(index.y >= grid.gridResolution.y - 1)
                        index.y = 0;
                    else
                        index.y += 1;
                    break;
                case D:
                    if(index.y <= 0)
                        index.y = grid.gridResolution.y - 1;
                    else
                        index.y -= 1;
                    break;
            }
        }
    }
}
