package com.monster.massivexov10;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import de.keyboardsurfer.android.widget.crouton.Crouton;

public class PuzzleView extends View {
	private static final String TAG = "Sudoku";
	public int[] flag = { 0 };

	private float width; // width of one tile
	private float height; // height of one tile
	private int selX; // X index of selection
	private int selY; // Y index of selection
	private final Rect selRect = new Rect();
	private final Rect rect = new Rect();
	private int count = 1;
	private int var = 0;
	private int nxtvalue = 1;
	private int prevalue;
	int preprevalue = 0;
	private int recentvalue = 1;
	int checkingcount = 0;
	int f = 0;
	int z = 0;
	int win;
	int bigwin = 0;
	int ex = 0;
	int x, y, undocount = 0;
	Context context;
    Style croutonStyle = Style.STROKE;

	private final Game game;

	public PuzzleView(Context context) {
		super(context);
		this.game = (Game) context;
		setFocusable(true);
		setFocusableInTouchMode(true);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		width = w / 9f;
		height = h / 9f;
		getRect(selX, selY, selRect);
		Log.d(TAG, "onSizeChanged: width " + width + ", height " + height);
		super.onSizeChanged(w, h, oldw, oldh);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// Draw the background...

		Paint background = new Paint();
		background.setColor(getResources().getColor(R.color.puzzle_background));
		canvas.drawRect(0, 0, getWidth(), getHeight(), background);

		// Draw the board...

		// Draw the numbers...
		Paint active = new Paint();
		Paint invalid = new Paint();
		invalid.setColor(getResources().getColor(R.color.puzzle_red));
		active.setColor(getResources().getColor(R.color.puzzle_cyan));
		getRect(selX, selY, selRect);

		if (var == 1
				|| ((f == 1) && (game.wildcard(nxtvalue - 1) == 5))
				|| (getgrid(prevalue, selX, selY) && (game
						.wildcard(nxtvalue - 1) == 5)) || z == 1 || ex == 2)
			canvas.drawRect(selRect, active);
		else
			canvas.drawRect(selRect, invalid);

		if (bigwin != 1) {
			if (nxtvalue == 1 && var != 0 && f != 1) {
				getRect(0, 0, selRect);
				canvas.drawRect(selRect, active);
				getRect(0, 1, selRect);
				canvas.drawRect(selRect, active);
				getRect(0, 2, selRect);
				canvas.drawRect(selRect, active);
				getRect(1, 0, selRect);
				canvas.drawRect(selRect, active);
				getRect(1, 1, selRect);
				canvas.drawRect(selRect, active);
				getRect(1, 2, selRect);
				canvas.drawRect(selRect, active);
				getRect(2, 0, selRect);
				canvas.drawRect(selRect, active);
				getRect(2, 1, selRect);
				canvas.drawRect(selRect, active);
				getRect(2, 2, selRect);
				canvas.drawRect(selRect, active);
			}

			else if (nxtvalue == 2 && f != 1) {
				getRect(3, 0, selRect);
				canvas.drawRect(selRect, active);
				getRect(3, 1, selRect);
				canvas.drawRect(selRect, active);
				getRect(3, 2, selRect);
				canvas.drawRect(selRect, active);
				getRect(4, 0, selRect);
				canvas.drawRect(selRect, active);
				getRect(4, 1, selRect);
				canvas.drawRect(selRect, active);
				getRect(4, 2, selRect);
				canvas.drawRect(selRect, active);
				getRect(5, 0, selRect);
				canvas.drawRect(selRect, active);
				getRect(5, 1, selRect);
				canvas.drawRect(selRect, active);
				getRect(5, 2, selRect);
				canvas.drawRect(selRect, active);
			}

			else if (nxtvalue == 3 && f != 1) {
				getRect(6, 0, selRect);
				canvas.drawRect(selRect, active);
				getRect(6, 1, selRect);
				canvas.drawRect(selRect, active);
				getRect(6, 2, selRect);
				canvas.drawRect(selRect, active);
				getRect(7, 0, selRect);
				canvas.drawRect(selRect, active);
				getRect(7, 1, selRect);
				canvas.drawRect(selRect, active);
				getRect(7, 2, selRect);
				canvas.drawRect(selRect, active);
				getRect(8, 0, selRect);
				canvas.drawRect(selRect, active);
				getRect(8, 1, selRect);
				canvas.drawRect(selRect, active);
				getRect(8, 2, selRect);
				canvas.drawRect(selRect, active);
			}

			else if (nxtvalue == 4 && f != 1) {
				getRect(0, 3, selRect);
				canvas.drawRect(selRect, active);
				getRect(0, 4, selRect);
				canvas.drawRect(selRect, active);
				getRect(0, 5, selRect);
				canvas.drawRect(selRect, active);
				getRect(1, 3, selRect);
				canvas.drawRect(selRect, active);
				getRect(1, 4, selRect);
				canvas.drawRect(selRect, active);
				getRect(1, 5, selRect);
				canvas.drawRect(selRect, active);
				getRect(2, 3, selRect);
				canvas.drawRect(selRect, active);
				getRect(2, 4, selRect);
				canvas.drawRect(selRect, active);
				getRect(2, 5, selRect);
				canvas.drawRect(selRect, active);
			}

			else if (nxtvalue == 5 && f != 1) {
				getRect(3, 3, selRect);
				canvas.drawRect(selRect, active);
				getRect(3, 4, selRect);
				canvas.drawRect(selRect, active);
				getRect(3, 5, selRect);
				canvas.drawRect(selRect, active);
				getRect(4, 3, selRect);
				canvas.drawRect(selRect, active);
				getRect(4, 4, selRect);
				canvas.drawRect(selRect, active);
				getRect(4, 5, selRect);
				canvas.drawRect(selRect, active);
				getRect(5, 3, selRect);
				canvas.drawRect(selRect, active);
				getRect(5, 4, selRect);
				canvas.drawRect(selRect, active);
				getRect(5, 5, selRect);
				canvas.drawRect(selRect, active);
			}

			else if (nxtvalue == 6 && f != 1) {
				getRect(6, 3, selRect);
				canvas.drawRect(selRect, active);
				getRect(6, 4, selRect);
				canvas.drawRect(selRect, active);
				getRect(6, 5, selRect);
				canvas.drawRect(selRect, active);
				getRect(7, 3, selRect);
				canvas.drawRect(selRect, active);
				getRect(7, 4, selRect);
				canvas.drawRect(selRect, active);
				getRect(7, 5, selRect);
				canvas.drawRect(selRect, active);
				getRect(8, 3, selRect);
				canvas.drawRect(selRect, active);
				getRect(8, 4, selRect);
				canvas.drawRect(selRect, active);
				getRect(8, 5, selRect);
				canvas.drawRect(selRect, active);
			}

			else if (nxtvalue == 7 && f != 1) {
				getRect(0, 6, selRect);
				canvas.drawRect(selRect, active);
				getRect(0, 7, selRect);
				canvas.drawRect(selRect, active);
				getRect(0, 8, selRect);
				canvas.drawRect(selRect, active);
				getRect(1, 6, selRect);
				canvas.drawRect(selRect, active);
				getRect(1, 7, selRect);
				canvas.drawRect(selRect, active);
				getRect(1, 8, selRect);
				canvas.drawRect(selRect, active);
				getRect(2, 6, selRect);
				canvas.drawRect(selRect, active);
				getRect(2, 7, selRect);
				canvas.drawRect(selRect, active);
				getRect(2, 8, selRect);
				canvas.drawRect(selRect, active);
			}

			else if (nxtvalue == 8 && f != 1) {
				getRect(3, 6, selRect);
				canvas.drawRect(selRect, active);
				getRect(3, 7, selRect);
				canvas.drawRect(selRect, active);
				getRect(3, 8, selRect);
				canvas.drawRect(selRect, active);
				getRect(4, 6, selRect);
				canvas.drawRect(selRect, active);
				getRect(4, 7, selRect);
				canvas.drawRect(selRect, active);
				getRect(4, 8, selRect);
				canvas.drawRect(selRect, active);
				getRect(5, 6, selRect);
				canvas.drawRect(selRect, active);
				getRect(5, 7, selRect);
				canvas.drawRect(selRect, active);
				getRect(5, 8, selRect);
				canvas.drawRect(selRect, active);
			}

			else if (nxtvalue == 9 && f != 1) {
				getRect(6, 6, selRect);
				canvas.drawRect(selRect, active);
				getRect(6, 7, selRect);
				canvas.drawRect(selRect, active);
				getRect(6, 8, selRect);
				canvas.drawRect(selRect, active);
				getRect(7, 6, selRect);
				canvas.drawRect(selRect, active);
				getRect(7, 7, selRect);
				canvas.drawRect(selRect, active);
				getRect(7, 8, selRect);
				canvas.drawRect(selRect, active);
				getRect(8, 6, selRect);
				canvas.drawRect(selRect, active);
				getRect(8, 7, selRect);
				canvas.drawRect(selRect, active);
				getRect(8, 8, selRect);
				canvas.drawRect(selRect, active);
			}
		}

		// Define colors for the grid lines
		Paint dark = new Paint();
		dark.setColor(getResources().getColor(R.color.puzzle_dark));

		Paint hilite = new Paint();
		hilite.setColor(getResources().getColor(R.color.puzzle_hilite));

		Paint black = new Paint();
		black.setColor(getResources().getColor(R.color.puzzle_black));

		Paint stripe = new Paint();
		stripe.setColor(getResources().getColor(R.color.puzzle_green));
		stripe.setStyle(Style.STROKE);
		stripe.setStrokeWidth(20f);

		Paint finish = new Paint();
		finish.setColor(getResources().getColor(R.color.puzzle_grey));

		Paint stripeO = new Paint();
		stripeO.setColor(getResources().getColor(R.color.puzzle_lightblue));
		stripeO.setStyle(Style.STROKE);
		stripeO.setStrokeWidth(20f);

		// printing stripes for win
		if (game.checkforstripe(1) == 'X') {

			getRect(0, 0, selRect);
			canvas.drawRect(selRect, finish);
			getRect(0, 1, selRect);
			canvas.drawRect(selRect, finish);
			getRect(0, 2, selRect);
			canvas.drawRect(selRect, finish);
			getRect(1, 0, selRect);
			canvas.drawRect(selRect, finish);
			getRect(1, 1, selRect);
			canvas.drawRect(selRect, finish);
			getRect(1, 2, selRect);
			canvas.drawRect(selRect, finish);
			getRect(2, 0, selRect);
			canvas.drawRect(selRect, finish);
			getRect(2, 1, selRect);
			canvas.drawRect(selRect, finish);
			getRect(2, 2, selRect);
			canvas.drawRect(selRect, finish);

			canvas.drawLine(40, 40, (getWidth() / 3)-40, (getHeight() / 3)-40, stripe);
			canvas.drawLine((getWidth() / 3)-40, 40, 40, (getHeight() / 3)-40, stripe);
		} else if (game.checkforstripe(1) == 'O') {

			getRect(0, 0, selRect);
			canvas.drawRect(selRect, finish);
			getRect(0, 1, selRect);
			canvas.drawRect(selRect, finish);
			getRect(0, 2, selRect);
			canvas.drawRect(selRect, finish);
			getRect(1, 0, selRect);
			canvas.drawRect(selRect, finish);
			getRect(1, 1, selRect);
			canvas.drawRect(selRect, finish);
			getRect(1, 2, selRect);
			canvas.drawRect(selRect, finish);
			getRect(2, 0, selRect);
			canvas.drawRect(selRect, finish);
			getRect(2, 1, selRect);
			canvas.drawRect(selRect, finish);
			getRect(2, 2, selRect);
			canvas.drawRect(selRect, finish);

			float leftx = 40;
			float topy = 40;
			float rightx = (getWidth() / 3)-40;
			float bottomy = (getHeight() / 3)-40;
			RectF ovalBounds = new RectF(leftx, topy, rightx, bottomy);
			canvas.drawOval(ovalBounds, stripeO);
		}

		if (game.checkforstripe(2) == 'X') {

			getRect(3, 0, selRect);
			canvas.drawRect(selRect, finish);
			getRect(3, 1, selRect);
			canvas.drawRect(selRect, finish);
			getRect(3, 2, selRect);
			canvas.drawRect(selRect, finish);
			getRect(4, 0, selRect);
			canvas.drawRect(selRect, finish);
			getRect(4, 1, selRect);
			canvas.drawRect(selRect, finish);
			getRect(4, 2, selRect);
			canvas.drawRect(selRect, finish);
			getRect(5, 0, selRect);
			canvas.drawRect(selRect, finish);
			getRect(5, 1, selRect);
			canvas.drawRect(selRect, finish);
			getRect(5, 2, selRect);
			canvas.drawRect(selRect, finish);

			canvas.drawLine((getWidth()/3)+40, 40, (getWidth() * 2 / 3)-40,
					(getHeight() / 3)-40, stripe);
			canvas.drawLine((getWidth() * 2 / 3)-40, 40, (getWidth()/3)+40,
					(getHeight() / 3)-40, stripe);
		} else if (game.checkforstripe(2) == 'O') {

			getRect(3, 0, selRect);
			canvas.drawRect(selRect, finish);
			getRect(3, 1, selRect);
			canvas.drawRect(selRect, finish);
			getRect(3, 2, selRect);
			canvas.drawRect(selRect, finish);
			getRect(4, 0, selRect);
			canvas.drawRect(selRect, finish);
			getRect(4, 1, selRect);
			canvas.drawRect(selRect, finish);
			getRect(4, 2, selRect);
			canvas.drawRect(selRect, finish);
			getRect(5, 0, selRect);
			canvas.drawRect(selRect, finish);
			getRect(5, 1, selRect);
			canvas.drawRect(selRect, finish);
			getRect(5, 2, selRect);
			canvas.drawRect(selRect, finish);

			float leftx = (getWidth()/3)+40;
			float topy = 40;
			float rightx = (getWidth() * 2 / 3)-40;
			float bottomy = (getHeight() / 3)-40;
			RectF ovalBounds = new RectF(leftx, topy, rightx, bottomy);
			canvas.drawOval(ovalBounds, stripeO);
		}

		if (game.checkforstripe(3) == 'X') {

			getRect(6, 0, selRect);
			canvas.drawRect(selRect, finish);
			getRect(6, 1, selRect);
			canvas.drawRect(selRect, finish);
			getRect(6, 2, selRect);
			canvas.drawRect(selRect, finish);
			getRect(7, 0, selRect);
			canvas.drawRect(selRect, finish);
			getRect(7, 1, selRect);
			canvas.drawRect(selRect, finish);
			getRect(7, 2, selRect);
			canvas.drawRect(selRect, finish);
			getRect(8, 0, selRect);
			canvas.drawRect(selRect, finish);
			getRect(8, 1, selRect);
			canvas.drawRect(selRect, finish);
			getRect(8, 2, selRect);
			canvas.drawRect(selRect, finish);

			canvas.drawLine((getWidth() * 2 / 3)+40, 40, getWidth()-40, (getHeight() / 3)-40,
					stripe);
			canvas.drawLine(getWidth()-40, 40, (getWidth() * 2 / 3)+40, (getHeight() / 3)-40,
					stripe);
		} else if (game.checkforstripe(3) == 'O') {

			getRect(6, 0, selRect);
			canvas.drawRect(selRect, finish);
			getRect(6, 1, selRect);
			canvas.drawRect(selRect, finish);
			getRect(6, 2, selRect);
			canvas.drawRect(selRect, finish);
			getRect(7, 0, selRect);
			canvas.drawRect(selRect, finish);
			getRect(7, 1, selRect);
			canvas.drawRect(selRect, finish);
			getRect(7, 2, selRect);
			canvas.drawRect(selRect, finish);
			getRect(8, 0, selRect);
			canvas.drawRect(selRect, finish);
			getRect(8, 1, selRect);
			canvas.drawRect(selRect, finish);
			getRect(8, 2, selRect);
			canvas.drawRect(selRect, finish);

			float leftx = (getWidth() * 2 / 3)+40;
			float topy = 40;
			float rightx = getWidth()-40;
			float bottomy = (getHeight() / 3)-40;
			RectF ovalBounds = new RectF(leftx, topy, rightx, bottomy);
			canvas.drawOval(ovalBounds, stripeO);
		}

		if (game.checkforstripe(4) == 'X') {

			getRect(0, 3, selRect);
			canvas.drawRect(selRect, finish);
			getRect(0, 4, selRect);
			canvas.drawRect(selRect, finish);
			getRect(0, 5, selRect);
			canvas.drawRect(selRect, finish);
			getRect(1, 3, selRect);
			canvas.drawRect(selRect, finish);
			getRect(1, 4, selRect);
			canvas.drawRect(selRect, finish);
			getRect(1, 5, selRect);
			canvas.drawRect(selRect, finish);
			getRect(2, 3, selRect);
			canvas.drawRect(selRect, finish);
			getRect(2, 4, selRect);
			canvas.drawRect(selRect, finish);
			getRect(2, 5, selRect);
			canvas.drawRect(selRect, finish);

			canvas.drawLine(40, (getHeight() / 3)+40, (getWidth()/3)-40,
					(getHeight() * 2 / 3)-40, stripe);
			canvas.drawLine((getWidth()/3)-40, (getHeight() / 3)+40, 40,
					(getHeight() * 2 / 3)-40, stripe);
		} else if (game.checkforstripe(4) == 'O') {

			getRect(0, 3, selRect);
			canvas.drawRect(selRect, finish);
			getRect(0, 4, selRect);
			canvas.drawRect(selRect, finish);
			getRect(0, 5, selRect);
			canvas.drawRect(selRect, finish);
			getRect(1, 3, selRect);
			canvas.drawRect(selRect, finish);
			getRect(1, 4, selRect);
			canvas.drawRect(selRect, finish);
			getRect(1, 5, selRect);
			canvas.drawRect(selRect, finish);
			getRect(2, 3, selRect);
			canvas.drawRect(selRect, finish);
			getRect(2, 4, selRect);
			canvas.drawRect(selRect, finish);
			getRect(2, 5, selRect);
			canvas.drawRect(selRect, finish);

			float leftx = 40;
			float topy = (getHeight() / 3)+40;
			float rightx = (getWidth()/3)-40;
			float bottomy = (getHeight() * 2 / 3)-40;
			RectF ovalBounds = new RectF(leftx, topy, rightx, bottomy);
			canvas.drawOval(ovalBounds, stripeO);
		}

		if (game.checkforstripe(5) == 'X') {

			getRect(3, 3, selRect);
			canvas.drawRect(selRect, finish);
			getRect(3, 4, selRect);
			canvas.drawRect(selRect, finish);
			getRect(3, 5, selRect);
			canvas.drawRect(selRect, finish);
			getRect(4, 3, selRect);
			canvas.drawRect(selRect, finish);
			getRect(4, 4, selRect);
			canvas.drawRect(selRect, finish);
			getRect(4, 5, selRect);
			canvas.drawRect(selRect, finish);
			getRect(5, 3, selRect);
			canvas.drawRect(selRect, finish);
			getRect(5, 4, selRect);
			canvas.drawRect(selRect, finish);
			getRect(5, 5, selRect);
			canvas.drawRect(selRect, finish);

			canvas.drawLine((getWidth()/3)+40, (getHeight() / 3)+40,
					(getWidth() * 2 / 3)-40, (getHeight() * 2 / 3)-40, stripe);
			canvas.drawLine((getWidth() * 2 / 3)-40, (getHeight() / 3)+40,
					(getWidth()/3)+40, (getHeight() * 2 / 3)-40, stripe);
		} else if (game.checkforstripe(5) == 'O') {

			getRect(3, 3, selRect);
			canvas.drawRect(selRect, finish);
			getRect(3, 4, selRect);
			canvas.drawRect(selRect, finish);
			getRect(3, 5, selRect);
			canvas.drawRect(selRect, finish);
			getRect(4, 3, selRect);
			canvas.drawRect(selRect, finish);
			getRect(4, 4, selRect);
			canvas.drawRect(selRect, finish);
			getRect(4, 5, selRect);
			canvas.drawRect(selRect, finish);
			getRect(5, 3, selRect);
			canvas.drawRect(selRect, finish);
			getRect(5, 4, selRect);
			canvas.drawRect(selRect, finish);
			getRect(5, 5, selRect);
			canvas.drawRect(selRect, finish);

			float leftx = (getWidth()/3)+40;
			float topy = (getHeight() / 3)+40;
			float rightx = (getWidth() * 2 / 3)-40;
			float bottomy = (getHeight() * 2 / 3)-40;
			RectF ovalBounds = new RectF(leftx, topy, rightx, bottomy);
			canvas.drawOval(ovalBounds, stripeO);
		}

		if (game.checkforstripe(6) == 'X') {

			getRect(6, 3, selRect);
			canvas.drawRect(selRect, finish);
			getRect(6, 4, selRect);
			canvas.drawRect(selRect, finish);
			getRect(6, 5, selRect);
			canvas.drawRect(selRect, finish);
			getRect(7, 3, selRect);
			canvas.drawRect(selRect, finish);
			getRect(7, 4, selRect);
			canvas.drawRect(selRect, finish);
			getRect(7, 5, selRect);
			canvas.drawRect(selRect, finish);
			getRect(8, 3, selRect);
			canvas.drawRect(selRect, finish);
			getRect(8, 4, selRect);
			canvas.drawRect(selRect, finish);
			getRect(8, 5, selRect);
			canvas.drawRect(selRect, finish);

			canvas.drawLine((getWidth() * 2 / 3)+40, (getHeight() / 3)+40, getWidth()-40,
					(getHeight() * 2 / 3)-40, stripe);
			canvas.drawLine(getWidth()-40, (getHeight() / 3)+40, (getWidth() * 2 / 3)+40,
					(getHeight() * 2 / 3)-40, stripe);
		} else if (game.checkforstripe(6) == 'O') {

			getRect(6, 3, selRect);
			canvas.drawRect(selRect, finish);
			getRect(6, 4, selRect);
			canvas.drawRect(selRect, finish);
			getRect(6, 5, selRect);
			canvas.drawRect(selRect, finish);
			getRect(7, 3, selRect);
			canvas.drawRect(selRect, finish);
			getRect(7, 4, selRect);
			canvas.drawRect(selRect, finish);
			getRect(7, 5, selRect);
			canvas.drawRect(selRect, finish);
			getRect(8, 3, selRect);
			canvas.drawRect(selRect, finish);
			getRect(8, 4, selRect);
			canvas.drawRect(selRect, finish);
			getRect(8, 5, selRect);
			canvas.drawRect(selRect, finish);

			float leftx = (getWidth() * 2 / 3)+40;
			float topy = (getHeight() / 3)+40;
			float rightx = getWidth()-40;
			float bottomy = (getHeight() * 2 / 3)-40;
			RectF ovalBounds = new RectF(leftx, topy, rightx, bottomy);
			canvas.drawOval(ovalBounds, stripeO);
		}

		if (game.checkforstripe(7) == 'X') {

			getRect(0, 6, selRect);
			canvas.drawRect(selRect, finish);
			getRect(0, 7, selRect);
			canvas.drawRect(selRect, finish);
			getRect(0, 8, selRect);
			canvas.drawRect(selRect, finish);
			getRect(1, 6, selRect);
			canvas.drawRect(selRect, finish);
			getRect(1, 7, selRect);
			canvas.drawRect(selRect, finish);
			getRect(1, 8, selRect);
			canvas.drawRect(selRect, finish);
			getRect(2, 6, selRect);
			canvas.drawRect(selRect, finish);
			getRect(2, 7, selRect);
			canvas.drawRect(selRect, finish);
			getRect(2, 8, selRect);
			canvas.drawRect(selRect, finish);

			canvas.drawLine(40, (getHeight() * 2 / 3)+40, (getWidth()/3)-40,
					getHeight()-40, stripe);
			canvas.drawLine((getWidth()/3)-40, (getHeight() * 2 / 3)+40, 40,
					getHeight()-40, stripe);
		} else if (game.checkforstripe(7) == 'O') {

			getRect(0, 6, selRect);
			canvas.drawRect(selRect, finish);
			getRect(0, 7, selRect);
			canvas.drawRect(selRect, finish);
			getRect(0, 8, selRect);
			canvas.drawRect(selRect, finish);
			getRect(1, 6, selRect);
			canvas.drawRect(selRect, finish);
			getRect(1, 7, selRect);
			canvas.drawRect(selRect, finish);
			getRect(1, 8, selRect);
			canvas.drawRect(selRect, finish);
			getRect(2, 6, selRect);
			canvas.drawRect(selRect, finish);
			getRect(2, 7, selRect);
			canvas.drawRect(selRect, finish);
			getRect(2, 8, selRect);
			canvas.drawRect(selRect, finish);

			float leftx = 40;
			float topy = (getHeight() * 2 / 3)+40;
			float rightx = (getWidth()/3)-40;
			float bottomy = getHeight()-40;
			RectF ovalBounds = new RectF(leftx, topy, rightx, bottomy);
			canvas.drawOval(ovalBounds, stripeO);
		}

		if (game.checkforstripe(8) == 'X') {

			getRect(3, 6, selRect);
			canvas.drawRect(selRect, finish);
			getRect(3, 7, selRect);
			canvas.drawRect(selRect, finish);
			getRect(3, 8, selRect);
			canvas.drawRect(selRect, finish);
			getRect(4, 6, selRect);
			canvas.drawRect(selRect, finish);
			getRect(4, 7, selRect);
			canvas.drawRect(selRect, finish);
			getRect(4, 8, selRect);
			canvas.drawRect(selRect, finish);
			getRect(5, 6, selRect);
			canvas.drawRect(selRect, finish);
			getRect(5, 7, selRect);
			canvas.drawRect(selRect, finish);
			getRect(5, 8, selRect);
			canvas.drawRect(selRect, finish);

			canvas.drawLine((getWidth()/3)+40, (getHeight() * 2 / 3)+40,
					(getWidth() * 2 / 3)-40, getHeight()-40, stripe);
			canvas.drawLine((getWidth() * 2 / 3)-40, (getHeight() * 2 / 3)+40,
					(getWidth()/3)+40, getHeight()-40, stripe);
		} else if (game.checkforstripe(8) == 'O') {

			getRect(3, 6, selRect);
			canvas.drawRect(selRect, finish);
			getRect(3, 7, selRect);
			canvas.drawRect(selRect, finish);
			getRect(3, 8, selRect);
			canvas.drawRect(selRect, finish);
			getRect(4, 6, selRect);
			canvas.drawRect(selRect, finish);
			getRect(4, 7, selRect);
			canvas.drawRect(selRect, finish);
			getRect(4, 8, selRect);
			canvas.drawRect(selRect, finish);
			getRect(5, 6, selRect);
			canvas.drawRect(selRect, finish);
			getRect(5, 7, selRect);
			canvas.drawRect(selRect, finish);
			getRect(5, 8, selRect);
			canvas.drawRect(selRect, finish);

			float leftx = (getWidth()/3)+40;
			float topy = (getHeight() * 2 / 3)+40;
			float rightx = (getWidth() * 2 / 3)-40;
			float bottomy = getHeight()-40;
			RectF ovalBounds = new RectF(leftx, topy, rightx, bottomy);
			canvas.drawOval(ovalBounds, stripeO);
		}

		if (game.checkforstripe(9) == 'X') {

			getRect(6, 6, selRect);
			canvas.drawRect(selRect, finish);
			getRect(6, 7, selRect);
			canvas.drawRect(selRect, finish);
			getRect(6, 8, selRect);
			canvas.drawRect(selRect, finish);
			getRect(7, 6, selRect);
			canvas.drawRect(selRect, finish);
			getRect(7, 7, selRect);
			canvas.drawRect(selRect, finish);
			getRect(7, 8, selRect);
			canvas.drawRect(selRect, finish);
			getRect(8, 6, selRect);
			canvas.drawRect(selRect, finish);
			getRect(8, 7, selRect);
			canvas.drawRect(selRect, finish);
			getRect(8, 8, selRect);
			canvas.drawRect(selRect, finish);

			canvas.drawLine((getWidth() * 2 / 3)+40, (getHeight() * 2 / 3)+40,
					getWidth()-40, getHeight()-40, stripe);
			canvas.drawLine(getWidth()-40, (getHeight() * 2 / 3)+40,
					(getWidth() * 2 / 3)+40, getHeight()-40, stripe);
		} else if (game.checkforstripe(9) == 'O') {

			getRect(6, 6, selRect);
			canvas.drawRect(selRect, finish);
			getRect(6, 7, selRect);
			canvas.drawRect(selRect, finish);
			getRect(6, 8, selRect);
			canvas.drawRect(selRect, finish);
			getRect(7, 6, selRect);
			canvas.drawRect(selRect, finish);
			getRect(7, 7, selRect);
			canvas.drawRect(selRect, finish);
			getRect(7, 8, selRect);
			canvas.drawRect(selRect, finish);
			getRect(8, 6, selRect);
			canvas.drawRect(selRect, finish);
			getRect(8, 7, selRect);
			canvas.drawRect(selRect, finish);
			getRect(8, 8, selRect);
			canvas.drawRect(selRect, finish);

			float leftx = (getWidth() * 2 / 3)+40;
			float topy = (getHeight() * 2 / 3)+40;
			float rightx = getWidth()-40;
			float bottomy = getHeight()-40;
			RectF ovalBounds = new RectF(leftx, topy, rightx, bottomy);
			canvas.drawOval(ovalBounds, stripeO);
		}

		// Draw the minor grid lines
		for (int i = 0; i < 9; i++) {
			canvas.drawLine(0, i * height, getWidth(), i * height, dark);
			canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1, dark);
			canvas.drawLine(i * width, 0, i * width, getHeight(), dark);
			canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), dark);
		}

		// Draw the major grid lines
		for (int i = 0; i < 9; i++) {
			if (i % 3 != 0)
				continue;
			canvas.drawLine(0, i * height, getWidth(), i * height, black);
			canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1,
					black);
			canvas.drawLine(i * width, 0, i * width, getHeight(), black);
			canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), black);
		}

		// Define color and style for numbers
		Paint foreground = new Paint(Paint.ANTI_ALIAS_FLAG);
		foreground.setColor(getResources().getColor(R.color.puzzle_foreground));
		foreground.setStyle(Style.FILL);
		foreground.setTextSize(height * 0.75f);
		foreground.setTextScaleX(width / height);
		foreground.setTextAlign(Paint.Align.CENTER);

		// Draw the number in the center of the tile
		FontMetrics fm = foreground.getFontMetrics();
		// Centering in X: use alignment (and X at midpoint)
		float x = width / 2;
		// Centering in Y: measure ascent/descent first
		float y = height / 2 - (fm.ascent + fm.descent) / 2;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				canvas.drawText(this.game.getTileString(i, j), i * width + x, j
						* height + y, foreground);
			}

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onCreateContextMenu(android.view.ContextMenu)
	 */
	@Override
	protected void onCreateContextMenu(ContextMenu menu) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu);
	}

	public int undoforpuzzleview() {
		if (undocount == 0) {
			if (bigwin == 0) {
				count--;
				setSelectedTile('\0');
				game.setTile(x, y, '\0');
				recentvalue = nxtvalue;
				nxtvalue = prevalue;
				prevalue = preprevalue;
				undocount = 1;
				f = 0;
				if (checkingcount == 1)
					game.setbigarray(nxtvalue);
			} else
				Toast.makeText(getContext(), "Sorry,  Game is Over!",
						Toast.LENGTH_SHORT).show();
		} else
			Toast.makeText(getContext(), "Undo can be done only once",
					Toast.LENGTH_SHORT).show();
		return z;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() != MotionEvent.ACTION_DOWN)
			return super.onTouchEvent(event);

		if (bigwin != 1) {
			z = 0;
			bigwin = 0;
			var++;

			select((int) (event.getX() / width), (int) (event.getY() / height));

			checkingcount = 0;
			preprevalue = prevalue;
			prevalue = nxtvalue;

			nxtvalue = game.tobeposed(selX, selY);
			recentvalue = game.current(selX, selY);
			// Toast.makeText(getContext(), "This is nxtvalue" + nxtvalue
			// ,Toast.LENGTH_LONG).show();
			System.out.println("This is value" + prevalue);
			if ((((var == 1 || f == 1) && (game.wildcard(recentvalue - 1) == 5)) || (getgrid(
					prevalue, selX, selY) && (game.wildcard(recentvalue - 1) == 5)))) {
				ex++;
				x = (int) (event.getX() / width);
				y = (int) (event.getY() / height);
				if (count % 2 == 0 && game.getTileString(x, y).equals("")) {
					setSelectedTile('O');
					game.setTile(x, y, 'O');
					undocount = 0;
					if (game.checkforstripe(recentvalue) != 'X'
							&& game.checkforstripe(recentvalue) != 'O') {
						win = game.getdiag(recentvalue);
						if (win == 1) {
							checkingcount = 1;
							char letter = game.checkforstripe(recentvalue);
							System.out.println("This is recentvalue"
									+ recentvalue);
							if (letter == 'X')
								bigwin = game.big_set(recentvalue, 'X');
							else
								bigwin = game.big_set(recentvalue, 'O');
							if (bigwin == 1) {

								game.finishgame('O');

							}
							// System.out.println("This is win for O");
							// System.out.println("this is value"+ prevalue);
						}
					}
					count++;
				} else if (game.getTileString(x, y).equals("")) {
					setSelectedTile('X');
					game.setTile(x, y, 'X');
					undocount = 0;
					if (game.checkforstripe(recentvalue) != 'X'
							&& game.checkforstripe(recentvalue) != 'O') {
						int win = game.getdiag(recentvalue);
						if (win == 1) {
							checkingcount = 1;
							char letter = game.checkforstripe(recentvalue);
							System.out.println("This is recentvalue"
									+ recentvalue);
							if (letter == 'X')
								bigwin = game.big_set(recentvalue, 'O');
							else
								bigwin = game.big_set(recentvalue, 'X');
							if (bigwin == 1) {

								game.finishgame('X');

							}
						}
					}

					count++;

				} else {
					recentvalue = nxtvalue;
					nxtvalue = prevalue;
					prevalue = preprevalue;
				}

				f = game.wildcard((nxtvalue - 1));
				if (f == 1) {
					// Toast.makeText(getContext(), "Field is Yours!!!",
					// Toast.LENGTH_SHORT).show();
					var = 1;
					ex = 1;
				}

			} else {
				recentvalue = nxtvalue;
				nxtvalue = prevalue;
				prevalue = preprevalue;
			}
		}
		return true;
	}

	public boolean getgrid(int value, int x, int y) {
		if (value == 1 && x < 3 && y < 3)
			return true;
		else if (value == 2 && x > 2 && x < 6 && y < 3)
			return true;
		else if (value == 3 && x > 5 && y < 3)
			return true;
		else if (value == 4 && x < 3 && y > 2 && y < 6)
			return true;
		else if (value == 5 && x > 2 && x < 6 && y > 2 && y < 6)
			return true;
		else if (value == 6 && x > 5 && y > 2 && y < 6)
			return true;
		else if (value == 7 && x < 3 && y > 5)
			return true;
		else if (value == 8 && x > 2 && x < 6 && y > 5)
			return true;
		else if (value == 9 && x > 5 && y > 5)
			return true;
		else if (value == 0)
			return true;
		return false;
	}

	public void setSelectedTile(char chara) {
		if (game.setTileIfValid(selX, selY, chara)) {
			invalidate();// may change hints
		} else {

			// Number is not valid for this tile
			Log.d(TAG, "setSelectedTile: invalid: " + chara);

			startAnimation(AnimationUtils.loadAnimation(game, R.anim.shake));

		}
	}

	private void select(int x, int y) {
		invalidate(selRect);
		selX = Math.min(Math.max(x, 0), 8);
		selY = Math.min(Math.max(y, 0), 8);
		getRect(selX, selY, selRect);
		invalidate(selRect);
	}

	private void getRect(int x, int y, Rect rect) {
		rect.set((int) (x * width), (int) (y * height),
				(int) (x * width + width), (int) (y * height + height));
	}

}