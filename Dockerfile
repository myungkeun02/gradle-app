#openjdk:17-alpine라는 Docker 이미지를 기반으로 새로운 Docker 이미지를 생성합니다.
FROM openjdk:17-slim

#현재 작업 디렉토리를 /app으로 설정합니다. 이 디렉토리는 이후의 명령어에서 작업할 기본 디렉토리가 됩니다.
WORKDIR /app

#재 Dockerfile이 위치한 디렉토리(./build/libs)에 있는 app_gradle.jar 파일을 Docker 이미지의 /app 디렉토리로 복사합니다. 또한, 파일을 app.jar로 이름을 변경하여 복사합니다.
COPY ./build/libs/app_gradle.jar app.jar

#Docker 컨테이너가 시작될 때 실행되는 명령어를 정의합니다. 여기서는 Java 어플리케이션을 실행하는 명령어로, java -jar app.jar가 실행됩니다. 이것은 복사한 JAR 파일을 실행하여 Java 어플리케이션을 시작하는 역할을 합니다.
ENTRYPOINT ["java", "-jar", "app.jar"]