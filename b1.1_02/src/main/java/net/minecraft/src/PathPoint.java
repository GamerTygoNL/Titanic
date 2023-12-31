package net.minecraft.src;

public class PathPoint {

    public final int xCoord;
    public final int yCoord;
    public final int zCoord;
    public final int hash;
    public boolean isFirst;
    int index;
    float totalPathDistance;
    float distanceToNext;
    float distanceToTarget;
    PathPoint previous;
    public PathPoint(int i, int j, int k) {
        index = -1;
        isFirst = false;
        xCoord = i;
        yCoord = j;
        zCoord = k;
        hash = i | j << 10 | k << 20;
    }

    public float distanceTo(PathPoint pathpoint) {
        float f = pathpoint.xCoord - xCoord;
        float f1 = pathpoint.yCoord - yCoord;
        float f2 = pathpoint.zCoord - zCoord;
        return MathHelper.sqrt_float(f * f + f1 * f1 + f2 * f2);
    }

    public boolean equals(Object obj) {
        return ((PathPoint) obj).hash == hash;
    }

    public int hashCode() {
        return hash;
    }

    public boolean isAssigned() {
        return index >= 0;
    }

    public String toString() {
        return (new StringBuilder()).append(xCoord).append(", ").append(yCoord).append(", ").append(zCoord).toString();
    }
}
