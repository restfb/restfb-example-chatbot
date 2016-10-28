# RestFB example ChatBot

The RestFB example chatbot is a simple example implementation that shows the possibilities of
RestFB. You see an example how a Facebook Chatbot is implemented.


## Compiling

You need to copy the sample configuration `config.sample.properties` 
to `config.properties` and add your own values.

At the end of the compiling you get a nice one-file jar.

```shell 
mvn package
```

## Usage

To run the example server you simply need to call this on your shell:

```shell
java -jar target/Chatbots-1.0-SNAPSHOT.jar
```

The server is started with the included configuration.

In our default configuration the server runs on localhost and port 8444.