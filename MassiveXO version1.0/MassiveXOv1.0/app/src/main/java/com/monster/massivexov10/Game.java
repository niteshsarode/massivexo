package com.monster.massivexov10;

/**
 * Created by Private on 1/15/2015.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;


public class Game extends Activity {
	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.undo:
                puzzleView.undoforpuzzleview();
                break;
            default:
                break;
        }

        return true;

    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {

        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                //do your action here.
                finish();
                break;
            case R.id.undo:
                puzzleView.undoforpuzzleview();
                break;

        }
        return true;
    }


    private static final String TAG = "Sudoku";

    protected static final int DIFFICULTY_CONTINUE = -1;

    public char puzzle[] = new char[9 * 9];
    public char big[] = new char[3 * 3];

    public int[] flag = { 0 };


    private PuzzleView puzzleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        puzzleView = new PuzzleView(this);
        setContentView(puzzleView);
        puzzleView.requestFocus();

        Crouton.makeText(this, "This is New Game", Style.INFO).show();


        for (int i = 0; i < 9; i++) {
            big[i] = '0';
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");

        // Save the current puzzle
    }

    /** Return the tile at the given coordinates */
    private char getTile(int x, int y) {
        return puzzle[y * 9 + x];
    }

    /** Change the tile at the given coordinates */
    void setTile(int x, int y, char Value) {

        puzzle[y * 9 + x] = Value;
    }



    public int tobeposed(int x, int y) {

        if (puzzle[y + x] == 'X')
            System.out.println((0 + y) * 9 + x);
        int z = 0;
        if ((x == 0 || x == 3 || x == 6) && (y == 0 || y == 3 || y == 6))
            z = 1;
        else if ((x == 1 || x == 4 || x == 7) && (y == 0 || y == 3 || y == 6))
            z = 2;
        else if ((x == 2 || x == 5 || x == 8) && (y == 0 || y == 3 || y == 6))
            z = 3;
        else if ((x == 0 || x == 3 || x == 6) && (y == 1 || y == 4 || y == 7))
            z = 4;
        else if ((x == 1 || x == 4 || x == 7) && (y == 1 || y == 4 || y == 7))
            z = 5;
        else if ((x == 2 || x == 5 || x == 8) && (y == 1 || y == 4 || y == 7))
            z = 6;
        else if ((x == 0 || x == 3 || x == 6) && (y == 2 || y == 5 || y == 8))
            z = 7;
        else if ((x == 1 || x == 4 || x == 7) && (y == 2 || y == 5 || y == 8))
            z = 8;
        else if ((x == 2 || x == 5 || x == 8) && (y == 2 || y == 5 || y == 8))
            z = 9;

        return z;
    }


    public int current(int x,int y)
    {
        if (x < 3 && y < 3)
            return 1;

        else if (x > 2 && x < 6 && y < 3)
            return 2;

        else if ( x > 5 && y < 3)
            return 3;

        else if (x < 3 && y > 2 && y < 6)
            return 4;

        else if ( x > 2 && x < 6 && y > 2 && y < 6)
            return 5;

        else if ( x > 5 && y > 2 && y < 6)
            return 6;

        else if ( x < 3 && y > 5)
            return 7;

        else if ( x > 2 && x < 6 && y > 5)
            return 8;

        else if (x > 5 && y > 5)
            return 9;
        return 0;
    }



    public int getdiag(int value) {
        int diagx = 0, diagy = 0;
        if (value == 1) {
            diagx = 0;
            diagy = 0;
        } else if (value == 2) {
            diagx = 3;
            diagy = 0;
        } else if (value == 3) {
            diagx = 6;
            diagy = 0;
        } else if (value == 4) {
            diagx = 0;
            diagy = 3;
        } else if (value == 5) {
            diagx = 3;
            diagy = 3;
        } else if (value == 6) {
            diagx = 6;
            diagy = 3;
        } else if (value == 7) {
            diagx = 0;
            diagy = 6;
        } else if (value == 8) {
            diagx = 3;
            diagy = 6;
        } else if (value == 9) {
            diagx = 6;
            diagy = 6;
        }

        int z = diagy * 9 + diagx;
        if ((puzzle[z] == 'X' && puzzle[z + 1] == 'X' && puzzle[z + 2] == 'X')
                || (puzzle[z] == 'O' && puzzle[z + 1] == 'O' && puzzle[z + 2] == 'O'))
            return 1;
        else if ((puzzle[z + 9] == 'X' && puzzle[z + 10] == 'X' && puzzle[z + 11] == 'X')
                || (puzzle[z + 9] == 'O' && puzzle[z + 10] == 'O' && puzzle[z + 11] == 'O'))
            return 1;
        else if ((puzzle[z + 18] == 'X' && puzzle[z + 19] == 'X' && puzzle[z + 20] == 'X')
                || (puzzle[z + 18] == 'O' && puzzle[z + 19] == 'O' && puzzle[z + 20] == 'O'))
            return 1;
        else if ((puzzle[z] == 'X' && puzzle[z + 9] == 'X' && puzzle[z + 18] == 'X')
                || (puzzle[z] == 'O' && puzzle[z + 9] == 'O' && puzzle[z + 18] == 'O'))
            return 1;
        else if ((puzzle[z + 1] == 'X' && puzzle[z + 10] == 'X' && puzzle[z + 19] == 'X')
                || (puzzle[z + 1] == 'O' && puzzle[z + 10] == 'O' && puzzle[z + 19] == 'O'))
            return 1;
        else if ((puzzle[z + 2] == 'X' && puzzle[z + 11] == 'X' && puzzle[z + 20] == 'X')
                || (puzzle[z + 2] == 'O' && puzzle[z + 11] == 'O' && puzzle[z + 20] == 'O'))
            return 1;
        else if ((puzzle[z] == 'X' && puzzle[z + 10] == 'X' && puzzle[z + 20] == 'X')
                || (puzzle[z] == 'O' && puzzle[z + 10] == 'O' && puzzle[z + 20] == 'O'))
            return 1;
        else if ((puzzle[z + 2] == 'X' && puzzle[z + 10] == 'X' && puzzle[z + 18] == 'X')
                || (puzzle[z + 2] == 'O' && puzzle[z + 10] == 'O' && puzzle[z + 18] == 'O'))
            return 1;
        return 0;
    }

    public char checkforstripe(int prevalue)
    {
        if(big[prevalue-1]=='X')
            return 'X';
        else if(big[prevalue-1]=='O')
            return 'O';
        return '\0';
    }

    public void setbigarray(int value)
    {
        big[value-1]='\0';
    }

    public int big_set(int prevalue, char a) {
        big[prevalue-1] = a;
        if ((big[0] == 'X' && big[1] == 'X' && big[2] == 'X')
                || (big[0] == 'O' && big[1] == 'O' && big[2] == 'O'))
            return 1;
        else if ((big[3] == 'X' && big[4] == 'X' && big[5] == 'X')
                || (big[3] == 'O' && big[4] == 'O' && big[5] == 'O'))
            return 1;
        else if ((big[6] == 'X' && big[7] == 'X' && big[8] == 'X')
                || (big[6] == 'O' && big[7] == 'O' && big[8] == 'O'))
            return 1;
        else if ((big[0] == 'X' && big[3] == 'X' && big[6] == 'X')
                || (big[0] == 'O' && big[3] == 'O' && big[6] == 'O'))
            return 1;
        else if ((big[1] == 'X' && big[4] == 'X' && big[7] == 'X')
                || (big[1] == 'O' && big[4] == 'O' && big[7] == 'O'))
            return 1;
        else if ((big[2] == 'X' && big[5] == 'X' && big[8] == 'X')
                || (big[2] == 'O' && big[5] == 'O' && big[8] == 'O'))
            return 1;
        else if ((big[0] == 'X' && big[4] == 'X' && big[8] == 'X')
                || (big[0] == 'O' && big[4] == 'O' && big[8] == 'O'))
            return 1;
        else if ((big[2] == 'X' && big[4] == 'X' && big[6] == 'X')
                || (big[2] == 'O' && big[4] == 'O' && big[6] == 'O'))
            return 1;
        return 0;
    }

    public int wildcard(int val) {
        if (big[val] == 'X' || big[val] == 'O') {
            return 1;
        }
        return 5;
    }

    public void finishgame(char win)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        Intent intent = new Intent(Game.this, Game.class);
                        startActivity(intent);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        Intent intent1 = new Intent(Game.this, MainActivity.class);
                        startActivity(intent1);
                        break;
                }
            }
        };

        builder.setMessage("Player " + win + " Wins!! \nDo you want to PLAY AGAIN?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();

    }

    /** Return a string for the tile at the given coordinates */
    protected String getTileString(int x, int y) {
        char v = getTile(x, y);
        if (v == '\0')
            return "";
        else
            return String.valueOf(v);
    }

    /** Change the tile only if it's a valid move */
    protected boolean setTileIfValid(int x, int y, char chara) {
        String tiles[] = getUsedTiles(x, y);

        setTile(x, y, chara);
        calculateUsedTiles();
        return true;
    }

    /** Cache of used tiles */
    private final String used[][][] = new String[9][9][];

    /** Return cached used tiles visible from the given coords */
    protected String[] getUsedTiles(int x, int y) {
        return used[x][y];
    }

    /** Compute the two dimensional array of used tiles */
    private void calculateUsedTiles() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                // used[x][y] = calculateUsedTiles(x, y);
                // Log.d(TAG, "used[" + x + "][" + y + "] = "
                // + toPuzzleString(used[x][y]));
            }
        }
    }


}
