public class Main {
    public static void main(String[] args) {
        Manager options = new Manager();
        Task task1 = new Task("Сегодня", "Помыть посуду");
        Task task2 = new Task("Суббота", "Сделать уборку");

        Subtask subtask1 = new Subtask("1", "123");
        Subtask subtask2 = new Subtask("2", "234");
        Subtask subtask3 = new Subtask("3", "345");
        Subtask subtask4 = new Subtask("4", "456");
        Subtask subtask5 = new Subtask("5", "567");
        Subtask subtask6 = new Subtask("6", "678");

        Epic epic1 = new Epic("1 2 3");
        epic1.addSubtasks(subtask1);
        epic1.addSubtasks(subtask2);
        epic1.addSubtasks(subtask3);

        Epic epic2 = new Epic("4 5 6");
        epic2.addSubtasks(subtask4);
        epic2.addSubtasks(subtask5);
        epic2.addSubtasks(subtask6);

        options.addTask(task1);
        options.addTask(task2);

        options.addEpic(epic1);
        options.addEpic(epic2);

        for (String s : options.getListTasks()) {
            System.out.println(s);
        }

        for (String s : options.getListEpics()) {
            System.out.println(s);
        }

        System.out.println("=======================");
        System.out.println(options.taskDelete(1));
        System.out.println(options.getTask(0));
        options.updateTask(0, task2);
        options.updateStatusTask(0, 'D');
        for (String s : options.getListTasks()) {
            System.out.println(s);
        }

        options.deletingAllTasks();
        for (String s : options.getListTasks()) {
            System.out.println(s);
        }
        System.out.println("=======================");

        Subtask subtask7 = new Subtask("7", "789");


        epic1 = epic2;
        options.updateEpic(0, epic1);

        options.addSubtask(1, subtask7);

        options.epicDelete(1);

        options.subtaskDelete(0, 0); //0
        for (String s : options.getListEpics()) {
            System.out.println(s);
        }

        options.updateSubtask(0, 1, subtask7);

        options.updateStatusSubtask(0, 0, 'I');

        for (String s : options.getEpic(0)) {
            System.out.println(s);
        }

        options.deletingAllEpic();
        for (String s : options.getEpic(0)) {
            System.out.println(s);
        }
    }
}
