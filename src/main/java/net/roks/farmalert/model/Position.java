package net.roks.farmalert.model;

/**
 * Immutable representation of a 3D coordinate point.
 */
public record Position(double x, double y, double z) {

    /**
     * Checks if X is within a target value with tolerance.
     */
    public boolean withinX(double target, double tolerance) {
        return Math.abs(x - target) <= tolerance;
    }

    /**
     * Checks if Y is within a target value with tolerance.
     */
    public boolean withinY(double target, double tolerance) {
        return Math.abs(y - target) <= tolerance;
    }

    /**
     * Checks if Z is within a target value with tolerance.
     */
    public boolean withinZ(double target, double tolerance) {
        return Math.abs(z - target) <= tolerance;
    }

    /**
     * Checks if the position is within XYZ boundaries.
     */
    public boolean withinXYZ(Position target,
                             double xTolerance,
                             double yTolerance,
                             double zTolerance) {

        return withinX(target.x(), xTolerance)
                && withinY(target.y(), yTolerance)
                && withinZ(target.z(), zTolerance);

    }
}
