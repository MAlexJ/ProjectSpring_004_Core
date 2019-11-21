package examples.requiredAnnotation;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Required;

@Log4j
@Getter
public class PrintService {

    private String fileName;
    private String fileExtension;

    @Required
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Required
    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

     void print() {
        log.info("File: " + fileName + fileExtension);
    }
}