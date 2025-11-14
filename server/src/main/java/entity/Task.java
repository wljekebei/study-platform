package entity;
import jakarta.persistence.*;
@Entity
@Table(name="Tasks")

public class Task{
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private long task_id;
private long group_id;
private long created_by;
private String title;
private String description;
private String status;
private String deadline;
private String created_at;

    public Task(){}

    public Long getTask_id(){
        return task_id;
    }
    public void setTask_id(long task_id){
        this.task_id = task_id;
    }
    public long getGroup_id(){
        return group_id;
    }
    public void setGroup_id(long group_id){
        this.group_id = group_id;
    }
    public long getCreated_by(){
        return created_by;
    }
    public void setCreated_by(long created_by){
        this.created_by = created_by;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getDeadline(){
        return deadline;
    }
    public void setDeadline(String deadline){
        this.deadline = deadline;
    }
    public String getCreated_at(){
        return created_at;
    }
    public void setCreated_at(String created_at){
        this.created_at = created_at;
    }

}
