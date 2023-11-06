public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        while (x < 10) {
            int tmp = 0;
            for(int i = 0; i <= x; i ++){
                tmp += i;
            }
            System.out.print(tmp + " ");
            x = x + 1;
        }
    }
}