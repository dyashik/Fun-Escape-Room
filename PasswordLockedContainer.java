
public class PasswordLockedContainer extends Container{
    /** the password to unlock the container */
    private String password;

    /**
     * Creates a password locked container with the given name, description and
     * password
     * 
     * @param name        the name of the container
     * @param description the description of the container
     * @param password    the password to unlock the container
     */
    public PasswordLockedContainer(String name, String description, String password) {
        this(name, description, false, true, password);
    }

    /**
     * Creates a password locked container with the given name, description, open
     * state, locked state, and password to unlock the container.
     * 
     * @param name        the name of the container
     * @param description the description of the container
     * @param isOpen      whether the container is open or not
     * @param isLocked    whether the container is locked or not
     * @param password    the password to unlock the container
     */
    public PasswordLockedContainer(String name, String description, boolean isOpen, boolean isLocked, String password) {
        super(name, description, isOpen, isLocked);
        this.password = password;
    }

    /**
     * Called when the password is entered successfully. Prints a message reporting
     * the success and then unlocks the container. This could be overridden by
     * subclasses to customize the message and/or add additional effects.
     */
    protected void onPasswordSuccess() {
        System.out.println("You have unlocked the " + getName());
        unlock();
    }

    /**
     * Called when an incorrect password is entered. Prints a message reporting the
     * failure. This could be overridden by subclasses to customize the message
     * and/or add additional effects.
     */
    protected void onPasswordFailure() {
        System.out.println("You failed to unlock the " + getName());
    }

    /**
     * The message to print when prompting the user for the password. This could be
     * overridden by subclasses to customize the prompt.
     * 
     * @return
     */
    protected String passwordPrompt() {
        return "password: ";
    }

    @Override
    public boolean execute(String command) {
        if (command.equals("unlock " + getName())) {
            if (isLocked()) {
                System.out.print(passwordPrompt());
                String pw = EscapeApp.IN.nextLine().trim();
                if (pw.equals(password)) {
                    onPasswordSuccess();
                } else {
                    onPasswordFailure();
                }
            } else {
                System.out.println("The " + getName() + " is not locked.");
            }
            return true;
        }
        return super.execute(command);
    }

    @Override
    public void printHelp() {
        super.printHelp();
        System.out.println("You can attempt to unlock the " + getName());
    }

}
