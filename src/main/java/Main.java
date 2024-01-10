import java.util.Scanner;

/** Note: This project designed to help me learn jsoup and perform data analytics

 Web Scrape Rule on FBREF:
 @URL: <a href="https://www.sports-reference.com/bot-traffic.html">...</a>
  * <= 20 requests per minute
  * if violated -> 1hr bot jail session  **/

public class Main {
    public static void main(String[] args) {

        Premier_League.table();
        // time . sleep 10

        {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Number Of Page e.g., 2 | enter: 2");
            System.out.println("1 | Calculate Win Percentage for Next Game");
            System.out.println("2 | Exit");
            int s = sc.nextInt();

            switch (s){
                case 1:
                    // method call
                    Premier_League.calcWinPercentageForNextGame();
                    break;
                case 2:
                    System.exit(0);
                    break;

            }
        }


    }
}
