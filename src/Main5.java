import java.time.LocalTime;
import java.util.Date;

public class Main5 {

    public static void main(String[] args) {
        LocalTime localTime = LocalTime.now();
        System.out.println("Local time is: " + localTime);

        Date globalTime = new Date();
        System.out.println("Global time is: " + globalTime.getTime());
    }
}
