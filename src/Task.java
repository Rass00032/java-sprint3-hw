public class Task {
    protected String title;
    private String description;
    protected char status;    // N (NEW) I (IN_PROGRESS)  D (DONE)

    public Task(){
        this.status = 'N';
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = 'N';
    }

    public void setStatus(char status){
        this.status = status;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public String getStatusText(){
        switch (status){
            case 'N':
                return "NEW";
            case 'I':
                return "IN PROGRESS";
            case 'D':
                return "DONE";
            default:
                return "Ошибка: статус не найден.";
        }
    }

    public char getStatus(){
        return status;
    }
}
