package codesample.other.educative._02_two_pointers;

public class _03_SortedArraySquares {


    public static int[] makeSquares(int[] arr) {
        int[] squares = new int[arr.length];
        int minIndex = 0;

        // find min
        for (int i = 0; i < arr.length; i++) {
            if (Math.abs(arr[i]) < Math.abs(arr[minIndex])) {
                minIndex = i;
            }
        }

        // create two pointers. One moves to the right and one to the left.
        // on each step -> add the smalles by absolut value

        // A BETTER APPROACH: Move from the SIDES of the array and add the lowest square -> much easier to
        // work with border conditions.

        squares[0] = arr[minIndex] * arr[minIndex];
        int nextIndex = 1;
        int left = minIndex - 1;
        int right = minIndex + 1;

        while (true) {
            if (left == -1 && right == arr.length) {
                return squares;
            } else if (left == -1 && right != arr.length) {
                squares[nextIndex] = arr[right] * arr[right];
                right++;
            } else if (left != -1 && right == arr.length) {
                squares[nextIndex] = arr[left] * arr[left];
                left--;
            } else { // both are available -> add the lower by absolut value
                if (Math.abs(arr[left]) < Math.abs(arr[right])) {
                    squares[nextIndex] = arr[left] * arr[left];
                    left--;
                } else {
                    squares[nextIndex] = arr[right] * arr[right];
                    right++;
                }
            }
            nextIndex++;
        }
    }

    public static void main(String[] args) {
        var result= _03_SortedArraySquares.makeSquares(new int[]{-2, -1, 0, 2, 3});
        for (int r: result) // expected: [0, 1, 4, 4, 9]
            System.out.print(r + ", ");
    }
}
