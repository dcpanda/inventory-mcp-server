package io.github.dcpanda.inventory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import io.modelcontextprotocol.server.McpSyncServer;
import io.modelcontextprotocol.spec.McpSchema.Tool;
import io.modelcontextprotocol.spec.McpSchema.ResourceTemplate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class McpServerIntegrationTest {

    @Autowired
    private McpSyncServer mcpSyncServer;

    @Test
    void testRegisteredTools() {
        assertNotNull(mcpSyncServer);
        List<Tool> tools = mcpSyncServer.listTools();
        
        assertEquals(2, tools.size(), "Should have 2 tools registered");
        
        boolean hasStockTool = tools.stream().anyMatch(t -> t.name().equals("getStockLevel"));
        boolean hasLeadTimeTool = tools.stream().anyMatch(t -> t.name().equals("updateLeadTime"));
        
        assertTrue(hasStockTool, "getStockLevel tool should be registered");
        assertTrue(hasLeadTimeTool, "updateLeadTime tool should be registered");

        // Validate JSON Schema generation for getStockLevel
        Tool stockTool = tools.stream().filter(t -> t.name().equals("getStockLevel")).findFirst().get();
        assertNotNull(stockTool.inputSchema());
        assertTrue(stockTool.description().contains("Retrieve current stock level"));
    }

    @Test
    void testRegisteredResources() {
        List<ResourceTemplate> templates = mcpSyncServer.listResourceTemplates();
        
        assertEquals(1, templates.size(), "Should have 1 resource template registered");
        assertEquals("inventory://warehouse/{warehouseCode}", templates.get(0).uriTemplate());
    }
}
