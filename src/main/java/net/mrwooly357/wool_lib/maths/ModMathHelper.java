package net.mrwooly357.wool_lib.maths;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class ModMathHelper {
    /** This class is used for some simple mathematical calculations which can be sometimes found in my projects.
     */

    /** Makes number opposite. For example:
     double a = 1;
     double oppositeA = this.opposite(a);

     And "oppositeA" will be equal to -1.
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
     * @param fromEntity is a point used as a minuend.
     * @param toEntity is a point used as a subtrahend.
     * @return gives you a value as a float which represents the distance between fromEntity and toEntity.
     * Note: The value might be not accurate. But it's still very close, so I decided to use it.
     */
    public static double getDistanceBetweenEntities(LivingEntity fromEntity, LivingEntity toEntity) {
        double rawXDistance = fromEntity.getX() - toEntity.getX();
        double rawYDistance = fromEntity.getY() - toEntity.getY();
        double rawZDistance = fromEntity.getZ() - toEntity.getZ();

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

    /**
     * @param fromEntity is a point used as a minuend.
     * @param toBlock is a point used as a subtrahend.
     * @return gives you a value as a float which represents the distance between fromEntity and toBlock.
     * Note: The value might be not accurate. But it's still very close, so I decided to use it.
     */
    public static double getDistanceBetweenEntityToBlock(LivingEntity fromEntity, BlockPos toBlock) {
        double rawXDistance = fromEntity.getX() - toBlock.getX();
        double rawYDistance = fromEntity.getY() - toBlock.getY();
        double rawZDistance = fromEntity.getZ() - toBlock.getZ();

        double xDistance;
        double yDistance;
        double zDistance;

        if (rawXDistance < 0) {
            xDistance = opposite(rawXDistance);
        } else {
            xDistance = rawXDistance;
        }

        if (rawYDistance < 0) {
            yDistance = opposite(rawYDistance);
        } else {
            yDistance = rawYDistance;
        }

        if (rawZDistance < 0) {
            zDistance = opposite(rawZDistance);
        } else {
            zDistance = rawZDistance;
        }

        return MathHelper.sqrt((float) (xDistance * xDistance + yDistance * yDistance + zDistance * zDistance));
    }
}
