To build run this command `mvn clean deploy`

```
<settings>
  <servers>
    <server>
      <id>github</id>
      <username>davidcarl</username>
      <password>here</password>
    </server>
  </servers>
</settings>
```

Add this to use our contract

```
<repositories>
    <repository>
        <id>mvn-repo</id>
        <url>https://github.com/DavidCarl/LargeSystemsDevelopment/mvn-repo/</url>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>LargeSystemDevelopment</groupId>
        <artifactId>LargeSystemDevelopment</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```