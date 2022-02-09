package codesample.other.educative._02_two_pointers;

public class _03_SortedArraySquaresEasier {

    public static int[] makeSquares(int[] arr) {
        int[] squares = new int[arr.length];

        // Move from the SIDES of the array and add the lowest square -> much easier to
        // work with border conditions.

        int left = 0;
        int right = arr.length - 1;
        int nextInsertPoint = squares.length - 1;

        while (right >= left) {
            if (Math.abs(arr[right]) > Math.abs(arr[left])) {
                squares[nextInsertPoint] = arr[right] * arr[right];
                right--;
            } else {
                squares[nextInsertPoint] = arr[left] * arr[left];
                left++;
            }

            nextInsertPoint--;
        }

        return squares;
    }

    public static void main(String[] args) {
        var result= _03_SortedArraySquaresEasier.makeSquares(new int[]{-2, -1, 0, 2, 3});
        for (int r: result) // expected: [0, 1, 4, 4, 9]
            System.out.print(r + ", ");

        System.out.println();

        result = _03_SortedArraySquaresEasier.makeSquares(new int[]{-7,-3,2,3,11});
        for (int r: result) // expected: [4,9,9,49,121]
            System.out.print(r + ", ");
    }
}
