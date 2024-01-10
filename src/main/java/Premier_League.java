import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashMap;

public class Premier_League {

    // Regular Season i.e., Current
    static String url = "https://fbref.com/en/comps/9/Premier-League-Stats";
    static Element tableBody;
    static Element tableHeading;
    static Elements columns;
    static Boolean bol;
    static HashMap<String, String> nameForm;

    // static int rank, squad, mp, w, d, l, gf, ga, pts, gd, xG, xGA, xGD, AttMp, TS;

    public static void table() {
        try {
            // Connect to URL and GET() HTML document
            Document document = (Document) Jsoup.connect(url).get();

            // Selects table heading element for regular season in PL
            tableHeading = document.select("thead").first(); // is Element

            // Selects 1st <tbody> in <thead>
            // tableBody is Element
            tableBody = document.select("tbody").first();

            // Method for format -> gets widths
            format();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void format() throws IOException {

        /**
         * Element Structure: body -> row -> head & data
         * <p>
         * <tbody>
         *  |-- <tr data-row="0">...</tr> // club #1
         *
         *      |-- <th data-stat="rank">1</th>
         *      |-- <td class... data-stat="team"></td>
         *      |-- ... more
         *      |-- <td class... data-stat="points">45</td>
         *
         *  |-- <tr data row="19">...</tr>
         * </tbody>
         **/

        // Local Var: "%-s" width formatting
        int rank = 5, team = 0, mp = 0, w = 0, d = 0, l = 0, gf = 0, ga = 0, pts = 0, ptsMp = 0, gd = 0, xG = 0, xGA = 0, xGD = 0, xGDper90 = 0, last5 = 0, AttMp = 0, TS = 0, note = 0;
        int currentWidth = 0;

        // Iterate each {tr} in {tbody} represented aa Element $row
        for (Element row : tableBody.select("tr")) {

            // In 0-19 {tr} it contains 20+ {td} pieces of data
            columns = row.select("td");

            // loops each {td} in {table row(s)} gets width
            for (int i = 0; i < columns.size(); i++) {
                Element column = columns.get(i);

                // ignore keeper row:col
                if (!"top_keeper".equals(column.attr("data-stat"))) {
                    currentWidth = column.text().length();

                    if ("team".equals(column.attr("data-stat")) && currentWidth > team) {
                        team = currentWidth;
                        if ("Club".length() > team) {
                            team = "Club".length();
                        }
                    }

                    if ("games".equals(column.attr("data-stat")) && currentWidth > mp) {
                        mp = currentWidth;
                        if ("MP".length() > mp) {
                            mp = "MP".length();
                        }
                    }

                    if ("wins".equals(column.attr("data-stat")) && currentWidth > w) {
                        w = currentWidth;
                        // if ("W".length() > w){w = "W".length();}
                    }

                    if ("ties".equals(column.attr("data-stat")) && currentWidth > d) {
                        d = currentWidth;
                    }

                    if ("losses".equals(column.attr("data-stat")) && currentWidth > l) {
                        l = currentWidth;
                    }

                    if ("goals_for".equals(column.attr("data-stat")) && currentWidth > gf) {
                        gf = currentWidth;
                        if ("GF".length() > gf) {
                            gf = "GF".length();
                        }
                    }

                    if ("goals_against".equals(column.attr("data-stat")) && currentWidth > ga) {
                        ga = currentWidth;
                        if ("GA".length() > ga) {
                            ga = "GA".length();
                        }
                    }

                    // fixed: contained "goals_diff" -> now: "goal_diff"
                    if ("goal_diff".equals(column.attr("data-stat")) && currentWidth > gd) {
                        gd = currentWidth;
                        if ("GD".length() > gd) {
                            gd = "GD".length();
                        }
                    }

                    if ("points".equals(column.attr("data-stat")) && currentWidth > pts) {
                        pts = currentWidth;
                        if ("Pts".length() > pts) {
                            pts = "Pts".length();
                        }
                    }

                    if ("points_avg".equals(column.attr("data-stat")) && currentWidth > ptsMp) {
                        ptsMp = currentWidth;
                        if ("Pts/Mp".length() > ptsMp) {
                            ptsMp = "Pts/Mp".length();
                        }
                    }

                    if ("xg_for".equals(column.attr("data-stat")) && currentWidth > xG) {
                        xG = currentWidth;
                        if ("xG".length() > xG) {
                            xG = "xG".length();
                        }
                    }

                    if ("xg_against".equals(column.attr("data-stat")) && currentWidth > xGA) {
                        xGA = currentWidth;
                        if ("xGA".length() > xGA) {
                            xGA = "xGA".length();
                        }
                    }

                    if ("xg_diff".equals(column.attr("data-stat")) && currentWidth > xGD) {
                        xGD = currentWidth;
                        if ("xGD".length() > xGD) {
                            xGD = "xGD".length();
                        }
                    }

                    if ("goals_for".equals(column.attr("data-stat")) && currentWidth > xGDper90) {
                        xGDper90 = currentWidth;
                        if ("xGD/90".length() > xGDper90) {
                            xGDper90 = "xGD/90".length();
                        }
                    }

                    if ("last_5".equals(column.attr("data-stat")) && currentWidth > last5) {
                        last5 = currentWidth;
                        if ("Form".length() > last5) {
                            last5 = "Form".length();
                        }
                    }

                    if ("attendance_per_g".equals(column.attr("data-stat")) && currentWidth > AttMp) {
                        AttMp = currentWidth;
                        if ("Att/Home-Game".length() > AttMp) {
                            AttMp = "Att/Home-Game".length();
                        }
                    }

                    if ("top_team_scorers".equals(column.attr("data-stat")) && currentWidth > TS) {
                        TS = currentWidth;
                        if ("Top Scorer".length() > TS) {
                            TS = "Top Scorer".length();
                        }
                    }


                    if ("notes".equals(column.attr("data-stat")) && currentWidth > note) {
                        note = currentWidth;
                        if ("Note".length() > note) {
                            note = "Note".length();
                        }
                    }


                }
            }

        }


        // set out the {tr} headings -> print headings
        for (Element th : tableHeading.select("tr th")) {

            String dsv = th.attr("data-stat");

            switch (dsv) {
                case "rank":
                    System.out.printf("%-" + rank + "s | ", "Rank");
                    break;
                case "team":
                    System.out.printf("%-" + team + "s | ", "Club");
                    break;
                case "games":
                    System.out.printf("%-" + mp + "s | ", "MP");
                    break;
                case "wins":
                    System.out.printf("%-" + w + "s | ", "W");
                    break;
                case "ties":
                    System.out.printf("%-" + d + "s | ", "D");
                    break;
                case "losses":
                    System.out.printf("%-" + l + "s | ", "L");
                    break;
                case "goals_for":
                    System.out.printf("%-" + gf + "s | ", "GF");
                    break;
                case "goals_against":
                    System.out.printf("%-" + ga + "s | ", "GA");
                    break;
                case "goal_diff":
                    System.out.printf("%-" + gd + "s | ", "GD");
                    break;
                case "points":
                    System.out.printf("%-" + pts + "s | ", "Pts");
                    break;
                case "points_avg":
                    System.out.printf("%-" + ptsMp + "s | ", "Pts/Mp");
                    break;
                case "xg_for":
                    System.out.printf("%-" + xG + "s | ", "xG");
                    break;
                case "xg_against":
                    System.out.printf("%-" + xGA + "s | ", "xGA");
                    break;
                case "xg_diff":
                    System.out.printf("%-" + xGD + "s | ", "xGD");
                    break;
                case "xg_diff_per90":
                    System.out.printf("%-" + xGDper90 + "s | ", "xGD/90");
                    break;
                case "last_5":
                    System.out.printf("%-" + last5 + "s | ", "Form");
                    break;
                case "attendance_per_g":
                    System.out.printf("%-" + AttMp + "s | ", "Att/Home-Game");
                    break;
                case "top_team_scorers":
                    System.out.printf("%-" + TS + "s | ", "Top Scorer");
                    break;
                case "notes":
                    System.out.printf("%-" + note + "s | ", "Note");
                    break;


            }

        }
        System.out.println();


        // Prints the BODY of regular season table
        for (Element row : tableBody.select("tr")) {
            // Iterate each {td} in {tr}
            Elements columns = row.select("td");

            // Each {tr} has a {th} for position
            Elements teamPositions = row.select("th");
            Element teamPos = teamPositions.first();
            System.out.printf("%-" + (rank) + "s | ", teamPos.text());

            for (Element column : columns) {

                // ignores field "top_keeper" in {tbody}
                if (!"top_keeper".equals(column.attr("data-stat"))) {
                    // sout text content of each table data {td} element


                    // col.text == rank print using width of rank=x?

                    if ("team".equals(column.attr("data-stat"))) {
                        System.out.printf("%-" + team + "s | ", column.text());

                    } else if (("games".equals(column.attr("data-stat")))) {
                        System.out.printf("%-" + mp + "s | ", column.text());

                    }
                    // here add
                    else if (("wins").equals(column.attr("data-stat"))) {
                        System.out.printf("%-" + w + "s | ", column.text());

                    } else if (("ties".equals(column.attr("data-stat")))) {
                        System.out.printf("%-" + d + "s | ", column.text());

                    } else if (("losses".equals(column.attr("data-stat")))) {
                        System.out.printf("%-" + l + "s | ", column.text());

                    } else if (("goals_for".equals(column.attr("data-stat")))) {
                        System.out.printf("%-" + gf + "s | ", column.text());

                    } else if (("goals_against".equals(column.attr("data-stat")))) {
                        System.out.printf("%-" + ga + "s | ", column.text());

                    } else if (("goal_diff".equals(column.attr("data-stat")))) {
                        System.out.printf("%-" + gd + "s | ", column.text());

                    } else if (("points".equals(column.attr("data-stat")))) {
                        System.out.printf("%-" + pts + "s | ", column.text());

                    } else if (("points_avg".equals(column.attr("data-stat")))) {
                        System.out.printf("%-" + ptsMp + "s | ", column.text());

                    } else if (("xg_for".equals(column.attr("data-stat")))) {
                        System.out.printf("%-" + xG + "s | ", column.text());

                    } else if (("xg_against".equals(column.attr("data-stat")))) {
                        System.out.printf("%-" + xGA + "s | ", column.text());

                    } else if (("xg_diff".equals(column.attr("data-stat")))) {
                        System.out.printf("%-" + xGD + "s | ", column.text());

                    } else if (("xg_diff_per90".equals(column.attr("data-stat")))) {
                        System.out.printf("%-" + xGDper90 + "s | ", column.text());

                    } else if (("last_5".equals(column.attr("data-stat")))) {
                        System.out.printf("%-" + last5 + "s | ", column.text());

                    } else if (("attendance_per_g".equals(column.attr("data-stat")))) {
                        System.out.printf("%-" + AttMp + "s | ", column.text());

                    } else if (("top_team_scorers".equals(column.attr("data-stat")))) {
                        System.out.printf("%-" + TS + "s | ", column.text());

                    } else if (("notes".equals(column.attr("data-stat")))) {
                        System.out.printf("%-" + note + "s | ", column.text());

                    }


                }
            }

            System.out.println();
        }
    }


    public static void calcWinPercentageForNextGame() {

        for (Element body : tableBody.getAllElements()) {

            // row suppose to get trs;
            Elements rows = body.select("tr");

            for (Element row: rows){

                Elements td = row.select("td[data-stat=last_5]");

                if (!td.isEmpty()) {
                    // get team and form
                    String name = row.select("td[data-stat=team] a[href]").text();
                    String form = row.select("td[data-stat=last_5] div[style] a[href] ").text();

                    // get mp and w
                    String matches = row.select("td[data-stat=games]").text();
                    String wins = row.select("td[data-stat=wins]").text();

                    // parse mp and w
                    int mp = Integer.parseInt(matches);
                    int w = Integer.parseInt(wins);

                    // replace [^w] everything except w
                    double winRate = (double) w / mp;
                    int formCount = form.replaceAll("[^W]", "").length();
                    double formRate = (double) formCount / 5;

                    double winPercent = ((winRate + formRate) /2) * 100;

                    // Use %% to print % in printf
                    System.out.printf("%s Win Percentage is %.2f%%\n", name, winPercent);

                } else {
                    System.out.println("not found");
                }

            }



            }
        }

    public static void calcWinPercentageForNextGame(String usrTeam){

         /** Logic Overview
          * #1 Scrap tbody
          * #2 Select table row
          * #3 get each data-stat="team" if equals to <userTeam>
          * #4 then calc w%
          * #5 else repeat #3 until end then if !found return "not found"
          * **/

        for (Element body : tableBody.getAllElements()) {

            // row suppose to get trs;
            Elements rows = body.select("tr");

            for (Element row: rows){

                Elements td = row.select("td[data-stat=last_5]");

                if (!td.isEmpty()) {
                    // get team name see if equals <userTeam>
                    String name = row.select("td[data-stat=team] a[href]").text();

                    if (name.equalsIgnoreCase(usrTeam)){


                        String form = row.select("td[data-stat=last_5] div[style] a[href] ").text();

                        // get mp and w
                        String matches = row.select("td[data-stat=games]").text();
                        String wins = row.select("td[data-stat=wins]").text();

                        // parse mp and w
                        int mp = Integer.parseInt(matches);
                        int w = Integer.parseInt(wins);

                        // replace [^w] everything except w
                        double winRate = (double) w / mp;
                        int formCount = form.replaceAll("[^W]", "").length();
                        double formRate = (double) formCount / 5;

                        double winPercent = ((winRate + formRate) / 2) * 100;

                        // Use %% to print % in printf
                        System.out.printf("\n%s has a win percentage of %.2f%% for their next game\n", name, winPercent);
                        return;

                }

            } else {
                    System.out.println("null");
                }
    }

    // Now in hashmap name and form

    // TODO: we need to also make String into 5 get W's only
    // TODO: we need assign each instance of 'W' as w++
    // TODO: Wins/MP = x% || FORM/5 = y% -> x+y=xy/2*100=Ans

        }
    }
}

