package src.professor.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Bucket implements Serializable {

    private long id;
    private String name;
    private byte[] sharedFile;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getSharedFile() {
        return sharedFile;
    }

    public void setSharedFile(byte[] sharedFile) {
        this.sharedFile = sharedFile;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
