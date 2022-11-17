import java.util.ArrayList;
import java.util.HashMap;

final class Epic extends Task {
    private HashMap<Integer, Subtask> subtasks;
    private Integer size;


    public Epic(String title) {
        super.title = title;
        size = 0;
        subtasks = new HashMap<>();
    }

    public void addSubtasks(Subtask subtask) {
        subtasks.put(size, subtask);
        size++;
    }

    public void setUpdateSubtask(int subtaskNumber, Subtask subtask){
        setRemoveSubtasks(subtaskNumber);
        subtasks.put(subtaskNumber,subtask);
    }
    public void setRemoveSubtasks(Integer subtaskNumber) {
        this.subtasks.remove(subtaskNumber);
    }

    public Subtask getSubtasks(Integer subtasksNumber) {
        return subtasks.get(subtasksNumber);
    }

    public int getEpicSize() {
        return subtasks.size();
    }

    public ArrayList<Subtask> getEpicValues(){
        ArrayList<Subtask> list = new ArrayList<>();
        for (Subtask  subtask: subtasks.values()){
            list.add(subtask);
        }
        return list;
    }

    public ArrayList<Integer> getEpicKey(){
        ArrayList<Integer> list = new ArrayList<>();
        for (Integer  key: subtasks.keySet()){
            list.add(key);
        }
        return list;
    }


}
