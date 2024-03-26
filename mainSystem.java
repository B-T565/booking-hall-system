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
    private static void bookingSeat(String[][] hall, Scanner input){
        System.out.print("Enter Seat: ");
        String InputUser = input.nextLine();
        System.out.print("Enter UserID: ");
        String[] seatArray = InputUser.split(",");
        String UserID = input.nextLine();
        String[] inputSplit = InputUser.split("-");
        String getResult = "";
        String addedHistory = "";
        char letterResult = 0;
        int no = 0;
        for (String seat : seatArray){
            String seatSplit = seat.replaceAll("-","");
            letterResult = seatSplit.charAt(0);
            char noChar = seat.charAt(1);
            no = Character.getNumericValue(noChar);
            char seats = 50;

            for (int i=0; i < hall.length; i++){
                for (int j=0; j < hall[i].length; j++){
                    if (hall[i][j] == null){
                        System.out.print("|" + (char)(seats + i) + "-" + (j + 1) + ":: " + hall[i][j] + "|\t");
                    }
                    if (letterResult == (char) (seats + i) && no == (j + 1)){
                        hall[i][j] = "BO";
                        addedHistory = addHistory(letterResult,no,UserID);
                        if (historyIndex < history.length){
                            history[historyIndex] = addedHistory;
                            historyIndex++;
                        }
                    }
                }
            }
        }
        System.out.println("============================== #BOOKING SUCCESSFULLY....!# ===================================");
    }
//    private static void toTitleCase(String inputVal){
//        if (inputVal.length() == 0) return;
//        if (inputVal.length() == 1) return inputVal.toUpperCase(String);
//
//    }
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
        String pattern = "[ 1 - 9 ] +";
        String msg = "Sorry...! Please Input Positive Number Only";
        char seat = 50;
        int hall = 0;
        int idUser = 0;

        do {
            displayMenu();
            System.out.println("=> Please Select Menu Item/No: ");
            String choose = scanner1.nextLine();
            if (displayInputDate(choose, pattern, msg)){
                toTitleCase = choose.nextLine();
                if (toTitleCase != null){
                    System.out.println("Sorry...! You can choose only of upperCase OR lowerCase...!");
                    optionCheck = true;
                }
                else {
                    switch (toTitleCase(AbCd)){
                        case 'A' ->{
                            boolean checkHall = true;
                            do {
                                System.out.println("============================== WELCOME START HALL BOOKING SYSTEM ==============================");
                                displayShowtime();
                                System.out.print("=> Please Select Showtime ( A | B | C ): ");
                                String select = scanner1.next();
                                if (displayInputDate(select, pattern, msg)){
                                    hall = Integer.parseInt(select);
                                    if (hall != 0){
                                        System.out.println("Sorry...! You can choose only...!");
                                        checkHall = true;
                                    }
                                    else {
                                        int i = 0, j = 0;
                                        switch (hall){
                                            case 'A' ->{
                                                System.out.println("============================== #HALL 1# ==============================");
                                                for (i=0; i< hall1.length; i++){
                                                    for (j=0; j< hall1[i].length; j++){
                                                        if (hall1[i][j] != null){
                                                            System.out.println("|" + (char) (seat + i) + "-" + (j + 1) + ":: " + hall1[i][j] + "|\t");
                                                        }
                                                        System.out.println();
                                                    }
                                                }
                                                alertMsg();
                                                bookingSeat(hall1,scanner1);
                                            }
                                            case 'B' ->{
                                                System.out.println("============================== #HALL 2# ==============================");
                                                for (i=0; i< hall2.length; i++){
                                                    for (j=0; j< hall2[i].length; j++){
                                                        if (hall2[i][j] != null){
                                                            System.out.println("|" + (char) (seat + i) + "-" + (j + 1) + ":: " + hall2[i][j] + "|\t");
                                                        }
                                                        System.out.println();
                                                    }
                                                }
                                                alertMsg();
                                                bookingSeat(hall2,scanner1);
                                            }
                                            case 'C' ->{
                                                System.out.println("============================== #HALL 3# ==============================");
                                                for (i=0; i< hall3.length; i++){
                                                    for (j=0; j< hall3[i].length; j++){
                                                        if (hall3[i][j] != null){
                                                            System.out.println("|" + (char) (seat + i) + "-" + (j + 1) + ":: " + hall3[i][j] + "|\t");
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
                                checkHall = false;
                            }while (checkHall);
                        }
                        case 'B' ->{
                            System.out.println("============================== #SHOW ALL HALL# ===========================");
                            System.out.println("=============================== # HALL 1# ================================");
                            showAllHall(hall1);
                            System.out.println("=============================== # HALL 2# ================================");
                            showAllHall(hall2);
                            System.out.println("=============================== # HALL 3# ================================");
                            showAllHall(hall3);
                        }
                        case 'C' ->{
                            displayShowtime();
                        }
                        case 'D' ->{
                            System.out.println("============================== #REBOOTING HALL# ===========================");
                            initHall(hall1);
                            initHall(hall2);
                            initHall(hall3);
                            System.out.println("================== #REBOOTING HALL SUCCESSFULLY...!# ======================");
                        }
                        case 'E' ->{
                            System.out.println("=============================== #SHOW HISTORY# ============================");
                            for (int i=0; i<history.length; i++){
                                if (history[i] != null){
                                    System.out.println(history[i]);
                                }
                            }
                            System.out.println("===========================================================================");
                        }
                        case 'F' ->{
                            System.exit(0);
                        }
                    }
                }
            }
            else {
                System.out.println(msg);
            }
            optionCheck = false;
        }while (optionCheck);
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
}
