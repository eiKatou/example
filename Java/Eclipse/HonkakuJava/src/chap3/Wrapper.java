package chap3;

public class Wrapper {
    public static void main(String[] args) {
        System.out.println("Hello World !");

        // ラッパー型
        Integer intwrap1 = Integer.valueOf(3);
        Integer intwrap2 = Integer.valueOf(3);

        System.out.println("intwrap1:" + intwrap1.toString());
        System.out.println("intwrap2:" + intwrap2.toString());

        System.out.println("equals:" + (intwrap1 == intwrap2));
    }
}