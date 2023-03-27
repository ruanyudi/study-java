package cn.ruanyudi.utils;

public class MyMath {
    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(a, a % b);
    }
}
