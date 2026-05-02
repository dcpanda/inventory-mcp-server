package io.github.dcpanda.inventory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class InventoryMcpApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private InventoryMcpAdapter inventoryMcpAdapter;

    @Test
    void contextLoads() {
        assertNotNull(applicationContext);
    }

    @Test
    void adapterIsPresent() {
        assertNotNull(inventoryMcpAdapter);
    }
}
