package src.dto;

import java.sql.Blob;
import java.sql.Date;

public class ResponseDto {

    public static class FindAllForm {
        private Long id;
        private Blob loadedFile;
        private Date createdAt;
        private Boolean status;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Blob getLoadedFile() {
            return loadedFile;
        }

        public void setLoadedFile(Blob loadedFile) {
            this.loadedFile = loadedFile;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }
    }
}
