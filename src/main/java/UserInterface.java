import models.CompanyRecord;
import models.DepotReport;

import java.util.Scanner;

public class UserInterface {


    public static void printWelcomeMessage(){
        System.out.println(" _____             _     ___  ___           _        _         __   _____ ");
        System.out.println("|_   _|           | |    |  \\/  |          | |      | |       /  | |  _  |");
        System.out.println("  | |_ __ __ _  __| | ___| .  . | __ _ _ __| | _____| |_      `| | | |/' |");
        System.out.println("  | | '__/ _` |/ _` |/ _ | |\\/| |/ _` | '__| |/ / _ | __|      | | |  /| |");
        System.out.println("  | | | | (_| | (_| |  __| |  | | (_| | |  |   |  __| |_      _| |_\\ |_/ /");
        System.out.println("  \\_|_|  \\__,_|\\__,_|\\___\\_|  |_/\\__,_|_|  |_|\\_\\___|\\__|     \\___(_\\___/ ");
        System.out.println();
        System.out.println();
    }

    public static void printGoobye(){
        System.out.println("Goodbye");
    }

    public static void promptToPressEnter(){
        System.out.println("                        Press Enter to continue. ");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static boolean setMode(){
        boolean flag = true;
        while(flag) {
            System.out.println("-------------------------------------------------------");
            System.out.println("Select one of the following options and press enter: ");
            System.out.println("1)  Autonomous");
            System.out.println("2)  Manual\n");
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();
            switch(option){
                case "1":
                    return false;
                case "2":
                    return true;
                default:
                    printTryAgain();
            }
        }
        return false;
    }
    public static int selectCompany(){
        boolean flag = true;

        while(flag) {
            System.out.println("-------------------------------------------------------");
            System.out.println("Select one of these companies and press enter: ");
            System.out.println("a)  Big A");
            System.out.println("b)  Big B");
            System.out.println("c)  Big C\n");
            Scanner scanner = new Scanner(System.in);
            String company = scanner.nextLine();
            switch (company.toUpperCase()) {
                case "A":
                    return 0;
                case "B":
                    return 1;
                case "C":
                    return 2;
                default:
                    printTryAgain();
            }
        }
        return -1;
    }
    public static void printTryAgain(){
        System.out.println("-------------------------------------------------------");
        System.out.println("You have selected an incorrect option. ");
        System.out.println();
        System.out.println("Press Enter and try again.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void printRecord(CompanyRecord record){
        System.out.println("=========================================");
        System.out.println("Company name | "+record.getCompanyName());
        System.out.println("-----------------------------------------");
        System.out.println("Income       | "+record.getIncome()+"€");
        System.out.println("Expenses     | "+record.getExpenses()+"€");
        System.out.println("Cashflow     | "+record.getCashflow()+"€");
    }

    public static void printDepotReport(DepotReport depotReport){
        int cashFlow = depotReport.getIncome() - depotReport.getExpenses();
        System.out.println("=================================================");
        System.out.println("        Depot Id        | "+depotReport.getDepotId());
        System.out.println("=================================================");
        System.out.println("       Products sold   | "+depotReport.getProdSold());
        System.out.println("       Products bought | "+depotReport.getProdBought());
        System.out.println("       Income          | "+depotReport.getIncome()+"€");
        System.out.println("       Expenses        | "+depotReport.getExpenses()+"€");
        System.out.println("-------------------------------------------------");
        System.out.println("       Cashflow        | "+cashFlow+"€");
        System.out.println("-------------------------------------------------");
    }

    public static void printTitles(){
        System.out.println("=================================================");
        System.out.println("================== FULL REPORT ==================");
        System.out.println("=================================================");
    }

    public static void printCompanyResultsTitle(){
        System.out.println("=================================================");
        System.out.println("================ COMPANY RESULTS ================");
        System.out.println("=================================================");
    }

    public static void printCompanyName(String companyName){
        System.out.println("=================================================");
        System.out.println("====================== "+companyName.toUpperCase()+" =====================");
    }

    public static void printHighestCashflowCompany(CompanyRecord winner){
        System.out.println("=================================================");
        System.out.println("============ Best Performance Company ===========");
        System.out.println("=================================================");
        System.out.println("Company name: "+winner.getCompanyName());
        System.out.println("Cashflow:     "+winner.getCashflow()+"€");
        System.out.println("-------------------------------------------------");
        System.out.println();
    }

    public static boolean promptToRestart(){
        boolean flag = true;
        System.out.println();
        System.out.println("Do you want to restart? (y/n)");
        while(flag){
            Scanner option = new Scanner(System.in);
            switch (option.nextLine().toUpperCase()){
                case "Y":
                    return true;
                case "N":
                    return false;
                default:
                    printTryAgain();
            }
        }
        return false;
    }
}
