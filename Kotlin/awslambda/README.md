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

# AWS SAM(Docker)での実行
build dockerfile  
https://github.com/eiKatou/dotfiles/tree/master/Dockerfile

setup zsh alias  
https://github.com/eiKatou/dotfiles/tree/master/zsh
```
aws-export.work
sam-docker validate -t template.yaml
sam-docker package --template-file template.yaml --output-template-file serverless-output.yaml --s3-bucket eikatou.programmer
aws-docker cloudformation deploy --template-file serverless-output.yaml --stack-name kotlinLambdaStack 
```