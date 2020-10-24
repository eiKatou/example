# このプロジェクトについて
Kotlinで書いたプログラムをAWS Lambdaで動かします。

# AWS SAMでの実行
```sh
./gralew clean
./gradlew buildZip

sam package \
    --profile work \
    --template-file template.yaml \
    --output-template-file serverless-output.yaml \
    --s3-bucket eikatou.programmer

aws cloudformation deploy \
    --template-file serverless-output.yaml \
    --stack-name kotlinLambdaStack \
    --profile work
```