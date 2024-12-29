package codesample.leetcode.easy;

import java.util.HashSet;

/**
 * 1496. Path Crossing — https://leetcode.com/problems/path-crossing/description/
 */
public class _1496_PathCrossing {
    public boolean isPathCrossing(String path) {
        var seen = new HashSet<String>();

        var x = 0;
        var y = 0;
        seen.add(toStr(x, y));

        for (var ch: path.toCharArray()) {
            if (ch == 'N') {
                y++;
            } else if (ch == 'S') {
                y--;
            } else if (ch == 'E') {
                x++;
            } else if (ch == 'W') {
                x--;
            }
            var strCoords = toStr(x,y);
            if (seen.contains(strCoords)) {
                return true;
            }
            seen.add(strCoords);
        }
        return false;
    }

    private String toStr(int x, int y) {
        var sb = new StringBuilder();
        sb.append(x);
        sb.append(";");
        sb.append(y);
        return sb.toString();
    }
}
