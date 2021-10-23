# このアプリケーションは
SpringBootのWebアプリケーションをDockerで動かすのを試しています。
[Docker で Spring Boot - 公式サンプルコード](https://spring.pleiades.io/guides/gs/spring-boot-docker/)

# コマンド
```
./gradlew build
docker build --build-arg JAR_FILE=build/libs/\*.jar -t springio/gs-spring-boot-docker .
docker images
docker run -p 8080:8080 springio/gs-spring-boot-docker
curl http://localhost:8080/
```