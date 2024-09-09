import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    //    System.out.println("enter number of test cases");
        int test = sc.nextInt();
        for (int i = 0; i < test; i++) {
    //        System.out.println("enter left and right");
            int left = sc.nextInt();
            int right = sc.nextInt();
            int k = 1;
            int l = 2;
            int max = 1;
            int numbers = 0;
            while (k == 1) {
                if (left * l <= right) {
                    max += 1;
                    l *= 2;
                } else k = 0;
            }
            l/=2;
            int m = (right / l);
            numbers+=(m-left+1)%998244353;
            int p = (right / (l * 3 / 2));
            if (p >= left)
            numbers+=((p-left+1)*((max - 1)%998244353)) % 998244353;
//            for (int j = left; j <= right; j++) {
//                if (j*l <= right){
//                    numbers = (numbers+1)%998244353 ;
//                    if ((j*l)*3/2 <= right){
//                        numbers = (numbers+max-1) % 998244353;
//                    }
//                }
//            }
            System.out.println(max  +  " " + numbers);
        }
    }
}