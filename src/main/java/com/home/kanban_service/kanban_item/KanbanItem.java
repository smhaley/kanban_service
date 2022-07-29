package com.home.kanban_service.kanban_item;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

//maps this class to a table in db via jpa
@Entity
@Table
public class KanbanItem {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @NotNull(message="Label is required")
    private String label;

    @NotNull(message="User is required")
    private String user;

    @Size(max = 5000)
    private String content;

    private LocalDateTime updateDateTime;

    @Enumerated(EnumType.STRING)
    @NotNull(message="Status is required")
    private TableOptions itemStatus;

    private LocalDateTime creationDateTime;

    private Integer position;

    private Priority priority;

    private String title;

    public KanbanItem() {
    }

    public KanbanItem(UUID id, String label, String user, String content, LocalDateTime updateDateTime, TableOptions itemStatus, LocalDateTime creationDateTime, Priority priority, Integer position, String title) {
        this.id = id;
        this.label = label;
        this.user = user;
        this.content = content;
        this.updateDateTime = updateDateTime;
        this.itemStatus = itemStatus;
        this.creationDateTime = creationDateTime;
        this.priority = priority;
        this.position = position;
        this.title = title;
    }

    public KanbanItem(String label, String user, String content, LocalDateTime updateDateTime, TableOptions itemStatus, LocalDateTime creationDateTime, Priority priority, Integer position, String title) {
        this.label = label;
        this.user = user;
        this.content = content;
        this.updateDateTime = updateDateTime;
        this.itemStatus = itemStatus;
        this.creationDateTime = creationDateTime;
        this.priority = priority;
        this.position = position;
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public TableOptions getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(TableOptions itemStatus) {
        this.itemStatus = itemStatus;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }


    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "KanbanItem{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", user='" + user + '\'' +
                ", content='" + content + '\'' +
                ", updateDateTime=" + updateDateTime +
                ", itemStatus=" + itemStatus +
                ", creationDateTime=" + creationDateTime +
                ", position=" + position +
                ", priority=" + priority +
                ", title='" + title + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
