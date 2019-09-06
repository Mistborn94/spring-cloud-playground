# Run a container for rabbit-mq
docker run -d --name my-rabbit -p 4369:4369 -p 5671:5671 -p 5672:5672 -p 25672:25672 -p 15671:15671 -p 15672:15672 rabbitmq:management