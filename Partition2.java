
import java.util.Scanner;

public class Partition2 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] array = new int[n + 1];
        int count = 0;
        for (int i = 1; i <= n; ++i) {
            array[i] = scan.nextInt();
        }
        int[] arrayp = new int[n + 1];
        int mid = (n / 2) + 1;
        for (int i = 1; i <= n; ++i) {
            if (array[mid] >= array[i]) {
                ++count;
                arrayp[count] = array[i];
            }
        }
        int mid2 = (count/2)+1;
        for (int i = 1 ; i <= count ; ++i){
            if (arrayp[mid2] >= arrayp[i]) {                
                System.out.print(arrayp[i] + " ");                
            }            
        } 
        System.out.println(""); 
        for (int i = 1 ; i <= count ; ++i){
            if (arrayp[mid2] < arrayp[i]) {                
                System.out.print(arrayp[i] + " ");                
            }            
        }       
    }

}
