package codesample.leetcode.medium;

/**
 * 735. Asteroid Collision — https://leetcode.com/problems/asteroid-collision/description/
 */
public class _735_AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        // go from left to right
        //  if next ele is negative and no positives behind => add it
        //  if next ele is positive and no positives behind => add it
        //  if next ele is negative and there ARE positives behind =>
        //      either crush it & continue OR get crushed, then analyze backwards to see if anything else must be crushed
        //      need to remember where positive elements are
        //      hashMap all positives to make it fast?
        //      put zero in place of crashed asteroid and then filter them out
        //  if next ele is positive and there ARE positives behind => add it

        int numOfPos = 0;
        int zeroes = 0;
        for (int i = 0; i < asteroids.length; i++) {
            var cur = asteroids[i];
            // nothing to do if next elements is positive
            if (cur > 0) {
                numOfPos++;
            } else if (cur < 0) {
                // if negative and no pos behind, do nothing, just continue
                if (numOfPos == 0) {
                    continue;
                } else {
                    // if negative but there are pos beding, try crush them
                    // we know that there are positives because numOfPos != 0
                    int prevIndex = i - 1;

                    // while because we may need to crush multiple asteroids
                    // we will break out of while if needed
                    while (numOfPos > 0 && prevIndex >= 0) {
                        int prev = asteroids[prevIndex];
                        if (prev <= 0) {
                            // skip because previous is negative too or null
                            prevIndex--;
                            continue;
                        } else if (prev > 0) {
                            // something has to be crushed
                            var curAbs = Math.abs(cur);
                            if (prev > curAbs) {
                                // prev positive crush current
                                // then break since nothing to do
                                asteroids[i] = 0;
                                zeroes++;
                                break;
                            } else if (prev == curAbs) {
                                // both crush each other, nothing to do
                                asteroids[i] = 0;
                                asteroids[prevIndex] = 0;
                                zeroes += 2;
                                break;
                            } else {
                                // negative crushed positive, has to continue if
                                // possible
                                asteroids[prevIndex] = 0;
                                zeroes++;
                                prevIndex--;
                                numOfPos--;
                            }
                        }
                    }
                }
            }
        }

        // TODO: can count zeroes during processing
        var res = new int[asteroids.length - zeroes];
        var index = 0;
        for (int e : asteroids) {
            if (e != 0) {
                res[index] = e;
                index++;
            }
        }
        return res;
    }
}
