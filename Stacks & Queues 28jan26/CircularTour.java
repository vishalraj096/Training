public class CircularTour {
    public static int findStart(int[] petrol, int[] distance) {
        int n = petrol.length;
        int start = 0, curr = 0, deficit = 0;
        for (int i = 0; i < n; i++) {
            curr += petrol[i] - distance[i];
            if (curr < 0) {
                deficit += curr;
                start = i + 1;
                curr = 0;
            }
        }
        return (curr + deficit) >= 0 ? (start % n) : -1;
    }

    public static void main(String[] args) {
        int[] petrol = { 4, 6, 7, 4 };
        int[] dist = { 6, 5, 3, 5 };
        System.out.println(findStart(petrol, dist));
    }
}
