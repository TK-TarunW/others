/**
 * Created by tarun.walia on 12/8/2016.
 */
public class MaximumSumStrictlyIncreasingSubarray {


    /**
     * Input  : arr[] = {1, 2, 3, 2, 5, 1, 7}
     * Output : 8
     * Explanation :  Some Strictly increasing subarrays are
     * {1, 2, 3} sum = 6,
     * {2, 5} sum = 7,
     * {1, 7} sum 8
     * Maximum Sum = 8
     * <p>
     * Input : arr[] = {1, 2, 2, 4}
     * Output: 6
     * Explanation : Increasing subarray with maximum sum
     * is 6.
     */


    static void findAllIncreasingSubArraysSum(int[] arr) {
        int sum = arr[0];
        int maximumSum = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] < arr[i]) {
                sum = sum + arr[i];
            } else {
                sum = arr[i];
                maximumSum = Math.max(maximumSum,sum);
            }
        }
        maximumSum = Math.max(maximumSum,sum);
        System.out.println("maximumSum = "+maximumSum);
    }


    public static void main(String[] args) {
        findAllIncreasingSubArraysSum(new int[]{1, 2, 3, 2, 5, 1, 7});
        findAllIncreasingSubArraysSum(new int[] {1, 2, 2, 4});
        findAllIncreasingSubArraysSum(new int[] {1, 2, 2, 4,1,12});
    }
}
