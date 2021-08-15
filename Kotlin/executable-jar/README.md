# 概要
実行可能jarを作るプロジェクトです。

# 環境
```
$ ./gradlew -version

------------------------------------------------------------
Gradle 7.1
------------------------------------------------------------

Build time:   2021-06-14 14:47:26 UTC
Revision:     989ccc9952b140ee6ab88870e8a12f1b2998369e

Kotlin:       1.4.31
Groovy:       3.0.7
Ant:          Apache Ant(TM) version 1.10.9 compiled on September 27 2020
JVM:          11.0.3 (Oracle Corporation 11.0.3+12-LTS)
OS:           Mac OS X 10.16 x86_64

```

# jar作成
```
./gradlew clean jar
```

# 実行
```
java -jar build/libs/executable-jar-1.0-SNAPSHOT.jar
```