
import java.util.Scanner;

public class ArrayJump1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] array = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            array[i] = scan.nextInt();
        }
        int position = 1;
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (position > n||count == 5) {
                if(count == 5){
                    break;
                }
                System.out.print("out of bounds");
                break;
            }
             else {
                ++count;
                System.out.print(position + " " + array[position]);
                position += array[position];
            }
            System.out.println("");
        }
    }
}
