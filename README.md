# spring-cloud-circuit-breaker
Spring Cloud Circuit Breaker pattern

# Integrate Prometheus and Grafana Dashboard
can also have these metrics collected by Prometheus and visualized in Grafana. 
To demonstrate this, the repo contains a Docker Compose file that will start Prometheus and Grafana locally and 
scrape the metrics being surfaces at /actuator/prometheus.



- Run docker-compose up
  - Go to http://localhost:3000 and login with the username admin and the password admin
  There will be a datasource pointing to the docker container running Prometheus and dashboard already 
  configured to visualize the Resilience4J metrics
- Prometheus metrics
  - curl -X GET http://localhost:8081/actuator/prometheus