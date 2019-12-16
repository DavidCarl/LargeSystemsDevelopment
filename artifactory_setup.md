# Artifactory dependency

## How to use

There should be a settings.xml file in this repository (located in root).

It needs to be moved into your ~/.m2 folder so it will be placed at ~/.m2/settings.xml

This should should make it possible to pull and push new contracts.

## Add maven depency:

1. Add as repository in pom.xml:


    <repository>
      <id>mvn-repo</id>
      <url>https://github.com/DavidC…/LargeSystemsDevelopment/mvn-repo/
      </url>
      <releases>
        <enabled>true</enabled>
      </releases>
      <snapshots>
        <enabled>true</enabled>
      </snapshots>
    </repository>

2. Add dependency in pom.xml:


    <dependency>
      <groupId>LargeSystemDevelopment</groupId>
      <artifactId>LargeSystemDevelopment</artifactId>
      <version>1.0-SNAPSHOT</version>
    </dependency>


