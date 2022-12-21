package src;

import java.io.Serializable;
import java.util.List;

public class ResponseAllListForm implements Serializable {

    List<ExtractedContent> extractedContents;

    public List<ExtractedContent> getExtractedContents() {
        return extractedContents;
    }

    public void setExtractedContents(List<ExtractedContent> extractedContents) {
        this.extractedContents = extractedContents;
    }
}
