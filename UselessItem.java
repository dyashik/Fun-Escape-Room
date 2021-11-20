
public class UselessItem extends Item{

    public UselessItem(String name, String description) {
        super(name, description);
    }

    @Override
    public void use() {
        System.out.println("You cannot use the " + getName() + ".");
    }
    
}
