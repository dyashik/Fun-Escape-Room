public class TextItem extends Item{
    String text;

    public TextItem(String name, String description, String text){
        super(name, description);
        this.text = text;
    }

    @Override
    public void use() {
        System.out.println(text);
    }
}
