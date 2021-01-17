package class01;

/**
 * Created by wwg on 2021/1/16 0016
 * 打印一个int类型的的32位二进制
 */
public class Code_01 {


    public static void main(String[] args) {

        int num = 16;

        print(num);
    }

    public static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
    }
}
