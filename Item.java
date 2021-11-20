
public abstract class Item {
    /** The name of the item */
    private String name;

    /** The description of the item */
    private String description;

    /** A reference to the room the item is in (will be null if not in a room) */
    private Room room;

    /**
     * Create an item with the given name and description.
     * 
     * @param name        the name of the item
     * @param description the description of the item
     * @throws IllegalArgumentException if the name contains white space
     */
    public Item(String name, String description) {
        if (!isValid(name)) {
            throw new IllegalArgumentException("Item names cannot contain white space.");
        }
        this.name = name;
        this.description = description;
        room = null;
    }

    /**
     * Returns true if the name is valid (i.e. does not contain white space) and
     * false otherwise.
     * 
     * @param name the name to check
     * @return true if the name is valid (i.e. does not contain white space) and
     *         false otherwise.
     */
    private boolean isValid(String name) {
        for (int i = 0; i < name.length(); i++) {
            if (Character.isWhitespace(name.charAt(i)))
                return false;
        }
        return true;
    }

    /**
     * returns the name of the item.
     * 
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * returns the item description.
     * 
     * @return the item description
     */
    public String getDescription() {
        return description;
    }

    /**
     * returns the room the item is in (could be null).
     * 
     * @return the room the item is in (could be null).
     */
    public Room getRoom() {
        return room;
    }

    /**
     * sets the room
     * 
     * @param room the room to set room to
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * returns the name and description of the item.
     */
    @Override
    public String toString() {
        return getName() + " - " + getDescription();
    }

    public abstract void use();
}
