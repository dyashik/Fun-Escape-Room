import java.util.ArrayList;
import java.util.List;

public class Container extends Item implements CommandHandler {
    /** the contents of the container */
    private List<Item> contents;

    /** Whether or not the container is open */
    private boolean isOpen;

    /** Whether or not the container is locked */
    private boolean isLocked;

    /**
     * Creates a closed, unlocked container with the given name and description.
     * 
     * @param name        the name of the container
     * @param description the description of the container
     */
    public Container(String name, String description) {
        this(name, description, false, false);
    }

    /**
     * Creates a container with the given name, description, open status, and locked
     * status
     * 
     * @param name        the name of the container
     * @param description the description of the container
     * @param isOpen      whether or not the container is open
     * @param isLocked    whether or not the container is locked
     */
    public Container(String name, String description, boolean isOpen, boolean isLocked) {
        super(name, description);
        contents = new ArrayList<>();
        this.isOpen = isOpen;
        this.isLocked = isLocked;
    }

    /**
     * Adds the item to the contents.
     * 
     * @param item the item to add
     * @throws IllegalArgumentException if an item with the same name is already in
     *                                  the container
     */
    public void add(Item item) {
        for (Item i : contents) {
            if (i.getName().equals(item.getName())) {
                throw new IllegalArgumentException(getName() + " already contains a " + item.getName());
            }
        }
        contents.add(item);
    }

    /**
     * Removes the given item from the container
     * 
     * @param item the item to remove
     */
    public void remove(Item item) {
        contents.remove(item);
    }

    /**
     * returns list containing the contents of the container. Modifying this list
     * has no effect on the underlying list of items contained in the container
     * because it is a different list.
     * 
     * @return returns list containing the contents of the container.
     */
    public List<Item> getContents() {
        return new ArrayList<>(contents);
    }

    /**
     * returns the item with the given name contained in the container, or null if
     * no such item exists.
     * 
     * @param itemName the name of the item to return
     * @return the item with the given name contained in the container, or null if
     *         no such item exists.
     */
    public Item getItem(String itemName) {
        for (Item item : contents) {
            if (item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    /**
     * Prints the contents of the container
     */
    public void printContents() {
        for (Item item : contents) {
            System.out.println("  " + item);
        }
    }

    /**
     * Adds the contents of the container to the room this container is in. This is
     * a helper method called by the open() method.
     */
    private void addContentsToRoom() {
        if (getRoom() != null) {
            for (Item item : contents) {
                getRoom().add(item);
            }
        }
    }

    /**
     * Removes the contents of the container from the room the container is in. This
     * is a helper method called by the close() method.
     */
    private void removeContentsFromRoom() {
        if (getRoom() != null) {
            for (Item item : contents) {
                getRoom().remove(item);
            }
        }
    }

    /**
     * Returns whether or not the container is open
     * 
     * @return whether or not the container is open
     */
    public boolean isOpen() {
        return isOpen;
    }

    /**
     * Opens the container and adds the contents to the room if it is unlocked and
     * closed.
     * 
     * @return true if the state changed from closed to open and false otherwise.
     */
    public boolean open() {
        if (!isOpen && !isLocked) {
            isOpen = true;
            addContentsToRoom();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Closes the container and removes the contents from the room if it is open.
     * 
     * @return true if the state changed from open to closed and false otherwise
     */
    public boolean close() {
        if (isOpen) {
            isOpen = false;
            removeContentsFromRoom();
            return true;
        } else {
            return false;
        }
    }

    /**
     * returns true if the container is locked and false otherwise.
     * 
     * @return true if the container is locked and false otherwise
     */
    public boolean isLocked() {
        return isLocked;
    }

    /**
     * Locks the container if it is closed and unlocked.
     * 
     * @return true if the state changed from unlocked to locked and false otherwise
     */
    public boolean lock() {
        if (!isOpen && !isLocked) {
            isLocked = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Unlocks the container if it is closed and locked.
     * 
     * @return true if the state changed from locked to unlocked and false otherwise
     */
    public boolean unlock() {
        if (!isOpen && isLocked) {
            isLocked = false;
            return true;
        } else {
            return false;
        }
    }

    /**
     * sets the room the container is in and if the container is open, this adds the
     * contents to the room.
     */
    @Override
    public void setRoom(Room room) {
        super.setRoom(room);
        if (isOpen)
            addContentsToRoom();
    }

    /**
     * prints the description and also whether or not the chest is open or closed.
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " It is " + (isOpen() ? "open" : "closed") + ".";
    }

    public boolean execute(String command) {
        if (command.equals("open " + getName())) {
            if (!isOpen) {
                if (isLocked) {
                    System.out.println("You try to open the " + getName() + ", but it is locked.");
                } else {
                    System.out.println("You open the " + getName() + ". Inside, you see: ");
                    printContents();
                    open();
                }
            } else {
                System.out.println("The " + getName() + " is already open.");
            }
            return true;
        } else if (command.equals("close " + getName())) {
            if (isOpen) {
                System.out.println("You close the " + getName());
                close();
            } else {
                System.out.println("The " + getName() + " is already closed.");
            }
            return true;
        }
        return false;

    }

    public void printHelp() {
        System.out.println("You can open or close the " + getName());
    }

    public void use(){
        if(isOpen){
            execute("close " + getName());
        } else {
            execute("open " + getName());
        }
    }
    
}
