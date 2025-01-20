package twoPointers;

public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int max = 0;
        while (i < j) {
            if (height[i] < height[j]) {
                max = Math.max(max, height[i] * (j - i));
                i++;
            } else if (height[i] > height[j]) {
                max = Math.max(max, height[j] * (j - i));
                j--;
            } else {
                max = Math.max(max, height[i] * (j - i));
                i++;
                j--;
            }
        }
        return max;
    }
}
