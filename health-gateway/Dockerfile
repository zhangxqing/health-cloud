FROM openjdk:8-jdk-alpine
MAINTAINER zhangqing/846455480qq.com
VOLUME ["/tmp"]
ADD health-gateway-docker.jar health-gateway-docker.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/health-gateway-docker.jar"]

