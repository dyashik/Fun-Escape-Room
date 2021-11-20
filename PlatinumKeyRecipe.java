
public class PlatinumKeyRecipe extends Recipe {
    public PlatinumKeyRecipe() {
        super(false, "nine_paper", "plus_paper", "ten_paper", "equal_paper", "twenty_one_paper");
    }
    public PlatinumKeyRecipe(boolean orderMatters, String ... ingredients) {
        super(orderMatters, ingredients);
    }
    // combine all the numbers
    @Override
    public void combineInRoom(Room r) {
        System.out.println("Success! You have made plantinum key.");
        PlatinumKey key = new PlatinumKey("platinum_key", "a platinum_key");
        r.add(key);
        removeIngredientsFromRoom(r);
    }
}
