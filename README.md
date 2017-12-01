# CodelyTV Scala HTTP API
 
[![Software License][ico-license]][link-license]
[![Build Status][ico-travis]][link-travis]
 
## Introduction 

This is a example project showing up how could you implement a HTTP API with Scala. It's used by the CodelyTV Pro course on Scala HTTP API (Spanish).

Libraries used:
* Akka HTTP
* Akka HTTP testkit

## How To Start

1. Take a look at the course outline just in case you're interested into it!
2. Clone this repository: `git clone https://github.com/CodelyTV/scala-http-api.git scala-http-api`
3. [Download and install SBT](http://www.scala-sbt.org/download.html)
4. Go into the SBT console `cd scala-http-api; sbt` 
5. Run the tests `t`
6. Start the local server (TBD)
7. Request for the server status (TBD) `curl`

## Pre-push Git hook

There's one Git hook included. It's inside the `doc/hooks` folder and it will run the `prep` SBT task before pushing to any remote.

This `prep` task is intended to run all the checks you consider before pushing. At this very moment, it try to compile and check the code style rules with ScalaFmt.
 
You can define what this task does modifying the `prep` task in the `build.sbt` file. We like the approach of just running 1 single SBT task as the hook instead of multiple tasks because it's more efficient (the hook doesn't have to run SBT multiple times), and also because this way we can control the pre push tasks with the SBT alias defined at the `build.sbt` without altering the hooks.
 
If you want to install this hook, just `cd doc/hooks` and run `./install-hooks.sh`.

## About

This hopefully helpful utility has been developed by [CodelyTV][link-author] and [contributors][link-contributors].

We'll try to maintain this project as simple as possible, but Pull Requests are welcome!

## License

The MIT License (MIT). Please see [License File][link-license] for more information.

[ico-license]: https://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat-square
[ico-travis]: https://img.shields.io/travis/CodelyTV/scala-http-api/master.svg?style=flat-square

[link-license]: LICENSE
[link-travis]: https://travis-ci.org/CodelyTV/scala-http-api
[link-author]: https://github.com/CodelyTV
[link-contributors]: https://github.com/CodelyTV/scala-http-api/graphs/contributors

