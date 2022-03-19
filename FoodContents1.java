
import java.util.Scanner;

class Food {

    String name;
    String contentName1, contentName2, contentName3;
    int w1, w2, w3;

    Food(String name, String contentName1, int w1) {
        this.name = name;
        this.contentName1 = contentName1;
        this.w1 = w1;
    }

    void addContent(String contentName, int w) {
        if (w2 == 0) {
            contentName2 = contentName;
            w2 = w;
        } else if (w3 == 0) {
            contentName3 = contentName;
            w3 = w;
        }
    }

    void printInfo() {
        System.out.println(name);
        int totalWeight = w1 + w2 + w3;
        System.out.println(totalWeight);
        System.out.print(contentName1 + " ");
        if (w2 > 0) {
            System.out.print(contentName2 + " ");
        }
        if (w3 > 0) {
            System.out.print(contentName3 + " ");
        }
        System.out.println();
    }
}

public class FoodContents1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String foodName = scan.next();
        String content1 = scan.next();
        int w1 = scan.nextInt();
        Food f = new Food(foodName, content1, w1);
        int q = scan.nextInt();
        while (q != 0) {
            if (q == 1) {
                f.printInfo();
            } else if (q == 2) {
                String content = scan.next();
                int w = scan.nextInt();
                f.addContent(content, w);
            }
            q = scan.nextInt();
        }
    }
}
