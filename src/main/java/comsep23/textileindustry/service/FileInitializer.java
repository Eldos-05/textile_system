package comsep23.textileindustry.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class FileInitializer {

    @PostConstruct
    public void createFilesIfNotExist() {
        createFile("sold.txt");
        createFile("need_material.txt");
        createFile("delivered.txt");
    }

    private void createFile(String fileName) {
        String BASE_PATH = "/mnt/data/textile_system/";
        File file = new File(BASE_PATH + fileName);
        try {
            File dir = new File(BASE_PATH);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            if (file.createNewFile()) {
                System.out.println("File created: " + file.getAbsolutePath());
            } else {
                System.out.println("File exists" + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("Error while creating  " + fileName);
            e.printStackTrace();
        }
    }
}

