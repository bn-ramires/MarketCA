package main;

import com.jakewharton.fliptables.FlipTable;
import models.CompanyRecord;
import models.DepotReport;
import models.Ticket;

import java.util.List;
import java.util.Scanner;

/**
 * This class represents the user interface. In this case, a simple terminal interface
 * used to interact with the the software and get information displayed to the screen.
 */
public class UserInterface {

    /**
     * Prints the first message seen on screen.
     */
    public static void printWelcomeMessage() {
        System.out.println(" _____             _     ___  ___           _        _         __   _____ ");
        System.out.println("|_   _|           | |    |  \\/  |          | |      | |       /  | |  _  |");
        System.out.println("  | |_ __ __ _  __| | ___| .  . | __ _ _ __| | _____| |_      `| | | |/' |");
        System.out.println("  | | '__/ _` |/ _` |/ _ | |\\/| |/ _` | '__| |/ / _ | __|      | | |  /| |");
        System.out.println("  | | | | (_| | (_| |  __| |  | | (_| | |  |   |  __| |_      _| |_\\ |_/ /");
        System.out.println("  \\_|_|  \\__,_|\\__,_|\\___\\_|  |_/\\__,_|_|  |_|\\_\\___|\\__|     \\___(_\\___/ ");
        System.out.println();
        System.out.println();
    }

    /**
     * Formats reports to be eventually printed to screen properly.
     *
     * @param report Depot report with the necessary data.
     * @return string to be printed.
     */
    private static String formatedReport(DepotReport report) {

        String detailsData = "";

        detailsData = detailsData.concat(formatDetailsForBoughtFrom(report));
        detailsData = detailsData.concat(formatDetailsForSoldTo(report));

        // If there are no transactions made by the depot. Do not display detailed info.
        if (detailsData.isEmpty()) {
            detailsData = "\n\n\n\n\n\n\n" +
                    "  No transactions made.  ";
        }

        return detailsData;

    }

    /**
     * Formats the data necessary for the "Bought From" transaction details section
     * to be eventually displayed to the screen if/when necessary.
     *
     * @param report Depot report with the necessary data.
     * @return formatted string.
     */
    private static String formatDetailsForBoughtFrom(DepotReport report) {

        List<Ticket> tickets = report.getTicketsAsBuyer();
        String result = "";

        for (Ticket ticket : tickets) {
            result = result.concat("\n     Bought From: " + ticket.getSeller());
            result = result.concat("\n     Depot ID: " + ticket.getSellerDepotId());
            result = result.concat("\n     Qty: " + ticket.getQuantity());
            result = result.concat("\n     Price: " + ticket.getProductCost() + "€");
            result = result.concat("\n     Delivery: " + ticket.getDelivery() + "€");
            result = result.concat("\n     Total: " + (ticket.getTotalCost() + ticket.getDelivery()) + "€");
            result = result.concat("\n-----------------------");
        }
        return result;
    }

    /**
     * Formats the data necessary for the "Sold To" transaction details section
     * to be eventually displayed to the screen if/when necessary.
     *
     * @param report Depot report with the necessary data.
     * @return formatted string.
     */
    private static String formatDetailsForSoldTo(DepotReport report) {

        List<Ticket> tickets = report.getTicketsAsSeller();
        String result = "";

        for (Ticket ticket : tickets) {
            result = result.concat("\n     Sold To: " + ticket.getBuyer());
            result = result.concat("\n     Depot ID: " + ticket.getBuyerDepotId());
            result = result.concat("\n     Qty: " + ticket.getQuantity());
            result = result.concat("\n     Price: " + ticket.getProductCost() + "€");
            result = result.concat("\n     Total: " + ticket.getTotalCost() + "€");
            result = result.concat("\n-----------------------");
        }
        return result;
    }

    /**
     * Prints a report of a depot to the screen. If there was at least one transaction made by the depot,
     * it will also contain the additional details of each individual transaction.
     *
     * @param report data necessary to be shown to the user.
     */
    public static void printDepotReport(DepotReport report) {

        /*
        First table. Contains the basic report for a depot.
        This data is shown in the left side of the screen, for each individual depot.
         */
        String[] firstTableHeader = {"Depot ID", String.valueOf(report.getDepotId())};
        String[][] firstTableData = {
                {"Products Sold", String.valueOf(report.getTotalProductSold())},
                {"Products Bought", String.valueOf(report.getTotalProductBought())},
                {"Income", String.valueOf(report.getIncome()) + "€"},
                {"Expenses", String.valueOf(report.getExpenses()) + "€"},
                {"Initial cash", String.valueOf(report.getCashAllowance()) + "€"},
                {"Current Balance", String.valueOf(report.getCurrentBalance()) + "€"},
                {"Cash Flow", String.valueOf(report.getCashFlow()) + "€"}
        };

        String depotInfo = FlipTable.of(firstTableHeader, firstTableData);

        /*
        Second table. Contains detailed info on individual transactions of a depot.
        This data is shown in the right side of the screen, for each individual depot.
         */
        String[] secondTableHeader = {"Depot", "Details"};
        String[][] secondTableData = {
                {depotInfo, formatedReport(report)}
        };

        // Printing both the first and second tables inside the main one.
        System.out.println(FlipTable.of(secondTableHeader, secondTableData));
    }

    /**
     * Prints the goodbye message in the end of the program's execution.
     */
    public static void printGoodbye() {
        System.out.println("Goodbye!");
    }

    /**
     * Asks user to press enter and continue the execution of the program.
     */
    public static void promptToPressEnter() {
        System.out.println("                        Press Enter to continue. ");
        new Scanner(System.in).nextLine();
    }

    /**
     * Prompts user to select between autonomous or manual mode before transactions start.
     *
     * @return if isManualMode is true or false.
     */
    public static boolean setMode() {

        boolean flag = true;
        boolean isManualMode = true;

        while (flag) {
            System.out.println("-------------------------------------------------------");
            System.out.println("Select one of the following options and press enter: ");
            System.out.println("1)  Autonomous");
            System.out.println("2)  Manual\n");

            String option = new Scanner(System.in).nextLine();

            switch (option) {
                case "1":
                    return !isManualMode;
                case "2":
                    return isManualMode;
                default:
                    printTryAgain();
            }
        }
        return isManualMode;
    }

    /**
     * Prompts user to select which company will be extra information when choosing Manual mode.
     *
     * @return the chosen company.
     */
    public static int selectCompany() {
        boolean flag = true;

        while (flag) {
            System.out.println("-------------------------------------------------------");
            System.out.println("Select one of these companies and press enter: ");
            System.out.println("a)  Big A");
            System.out.println("b)  Big B");
            System.out.println("c)  Big C\n");

            String company = new Scanner(System.in).nextLine();

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

    /**
     * Prints an error message for when choosing a wrong option on any prompt.
     */
    public static void printTryAgain() {

        System.out.println("-------------------------------------------------------");
        System.out.println("You have selected an incorrect option.\n");
        System.out.println("Press Enter and try again.");

        new Scanner(System.in).nextLine();
    }

    /**
     * Prints each individual company record as a table.
     *
     * @param record necessary record data.
     */
    public static void printRecord(CompanyRecord record) {

        String[] headers = {"Company", record.getCompanyName()};
        String[][] data = {
                {"Income", String.valueOf(record.getIncome()) + "€"},
                {"Expenses", String.valueOf(record.getExpenses()) + "€"},
                {"Cash Flow", String.valueOf(record.getCashflow()) + "€"}
        };

        System.out.println(FlipTable.of(headers, data));
    }

    /**
     * Prints a banner for the report section.
     */
    public static void printFullReportTitle() {
        System.out.println("=================================================");
        System.out.println("================== FULL REPORT ==================");
        System.out.println("=================================================");
    }

    /**
     * Prints a banner for the company results section.
     */
    public static void printCompanyResultsTitle() {
        System.out.println("=================================================");
        System.out.println("================ COMPANY RESULTS ================");
        System.out.println("=================================================");
    }

    /**
     * Prints a banner with the chosen company name.
     */
    public static void printCompanyName(String companyName) {
        System.out.println("=================================================");
        System.out.println("====================== " + companyName.toUpperCase() + " =====================");
    }

    /**
     * Prints a section with the winning company, based on best cash flow.
     *
     * @param winner the record of the winning company.
     */
    public static void printHighestCashflowCompany(CompanyRecord winner) {

        System.out.println("=========================");
        System.out.println("Best Performance Company");
        System.out.println("=========================");
        System.out.println("Company name: " + winner.getCompanyName());
        System.out.println("Cash Flow:     " + winner.getCashflow() + "€");
        System.out.println("-------------------------");

    }
}
