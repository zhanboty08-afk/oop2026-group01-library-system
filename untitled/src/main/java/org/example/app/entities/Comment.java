package org.example.app.entities;

public class Comment {
    private Long id;
    private Long taskId;
    private Long authorId;
    private String text;

    public Comment() {}

    public Comment(Long id, Long taskId, Long authorId, String text) {
        this.id = id; this.taskId = taskId; this.authorId = authorId; this.text = text;
    }

    public Comment(Long taskId, Long authorId, String text) {
        this.taskId = taskId; this.authorId = authorId; this.text = text;
    }

    public Long getId() { return id; }
    public Long getTaskId() { return taskId; }
    public Long getAuthorId() { return authorId; }
    public String getText() { return text; }

    public void setId(Long id) { this.id = id; }
    public void setTaskId(Long taskId) { this.taskId = taskId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }
    public void setText(String text) { this.text = text; }
}

