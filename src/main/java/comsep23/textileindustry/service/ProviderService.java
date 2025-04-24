package comsep23.textileindustry.service;

import comsep23.textileindustry.file_util.FileWriterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProviderService {

    private final FileWriterUtil fileWriterUtil;

    private final String BASE_PATH = "/mnt/data/textile_system/";
    private final String FILE_NAME = "need_material.txt";

    public List<String> getAllRequests() {
        return readFileLines();
    }

    public int getTotalQuantity() {
        return readFileLines().stream()
                .mapToInt(this::extractQuantity)
                .sum();
    }

    public String getMostRequestedMaterial() {
        return getMaterialByExtremeQuantity(true);
    }

    public String getLeastRequestedMaterial() {
        return getMaterialByExtremeQuantity(false);
    }

    public void recordMaterialRequest(String materialName, int requiredQuantity) {
        fileWriterUtil.writeNeedMaterialRecord(materialName, requiredQuantity);
    }

    private List<String> readFileLines() {
        List<String> lines = new ArrayList<>();
        File file = new File(BASE_PATH + FILE_NAME);
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

    private int extractQuantity(String line) {
        try {
            String[] parts = line.split("Required quantity: ");
            if (parts.length > 1) {
                String qtyPart = parts[1].split(",")[0].trim();
                return Integer.parseInt(qtyPart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private String extractMaterialName(String line) {
        try {
            String[] parts = line.split("Request for supply: ");
            if (parts.length > 1) {
                return parts[1].split(",")[0].trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown";
    }

    private String getMaterialByExtremeQuantity(boolean findMax) {
        List<String> lines = readFileLines();
        Map<String, Integer> materialCountMap = new HashMap<>();

        for (String line : lines) {
            String name = extractMaterialName(line);
            int quantity = extractQuantity(line);
            materialCountMap.put(name, materialCountMap.getOrDefault(name, 0) + quantity);
        }

        return materialCountMap.entrySet()
                .stream()
                .sorted((a, b) -> findMax ?
                        Integer.compare(b.getValue(), a.getValue()) :
                        Integer.compare(a.getValue(), b.getValue()))
                .map(entry -> entry.getKey() + " — " + entry.getValue() + " шт.")
                .findFirst()
                .orElse("No data found");
    }
}
