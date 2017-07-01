public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World !");
        
        // 文字列の逆転
        String str = "Hello World";
        StringBuffer sb = new StringBuffer(str);
        sb.reverse();
        System.out.println(sb.toString());
    }   
}
