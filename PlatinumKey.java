
public class PlatinumKey extends Item{

    String name;
    String description;

    public PlatinumKey(String name, String description) {
        super(name, description);
    }

    @Override
    public void use() {
        System.out.println("YAY! You have escaped your whole life in prison. This appears to be the key to the door.");
        //super.getRoom().add(u);
    }

}
