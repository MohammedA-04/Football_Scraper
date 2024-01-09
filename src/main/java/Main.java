/** Note: This project designed to help me learn jsoup and perform data analytics

 Web Scrape Rule on FBREF:
 @URL: <a href="https://www.sports-reference.com/bot-traffic.html">...</a>
  * <= 20 requests per minute
  * if violated -> 1hr bot jail session  **/

public class Main {
    public static void main(String[] args) {

        Premier_League.table();

        
        // Errors
        // * GA missing i.e., 18   (now: 43)
        // * GD missing i.e., +25  (now: )
        // *

        // * 43, 18, +25, 45, 2.25, 44.1, 22.6, +21.6, +1.08, Form, Att, Mo Salah
        // Missing ga and gd

        // should be Gf  ga   gd  pts
        // Should be 43, 18, +25, 45, 3

    }
}
