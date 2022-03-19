
import java.util.Scanner;

class TriangleSet {

    private int maxItem = 10;
    private int noItem = 0;
    private Triangle[] triangleArr;
    private String title = "";

    public TriangleSet(int max) {
        this.maxItem = max;
        triangleArr = new Triangle[max];
    }

    public TriangleSet(Triangle[] tri) {
        this.triangleArr = tri.clone();
    }

    public TriangleSet(Triangle[] tri, int max) {
        this.maxItem = max;
        this.triangleArr = tri.clone();
    }

    public TriangleSet(TriangleSet triSet) {
        this.maxItem = triSet.getMaxSize();
        add(triSet);
        this.title = triSet.getTitle();
    }

    public TriangleSet(TriangleSet triSet, int max) {
        this.maxItem = max;
        add(triSet);
        this.title = triSet.getTitle();
    }

    public TriangleSet(String title) {
        this.title = title;
        triangleArr = new Triangle[maxItem];
    }

    public TriangleSet(String title, int max) {
        this.maxItem = max;
        this.title = title;
        triangleArr = new Triangle[max];
    }

    public TriangleSet() {
        triangleArr = new Triangle[maxItem];
    }

    void setTitle(String title) {
        this.title = title;
    }

    void add(Triangle tri) {
        triangleArr[noItem] = tri;
        noItem++;
    }

    void add(TriangleSet triSet) {
        for (int i = 0; i < triSet.getSize(); i++) {
            triangleArr[i] = triSet.getItem(i);
        }
        noItem = triSet.getSize();
    }

    void merge(TriangleSet triSet) {
        this.maxItem += triSet.getMaxSize();
        this.noItem += triSet.getSize();
    }

    void removeTitle(String title) {
        this.title = title;
    }

    void removeItem(int no) {
        noItem--;
        triangleArr[no] = triangleArr[noItem];
    }

    void removeAll() {
        triangleArr = new Triangle[maxItem];
        noItem = 0;
    }

    void inputData() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter triangle's title > ");
        String title = sc.next();
        System.out.print("Is it a equilateral triangle (y/n) ? ");
        char equal = sc.next().charAt(0);
        Triangle triangle;
        if (equal == 'y' || equal == 'Y') {
            System.out.print("Enter side length > ");
            double side = sc.nextDouble();
            triangle = new Triangle(side, title);
        } else {
            System.out.print("Enter base length > ");
            double base = sc.nextDouble();
            System.out.print("Enter height length > ");
            double height = sc.nextDouble();
            triangle = new Triangle(base, height, title);
        }
        add(triangle);
    }

    int getSize() {
        return noItem;
    }

    int getMaxSize() {
        return maxItem;
    }

    boolean isAllEquilateral() {
        boolean check = true;
        for (int i = 0; i < noItem; i++) {
            if (!triangleArr[i].isEquilateral()) {
                check = false;
            }
        }
        return check;
    }

    boolean isEmpty() {
        return noItem == 0;
    }

    boolean isFull() {
        return noItem == maxItem;
    }

    double getSumArea() {
        double sum = 0;
        for (int i = 0; i < noItem; i++) {
            sum += triangleArr[i].getArea();
        }
        return sum;
    }

    Triangle getItem(int no) {
        return triangleArr[no];
    }

    String getTitle() {
        return title;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < noItem; i++) {
            s += triangleArr[i];
        }
        return title + " " + maxItem + ":" + noItem + "\n" + s;
    }
}

class Triangle {

    String title = "";
    private double base = 1, height = 1, side;
    private boolean equalSide = false;

    Triangle(double base, double height, String title) {
        this.base = base;
        this.height = height;
        this.title = title;
        equalSide = false;
    }

    Triangle(double base, double height) {
        this.base = base;
        this.height = height;
        equalSide = false;
    }

    Triangle(double side, String title) {
        this.side = side;
        this.title = title;
        equalSide = true;
    }

    Triangle(double side) {
        this.side = side;
        equalSide = true;
    }

    Triangle(String title) {
        this.title = title;
    }

    Triangle() {
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    double getBase() {
        return base;
    }

    void setBase(double base) {
        this.base = base;
    }

    double getHeight() {
        return height;
    }

    void setHeight(double height) {
        this.height = height;
    }

    double getSide() {
        return side;
    }

    void setSide(double side) {
        this.side = side;
    }

    void setBaseHeight(double base, double height) {
        this.base = base;
        this.height = height;
    }

    boolean isEquilateral() {
        return equalSide;
    }

    double getArea() {
        if (equalSide) {
            return Math.sqrt(3) / 4 * side * side;
        }
        return 0.5 * base * height;
    }

    public String toString() {
        if (equalSide) {
            return title + "[" + side + ":" + getArea() + "]\n";
        }
        return title + "[" + base + ":" + height + "=>" + getArea() + "]\n";
    }
}

public class ClassTriangle {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TriangleSet triSet = new TriangleSet("TriSetX");
        System.out.print("Enter TriangleSet’s title [Current: " + triSet.getTitle()
                + " , - <NONE>, Return <Not Change> ] > ");
        String titleSet = sc.nextLine();
        triSet.removeTitle(titleSet);
        for (int i = 4;; i++) {
            System.out.print("[" + i + "]" + " Enter triangle's title > ");
            String title = sc.nextLine();
            if (title.equalsIgnoreCase("-")) {
                break;
            }
            System.out.print("Is it a equilateral triangle (y/n) ? ");
            char equal = sc.next().charAt(0);
            Triangle triangle;
            if (equal == 'y' || equal == 'Y') {
                System.out.print("Enter side length > ");
                double side = sc.nextDouble();
                triangle = new Triangle(side, title);
            } else {
                System.out.print("Enter base length > ");
                double base = sc.nextDouble();
                System.out.print("Enter height > ");
                double height = sc.nextDouble();
                triangle = new Triangle(base, height, title);
            }
            System.out.println("");
            sc.nextLine();
            triSet.add(triangle);
        }
    }
}
