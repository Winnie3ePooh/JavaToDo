package database;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;


@Entity
@Table(name = "TASK")

public class Task extends DBClass {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "TASK_ID")
    private Long taskId;
    @Column(name = "TASK_TEXT")
    private String taskText;
    @Column(name = "IS_DONE")//, nullable = false
    @Type(type = "org.hibernate.type.BooleanType")
    private Boolean isDone;
    @Column(name = "PRIORITY")
    private Integer priority;
    //taskList используестя в классе TaskList для указания mappedBy = "taskList"
    //НЕ ТРОГАТЬ
    @ManyToOne
    @JoinColumn(name = "LIST_ID")
    private TaskList taskList;

    public Task() {}
    /*
    public Task(String taskText, Integer priority) {
        this.taskText = taskText;
        this.isDone = Boolean.FALSE;
        this.priority = priority;
    }
    */

    public TaskList getList() {
        return taskList;
    }

    public void setList(TaskList taskList) {
        this.taskList = taskList;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

}