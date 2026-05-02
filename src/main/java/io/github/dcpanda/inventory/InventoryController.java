package io.github.dcpanda.inventory;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/stock/{sku}")
    public ResponseEntity<Integer> getStockLevel(@PathVariable String sku) {
        int level = inventoryService.getStockLevel(sku);
        return ResponseEntity.ok(level);
    }

    @PutMapping("/po/{poId}/lead-time")
    public ResponseEntity<Void> updateLeadTime(@PathVariable String poId, @RequestParam int newLeadTime) {
        boolean success = inventoryService.updateSupplierLeadTime(poId, newLeadTime);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/warehouse/{warehouseCode}/snapshot", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getWarehouseSnapshot(@PathVariable String warehouseCode) {
        String snapshot = inventoryService.getWarehouseSnapshotJson(warehouseCode);
        return ResponseEntity.ok(snapshot);
    }
}
