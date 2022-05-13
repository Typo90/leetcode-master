import java.util.Scanner;

public class HelloGoodbye {

    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        Scanner sc = new Scanner(System.in);
        String name1 = sc.next();
        String name2 = sc.next();

        String first = "hello " + name1 + " and " + name2;
        String second = "Goodbye " + name2 + " and " + name1;

        System.out.println(first);
        System.out.println(second);

    }

}