package com.lesliedahlberg.snake;

/**
 * Created by lesliedahlberg on 12/05/16.
 */

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;

public class Grid {
    public GridPoint2 screenResolution;
    public GridPoint2 gridResolution;
    public GridPoint2 tileSize;
    public GridPoint2[][] grid;

    public Grid(GridPoint2 screenResolution, GridPoint2 gridResolution) {
        this.screenResolution = screenResolution;
        this.gridResolution = gridResolution;
        tileSize = new GridPoint2();
        tileSize.x = screenResolution.x / gridResolution.x;
        tileSize.y = screenResolution.y / gridResolution.y;
        grid = new GridPoint2[gridResolution.x][gridResolution.y];
        for (int c = 0; c < gridResolution.x; c++) {
            for (int r = 0; r < gridResolution.y; r++) {
                int x = c * tileSize.x;
                int y = r * tileSize.y;
                grid[c][r] = new GridPoint2(x, y);
            }
        }
    }

    public GridPoint2 centerIndex(){
        int x = gridResolution.x / 2;
        int y = gridResolution.y / 2;
        return new GridPoint2(x, y);
    }

}
