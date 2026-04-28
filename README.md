# Inventory MCP Server Demo

This project is a Spring Boot 3 application that exposes a legacy inventory service as an MCP (Model Context Protocol) server using Spring AI.

## Prerequisites
- Java 17 or higher
- Maven 3.8+

## How to Run

1.  **Build the project:**
    ```bash
    mvn clean install
    ```

2.  **Run the application:**
    ```bash
    mvn spring-boot:run
    ```

3.  **Access the MCP Server:**
    - The server will be running on `http://localhost:8080`.
    - The MCP SSE endpoint is available at `http://localhost:8080/mcp`.

## Exposed Tools and Resources

### Tools
- `getStockLevel`: Retrieve current stock level for a product.
- `updateLeadTime`: Update supplier lead time for a purchase order.

### Resources
- `inventory://warehouse/{warehouseCode}`: Get a JSON snapshot of a warehouse's inventory.

## Understanding Tools vs. Resources

In MCP, choosing between a Tool and a Resource depends on the intent of the interaction.

| Feature | Tools | Resources |
| :--- | :--- | :--- |
| **Nature** | **Active** (Action-oriented) | **Passive** (Data-oriented) |
| **Interaction** | LLM *calls* the tool to perform a task. | LLM *reads* the resource to gain context. |
| **Side Effects** | Can change system state (Write/Update). | Read-only (Idempotent). |
| **Example** | `updateLeadTime(orderId, days)` | `inventory://warehouse/WH001` |

### When to use a Tool
Use a **Tool** when the LLM needs to *do* something or perform a calculation that requires specific parameters.
- **Example:** `getStockLevel` is a tool because the LLM needs to provide a `productCode` to get a specific answer. It's a "query" action.
- **Example:** `updateLeadTime` is a tool because it changes data in the system.

### When to use a Resource
Use a **Resource** when you want to provide a "document" or a "snapshot" of data that the LLM can use as background knowledge.
- **Example:** `inventory://warehouse/{warehouseCode}` is a resource because it provides a complete snapshot of everything in a warehouse. The LLM can then look through this data to answer multiple questions without making further tool calls.

## Testing with MCP Inspector

You can test this server using the [MCP Inspector](https://github.com/modelcontextprotocol/inspector):
