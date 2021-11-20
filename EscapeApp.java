import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EscapeApp implements CommandHandler {
    /** Use this field to read input from System.in */
    public static final Scanner IN = new Scanner(System.in);

    /**
     * A list of CommandHandler objects that will attempt to handle user input
     * commands
     */
    private List<CommandHandler> handlers;

    /** A reference to the room the user is in */
    private Room room;

    /**
     * Creates an EscapeApp with the given room.
     * 
     * @param room the room the user is in.
     */
    public EscapeApp(Room room) {
        handlers = new ArrayList<>();
        this.room = room;
        room.setApp(this);
    }

    /**
     * Adds a CommandHandler to the list of handlers. It will ignore attempts to add
     * the same handler multiple times.
     * 
     * @param handler the CommandHandler to add
     */
    public void addHandler(CommandHandler handler) {
        if (!handlers.contains(handler)) {
            handlers.add(handler);
        }
    }

    /**
     * Removes a CommandHandler from the list of handlers.
     * 
     * @param handler the CommandHandler to remove
     */
    public void removeHandler(CommandHandler handler) {
        handlers.remove(handler);
    }

    /**
     * The method that starts the game and runs the main game loop.
     * 
     * After the intro and room description are printed, the user keeps being
     * prompted with the custom message defined in the room and presented with a
     * cursor to enter commands until they satisfy the escape conditions defined in
     * either the escaped() or failed() methods of the Room.
     * 
     * Once the user has either escaped or failed, it will call either the
     * onEscaped() or onFailed() method of the Room. These methods give the room a
     * chance to print a custom message or perform other custom behavior when the
     * room has been escaped or failed.
     * 
     * Note: Each time the user enters a command, each CommandHandler in the list of
     * handlers attempts to execute the command. If none of the handlers are able to
     * execute the command, then this class will attempt to execute it which will
     * either print help, or print out a message saying the command is invalid. Note
     * that while this system does allow more than one handler to execute the same
     * command, you should probably avoid such overlapping scenarios unless you have
     * a good reason, since it will be confusing to the user.
     */
    public void runGame() {
        room.printIntro();
        room.printDescription();
        do {
            room.printRoomPrompt();
            System.out.print("> ");
            String command = IN.nextLine();
            boolean handled = false;
            for (CommandHandler ch : handlers) {
                if (ch.execute(command)) {
                    handled = true;
                    break;
                }
            }
            if (!handled) {
                execute(command);
            }
            room.onCommandAttempted(command, handled);
        } while (!room.escaped() && !room.failed());
        if (room.escaped())
            room.onEscaped();
        else
            room.onFailed();
    }

    @Override
    public boolean execute(String str) {
        int i = str.indexOf(' ');
        String word = str.substring(0, i);
        if (str.equalsIgnoreCase("help")) {
            System.out.println("The following commands are avaliable");
            printHelp();
        } else if (word.equalsIgnoreCase("say")) {
            System.out.println("You made a twenty_one_paper");
        } else if (word.equalsIgnoreCase("order")){
            
        } else {
            System.out.println("Invalid command. Type help for information.");
        }
        return true;
    }

    

    @Override
    public void printHelp() {
        System.out.println("help prints common commands, but there may be additional secret commands.");
        for (CommandHandler ch : handlers) {
            ch.printHelp();
        }
    }

}
