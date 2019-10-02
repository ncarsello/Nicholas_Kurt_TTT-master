package kdaill_ncasello.tttbeta;

/**
 * Created by kdail_000 on 9/12/2018.
 */

public class Grid
{
    //Figure out where to put this thing, until then just call Grid.gridSpace or import or whatever
    enum gridSpace{
        X, BLANK, O;
    }

    gridSpace[][] gameBoard;

    public boolean setTile(int x, int y, gridSpace gS) {
        if (x >= 0 && x <= 2 && y >= 0 && y <= 2) {
            gameBoard[x][y] = gS;
        }
        return false;
    }

    public void clearGrid(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = gridSpace.BLANK;
            }
        }
    }

    public gridSpace getTileData(int x, int y) {
        return gameBoard[x][y];
    }

    public Grid() {
        gameBoard = new gridSpace[3][3];
        clearGrid();
    }
}
