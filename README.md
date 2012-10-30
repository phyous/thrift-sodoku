thrift-sodoku
=====

A thrift(http://thrift.apache.org/) based client & server that accepts sodoku puzzles (formatted as a
String) and outputs a solved version.

**Usage Instructions**

1. Make sure maven is installed on your system
>See http://maven.apache.org/download.html

2. Build code for server
>mvn package

3. Build code for client
>mvn package -f pom-client.xml

4. Run server
>java -jar target/thrift-sodoku-server-1.0.jar

5. Run client in a separate console (which generates request and prints output)
>java -jar target/thrift-sodoku-client-1.0.jar

5. Observe the beautifully logged data
```text
Attempting to solve:
 ---+---+---
|862|300|000|
|500|908|040|
|039|000|180|
 ---+---+---
|108|090|037|
|000|207|000|
|920|010|604|
 ---+---+---
|013|000|490|
|080|409|001|
|000|005|863|
 ---+---+---
Solution from server:
 ---+---+---
|854|139|267|
|673|542|189|
|219|867|354|
 ---+---+---
|395|628|741|
|467|951|832|
|182|473|695|
 ---+---+---
|531|296|478|
|748|315|926|
|926|784|513|
 ---+---+---
 ```