# Orders Chat Demo — How to Use

This project runs two Spring Boot services:

- orders-app (server): exposes a REST endpoint to list orders and an MCP tool the client can call.
- orders-chat-client (client): exposes a /chat endpoint; sends your prompt to the LLM and, when helpful, calls the server’s MCP tool.

This README focuses only on how to run and use the app (no Java code).

---

## Prerequisites
- Docker and Docker Compose (Compose v3.9 file assumed).
- Optional: Spring Boot Actuator on the server if you want /actuator/health for health checks.

---

## Start the stack
1) From the repository root, build and run:
   docker compose up --build -d
2) Verify containers:
   docker compose ps
   docker compose logs -f orders-app
   docker compose logs -f orders-chat-client

Note: In Compose v3.9, depends_on does not wait for readiness. If the client starts too early, start the server first or add a simple wait in the client entrypoint.

---

## Using the services
- List orders directly from the server (REST):
  Open your REST list URL in a browser or any HTTP client (e.g., http://localhost:8081/api/orders).
- Talk to the model via the client:
  Send a POST request to http://localhost:8080/chat with a JSON body like:
  { "message": "List the first 10 orders." }
  You should receive a JSON response with "answer" containing the generated reply.

---

## Stop and clean up
- Stop containers: docker compose down
- Stop and remove volumes: docker compose down -v

---

## Troubleshooting
- Can’t reach the server from the client? Use the service name (orders-app) inside Docker, not localhost.
- Client starts before server is ready (v3.9)? Start the server first, or add a simple wait loop so the client retries until the server responds.
- 404 on /actuator/health? Add the Actuator dependency to the server or use your own health endpoint.
- Port conflicts on host? Change the published ports in docker-compose.yml.
