
import java.util.Scanner;

public class MapMarking {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        int map[][] = new int[r + 1][c + 1];
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                map[i][j] = 1;
            }
        }
        int startr = sc.nextInt();
        int startc = sc.nextInt();
        int now[][] = new int[r + 1][c + 1];
        int n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            int num = sc.nextInt();
            System.out.println(num);
        }
    }
}
