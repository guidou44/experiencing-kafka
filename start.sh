docker-compose up -d
mvn clean package -Dmaven.test.skip=true
mvn spring-boot:run -f scanner/pom.xml -Dspring-boot.run.profiles=prod
