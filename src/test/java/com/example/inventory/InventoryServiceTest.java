package com.example.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {

    private InventoryService inventoryService;

    @BeforeEach
    void setUp() {
        inventoryService = new InventoryService();
    }

    @Test
    void testGetStockLevel() {
        assertEquals(50, inventoryService.getStockLevel("SKU-8842"));
        assertEquals(3, inventoryService.getStockLevel("SKU-1001"));
        assertEquals(0, inventoryService.getStockLevel("NON-EXISTENT"));
    }

    @Test
    void testUpdateSupplierLeadTime() {
        assertTrue(inventoryService.updateSupplierLeadTime("PO-1029", 10));
        assertFalse(inventoryService.updateSupplierLeadTime("PO-1029", -1));
    }

    @Test
    void testGetWarehouseSnapshotJson() {
        String json = inventoryService.getWarehouseSnapshotJson("WH-EU-01");
        assertTrue(json.contains("WH-EU-01"));
        assertTrue(json.contains("ACTIVE"));
    }
}
