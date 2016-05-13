package com.lesliedahlberg.snake;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by lesliedahlberg on 12/05/16.
 */
public class Body {
    Grid grid;
    Array<Tile> snake;
    Food food;
    Color headColor = new Color(0x90BE6DFF);
    Color tailColor = new Color(0xC9E3ACFF);

    Tile.orientation facing;
    boolean colission;
    public Body(Grid grid, Food food){
        this.grid = grid;
        this.food = food;
        snake = new Array<Tile>();
        genHead();
        facing = Tile.orientation.N;
        colission = false;
    }
    private void genHead(){

        Tile head = new Tile(grid.centerIndex(), grid, false, headColor);
        snake.add(head);
    }
    private Tile genTail(){
        GridPoint2 last = new GridPoint2(snake.get(snake.size-1).index.x, snake.get(snake.size-1).index.y);
        //last.x +=1;
        Tile tail = new Tile(last, grid, true, tailColor);
        return tail;
    }
    public void render(ShapeRenderer shapeRenderer){
        for (int i = snake.size - 1; i >= 0; i--){
            snake.get(i).render(shapeRenderer);
        }
    }
    public void move(Tile.orientation o){

        for(int i = snake.size - 1; i > 0; i--){
            snake.get(i).index = new GridPoint2(snake.get(i-1).index);
        }
        snake.first().move(o);
    }
    public void grow(){
        snake.add(genTail());
    }

    public void eat(){
        if(food != null){
            for (int i = 0; i < food.food.size; i++){
                if(snake.get(0).index.x == food.food.get(i).index.x && snake.get(0).index.y == food.food.get(i).index.y){
                    grow();
                    food.food.removeIndex(i);
                }
            }
        }
    }

    public boolean collide(){
        for (int i = 1; i < snake.size - 1; i++){
            if (snake.get(i).index.x == snake.get(0).index.x && snake.get(i).index.y == snake.get(0).index.y){
                    return true;
            }
        }
        return false;
    }

    public void reset(){
        colission = false;
        facing = Tile.orientation.N;
        snake.clear();
        genHead();
        food.reset();
    }

    public void autoMove() {
        if(!colission){
            if(collide()){
                facing = Tile.orientation.N;
                colission = true;
            }
            eat();
            if(facing != Tile.orientation.N){
                move(facing);
            }
        }
    }
}
