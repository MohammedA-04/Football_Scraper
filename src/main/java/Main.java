import java.util.Scanner;

/** Note: This project designed to help me learn jsoup and perform data analytics

 Web Scrape Rule on FBREF:
 @URL: <a href="https://www.sports-reference.com/bot-traffic.html">...</a>
  * <= 20 requests per minute
  * if violated -> 1hr bot jail session  **/

public class Main {
    static String usrTeam;
    static int s;


    public static void main(String[] args) throws InterruptedException {

        Premier_League.table();
        Thread.sleep(1000*5);
        // time . sleep 10

        {
            Scanner sc = new Scanner(System.in);
            System.out.println("\n\n\u001B[1m\u001B[31mWARNING:\u001B[0m\u001B[1m Fbref have removed the current season table this means only previous seasons can be used and \nnow the code is redundant however this model was working during Fbref time when it table on its website\u001B[0m");
            System.out.println("\nEnter Number Of Page e.g., 2 | enter: 2");
            System.out.println("1 | Calculate Win Percentage for Next Game [For All Teams]");
            System.out.println("2 | Calculate Win Percentage for Next Game [Your Team]");
            System.out.println("3 | Calculate Draw Percentage for Next Game [Your Team]");
            System.out.println("4 | Calculate Loss Percentage for Next Game [Your Team]");
            System.out.println("5 | Exit");
            s = sc.nextInt();

            switch (s){
                case 1:
                    // method call
                    Premier_League.calcWinPercentageForNextGame();
                    break;

                case 2:
                case 3:
                case 4:
                    // if user want % of {win=2, draw=3, loss=4} for his team
                    System.out.println("\nEnter Your Team: (e.g., Liverpool or Aston Villa");
                    usrTeam = sc.nextLine();
                    usrTeam = sc.nextLine();
                    Premier_League.calcWinPercentageForNextGame(usrTeam, s);
                    break;


                case 5:
                    System.exit(0);
                    break;

            }
        }


    }


    public int getS(){
        return s;
    }
}
