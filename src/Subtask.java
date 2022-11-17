public class Subtask extends Task{
    private String description;
    public Subtask(String title, String description) {
        super.title = title;
        this.description = description;
    }
@Override
    public String getDescription(){
        return description;
    }
}
