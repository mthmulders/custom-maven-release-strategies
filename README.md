# Custom strategies for the Maven Release Plugin
[![CircleCI](https://circleci.com/gh/mthmulders/custom-maven-release-strategies.svg?style=svg)](https://circleci.com/gh/mthmulders/custom-maven-release-strategies)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=it.mulders.maven%3Acustom-maven-release-strategies&metric=alert_status)](https://sonarcloud.io/dashboard?id=it.mulders.maven%3Acustom-maven-release-strategies)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=it.mulders.maven%3Acustom-maven-release-strategies&metric=coverage)](https://sonarcloud.io/dashboard?id=it.mulders.maven%3Acustom-maven-release-strategies)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=it.mulders.maven%3Acustom-maven-release-strategies&metric=sqale_index)](https://sonarcloud.io/dashboard?id=it.mulders.maven%3Acustom-maven-release-strategies)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=it.mulders.maven%3Acustom-maven-release-strategies&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=it.mulders.maven%3Acustom-maven-release-strategies)
[![Download](https://api.bintray.com/packages/mthmulders/open-source/custom-release-strategies/images/download.svg) ](https://bintray.com/mthmulders/open-source/custom-release-strategies/_latestVersion)

## Using it

Add the following to `pom.xml`:

```xml
<repositories>
    <repository>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
        <id>bintray-mthmulders-open-source</id>
        <name>bintray</name>
        <url>https://dl.bintray.com/mthmulders/open-source</url>
    </repository>
</repositories>
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