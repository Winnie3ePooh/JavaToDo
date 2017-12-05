package database;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "TASKLIST_USERS")
public class TaskListUsers extends ObjectsDAO {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "LIST_USERS_ID")
    private Long listUsersId;
    @Column(name = "MAY_EDIT")//, nullable = false
    @Type(type = "org.hibernate.type.BooleanType")
    private Boolean mayEdit;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User userListTasks;
    @ManyToOne
    @JoinColumn(name = "LIST_ID")
    private TaskList taskListUsers;

    public TaskListUsers() {}
    /*
    public TaskListUsers(User userListTasks, TaskList taskListUsers, Boolean mayEdit) {
        this.userListTasks = userListTasks;
        this.taskListUsers = taskListUsers;
        this.mayEdit = mayEdit;
    }
    */

    public Long getListUsersId() {
        return listUsersId;
    }

    public void setListUsersId(Long listUsersId) {
       this.listUsersId = listUsersId;
    }

    public Boolean getMayEdit() {
        return mayEdit;
    }

    public void setMayEdit(Boolean mayEdit) {
        this.mayEdit = mayEdit;
    }

    public User getUser() {
        return userListTasks;
    }

    public void setUser(User userListTasks) {
        this.userListTasks = userListTasks;
    }

    public TaskList getList() {
        return taskListUsers;
    }

    public void setList(TaskList taskListUsers) {
        this.taskListUsers = taskListUsers;
    }
}
