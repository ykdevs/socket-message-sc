# ServerとClientでSocket通信を行うプログラム

## 概要

ServerとClientでSocket通信を行うプログラムです。

## サーバー

引数なしで実行すると、サーバーとして起動します。

```shell
java -jar build/libs/socket-message-sc-0.0.1-SNAPSHOT.jar
```

## クライアント

引数にclientを指定して実行すると、クライアントとして起動します。

```shell
java -jar build/libs/socket-message-sc-0.0.1-SNAPSHOT.jar client
```

## 通信内容

| クライアント送信   | サーバ応答      | 内容                                             |
|------------|------------|------------------------------------------------|
| つなぎます      | どうもこんにちは   | クライアントからサーバに接続します                              |
| さようなら      | さようなら      | サーバ、クライアントそれぞれ切断します                            |
| こんにちは      | こんにちは      |                                                |
| ありがとうございます | どういたしまして   |                                                |
| こんばんは      | もう一度お願いします | 不明な言葉の場合、サーバは「もう一度お願いします」と応答します                |
| その他        | -          | それ以外の場合、クライアントは「もう一度入力してください.」と出力しサーバには送信しません。 |

クライアント

```text
Prompt(未接続): つなぎます
Client: サーバに接続します
Server: どうもこんにちは
Prompt(接続済): こんにちは
Server: こんにちは
Prompt(接続済): ありがとうございます
Server: どういたしまして
Prompt(接続済): こんばんは
Server: もう一度お願いします
Prompt(接続済): さようなら
Server: さようなら
Prompt(未接続): おわります
Disconnected from server.
```

サーバ

```text
Server is running on port 9000...
Client connected: 127.0.0.1
Server: どうもこんにちは
Client: こんにちは
Server: こんにちは
Client: ありがとうございます
Server: どういたしまして
Client: こんばんは
Server: もう一度お願いします
Client: さようなら
Server: さようなら
```
