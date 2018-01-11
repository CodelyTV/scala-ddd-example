# CodelyTV Scala HTTP API
 
[![Software License][ico-license]][link-license]
[![Build Status][ico-travis]][link-travis]
 
## Introduction 

This is a example project showing up how could you implement a HTTP API with Scala. It's used by the CodelyTV Pro course on Scala HTTP API (Spanish).

Libraries used:
* Akka HTTP
* Akka HTTP testkit

## How To Start

### Install the needed tools
1. Clone this repository: `git clone https://github.com/CodelyTV/scala-http-api.git scala-http-api`
2. Download and install [Docker compose](https://docs.docker.com/compose/install/). We'll need it in order to run all the project infrastructure.
3. Download and install [SBT](http://www.scala-sbt.org/download.html)

### Prepare the application environment
1. Copy [the Docker environment variables config file](docker/.env.dist) and tune it with your desired values: `cp docker/.env.dist docker/.env`
2. Start Docker and bring up the project needed containers: `cd docker/; docker-compose up -d`
3. Create the database tables in your Docker MySQL container: `sbt createDbTables`

### Enjoy!
1. Go into the SBT console `sbt` 
2. Run the tests `t`
3. Start the local server (TBD)
4. Request for the server status (TBD) `curl`
5. Take a look at the courses related to this repository (Spanish) just in case you're interested into them!
    * [Introducción a Scala](https://pro.codely.tv/library/introduccion-a-scala/63278/about/)
    * [API HTTP con Scala y Akka](https://pro.codely.tv/library/api-http-con-scala-y-akka/66747/about/)

## Pre-push Git hook

There's one Git hook included. It's inside the `doc/hooks` folder and it will run the `prep` SBT task before pushing to any remote.

This `prep` task is intended to run all the checks you consider before pushing. At this very moment, it try to compile and check the code style rules with ScalaFmt.
 
You can define what this task does modifying the `prep` task in the `build.sbt` file. We like the approach of just running 1 single SBT task as the hook instead of multiple tasks because it's more efficient (the hook doesn't have to run SBT multiple times), and also because this way we can control the pre push tasks with the SBT alias defined at the `build.sbt` without altering the hooks.
 
If you want to install this hook, just `cd doc/hooks` and run `./install-hooks.sh`.

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

The MIT License (MIT). Please see [License File][link-license] for more information.

[ico-license]: https://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat-square
[ico-travis]: https://img.shields.io/travis/CodelyTV/scala-http-api/master.svg?style=flat-square

[link-license]: LICENSE
[link-travis]: https://travis-ci.org/CodelyTV/scala-http-api

