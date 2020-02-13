.PHONY: start prep test

prep:
	@sbt prep

test:
	@sbt -Dfile.encoding=UTF8 -J-XX:ReservedCodeCacheSize=256M coverage test coverageReport && sbt coverageAggregate

start:
	@docker-compose up -d
