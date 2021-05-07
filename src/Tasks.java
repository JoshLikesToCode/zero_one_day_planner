public class Tasks {
    private int importance;
    private int time_to_complete;
    private String task_name;

    public Tasks(int importance, int time_to_complete, String task_name) {
        this.importance = importance;
        this.time_to_complete = time_to_complete;
        this.task_name = task_name;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public int getTime_to_complete() {
        return time_to_complete;
    }

    public void setTime_to_complete(int time_to_complete) {
        this.time_to_complete = time_to_complete;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }
}
