package kdaill_ncasello.tttbeta;

/**
 * Created by kdail_000 on 9/16/2018.
 */

public class GameEngine {
    enum gameState{
        X_WINS, O_WINS, DRAW, CONTESTED
    }

    Grid curGrid;

    int turnCounter;

    public GameEngine(Grid gameGrid){
        turnCounter = 0;
        curGrid = gameGrid;
    }

    public boolean takeTurn(int x, int y, Grid.gridSpace gS){
        if (curGrid.getTileData(x,y) == Grid.gridSpace.BLANK){
            curGrid.setTile(x, y, gS);
            return true;
        }
        return false;
    }

    //Checks for 3-In-A-Row along a row along y
    private boolean checkRow(int y){
        if(curGrid.getTileData(0,y) == curGrid.getTileData(1, y)){
            if(curGrid.getTileData(1,y) == curGrid.getTileData(2, y)){
                return true;
            }
        }
        return false;
    }

    ////Checks for 3-In-A-Row along a column along x
    private boolean checkColumn(int x){
        if(curGrid.getTileData(x, 0) == curGrid.getTileData(x, 1)){
            if(curGrid.getTileData(x, 1) == curGrid.getTileData(x, 2)){
                return true;
            }
        }
        return false;
    }

    //Checks for 3-In-A-Row for the diagonal that resembles y = -x
    private boolean checkNegativeDiag(){
        for(int i = 0; i < 2; i++){
            if(curGrid.getTileData(2-i, i) == curGrid.getTileData(1-i, i)){
                continue;
            }
            return false;
        }
        return true;
    }

    ////Checks for 3-In-A-Row for the diagonal that resembles y = x
    private boolean checkPositiveDiag(){
        for(int i = 0; i < 2; i++){
            if(curGrid.getTileData(i, i) == curGrid.getTileData(1+i, 1+i)){
                continue;
            }
            return false;
        }
        return true;
    }

    //Is used to check which player just finished the game
    private gameState checkWinningPlayer(int x, int y){
        if(curGrid.getTileData(x,y) == Grid.gridSpace.X){
            return gameState.X_WINS;
        }else{
            return gameState.O_WINS;
        }
    }

    /*This function is used each turn to check if the most recent move has ended the game.
     *This function has been streamlined by only checking for end-states that could have been
     *caused by the most recent move.
     */
    public gameState checkForEndState(int x, int y, Grid.gridSpace gS){
        if(checkColumn(x)){
            return checkWinningPlayer(x,y);
        }
        if(checkRow(y)){
            return checkWinningPlayer(x,y);
        }
        if(checkNegativeDiag()){
            return checkWinningPlayer(x,y);
        }
        if(checkPositiveDiag()){
            return checkWinningPlayer(x,y);
        }

        turnCounter++;
        if(turnCounter >= 9){
            return gameState.DRAW;
        }

        return gameState.CONTESTED;
    }
}
