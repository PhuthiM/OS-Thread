
import java.util.Random;
import java.util.*;
class Tree {

    private int info;
    private Tree left ;
    private Tree right ;

    public Tree(int info, Tree left, Tree right) {
        this.info = info;
        this.left = left;
        this.right = right;

    }

    public Tree(int info) {
        this(info, null, null);
    }

    public Tree() {
        this(0, null, null);
    }

    public int getInfo() {
        return this.info;
    }

    public void setinfo() {
        this.info = info;
    }

    public Tree getleft() {
        return this.left;
    }

    public Tree getright() {
        return this.right;
    }

    public void setleft(Tree node) {
        this.left = node;
    }

    public void setright(Tree node) {
        this.right = node;
    }

    public static void printPreOrder(Tree tree) {
        if (tree == null) {
            System.out.print(" - ");
            return;
        }
        System.out.print(tree.getInfo());
        System.out.print(" [ ");
        printPreOrder(tree.getleft());
        printPreOrder(tree.getright());
        System.out.print(" ] ");
    }

    public static void insert(Tree tree, int info) {
        if (info < tree.getInfo()) {
            if (tree.getleft() != null) {
                insert(tree.getleft(), info);
            } else {
                tree.setleft(new Tree(info));
            }
        } else {
            if (tree.getright() != null) {
                insert(tree.getright(), info);
            } else {
                tree.setright(new Tree(info));
            }
        }
    }

    public static int height(Tree tree) {
        if (tree == null) {
            return -1;
        }
        return Math.max(1+ height(tree.getleft()) , 1+ height(tree.getright()));
    }

    public static boolean search(Tree tree, int info) {
        boolean ch = false ;
        if (info == tree.getInfo()) {
            System.out.println(" Found! )");
            ch =  true;

        } else if (info < tree.getInfo()) {
            
            if (tree.getleft() == null) {
                System.out.println(" Not Found! )");
                
            } else {
                System.out.print("L");
                Tree.search(tree.getleft(), info);
               ch = false;
            }
        } else {
            if (tree.getright() == null) {
                System.out.println(" Not Found! )");
            } else {
                System.out.print("R");
                Tree.search(tree.getright(), info);
               ch = false ;
            }         
        }
        return ch ;
    }
}

public class findtree {

    public static void main(String[] args) {
        Tree tree = new Tree();
        Random rand = new Random();
        int n = rand.nextInt(11) + 10;
        System.out.println("--- Random numbers [" + n + "] ---");
        for (int i = 0; i < n; i++) {
            int r = rand.nextInt(51);
            System.out.print(r + " ");
            tree.insert(tree, r);
        }
        System.out.println("\n--- Tree (Height = " + Tree.height(tree) + ") ---");
        Tree.printPreOrder(tree);
        System.out.println();
        System.out.println("--- Search ---");
        boolean check = false;
        while(!check) {
            int x = rand.nextInt(51);
            System.out.print(x + ": (");
            check = Tree.search(tree, x);
        }
    }
}
