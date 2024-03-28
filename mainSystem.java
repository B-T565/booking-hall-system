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
        System.out.println("||                             [<1>] BOOKING HALL                                           ||");
        System.out.println("||                             [<2>] SHOW HALL                                              ||");
        System.out.println("||                             [<3>] SHOWTIME                                               ||");
        System.out.println("||                             [<4>] REBOOT SHOWTIME                                        ||");
        System.out.println("||                             [<5>] HISTORY                                                ||");
        System.out.println("||                             [<6>] EXIT HALL                                              ||");
        System.out.println("==============================================================================================");
    }
    public static void displayShowtime(){
        System.out.println("============================== #DAILY SHOWTIME OF CSTAD HALL# ================================");
        System.out.println("||                             # 1) Morning (10:00AM - 12:30PM)  #                          ||");
        System.out.println("||                             # 2) Afternoon (03:00PM - 5:30PM) #                          ||");
        System.out.println("||                             # 3) Night (07:00PM - 09:30PM)    #                          ||");
        System.out.println("==============================================================================================");
    }
    public static void displayTitle(){
        System.out.println("==============================================================================================");
        System.out.println("" +
                " ██████╗███████╗████████╗ █████╗ ██████╗     ██████╗  ██████╗  ██████╗ ██╗  ██╗██╗███╗   ██╗ ██████╗   \n" +
                "██╔════╝██╔════╝╚══██╔══╝██╔══██╗██╔══██╗    ██╔══██╗██╔═══██╗██╔═══██╗██║ ██╔╝██║████╗  ██║██╔════╝   \n" +
                "██║     ███████╗   ██║   ███████║██║  ██║    ██████╔╝██║   ██║██║   ██║█████╔╝ ██║██╔██╗ ██║██║  ███╗  \n" +
                "██║     ╚════██║   ██║   ██╔══██║██║  ██║    ██╔══██╗██║   ██║██║   ██║██╔═██╗ ██║██║╚██╗██║██║   ██║  \n" +
                "  ╚██████╗███████║   ██║   ██║  ██║██████╔╝    ██████╔╝╚██████╔╝╚██████╔╝██║  ██╗██║██║ ╚████║╚██████╔╝\n" +
                "   ╚═════╝╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═════╝     ╚═════╝  ╚═════╝  ╚═════╝ ╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝ ╚═════╝   ");
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
        String pattern = "[1-9]+";
        String msg = "Sorry...! Please Input Positive Number Only";

        int rows = 0, cols = 0;
        int checkedRow = checkRows(scanner, pattern, msg, rows);
        int checkedCol = checkCols(scanner, pattern, msg, cols);
        int total = checkedRow * checkedCol;

        System.out.println("==============================================================================================");
        System.out.println("||                  => Number Of Rows: " + checkedRow + "Rows" + "                          ||");
        System.out.println("||                  => Number Of Seaters per Row: " + checkedCol + "Seaters" + "            ||");
        System.out.println("||                  => Total Seaters: " + total + "Seaters" + "                             ||");
        System.out.println("==============================================================================================");
        optionMenu(checkedRow, checkedCol);
    }
    private static int checkRows(Scanner scanner, String pattern, String msg, int rows){
        do {
            System.out.println("==============================================================================================");
            System.out.println("||                  # Setting: Set row and seater per row #                                 ||");
            System.out.print("||                  => Please Config total rows in hall : ");
            String rowInHall = scanner.nextLine();
            System.out.println("==============================================================================================");
            if (displayInputDate(rowInHall, pattern, msg)){
                rows = Integer.parseInt(rowInHall);
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
            System.out.println("==============================================================================================");
            System.out.print("||                  => Please Config total seats rows in hall : ");
            String totalSeatRow = scanner.nextLine();
            System.out.println("==============================================================================================");
            if (displayInputDate(totalSeatRow, pattern, msg)){
                cols = Integer.parseInt(totalSeatRow);
                System.out.println("======================== You have successfully set up in hall ============================");
                break;
            }
            else {
                System.out.println(msg);
            }
        }while (true);
        return cols;
    }
    private static void bookingSeat(String[][] hall, Scanner input){
        System.out.print("Enter Seats: ");
        String inputUser = input.nextLine();
        System.out.print("Enter UserID: ");
        String[] seatArray = inputUser.split(",");
        String userID = input.nextLine();
        String addedHistory = "";

        for (String seat : seatArray) {
            String[] seatSplit = seat.split("-");

            if (seatSplit.length != 2) {
                System.out.println("Invalid seat format: " + seat);
                continue;
            }

            char letterResult = seatSplit[0].charAt(0);
            int number = Integer.parseInt(seatSplit[1]);

            for (int i = 0; i < hall.length; i++) {
                for (int j = 0; j < hall[i].length; j++) {
                    if (hall[i][j].equals("AV")) {
                        char seatChar = (char) ('A' + i);
                        if (letterResult == seatChar && number == (j + 1)) {
                            hall[i][j] = "BO";
                            addedHistory = addHistory(letterResult, number, userID);
                            if (historyIndex < history.length) {
                                history[historyIndex] = addedHistory;
                                historyIndex++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("============================== #BOOKING SUCCESSFULLY....!# ===================================");
    }
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
        char seat = 65;
        for (int i=0; i < hall.length; i++){
            for (int j=0; j < hall[i].length; j++){
                System.out.print("|" + (char)(seat + i) + "-" + (j + 1) + ":: " + hall[i][j] + "|\t");
            }
            System.out.println();
        }
    }
    private static String addHistory(char letter, int number, String UserId){
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        String formattedDateTime = date.format(formatter);
        String seat = letter + "-" + number;
        System.out.println("==============================================================================================");
        return String.format(
                        "\n #Seat : %s" +
                        "\n #User ID : %s" +
                        "\n #Date : %s",
                        seat, UserId, formattedDateTime);
    }
    public static void optionMenu(int rowHall, int colHall){
        String[][] hall1 = new String[rowHall][colHall];
        String[][] hall2 = new String[rowHall][colHall];
        String[][] hall3 = new String[rowHall][colHall];

        initHall(hall1);
        initHall(hall2);
        initHall(hall3);
        String[][] newHall = new String[rowHall][colHall];

        boolean optionCheck = true;
        Scanner scanner1 = new Scanner(System.in);
        String pattern = "[1-9]+";
        String msg = "Sorry...! Please Input Positive Number Only";

        int optionCase = 0;
        char seat = 65;
        int hall = 0;
        char ch;

        do {
            displayMenu();
            System.out.print("=> Please Select Menu Item/No: ");
            String option = scanner1.nextLine();
            if (displayInputDate(option, pattern, msg)){
                optionCase = Integer.parseInt(option);
                if (optionCase > 6){
                    System.out.println("Sorry...! You can choose only...!");
                    optionCheck = true;
                }
                else {
                    switch (optionCase){
                        case 1 ->{
                            boolean checkHall = true;
                            do {
                                System.out.println("============================== WELCOME START HALL BOOKING SYSTEM ==============================");
                                displayShowtime();
                                System.out.print("=> Please Select Showtime ( 1 | 2 | 3 ): ");
                                String optionHall = scanner1.nextLine();
                                if (displayInputDate(optionHall, pattern, msg)){
                                    hall = Integer.parseInt(optionHall);
                                    if (hall > 4){
                                        System.out.println("Sorry...! You can choose only...!");
                                        checkHall = true;
                                    }
                                    else {
                                        int i = 0;
                                        int j = 0;
                                        switch (hall){
                                            case 1 ->{
                                                System.out.println("======================================== #HALL 1# ========================================");
                                                for (i=0; i< hall1.length; i++){
                                                    for (j=0; j< hall1[i].length; j++){
                                                        if (hall1[i][j] != null){
                                                            System.out.print("|" + (char) (seat + i) + "-" + (j + 1) + ":: " + hall1[i][j] + "|\t");
                                                        }
                                                        System.out.println();
                                                    }
                                                }
                                                alertMsg();
                                                bookingSeat(hall1,scanner1);
                                            }

                                            case 2 ->{
                                                System.out.println("======================================== #HALL 2# ========================================");
                                                for (i=0; i< hall2.length; i++){
                                                    for (j=0; j< hall2[i].length; j++){
                                                        if (hall2[i][j] != null){
                                                            System.out.print("|" + (char) (seat + i) + "-" + (j + 1) + ":: " + hall2[i][j] + "|\t");
                                                        }
                                                        System.out.println();
                                                    }
                                                }
                                                alertMsg();
                                                bookingSeat(hall2,scanner1);
                                            }

                                            case 3 ->{
                                                System.out.println("======================================== #HALL 3# ========================================");
                                                for (i=0; i< hall3.length; i++){
                                                    for (j=0; j< hall3[i].length; j++){
                                                        if (hall3[i][j] != null){
                                                            System.out.print("|" + (char) (seat + i) + "-" + (j + 1) + ":: " + hall3[i][j] + "|\t");
                                                        }
                                                        System.out.println();
                                                    }
                                                }
                                                alertMsg();
                                                bookingSeat(hall3,scanner1);
                                            }
                                        }
                                    }
                                }
                                else {
                                    System.out.println(msg);
                                }
                                checkHall = false;
                            }while (checkHall);
                        }

                        case 2 ->{
                            System.out.println("============================== #SHOW ALL HALL# ===========================");
                            System.out.println("=============================== # HALL 1# ================================");
                            showAllHall(hall1);
                            System.out.println("=============================== # HALL 2# ================================");
                            showAllHall(hall2);
                            System.out.println("=============================== # HALL 3# ================================");
                            showAllHall(hall3);
                        }

                        case 3 ->{
                            displayShowtime();
                        }

                        case 4 ->{
                            System.out.println("============================== #REBOOTING HALL# ===========================");
                            initHall(hall1);
                            initHall(hall2);
                            initHall(hall3);
                            System.out.println("================== #REBOOTING HALL SUCCESSFULLY...!# ======================");
                        }

                        case 5 ->{
                            System.out.println("=============================== #SHOW HISTORY# ============================");
                            for (int i=0; i<history.length; i++){
                                if (history[i] != null){
                                    System.out.println(history[i]);
                                }
                            }
                            System.out.println("===========================================================================");
                        }

                        case 6 ->{
                            System.exit(0);
                        }
                    }
                }
            }
            else {
                System.out.println(msg);
            }
            optionCheck = false;
            System.out.println("==============================================================================================");
            System.out.print("Are You Sure to Booking?(y/n): ");
            ch = new Scanner(System.in).next().charAt(0);
            if (ch == 'n' || ch == 'N'){
                System.out.println("==============================================================================================");
                System.out.println("" +
                        "███████╗██╗   ██╗ ██████╗ ██████╗███████╗███████╗███████╗███████╗██╗   ██╗██╗     ██╗  ██╗   ██╗\n" +
                        "██╔════╝██║   ██║██╔════╝██╔════╝██╔════╝██╔════╝██╔════╝██╔════╝██║   ██║██║     ██║  ╚██╗ ██╔╝\n" +
                        "███████╗██║   ██║██║     ██║     █████╗  ███████╗███████╗█████╗  ██║   ██║██║     ██║   ╚████╔╝ \n" +
                        "╚════██║██║   ██║██║     ██║     ██╔══╝  ╚════██║╚════██║██╔══╝  ██║   ██║██║     ██║    ╚██╔╝  \n" +
                        "███████║╚██████╔╝╚██████╗╚██████╗███████╗███████║███████║██║     ╚██████╔╝███████╗███████╗██║   \n" +
                        "╚══════╝ ╚═════╝  ╚═════╝ ╚═════╝╚══════╝╚══════╝╚══════╝╚═╝      ╚═════╝ ╚══════╝╚══════╝╚═╝     ");
            }
        }while (ch == 'y' || ch == 'Y');
    }
}
