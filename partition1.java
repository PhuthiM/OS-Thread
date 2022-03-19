import java.util.Scanner;
public class partition1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(); 
        int[] array = new int[n+1];
        for(int i=0 ; i<n ; ++i){
            array[i] = scan.nextInt();
        }
        int mid = (n/2);
        //int posmid = scan.nextInt();        
        for(int i=0 ; i<n ; ++i){
            if(array[mid] >= array[i]){
                System.out.print(array[i] + " ");                
            }
        }
        System.out.println(" ");
        for(int i=0 ; i<n ; ++i){
            if(array[mid] < array[i]){
                System.out.print(array[i] + " ");               
            }
        }
        //System.out.print("\n");
    }
}
                   