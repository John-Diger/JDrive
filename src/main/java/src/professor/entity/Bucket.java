package src.professor.entity;

import java.sql.Blob;
import java.time.LocalDateTime;

public class Bucket {

    private long id;
    private Blob sharedFile;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Blob getSharedFile() {
        return sharedFile;
    }

    public void setSharedFile(Blob sharedFile) {
        this.sharedFile = sharedFile;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
