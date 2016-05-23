package com.dangilbert98gmail.pi_thon;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;


public class GameSection extends Fragment {
    private SnakeDirection direction;
    private Handler handler = new Handler();
    private Runnable runnable;
    private GridView grid;
    private PlayScreen playActivity;
    private final int GRID_WIDTH = 9;
    private final int GRID_HEIGHT = 11;
    private final int TAIL = R.drawable.tail;
    private final int HEAD = R.drawable.head;
    private final int EMPTY = R.drawable.background;
    private final int CONSUMABLE = R.drawable.consumable;
    private boolean shouldDie;
    private int currLoc;
    private int[] images;
    private int delay;
    private Queue queue;
    private boolean paused = false;
    private int maxQueueSize;
    private TextView score;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Log.d("create", "created" );

        view = inflater.inflate(R.layout.fragment_game_section, container, false);

        initialize();

        runnable = new Runnable() {
            @Override
            public void run() {
                move(direction);
                if (!playActivity.isPaused()) {
                    handler.postDelayed(this, delay);
                }
            }
        };
        handler.postDelayed(runnable, delay);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            playActivity = (PlayScreen) context;
        } catch (Exception e) {
            throw new ClassCastException(playActivity.toString());
        }
    }

    public boolean setDirection(SnakeDirection d) {
        if (d == direction) {
            return false;
        }
        if (maxQueueSize > 0) {
            if (direction == getOppositeDirection(d)) {
                return false;
            }
        }
        direction = d;
        return true;
    }

    public void move(SnakeDirection d) {
        //Log.d("direction", d.toString() + "    " + getCurrLoc() );
        setImages(getCurrLoc(), EMPTY);
        int prevLoc = getCurrLoc();
        int tempLoc = currLoc;
        switch (d) {
            case UP:
                if ((getCurrLoc() - GRID_WIDTH) < 0) {
                    shouldDie = true;
                }
                break;
            case DOWN:
                if ((getCurrLoc() + GRID_WIDTH) >= (GRID_HEIGHT * GRID_WIDTH)) {
                    shouldDie = true;
                }
                break;
            case RIGHT:
                if ((getCurrLoc() + 1) % GRID_WIDTH == 0) {
                    shouldDie = true;
                }
                break;
            case LEFT:
                if (getCurrLoc() % GRID_WIDTH == 0) {
                    shouldDie = true;
                }
                break;
        }
        if (!shouldDie) {
            tempLoc = getAdjacentLocation(currLoc, d);
            if (images[tempLoc] == TAIL) {
                shouldDie = true;
            }
        }
        if (shouldDie) {
            die();
        } else {
            currLoc = tempLoc;
            if (images[currLoc] == CONSUMABLE) {
                eatConsumable();
                spawnConsumable();
            }
            setImages(currLoc, HEAD);
            setImages(prevLoc, TAIL);
            queue.enqueue(prevLoc);
            while (queue.size() > maxQueueSize) {
                setImages((int) queue.dequeue(), EMPTY);
            }
            grid.setAdapter(new TileAdapter(getGameContext(), images));
        }
    }

    public void eatConsumable() {
        if (playActivity.areQuestionsEnabled()) {
            playActivity.pauseGame(PauseType.QUESTION);
        } else {
            addTail(true);
        }
    }

    public void die() {
        //.d("intents boi", "" + shouldDie + " - " + currLoc );
        playActivity.pauseGame(PauseType.DEATH);
        //may death consume us all #trump2016
    }

    public int[] getImages() {
        return images;
    }

    public void setImages(int pos, int val) {
        images[pos] = val;
    }

    public void pause() {
        handler.removeCallbacks(runnable);
    }

    public void resume() {
        handler.postDelayed(runnable, delay);
    }

    public void restart() {
        initialize();
        pause();
    }

    public boolean spawnConsumable() {
        boolean full = maxQueueSize >= GRID_HEIGHT * GRID_HEIGHT - 1;
        if (full) {
            Log.d("spawn", "false");
            return false;
        }
        boolean spotFound = false;
        while (!spotFound) {
            int r = (int) (Math.random() * (GRID_WIDTH * GRID_HEIGHT));
            if (images[r] == EMPTY) {
                spotFound = true;
                Log.d("location", "" + r);
                images[r] = CONSUMABLE;
                delay = (int) (Math.pow(Math.E, 6.0 - (maxQueueSize / 5.0)) + 250);
            }
        }
        return true;
    }

    public void initialize() {
        queue = new Queue();
        maxQueueSize = 0;

        shouldDie = false;

        direction = SnakeDirection.UP;

        images = new int[GRID_WIDTH * GRID_HEIGHT];
        for (int r = 0; r < GRID_HEIGHT; r++) {
            for (int c = 0; c < GRID_WIDTH; c++) {
                images[(r * GRID_WIDTH) + c] = EMPTY;
            }
        }
        currLoc = ((GRID_HEIGHT / 2) * GRID_WIDTH) + (GRID_WIDTH / 2);
        images[currLoc] = HEAD;
        spawnConsumable();

        grid = (GridView) view.findViewById(R.id.gameGrid);
        grid.setAdapter(new TileAdapter(this.getContext(), images));

        score = (TextView) view.findViewById(R.id.Score);
        updateScore();
    }

    public int getCurrLoc() {
        return currLoc;
    }

    private void updateScore() {
        score.setText("Score: " + maxQueueSize);
    }

    private int getAdjacentLocation(int startLoc, SnakeDirection d) {
        int loc;
        switch (d) {
            case UP:
                loc = startLoc - GRID_WIDTH;
                break;
            case DOWN:
                loc = startLoc + GRID_WIDTH;
                break;
            case LEFT:
                loc = startLoc - 1;
                break;
            default:
                loc = startLoc + 1;
                break;
        }
        return loc;
    }

    private SnakeDirection getOppositeDirection(SnakeDirection initD) {
        switch (initD) {
            case UP:
                return SnakeDirection.DOWN;
            case DOWN:
                return SnakeDirection.UP;
            case LEFT:
                return SnakeDirection.RIGHT;
            default:
                return SnakeDirection.LEFT;
        }
    }

    public void addTail(boolean add) {
        if (add) {
            maxQueueSize++;
        } else {
            if (maxQueueSize > 0) {
                maxQueueSize--;
            }
        }
        updateScore();
    }

    public Context getGameContext() {
        return this.getContext();
    }

    interface GameSectionListener {
        public void displayQuestionScreen();

        public boolean isPaused();

        public boolean areQuestionsEnabled();

        public void pauseGame(PauseType p);
    }

    public int getScore() {
        return maxQueueSize;
    }
}
