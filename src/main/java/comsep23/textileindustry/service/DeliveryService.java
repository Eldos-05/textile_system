package comsep23.textileindustry.service;

import comsep23.textileindustry.file_util.FileWriterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final FileWriterUtil fileWriterUtil;

    private final String BASE_PATH = "/mnt/data/textile_system/";
    private final String SOLD_FILE = "sold.txt";
    private final String DELIVERED_FILE = "delivered.txt";
    private final double DELIVERY_RATE = 100.0;

    public List<String> getPendingDeliveries() {
        return readFileLines(SOLD_FILE);
    }

    public List<String> getDeliveredOrders() {
        return readFileLines(DELIVERED_FILE);
    }

    public String deliverOrder(String keyword) {
        List<String> allSales = readFileLines(SOLD_FILE);
        List<String> updatedSales = new ArrayList<>();
        boolean found = false;

        for (String line : allSales) {
            if (line.toLowerCase().contains(keyword.toLowerCase())) {
                String materialName = extractMaterialName(line);
                int quantity = extractQuantity(line);
                fileWriterUtil.writeDeliveredRecord(materialName, quantity);
                found = true;
            } else {
                updatedSales.add(line);
            }
        }

        if (found) {
            overwriteFile(SOLD_FILE, updatedSales);
            return "The order has been delivered and recorded.";
        } else {
            return "No equipment has been found for delivery.";
        }
    }

    public int countDelivered() {
        return readFileLines(DELIVERED_FILE).size();
    }

    public int countPending() {
        return readFileLines(SOLD_FILE).size();
    }

    public double calculateEarnings() {
        return countDelivered() * DELIVERY_RATE;
    }

    private List<String> readFileLines(String fileName) {
        List<String> lines = new ArrayList<>();
        File file = new File(BASE_PATH + fileName);
        if (!file.exists()) return lines;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private void overwriteFile(String fileName, List<String> lines) {
        File file = new File(BASE_PATH + fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String extractMaterialName(String line) {
        try {
            String[] parts = line.split("Sale: ");
            if (parts.length > 1) {
                return parts[1].split(",")[0].trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown";
    }

    private int extractQuantity(String line) {
        try {
            String[] parts = line.split("Quantity: ");
            if (parts.length > 1) {
                return Integer.parseInt(parts[1].split(",")[0].trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
