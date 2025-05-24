package net.mrwooly357.medievalstuff.util;

import net.minecraft.util.math.MathHelper;

public class ModUtil {

    /** Makes number opposite. For example:
     double a = 1;
     double oppositeA = this.opposite(a);
     <p> And "oppositeA" will be equal to -1.
     */
    public static double opposite(double numberToOpposite) {
        double oppositeNumber;

        if (numberToOpposite > 0 || numberToOpposite < 0) {
            oppositeNumber = numberToOpposite - numberToOpposite * 2;
        } else {
            oppositeNumber = 0;
        }

        return oppositeNumber;
    }

    /**
     * Calculates distance between to points. "From" point is the place you start. "To" point is the place you finish.
     * @param fromX is x coordinates of "from" point.
     * @param fromY is y coordinates of "from" point.
     * @param fromZ is z coordinates of "from" point.
     * @param toX is x coordinates of "to" point.
     * @param toY is y coordinates of "to" point.
     * @param toZ is z coordinates of "to" point.
     * @return the distance between "from" point and "to" point.
     * <p> Note:
     * <p> If you don't understand what this is, imagine a chicken and a cow on your farm, draw a line between them and measure its length.
     */
    public static double getDistanceBetween(double fromX, double fromY, double fromZ, double toX, double toY, double toZ) {
        double rawXDistance = fromX - toX;
        double rawYDistance = fromY - toY;
        double rawZDistance = fromZ - toZ;
        double xDistance;
        double yDistance;
        double zDistance;

        if (rawXDistance < 0 ) {
            xDistance = opposite(rawXDistance);
        } else {
            xDistance = rawXDistance;
        }

        if (rawYDistance < 0 ) {
            yDistance = opposite(rawYDistance);
        } else {
            yDistance = rawYDistance;
        }

        if (rawZDistance < 0 ) {
            zDistance = opposite(rawZDistance);
        } else {
            zDistance = rawZDistance;
        }

        return MathHelper.sqrt((float) (xDistance * xDistance + yDistance * yDistance + zDistance * zDistance));
    }

    public static int rgbToPackedInt(int red, int green, int blue) {
        red = red & 0xFF;
        green = green & 0xFF;
        blue = blue & 0xFF;

        return (red << 16) | (green << 8) | blue;
    }
}
