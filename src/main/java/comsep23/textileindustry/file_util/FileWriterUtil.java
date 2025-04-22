package comsep23.textileindustry.file_util;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class FileWriterUtil {


    public void writeToFile(String fileName, String content) {
        String BASE_PATH = "/mnt/data/textile_system/";
        File file = new File(BASE_PATH + fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.append(content);
            writer.newLine();
            System.out.println("Record added to file: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error while writing to file: " + fileName);
            e.printStackTrace();
        }
    }

    public void writeSaleRecord(String materialName, int quantity, double price) {
        String content = String.format("Sale: %s, Quantity: %d, Price: %.2f, Date: %s",
                materialName, quantity, price, java.time.LocalDate.now());
        writeToFile("sold.txt", content);
    }

    public void writeNeedMaterialRecord(String materialName, int requiredQuantity) {
        String content = String.format("Request for supply: %s, Required quantity: %d, Date: %s",
                materialName, requiredQuantity, java.time.LocalDate.now());
        writeToFile("need_material.txt", content);
    }

    public void writeDeliveredRecord(String materialName, int quantity) {
        String content = String.format("Delivered: %s, Quantity: %d, Date: %s",
                materialName, quantity, java.time.LocalDate.now());
        writeToFile("delivered.txt", content);
    }
}

