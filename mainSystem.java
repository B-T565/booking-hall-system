import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class mainSystem {
    public static String[] history = new String[50];
    public static int historyIndex = 0;
    public static void displayMenu(){
        System.out.println("============================== WELCOME HALL BOOKING SYSTEM MENU ==============================");
        System.out.println("||                             [[ Application Menu ]]                                       ||");
        System.out.println("||                             [<A>] BOOKING HALL                                           ||");
        System.out.println("||                             [<B>] SHOW HALL                                              ||");
        System.out.println("||                             [<C>] SHOWTIME                                               ||");
        System.out.println("||                             [<D>] REBOOT SHOWTIME                                        ||");
        System.out.println("||                             [<E>] HISTORY                                                ||");
        System.out.println("||                             [<F>] EXIT HALL                                              ||");
        System.out.println("==============================================================================================");
    }
    public static void displayShowtime(){
        System.out.println("============================== #DAILY SHOWTIME OF CSTAD HALL# ================================");
        System.out.println("||                             # A) Morning (10:00AM - 12:30PM)  #                          ||");
        System.out.println("||                             # B) Afternoon (03:00PM - 5:30PM) #                          ||");
        System.out.println("||                             # C) Night (07:00PM - 09:30PM)    #                          ||");
        System.out.println("==============================================================================================");
    }
    public static void displayTitle(){
        System.out.println("==============================================================================================");
        System.out.println("||                                       ACCESS BY CSTAD                                    ||");
        System.out.println("||                                       BOOKING SYSTEM                                     ||");
        System.out.println("==============================================================================================");
    }
    public static boolean displayInputDate(String input, String pattern, String msg){
        Pattern pattern1 = Pattern.compile(pattern);
        Matcher matcher = pattern1.matcher(input);
        return matcher.matches();
    }

    public static void main(String[] args){
        addHistory('a',1,"asd");
        displayTitle();
        String[] history = new String[50];
        Scanner scanner = new Scanner(System.in);
        boolean checkScanner = true;
        String pattern = "[ 1 + 9 ] + ";
        String msg = "Sorry...! Please Input Positive Number Only";
        int rows = 0;
        int cols = 0;
        int total;
        int checkedRow = checkRows(scanner, pattern, msg, rows);
        int checkedCol = checkCols(scanner, pattern, msg, cols);
        total = checkedRow * checkedCol;
        System.out.println("==============================================================================================");
        System.out.println("||                  => Number Of Rows: " + checkedRow + "Rows" + "                          ||");
        System.out.println("||                  => Number Of Seaters per Row: " + checkedCol + "Seaters" + "             ||");
        System.out.println("||                  => Total Seaters: " + total + "Seaters" + "                             ||");
        System.out.println("==============================================================================================");
        optionMenu(checkedRow, checkedCol);
    }
    private static int checkRows(Scanner scanner, String pattern, String msg, int rows){
        do {
            System.out.println("==========================================================================================");
            System.out.println("||                  # Setting: Set row and seater per row #                             ||");
            System.out.print("||                  => Please Config total rows in hall : " + "                           ||");
            String rowHall = scanner.nextLine();
            System.out.println("==========================================================================================");
            if (displayInputDate(rowHall, pattern, msg)){
                rows = Integer.parseInt(rowHall);
                break;
            }
            else {
                System.out.println(msg);
            }
        }while (true);
        return rows;
    }
    private static int checkCols(Scanner scanner, String pattern, String msg, int cols){
        do {
            System.out.println("==========================================================================================");
            System.out.println("||                  => Please Config total seats rows in hall : " + "                   ||");
            String totalSeatRow = scanner.nextLine();
            System.out.println("==========================================================================================");
            if (displayInputDate(totalSeatRow, pattern, msg)){
                cols = Integer.parseInt(totalSeatRow);
                System.out.println("======================== You have successfully set up in hall ========================");
                break;
            }
            else {
                System.out.println(msg);
            }
        }while (true);
        return cols;
    }
//    public static void optionMenu

    private static void alertMsg(){
        System.out.println("=============================== # INSTRUCTION IN CHAIR # =====================================");
        System.out.println("================================== # Single : C-1 # ==========================================");
        System.out.println("========================= # Multiple (Separate by comma) : C-1, C-2 # ========================");
    }
    private static void initHall(String[][] hall){
        for (int i=0; i < hall.length; i++){
            for (int j=0; j< hall[i].length; j++){
                hall[i][j] = "AV";
            }
        }
    }
    private static void showAllHall(String[][] hall){
        char seat = 50;
        for (int i=0; i < hall.length; i++){
            for (int j=0; j < hall[i].length; j++){
                System.out.print("|" + (char)(seat + i) + "-" + (j + 1) + ":: " + hall[i][j] + "|\t");
            }
            System.out.println();
        }
    }
    private static String addHistory(char letter, int numberNo, String idUser){
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = date.format(formatter);
        String seat = letter + "-" + numberNo;
        System.out.println("==============================================================================================");
        return String.format("\n" +
                "\n #Seat : %s" +
                "\n #User ID : %s" +
                "\n #Date : %s",
                seat, idUser, formattedDateTime);
    }
}
