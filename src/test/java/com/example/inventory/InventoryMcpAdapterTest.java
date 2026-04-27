package com.example.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryMcpAdapterTest {

    private InventoryService inventoryService;
    private InventoryMcpAdapter adapter;

    @BeforeEach
    void setUp() {
        inventoryService = new InventoryService();
        adapter = new InventoryMcpAdapter(inventoryService);
    }

    @Test
    void testGetStockLevel() {
        int stock = adapter.getStockLevel("SKU-8842");
        assertEquals(50, stock);
    }

    @Test
    void testUpdateLeadTime() {
        boolean result = adapter.updateLeadTime("PO-1029", 10);
        assertTrue(result);
    }

    @Test
    void testGetWarehouseSnapshot() {
        String result = adapter.getWarehouseSnapshot("WH1");
        assertTrue(result.contains("WH1"));
    }
}
