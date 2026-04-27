package com.example.inventory;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InventoryService {

    private final Map<String, Integer> stockLevels = new ConcurrentHashMap<>();
    private final Map<String, Integer> poLeadTimes = new ConcurrentHashMap<>();

    public InventoryService() {
        // Seed some data
        stockLevels.put("SKU-8842", 50);
        stockLevels.put("SKU-1001", 3);
        stockLevels.put("SKU-9999", 12);
        
        poLeadTimes.put("PO-1029", 5);
        poLeadTimes.put("PO-2020", 14);
    }

    public int getStockLevel(String sku) {
        return stockLevels.getOrDefault(sku, 0);
    }

    public boolean updateSupplierLeadTime(String poId, int newLeadTime) {
        if (newLeadTime < 0) return false;
        poLeadTimes.put(poId, newLeadTime);
        return true;
    }

    public String getWarehouseSnapshotJson(String warehouseCode) {
        return String.format("{\"warehouse\": \"%s\", \"status\": \"ACTIVE\", \"itemCount\": %d}", 
            warehouseCode, stockLevels.size());
    }
}
