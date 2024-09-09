import java.util.Scanner;
class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcases = sc.nextInt();
        for (int i = 0; i < testcases; i++) {
            int total = sc.nextInt();
            int pos = sc.nextInt();
            int counter = 0;
            for (int k = 2; k < total; k++) {
                int temp = (total) % (2*k - 2);
                if(temp == 0)
                    temp = 2;

                if(temp <= k) {
                    if (pos == temp)
                        counter++;
                }
                else
                if(2*k - temp == pos)
                    counter++;
                System.out.println(temp);
                //1234321234
            }
        }
    }
}