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

## Testing with MCP Inspector

You can test this server using the [MCP Inspector](https://github.com/modelcontextprotocol/inspector):
