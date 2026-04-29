package com.example.inventory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetStockLevel() throws Exception {
        // SKU-8842 is seeded with 50 in InventoryService
        mockMvc.perform(get("/api/inventory/stock/SKU-8842"))
                .andExpect(status().isOk())
                .andExpect(content().string("50"));
    }

    @Test
    public void testUpdateLeadTime_Success() throws Exception {
        mockMvc.perform(put("/api/inventory/po/PO-1029/lead-time")
                .param("newLeadTime", "10"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateLeadTime_Failure() throws Exception {
        mockMvc.perform(put("/api/inventory/po/PO-1029/lead-time")
                .param("newLeadTime", "-1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetWarehouseSnapshot() throws Exception {
        mockMvc.perform(get("/api/inventory/warehouse/WH1/snapshot"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.warehouse").value("WH1"))
                .andExpect(jsonPath("$.status").value("ACTIVE"));
    }
}
