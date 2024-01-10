import java.util.Scanner;

/** Note: This project designed to help me learn jsoup and perform data analytics

 Web Scrape Rule on FBREF:
 @URL: <a href="https://www.sports-reference.com/bot-traffic.html">...</a>
  * <= 20 requests per minute
  * if violated -> 1hr bot jail session  **/

public class Main {
    static String usrTeam;


    public static void main(String[] args) throws InterruptedException {

        Premier_League.table();
        Thread.sleep(1000*5);
        // time . sleep 10

        {
            Scanner sc = new Scanner(System.in);
            System.out.println("\nEnter Number Of Page e.g., 2 | enter: 2");
            System.out.println("1 | Calculate Win Percentage for Next Game [For All Teams]");
            System.out.println("2 | Calculate Win Percentage for Next Game [For Your Team]");
            System.out.println("3 | Exit");
            int s = sc.nextInt();

            switch (s){
                case 1:
                    // method call
                    Premier_League.calcWinPercentageForNextGame();
                    break;
                case 2:
                    // if user want % of winning for his team
                    System.out.println("\nEnter Your Team: (e.g., Liverpool or Aston Villa");
                    usrTeam = sc.nextLine();
                    usrTeam = sc.nextLine();
                    Premier_League.calcWinPercentageForNextGame(usrTeam);
                    // TODO: loop fields -> add to hashmap -> check if contains -> return w% for x team
                    // Premier_League.calcWinPercentageForNextGame();
                case 3:
                    System.exit(0);
                    break;

            }
        }


    }
}
