# 基础镜像
FROM openjdk:8-jdk-alpine
# 作者
MAINTAINER zhangqing/846455480qq.com
VOLUME /tmp
ADD target/health-system-1.1.0-SNAPSHOT.jar health-system-docker.jar
#指定配置文件，以及jar包在服务器上的路径
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/health-auth-docker.jar"]
#暴露端口
EXPOSE 8005

