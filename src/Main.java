
public class Main {

    public static void main(String[] args) {
        UserInterface.printWelcomeMessage();
        UserInterface.promptToPressEnter();
        Market market = Market.getInstance();
        market.marksRequirements();
    }
}
