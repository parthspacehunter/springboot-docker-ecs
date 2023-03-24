mvn clean install -DskipTests=True
docker-compose build --no-cache
docker-compose up -d
