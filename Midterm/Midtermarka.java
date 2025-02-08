package sk.upjs.midterm;

import sk.upjs.jpaz2.*;

public class Midtermarka extends Turtle {

    // Task 1
    public void christmastree(int size, double radius) {

    }

    // Task 2
    public boolean divisibleDigitsSums(int m, int n) {
        if (m <= 0 || n <= 0) return false;
        if (m == 1 || n == 1) return true;

        int newM = m;
        int newN = n;
        int sumM = 0;
        int sumN = 0;
        int tempM = 0;
        int tempN = 0;

        while (newM > 0) {
            sumM += newM % 10;
            newM /= 10;
        }
        while (newN > 0) {
            sumN += newN % 10;
            newN /= 10;
        }

        for (int i = 1; i < n; i++) {
            tempN = sumN * i;
            if (tempN == sumM) return true;
        }

        for (int i = 1; i < m; i++) {
            tempM = sumM * i;
            if (tempM == sumN) return true;
        }

        return false;
    }

    // Task 3
    public int containsTwinChar(String s) {
        if (s == null) return 0;

        int count = 0;
        int wordCount = 0;
        char lastChar = ' ';

        s = s.toLowerCase();

        for (int i = 0; i < s.length(); i++) {
            if (i == 0) {
                lastChar = s.charAt(i);
                continue;
            }
            if (Character.isSpaceChar(s.charAt(i)) || i == s.length() - 1) {
                if (count != 0) {
                    wordCount++;
                    count = 0;
                }
            }
            if (s.charAt(i) == lastChar) count++;
            lastChar = s.charAt(i);
        }

        return wordCount;
    }

}
