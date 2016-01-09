package com.sidm.mgpgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Space;

import java.util.Random;

/**
 * Created by tansiewlan on 11/23/2015.
 */
public class GamePanelSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    // Implement this interface to receive information about changes to the surface.

    private GameThread myThread = null; // Thread to control the rendering

    // 1a) Variables used for background rendering
    private Bitmap bg, scaledbg;

    // 1b) Define Screen width and Screen height as integer
    int Screenwidth, Screenheight;
    // 1c) Variables for defining background start and end point
    private short bgX = 0, bgY = 0, mX = 0, mY = 0;

    private int aX = 0, aY = 0;

    private boolean moveShip = false;
    // 4a) bitmap array to stores 4 images of the spaceship
    private Bitmap[] Spaceship = new Bitmap[4];
    // 4b) Variable as an index to keep track of the spaceship images
    private short SpaceshipIndex = 0;

    public Vibrator vibrate;
    protected static final String TAG = null;
    // Define Paint Object
    Paint paint = new Paint();

    // Load Sprite Animation for flying stone
    private SpriteAnimation Flystone;

    // Variables for FPS
    public float FPS;
    float deltaTime;
    long dt;

    // Variable for Game State check
    private short GameState;

    //constructor for this GamePanelSurfaceView class
    public GamePanelSurfaceView(Context context) {

        // Context is the current state of the application/object
        super(context);

        // Adding the callback (this) to the surface holder to intercept events
        getHolder().addCallback(this);

        // 1d) Set information to get screen size
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        Screenwidth = metrics.widthPixels;
        Screenheight = metrics.heightPixels;
        // 1e)load the image when this class is being instantiated
        bg = BitmapFactory.decodeResource(getResources(), R.drawable.gamescene);
        scaledbg = Bitmap.createScaledBitmap(bg, Screenwidth, Screenheight,
                true);
        // 4c) Load the images of the spaceships
        Spaceship[0] = BitmapFactory.decodeResource(getResources(), R.drawable
                .ship2_1);
        Spaceship[1] = BitmapFactory.decodeResource(getResources(), R.drawable
                .ship2_2);
        Spaceship[2] = BitmapFactory.decodeResource(getResources(), R.drawable
                .ship2_3);
        Spaceship[3] = BitmapFactory.decodeResource(getResources(), R.drawable
                .ship2_4);

        // Loading of flying stone animation sprite sheet
        Flystone = new SpriteAnimation(BitmapFactory.decodeResource(getResources(),
                R.drawable.flystone), 320, 64, 5, 5);

        // Create the game loop thread
        myThread = new GameThread(getHolder(), this);

        // Make the GamePanel focusable so it can handle events
        setFocusable(true);
    }

    //must implement inherited abstract methods
    public void surfaceCreated(SurfaceHolder holder) {
        // Create the thread
        if (!myThread.isAlive()) {
            myThread = new GameThread(getHolder(), this);
            myThread.startRun(true);
            myThread.start();
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // Destroy the thread
        if (myThread.isAlive()) {
            myThread.startRun(false);


        }
        boolean retry = true;
        while (retry) {
            try {
                myThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    public void startVibrate()
    {
        long pattern[] = {0, 200, 500};
        vibrate = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
        vibrate.vibrate(pattern, 0);
        Log.v(TAG, "Testvibration");
    }

    public void stopVibrate(){
        vibrate.cancel();
    }
    public boolean checkCollision(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {

        //top left
        if (x2 >= x1 && x2 <= x1 + w1) {
            if (y2 >= y1 && y2 <= y1 + h1)
                return true;
        }

        //top right
        if (x2 + w2 >= x1 && x2 + w2 <= x1 + w1) {
            if (y2 >= y1 && y2 <= y1 + h1)
                return true;
        }

        return false;
    }

     public void RenderGameplay(Canvas canvas) {
        // 2) Re-draw 2nd image after the 1st image ends
        if (canvas == null) {
            return;
        }
        canvas.drawBitmap(scaledbg, bgX, bgY, null);
        canvas.drawBitmap(scaledbg, bgX + Screenwidth, bgY, null);
        // 4d) Draw the spaceships
        canvas.drawBitmap(Spaceship[SpaceshipIndex], mX, mY, null);

        // Bonus) To print FPS on the screen
        paint.setARGB(255, 0, 0, 0);
        paint.setStrokeWidth(100);
        paint.setTextSize(30);
        canvas.drawText("FPS =" + FPS, 130, 75, paint);

        // Draw flystone sprite animation
        Flystone.draw(canvas);
        Flystone.setX(aX);

     }

    //Update method to update the game play
    public void update(float dt, float fps) {
        FPS = fps;

        switch (GameState) {
            case 0: {
                // 3) Update the background to allow panning effect
                bgX -= 500 * dt; // Allow panning speed
                if (bgX < -Screenwidth) {
                    bgX = 0;
                }

                // 4e) Update the spaceship images / shipIndex so that the animation will occur.
                SpaceshipIndex++;
                SpaceshipIndex %= 4;

                // Update flystone animation based on system time
                Flystone.update(System.currentTimeMillis());

            }
            break;

        }
    }

    // Rendering is done on Canvas
    public void doDraw(Canvas canvas) {
        switch (GameState) {
            case 0:
                RenderGameplay(canvas);
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction(); //check for action touch

        short X = (short) event.getX();
        short Y = (short) event.getY();


        switch(action)
        {
            case MotionEvent.ACTION_DOWN:
                if(checkCollision(mX, mY, Spaceship[SpaceshipIndex].getWidth(), Spaceship[SpaceshipIndex].getHeight(), X, Y, 0, 0))
                    //check for the image to the position to where u press (x, y)
                {
                    moveShip = true;
                }
                else
                {
                    moveShip = false;
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if(moveShip == true)
                {
                    mX = (short)(X - Spaceship[SpaceshipIndex].getWidth() / 2);
                    mY = (short)(Y - Spaceship[SpaceshipIndex].getHeight() / 2);
                }
                break;
        }

        return true;

    }


}

/*
// 5) In event of touch on screen, the spaceship will relocate to the point of touch
        short X = (short) event.getX();
        short Y = (short) event.getY();

        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            // New location where the image to land on
            mX = (short) (X - Spaceship[SpaceshipIndex].getWidth() / 2);
            mY = (short) (Y - Spaceship[SpaceshipIndex].getHeight() / 2);

            //Check if ship and stone collide
            if (checkCollision(mX, mY, Spaceship[SpaceshipIndex].getWidth(),
                    Spaceship[SpaceshipIndex].
                            getHeight(), aX, aY, Flystone.getSpriteWidth(), Flystone.getSpriteHeight
                            ())) {

                Random r = new Random();
                aX = r.nextInt(Screenwidth);
                aY = r.nextInt(Screenheight);

            }

        }

        return super.onTouchEvent(event);
    }
* */