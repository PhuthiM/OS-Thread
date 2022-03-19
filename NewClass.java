/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author phuth
 */
public class NewClass {

    public static void main(String[] args) {
        int n = 4;
        if (n <= 4) {
            n -= 2;
        }
        if (n <= 3) {
            n -= 1;
        }
        if (n <= 2) {
            n -= 3;
        }
        if (n == 1) {
            n = 9;
        }
        System.out.println(n);
    }
}
