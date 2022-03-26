import java.util.Arrays;

public class Main6 {

    public static void main(String[] args) {
        String text = "line 1/line 2";
        String[] lines = text.split("/");

        System.out.println(Arrays.toString(lines));
    }
}
