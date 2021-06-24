package com.github.hanyaeger.api;

import com.github.hanyaeger.api.entities.YaegerEntity;
import com.github.hanyaeger.api.scenes.YaegerScene;

/**
 * Abstract superclass of all timers that are available for both a {@link YaegerScene}
 * and an {@link YaegerEntity}.
 * <p>
 * Extend this class and implement the method {@link #onAnimationUpdate(long)}.
 */
public abstract class Timer {

    private final long interval;
    private long prevTime = 0;

    private boolean active = true;
    private boolean garbage = false;

    /**
     * Create a new instance of {@link Timer} for the given interval in milliseconds.
     *
     * @param intervalInMs the interval in milliseconds
     */
    protected Timer(final long intervalInMs) {
        this.interval = intervalInMs * 1_000_000;
    }

    /**
     * Handle an update. This method is called on each interval as passed to the constructor of
     * this {@code Timer}.
     *
     * @param now the current timestamp
     */
    protected void handle(final long now) {
        if (!active) return;

        if (prevTime == 0) {
            prevTime = now;
        }

        if ((now - prevTime) < interval) {
            return;
        }

        prevTime = now;

        onAnimationUpdate(now);
    }

    /**
     * Pause the timer so it will no longer update with each animation.
     */
    public final void pause() {
        active = false;
    }

    /**
     * Resume the timer so it will start updating on each animation again.
     */
    public final void resume() {
        active = true;
    }

    /**
     * When this method is called, this {@code Timer} is set for removal. This will
     * be done in the next Game World Update.
     */
    public void remove() {
        garbage = true;
    }

    /**
     * Implement this method, which will be called each animation update with the given interval.
     *
     * @param timestamp the timestamp of the current frame given in nanoseconds
     */
    public abstract void onAnimationUpdate(final long timestamp);

    /**
     * State whether this {@code Timer} has been marked as garbage.
     *
     * @return a boolean stating whether this {@code Timer} has been marked as garbage
     */
    public boolean isGarbage() {
        return garbage;
    }

    /**
     * State whether this {@code Timer} is currently active. When a {@code Timer} is paused, it is
     * no longer active.
     *
     * @return a boolean stating whether this {@code Timer} is active
     */
    public boolean isActive() {
        return active;
    }
}
