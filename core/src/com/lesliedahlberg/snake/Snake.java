package com.lesliedahlberg.snake;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.*;

public class Snake extends ApplicationAdapter implements GestureDetector.GestureListener {

	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	private Grid grid;
	private GridPoint2 screenResolution;
	private GridPoint2 gridResolution;
	private Body snake;
	private Food food;
	private int width;
	private int height;
	private float ratio;
	private Color background;
	
	@Override
	public void create () {
		background = new Color(0xEAEFBDFF);
		width = Gdx.graphics.getWidth();
		height  = Gdx.graphics.getHeight();
		ratio = (float) width / (float) height;
		screenResolution = new GridPoint2(width, height);
		double g = 10/ratio;
		gridResolution = new GridPoint2(10, (int)Math.ceil(g));
		grid = new Grid(screenResolution, gridResolution);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, screenResolution.x, screenResolution.y);
		shapeRenderer = new ShapeRenderer();
		food = new Food(grid);
		snake = new Body(grid, food);
		GestureDetector gd = new GestureDetector(this);
		Gdx.input.setInputProcessor(gd);
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				snake.autoMove();
			}
		},0,0.125f);
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				food.spawn();
			}
		},0,2f);
	}

	@Override
	public void render () {

		//Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClearColor(background.r,background.g,background.b, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



		camera.update();
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Filled);
		food.render(shapeRenderer);
		snake.render(shapeRenderer);
		shapeRenderer.end();

		if(snake.colission){

		}

	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		if(!snake.colission){
			snake.grow();
		}else {
			snake.reset();
		}



		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {

		if(Math.abs(velocityX)>Math.abs(velocityY)){
			if(velocityX>0){
				if(snake.facing != Tile.orientation.L)
					snake.facing = Tile.orientation.R;

			}else{
				if(snake.facing != Tile.orientation.R)
					snake.facing = Tile.orientation.L;
				//snake.move(Tile.orientation.L);
			}
		}else{
			if(velocityY>0){
				if(snake.facing != Tile.orientation.U)
					snake.facing = Tile.orientation.D;
				//snake.move(Tile.orientation.D);
			}else{
				if(snake.facing != Tile.orientation.D)
					snake.facing = Tile.orientation.U;
				//snake.move(Tile.orientation.U);
			}
		}



		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		return false;
	}
}
