import models.CompanyRecord;
import models.DepotReport;

import java.util.Scanner;

public class UserInterface {


    public static void printWelcomeMessage(){
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("---------------------------  VirtualMarket 1.0 ----------------------------------------");
        System.out.println("---------------------------------------------------------------------------------------");
    }

    public static void promptToPressEnter(){
        System.out.println("Press Enter to continue. ");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static boolean printMenu(){
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Select one of the following options and press enter: ");
        System.out.println("1)  Autonomous");
        System.out.println("2)  Manual\n");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        if(option.equals("2")) return true;
        return false;
    }

    public static void printRecord(CompanyRecord record){
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Company name | "+record.getCompanyName());
        System.out.println("Income       | "+record.getIncome());
        System.out.println("Expenses     | "+record.getExpenses());
        System.out.println("Cashflow     | "+record.getCashflow());
    }

    public static void printDepotReport(DepotReport depotReport){
        int cashFlow = depotReport.getIncome() - depotReport.getExpenses();
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Depot Id        | "+depotReport.getDepotId());
        System.out.println("Products sold   | "+depotReport.getProdSold());
        System.out.println("Products bought | "+depotReport.getProdBought());
        System.out.println("income          | "+depotReport.getIncome());
        System.out.println("expenses        | "+depotReport.getExpenses());
        System.out.println("Cashflow        | "+cashFlow);
    }

    public static void printTitles(String title){
        System.out.println();
        System.out.println("========================================================================================");
        System.out.println("============================== "+title.toUpperCase()+" ======================================");
        System.out.println("========================================================================================");
    }
}
