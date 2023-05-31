# Custom strategies for the Maven Release Plugin
[![Build status](https://github.com/mthmulders/custom-maven-release-strategies/actions/workflows/main.yml/badge.svg)](https://github.com/mthmulders/custom-maven-release-strategies/actions/workflows/main.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=it.mulders.maven%3Acustom-maven-release-strategies&metric=alert_status)](https://sonarcloud.io/dashboard?id=it.mulders.maven%3Acustom-maven-release-strategies)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=it.mulders.maven%3Acustom-maven-release-strategies&metric=coverage)](https://sonarcloud.io/dashboard?id=it.mulders.maven%3Acustom-maven-release-strategies)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=it.mulders.maven%3Acustom-maven-release-strategies&metric=sqale_index)](https://sonarcloud.io/dashboard?id=it.mulders.maven%3Acustom-maven-release-strategies)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=it.mulders.maven%3Acustom-maven-release-strategies&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=it.mulders.maven%3Acustom-maven-release-strategies)
[![Download](https://api.bintray.com/packages/mthmulders/open-source/custom-release-strategies/images/download.svg) ](https://bintray.com/mthmulders/open-source/custom-release-strategies/_latestVersion)

## Using it

Add the following to the root of your projects `pom.xml`:

```xml
<pluginRepositories>
    <pluginRepository>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
        <id>bintray-mthmulders-open-source</id>
        <name>bintray-plugins</name>
        <url>https://dl.bintray.com/mthmulders/open-source</url>
    </pluginRepository>
</pluginRepositories>
```

Then configure the Maven Release Plugin as follows:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-release-plugin</artifactId>
    <version>3.0.0-M1</version><!-- You need at least version 3.0.0-M1! -->
    <dependencies>
        <dependency>
            <groupId>it.mulders.maven</groupId>
            <artifactId>ascii-art-logger</artifactId>
            <version>0.0.6</version>
        </dependency>
    </dependencies>
    <configuration>
        <releaseStrategyId>ascii-art-enhanced-strategy</releaseStrategyId>
    </configuration>
</plugin>
```