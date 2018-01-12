# CodelyTV Scala HTTP API

<img src="http://codely.tv/wp-content/uploads/2016/05/cropped-logo-codelyTV.png" align="left" width="192px" height="192px"/>
<img align="left" width="0" height="192px" hspace="10"/>

[![License](https://img.shields.io/github/license/CodelyTV/scala-http-api.svg?style=flat-square)](LICENSE)
[![Build Status](https://img.shields.io/travis/CodelyTV/scala-http-api/master.svg?style=flat-square)](https://travis-ci.org/CodelyTV/scala-http-api)
[![Coverage Status](https://img.shields.io/coveralls/github/CodelyTV/scala-http-api/master.svg?style=flat-square)](https://coveralls.io/github/CodelyTV/scala-http-api?branch=master)

Project showing up how you could implement a HTTP API with Scala.
 
This is the first iteration of the project where you will find a very Object Oriented approach. We've followed the Ports & Adapters (or Hexagonal Architecture) Software Architecture using `trait`s as the domain contracts/ports in order to be implemented by the infrastructure adapters.

## Contents

* [Endpoints](#endpoints)
* [Libraries and implementation examples](#libraries-and-implementation-examples)
* [Environment setup](#environment-setup)
    * [Install the needed tools](#install-the-needed-tools)
    * [Prepare the application environment](#prepare-the-application-environment)
    * [Run the tests and start the HTTP server](#run-the-tests-and-start-the-http-server)
    * [Pre-push Git hook](#pre-push-git-hook)
* [Logs](#logs)
* [Deploy](#deploy)
* [About](#about)
* [License](#license)

## Endpoints

One of the goals of this project is to serve as an example for the [course on Scala HTTP API (Spanish)](https://pro.codely.tv/library/api-http-con-scala-y-akka/66747/about/) illustrating how to implement several concepts you would commonly find in any production application. In order to accomplish so, we have implemented the following 5 endpoints:
* `GET /status`: Application status health check.
* `POST /videos`: Create new video inserting it into the database and publishing the `video_created` domain event to the message queue.
* `GET /videos`: Obtain all the system videos.
* `POST /users`: Create new user inserting it into the database and publishing the `user_registered` domain event to the message queue.
* `GET /users`: Obtain all the system users.

## Libraries and implementation examples

| Feature                   | Library                                                     | Implementation examples    |
| ------------------------- | ----------------------------------------------------------- | -------------------------- |
| Build tool                | [SBT](https://www.scala-sbt.org/)                           | [Dependencies](project/Dependencies.scala), [configuration](project/Configuration.scala) & [build.sbt](build.sbt) |
| Style formatting          | [ScalaFmt](http://scalameta.org/scalafmt/)                  | [Rules](.scalafmt.conf) |
| HTTP server               | [Akka HTTP](https://doc.akka.io/docs/akka-http/current/)    | [Routes definition](src/main/tv/codely/scala_http_api/entry_point/Routes.scala), [server implementation](src/main/tv/codely/scala_http_api/entry_point/ScalaHttpApi.scala),<br> [Video POST controller](src/main/tv/codely/scala_http_api/entry_point/controller/video/VideoPostController.scala) & [its corresponding acceptance test](src/test/tv/codely/scala_http_api/entry_point/VideoEntryPointShould.scala) |
| JSON marshalling          | [Spray JSON](https://github.com/spray/spray-json)           | [User](src/main/tv/codely/scala_http_api/module/user/infrastructure/marshaller/UserJsonFormatMarshaller.scala) & [User attributes](src/main/tv/codely/scala_http_api/module/user/infrastructure/marshaller/UserAttributesJsonFormatMarshaller.scala) marshallers |
| Database integration      | [Doobie](http://tpolecat.github.io/doobie/)                 | [Video repository](src/main/tv/codely/scala_http_api/module/video/infrastructure/repository/DoobieMySqlVideoRepository.scala) & [its corresponding integration test](src/test/tv/codely/scala_http_api/module/video/infrastructure/repository/DoobieMySqlVideoRepositoryShould.scala) |
| Domain events publishing  | [Akka RabbitMQ](https://github.com/NewMotion/akka-rabbitmq) | [Publisher implementation](src/main/tv/codely/scala_http_api/module/shared/infrastructure/message_broker/rabbitmq/RabbitMqMessagePublisher.scala) & [its corresponding integration test](src/test/tv/codely/scala_http_api/module/shared/infrastructure/message_broker/rabbitmq/RabbitMqMessagePublisherShould.scala) |
| Infrastructure management | [Docker](https://www.docker.com/)                           | [Docker Compose definition](docker/docker-compose.yml) |
| Logging                   | [ScalaLogging](https://github.com/typesafehub/scala-logging)<br> + [Logback](https://logback.qos.ch/)<br> + [Logstash encoder](https://github.com/logstash/logstash-logback-encoder) | [Logback configuration](conf/logback.xml), [logger implementation](src/main/tv/codely/scala_http_api/module/shared/infrastructure/logger/scala_logging/ScalaLoggingLogger.scala) & [its corresponding integration test](src/test/tv/codely/scala_http_api/module/shared/infrastructure/logger/scala_logging/ScalaLoggingLoggerShould.scala) |
| Command line command      | [Scopt](https://github.com/scopt/scopt)                     | [Database tables creation script](src/main/tv/codely/scala_http_api/entry_point/cli/DbTablesCreator.scala) |
| Distribution/deploy       | [SBT Native packager](http://sbt-native-packager.readthedocs.io/en/latest/) | [Build & deploy instructions](#Deploy) |
| Continuous Integration    | [Travis CI](https://travis-ci.org/)                         | [Travis definition](.travis.yml) |
| Acceptance tests          | [Akka HTTP TestKit](https://doc.akka.io/docs/akka-http/current/routing-dsl/testkit.html) | Previously specified acceptance tests |
| Integration tests         | [ScalaTest](http://www.scalatest.org/) | Previously specified integration tests |
| Unit tests                | [ScalaTest](http://www.scalatest.org/)<br> + [ScalaMock](http://scalamock.org/) | [Video creator use case test](src/test/tv/codely/scala_http_api/module/video/application/create/VideoCreatorShould.scala) |
| Continuous Integration    | [Travis CI](https://travis-ci.org/)<br> + [SBT Coveralls](https://github.com/scoverage/sbt-coveralls) | [Travis definition](.travis.yml) |

## Environment setup

### Install the needed tools
1. Clone this repository: `git clone https://github.com/CodelyTV/scala-http-api.git scala-http-api`
2. Download and install [Docker compose](https://docs.docker.com/compose/install/). We'll need it in order to run all the project infrastructure.
3. Download and install [SBT](http://www.scala-sbt.org/download.html)

### Prepare the application environment
1. Copy [the Docker environment variables config file](docker/.env.dist) and tune it with your desired values: `cp docker/.env.dist docker/.env`
2. Start Docker and bring up the project needed containers: `cd docker/; docker-compose up -d`
3. Create the database tables in your Docker MySQL container: `sbt createDbTables`

### Run the tests and start the HTTP server
1. Enter into the SBT console: `sbt` 
2. Run the tests: `t`
3. Start the local server: `run`
4. Request for the server status: `curl http://localhost:8080/status`
5. Take a look at the courses related to this repository (Spanish) just in case you're interested into them!
    * [Introducción a Scala](https://pro.codely.tv/library/introduccion-a-scala/63278/about/)
    * [API HTTP con Scala y Akka](https://pro.codely.tv/library/api-http-con-scala-y-akka/66747/about/)

### Pre-push Git hook

There's one Git hook included. It's inside the `doc/hooks` folder and it will run the `prep` SBT task before pushing to any remote.

This `prep` task is intended to run all the checks you consider before pushing. At this very moment, it try to compile and check the code style rules with ScalaFmt.
 
You can define what this task does modifying the `prep` task in the `build.sbt` file. We like the approach of just running 1 single SBT task as the hook instead of multiple tasks because it's more efficient (the hook doesn't have to run SBT multiple times), and also because this way we can control the pre push tasks with the SBT alias defined at the `build.sbt` without altering the hooks.
 
In order to install this hook, just `cd doc/hooks` and run `./install-hooks.sh`.

## Logs

We've added a logging mechanism thanks to [logback](https://github.com/qos-ch/logback) and [logstash-logback-encoder](https://github.com/logstash/logstash-logback-encoder/) in order to:
* Output the log records through the standard output channel (usually, your terminal :P)
* Store the log records in JSON format in a log file available at `var/log/app_log.json`
* Compress the historical log files into `var/log/app_log-%d{yyyy-MM-dd}.gz` files
* Delete compressed historical logs older than 10 days 

If you want more information on the logging policies and appenders, [take a look at the logback.xml](conf/logback.xml).  

## Deploy

We use [SBT Native Packager](http://sbt-native-packager.readthedocs.io/en/latest/) in order to package the app in single Jar file that you can execute.

1. Create the universal package: `sbt universal:packageBin`.
2. Extract the generated zip: `unzip target/universal/codelytv-scala-http-api-1.0.zip -d ~/var/www/` which will contain:
    * `bin/`: All the executable binaries of our main classes in Unix and Windows (bat) format
    * `lib/`: All the project dependencies jar files.
3. Run the main app binary:
    * Without specifying any parameters (OK for this example app): `~/var/www/codelytv-scala-http-api-1.0/bin/scala-http-api`
    * Specifying parameters for the JVM: `~/var/www/codelytv-scala-http-api-1.0/bin/scala-http-api -Dconfig.resource=application/$CONFIG_PATH`
    * Specifying application parameters: `~/var/www/codelytv-scala-http-api-1.0/bin/scala-http-api -Dconfig.resource=application/$CONFIG_PATH -- -appParam`

## About

This hopefully helpful utility has been developed by [CodelyTV](https://github.com/CodelyTV) and [contributors](https://github.com/CodelyTV/scala-http-api/graphs/contributors).

We'll try to maintain this project as simple as possible, but Pull Requests are welcome!

## License

The MIT License (MIT). Please see [License](LICENSE) for more information.
