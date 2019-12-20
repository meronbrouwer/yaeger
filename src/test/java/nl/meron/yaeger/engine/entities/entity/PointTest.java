package nl.meron.yaeger.engine.entities.entity;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void addingPointWorksAsExpected() {
        // Setup
        var position = new Point(0d, 0d);
        var positionToAdd = new Point(1d, 1d);

        // Test
        var addedPosition = position.add(positionToAdd);

        // Verify
        assertEquals(1d, addedPosition.getX());
        assertEquals(1d, addedPosition.getY());
    }

    @Test
    void addingPoint2DWorksAsExpected() {
        // Setup
        var position = new Point(0d, 0d);
        var positionToAdd = new Point2D(1d, 1d);

        // Test
        var addedPosition = position.add(positionToAdd);

        // Verify
        assertEquals(1d, addedPosition.getX());
        assertEquals(1d, addedPosition.getY());
    }
}
