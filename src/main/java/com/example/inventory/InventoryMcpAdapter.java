package com.example.inventory;

import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springaicommunity.mcp.annotation.McpResource;
import org.springframework.stereotype.Service;

@Service
public class InventoryMcpAdapter {

    private final InventoryService legacyService;

    public InventoryMcpAdapter(InventoryService legacyService) {
        this.legacyService = legacyService;
    }

    @McpTool(description = "Retrieve current stock level for a product across all warehouses")
    public int getStockLevel(
            @McpToolParam(description = "Product SKU identifier", required = true) String sku) {
        return legacyService.getStockLevel(sku);
    }

    @McpTool(description = "Update supplier lead time for a purchase order")
    public boolean updateLeadTime(
            @McpToolParam(description = "Purchase order identifier", required = true) String poId,
            @McpToolParam(description = "New supplier lead time in days (non-negative)", required = true) int newLeadTime) {
        return legacyService.updateSupplierLeadTime(poId, newLeadTime);
    }

    @McpResource(uri = "inventory://warehouse/{warehouseCode}", name = "Warehouse Inventory Snapshot")
    public String getWarehouseSnapshot(String warehouseCode) {
        return legacyService.getWarehouseSnapshotJson(warehouseCode);
    }
}
