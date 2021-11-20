import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public abstract class Recipe {
    /** The ingredients required for this recipe */
    private List<String> ingredients;

    /** Whether or not the order of the items listed in the recipe matters */
    private boolean orderMatters;

    /**
     * Creates a recipe with the given orderMatters and list of ingredients. Recipes
     * must contain at least two ingredients and each ingredient must be unique.
     * 
     * @param orderMatters whether the order the ingredients are listed in matters
     * @param ingredients  the list of ingredients
     * @throws IllegalArgumentException if the recipe does not contain at least two
     *                                  ingredients or if the names are not unique
     */
    public Recipe(boolean orderMatters, String... ingredients) {
        if (ingredients.length < 2) {
            throw new IllegalArgumentException("Recipes must have at least two ingredients");
        }
        this.ingredients = new ArrayList<>(Arrays.asList(ingredients));
        if (new HashSet<String>(this.ingredients).size() < this.ingredients.size()) {
            throw new IllegalArgumentException(
                    "Each item name in a recipe must be unique. Ingredients: " + this.ingredients);
        }
        this.orderMatters = orderMatters;
    }

    /**
     * Returns true if the names of the items match the ingredients (including the
     * order if it matters) and false otherwise. Each item name in a recipe must be
     * unique and each item in a room must have a unique name, so we don't need to
     * worry about multiple items or ingredients with the same name. The items must
     * be exactly the correct items with no extra items though.
     * 
     * @param items the list of items to compare with the recipe ingredients
     * @return true if the names of the items match the ingredients (including the
     *         order if it matters) and false otherwise
     */
    public boolean matchesItems(List<Item> items) {
        boolean matches = true;
        for(int i = 0; i < items.size(); i++){
            boolean match = false;
            if(orderMatters){
                for (int j = 0; j < ingredients.size(); j++) {
                    if (ingredients.get(i).equals(items.get(i).getName())) {
                        match = true;
                    }
                }
            } else {
                match = true;
                if (!ingredients.get(i).equals(items.get(i).getName())) {
                    matches = false;
                }
            }
            matches = matches && match;
        }
        return matches;
    }

    /**
     * removes the ingredients in this recipe from the given room. The combineInRoom
     * method may call this if the items are supposed to be consumed in the recipe.
     * 
     * @param room the room to remove the items from
     */
    public void removeIngredientsFromRoom(Room room) {
        for (String ingredient : ingredients) {
            room.remove(room.getItem(ingredient));
        }
    }

    /**
     * Returns a list of the ingredients in this recipe.
     * 
     * @return a list of the ingredients in this recipe.
     */
    protected List<String> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    /**
     * Returns whether or not the order of the items matters in the recipe
     * 
     * @return whether or not the order of the items matters in the recipe
     */
    public boolean orderMatters() {
        return orderMatters;
    }


    public abstract void combineInRoom(Room r);
}
