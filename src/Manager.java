import java.util.ArrayList;

public class Manager {
    private ArrayList<Task> tasks;
    private ArrayList<Epic> epics;

    // конструктор Manager:

    public Manager() {
        this.tasks = new ArrayList<>();
        this.epics = new ArrayList<>();
    }

    // мететоды Task:

    public String addTask(Task task) {
        tasks.add(task);
        return "Задача \"" + task.getTitle() + "\" добавлена.";
    }

    public String taskDelete(int taskNumber) {
        if (tasks.size() > taskNumber) {
            tasks.remove(taskNumber);
            return "Успешно.";
        } else {
            return "Ошибка, задача не найдена.";
        }
    }

    public ArrayList<String> getListTasks() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Список задач пуст");
        if (tasks.size() == 0) return list;
        list.clear();


        for (Task task : tasks) {
            list.add("\n" + task.getTitle() + "\n\t" + task.getDescription() + "\n\t"
                    + "Статус: " + task.getStatusText());
        }

        return list;
    }

    public String getTask(int taskNumber) {
        String task;
        if (tasks.size() > taskNumber) {
            task = tasks.get(taskNumber).getTitle() + "\n\t" + tasks.get(taskNumber).getDescription()
                    + "\n\tСтатус: " + tasks.get(taskNumber).getStatusText();
            return task;
        } else {
            return "Ошибка, задача не найдена.";
        }
    }

    public String deletingAllTasks() {
        tasks.clear();
        return "Все задачи удалены.";
    }

    public String updateTask(int taskNumber, Task task) {
        if (tasks.size() <= taskNumber) return "Ошибка: задача не найдена";
        tasks.remove(taskNumber);
        tasks.add(task);
        return "Задача обновлена.";
    }

    public String updateStatusTask(int taskNumber, char status) {
        if (tasks.size() <= taskNumber) return "Ошибка: задача не найдена.";
        if (status == 'I' || status == 'D') {
            tasks.get(taskNumber).setStatus(status);
            return "Статус обновлен.";
        } else {
            return "Ошибка некорректный статус.";
        }
    }


    // методы Epic:


    public String addEpic(Epic epic) {
        epics.add(epic);
        return "Эпик \"" + epic.getTitle() + "\" создан.";
    }

    public String addSubtask(int epicNumber, Subtask subtask) {
        epics.get(epicNumber).addSubtasks(subtask);
        return "Задача \"" + subtask.getTitle() + "\" добавлена в эпик \"" + epics.get(epicNumber).getTitle() + "\".\n";
    }

    public String epicDelete(int epicNumber) {
        if (epics.size() > epicNumber) {
            epics.remove(epicNumber);
            return "Успешно.";
        } else {
            return "Ошибка:эпик не найден.";
        }
    }

    public String subtaskDelete(int epicNumber, int subtaskNumber) {
        if (epics.size() < epicNumber) return "Эпик не найден.";

        boolean match = false;
        for (Integer values : epics.get(epicNumber).getEpicKey()) {
            if (subtaskNumber == values)  match = true;
        }
        if (!match) return "Подзадача не найдена";

        epics.get(epicNumber).setRemoveSubtasks(subtaskNumber);
        return "Успешно.";
    }

    public ArrayList<String> getListEpics() {

        ArrayList<String> list = new ArrayList<>();
        list.add("Список задач пуст");

        if (epics.size() == 0) return list;
        list.clear();
        Subtask subtask;

        for (Epic epic : epics) {
            list.add("\n" + epic.getTitle() + "\n" + "Статус: " + epic.getStatusText());

            for (Subtask values : epic.getEpicValues()) {
                list.add("\n\t" + values.getTitle() + "\n\t\t" + values.getDescription() + "\n\t\t"
                        + "Статус: " + values.getStatusText());
            }
        }

        return list;
    }

    public ArrayList<String> getEpic(int epicNumber) {
        ArrayList<String> result = new ArrayList<>();

        result.add("Эпик не найден.");
        if (epics.size() <= epicNumber) return result;
        result.clear();

        Epic epic = epics.get(epicNumber);
        result.add(epic.getTitle() + "\n\t" + "Статус: " + epic.getStatusText());

        for (Subtask values : epic.getEpicValues()) { ///

            result.add("\n\t" + values.getTitle() + "\n\t\t" + values.getDescription() + "\n\t\t"
                    + "Статус: " + values.getStatusText());
        }

        return result;
    }

    public String deletingAllEpic() {
        epics.clear();
        return "Все эпики удалены.";
    }

    public String updateEpic(int epicNumber, Epic epic) {
        if (epics.size() <= epicNumber) return "Ошибка: эпик не найден.";
        epics.remove(epicNumber);
        epics.add(epic);

        // проверяем статус эпика
        Subtask subtask;
        boolean allNew = true;
        boolean allDone = true;

        for (int i = 0; i < epic.getEpicSize(); i++) {
            subtask = epics.get(epicNumber).getSubtasks(i);
            if (subtask.getStatus() == 'N') allDone = false;
            if (subtask.getStatus() == 'D') allNew = false;
        }
        if (allNew) epics.get(epicNumber).setStatus('N');
        if (allDone) epics.get(epicNumber).setStatus('D');
        if (!allDone && !allNew) epics.get(epicNumber).setStatus('I');

        return "Эпик обновлён.";
    }

    public String updateSubtask(int epicNumber, int subtaskNumber, Subtask subtask) {
        if (epics.size() <= epicNumber) return "Ошибка: эпик не найден.";

        boolean match = false;
        for (Integer values : epics.get(epicNumber).getEpicKey()) {
            if (subtaskNumber == values)  match = true;
        }
        if (!match) return "Подзадача не найдена";
        epics.get(epicNumber).setRemoveSubtasks(subtaskNumber);
        epics.get(epicNumber).setUpdateSubtask(subtaskNumber, subtask);
        return "Задача обновлена.";
    }

    public String updateStatusSubtask(int epicNumber, int subtaskNumber, char status) { //проверить статус эпика
        if (epics.size() <= epicNumber) return "Ошибка эпик не найден.";

        boolean match = false;
        for (Integer values : epics.get(epicNumber).getEpicKey()) {
            if (subtaskNumber == values)  match = true;
        }
        if (!match) return "Подзадача не найдена";

        Subtask subtask = epics.get(epicNumber).getSubtasks(subtaskNumber);

        if (status == 'I' || status == 'D') {
            // меняем статус  подзадачи
            subtask.setStatus(status);

            // проверяем статус эпика
            boolean allNew = true;
            boolean allDone = true;

            for (int i = 0; i < epics.get(epicNumber).getEpicSize(); i++) {
                subtask = epics.get(epicNumber).getSubtasks(i);
                if (subtask.getStatus() == 'N') allDone = false;
                if (subtask.getStatus() == 'D') allNew = false;
            }
            if (allNew) epics.get(epicNumber).setStatus('N');
            if (allDone) epics.get(epicNumber).setStatus('D');
            if (!allDone && !allNew) epics.get(epicNumber).setStatus('I');

            return "Статус обновлен.";
        } else {
            return "Ошибка некорректный статус.";
        }
    }

}
