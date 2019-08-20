# RestFB example ChatBot

The RestFB example chatbot is a simple example implementation that shows the possibilities of
RestFB. You see an example how a Facebook Chatbot is implemented.

The example creates a war file and so you can use it within a docker container or on your own 
java container.

## Compiling

You need to copy the sample configuration `config.sample.properties` 
to `config.properties` and add your own values.

At the end of the compiling you get a `chatbots.war`.

```shell 
mvn package
```

## Usage 

To run the example chatbots you have to deploy the war as usual.

A simple possibility to test the chatbot war, you can use it in a docker container.

## Usage with Docker
You can use this (or a similar) docker-compose file:

```yaml
version: '3'

services:
  jetty:
    container_name: chatbot_example_jetty
    image: jetty:alpine
    restart: always
    ports:
    - "8544:8080"
    volumes:
    - ./jetty/webapps:/var/lib/jetty/webapps
    - ./jetty/wars/:/var/lib/jetty/wars/
    - ./jetty/etc/:/var/lib/jetty/etc/
    - ./jetty/home/jetty/:/home/jetty/
```

Don't forget to create the directory structure and put the war in the `webapps` directory. 

To deploy the war in the root you only need to name it `ROOT.war`, otherwise the war is deployed in the context named
like the war. Nothing special, but good to know 😎