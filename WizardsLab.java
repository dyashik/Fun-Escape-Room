
public class WizardsLab extends Room {
    /** max number of turns the player can take before failing */
    private int maxTurns;

    /** the number of turns the player has taken */
    private int numTurns;

    /**
     * Creates a Wizardslab with the given description, intro, and maxTurns
     * 
     * @param description the description of the room
     * @param intro       the introduction describing the scenario
     * @param maxTurns    the number of turns the player can take before failing
     */
    public WizardsLab(String description, String intro, int maxTurns) {
        super(description, intro);
        add(new PlatinumKeyRecipe());
        Container cookbook = new PasswordLockedContainer("cookbook",
                "Gordon Ramsay's Cookbook. Cost: $35. It says that some controversy"
                        + "can be good for your stomach. There seems to be a receipt on it",
                "pineapple");
        TextItem receipt = new TextItem("receipt", "a dominos receipt with a passage on it",
                "The receipt contains the following text:\n" + "Man gets arrested for\n" + "teaching his dog to bite\n"
                        + "people who put *********\n"
                        + "on pizza. \n\n*insert british accent* \nI, Gordon James Ramsay, believe that"
                        + "that this is outrageous. This fruit should belong on pizza. You are an idiot sandwich if you don't like it.");

        Container pizzabox = new Container("pizza_box", "a dominoes cheese pizza box");
        Container pizza = new Container("pizza",
                "a cheese pizza with pineapple on it. There seems to be something underneath it", false, false);
        TextItem nine = new TextItem("nine_paper", "a slip of paper that says 9", "9");
        pizza.add(nine);
        pizzabox.add(pizza);
        cookbook.add(pizzabox);
        add(receipt);
        add(cookbook);
        UselessItem milk = new UselessItem("milk", "Organic Horizon Milk. Order Matters");
        add(milk);
        UselessItem cereal = new UselessItem("cereal", "Cocoa Puffs Cereal. Order Matters");
        add(cereal);
        UselessItem clock = new UselessItem("clock", "The time is stuck on 6:27pm. It also says the date is 04/10");
        add(clock);
        Container bag = new PasswordLockedContainer("leather_bag",
                "a leather bag which seems to have a 3-digit lock on it.", "627");
        TextItem plus = new TextItem("plus_paper", "a slip of paper that has a plus sign", "+");
        bag.add(plus);
        add(bag);
        Container purse = new PasswordLockedContainer("purse",
                "a women's purse which seems to have a 4-digit lock on it.", "0410");
        TextItem equals = new TextItem("equal_paper",
                "a slip of paper that has an equals sign. Somethings need to be put together for an answer", "=");
        purse.add(equals);
        add(purse);
        Container bread = new Container("expired_bread",
                "expired loaf of Dave's Killer Bread with an expiration date on it");
        bread.add(new UselessItem("expiration_date", "Best Use By: 09/10/21"));
        add(bread);
        Container blackboard = new PasswordLockedContainer("blackboard",
                "a chalk blackboard that says \"Eternal Doom Awaits You!\". There seems to be a"
                        + "a 6-digit number lock on it.",
                "091021");
        Container wall = new PasswordLockedContainer("labeled_brick_wall",
                "each brick says \n\"1. milk\" \n\"2. cereal\" \n\"3. spoon\" \n\"4. bowl\". And it says order matters. \n There is a 4-digit lock on it.",
                "4123");
        TextItem ten = new TextItem("ten_paper", "a slip of paper that says 10", "10");
        wall.add(ten);
        blackboard.add(wall);
        add(blackboard);
        this.numTurns = 0;
        this.maxTurns = maxTurns;
    }

    @Override
    public void printRoomPrompt() {
        System.out.println(
                "You have taken " + numTurns + " turns. You have " + (maxTurns - numTurns) + " turns left to escape.");
    }

    @Override
    public void onCommandAttempted(String command, boolean handled) {
        if (handled) {
            numTurns++;
        }
    }

    @Override
    public boolean escaped() {
        return getItem("platinum_key") != null;
    }

    @Override
    public void onEscaped() {
        System.out.println(
                "Using the platinum key, you open the prison door and escape to freedom! Congratulations, you have escaped in "
                        + numTurns + " turns!");
    }

    @Override
    public void onFailed() {
        System.out.println("Oh no! You ran out of time and now you are in prison forever.");
        System.out.println("Game Over");
    }

    @Override
    public boolean failed() {
        return numTurns >= maxTurns;
    }

}
