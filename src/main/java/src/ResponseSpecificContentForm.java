package src;

import src.professor.entity.Bucket;

import java.io.Serializable;

public class ResponseSpecificContentForm implements Serializable {

    Bucket bucket;

    public Bucket getBucket() {
        return bucket;
    }
}
