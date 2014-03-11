# pom.xml



## Dependencies



## Build


### plugins

```xml
      <plugins>
              <!--Compiler plugin, specify JDK version-->
              <plugin>
                  <groupId>org.apache.maven.plugins</groupId>
                  <artifactId>maven-compiler-plugin</artifactId>
                  <version>3.1</version>
                  <configuration>
                      <source>${jdk.version}</source>
                      <target>${jdk.version}</target>
                      <meminitial>128m</meminitial>
                      <maxmem>512m</maxmem>
                  </configuration>
              </plugin>

              <!--Resources plugin : configure for UTF-8 here -->
              <plugin>
                  <groupId>org.apache.maven.plugins</groupId>
                  <artifactId>maven-resources-plugin</artifactId>
                  <version>2.6</version>
                  <configuration>
                      <encoding>UTF-8</encoding>
                  </configuration>
              </plugin>

              <!-- Jetty Plugin -->
              <plugin>
                  <groupId>org.mortbay.jetty</groupId>
                  <artifactId>jetty-maven-plugin</artifactId>
                  <version>8.1.12.v20130726</version>
                  <configuration>
                      <scanIntervalSeconds>10</scanIntervalSeconds>
                      <stopKey>stop</stopKey>
                      <stopPort>9999</stopPort>
                      <connectors>
                          <connector implementation="org.eclipse.jetty.server.nio.SelectChannelConnector">
                              <maxIdleTime>3600000</maxIdleTime>
                              <port>${jetty.port}</port>
                          </connector>
                      </connectors>
                  </configuration>

                  <executions>
                      <execution>
                          <id>start-jetty</id>
                          <phase>pre-integration-test</phase>
                          <goals>
                              <goal>run</goal>
                          </goals>
                          <configuration>
                              <scanIntervalSeconds>0</scanIntervalSeconds>
                              <daemon>true</daemon>
                          </configuration>
                      </execution>

                      <execution>
                          <id>stop-jetty</id>
                          <phase>post-integration-test</phase>
                          <goals>
                              <goal>stop</goal>
                          </goals>
                      </execution>
                  </executions>
              </plugin>

              <!--new plugin here-->




      </plugins>
```

**比较奇怪的是cobertura放到reporting里面，site生成的报告无法过滤excludes的测试。**
根据[Cobertura](http://mojo.codehaus.org/cobertura-maven-plugin/usage.html)网站上的说明：

### 基本配置如下：
默认plugin所放大跑reporting元素里面的
```xml
<project>
  ...
  <reporting>
    <plugins>
      ...
      <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>cobertura-maven-plugin</artifactId>
        <version>2.6</version>
      </plugin>
    </plugins>
  </reporting>
</project>

```

### 定制化的配置Instrumentation
如果需要定制化的配置Instrumentation, 则该插件需要放到build阶段。

```xml
<!--Code coverage Cobertura -->
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>cobertura-maven-plugin</artifactId>
                <version>${cobertura.version}</version>
                <configuration>
                    <instrumentation>
                        <!--excludes: To exclude classes from being instrumented-->
                        <excludes>
                            <exclude>com/depeng/igtest/**</exclude>
                        </excludes>

                        <!--&lt;!&ndash;ignores: to instructs Cobertura to ignore any calls to any method-->
                        <!--that matches the ignore regular expression. It will NOT skip over these-->
                        <!--classes during instrumentation &ndash;&gt;-->
                        <!--<ignores>-->
                        <!--<ignore>com.depeng.web.WebUtils</ignore>-->
                        <!--</ignores>-->
                    </instrumentation>
                    <formats>
                        <format>html</format>
                        <format>xml</format>
                    </formats>
                    <check/>
                </configuration>
            </plugin>


```


## Reporting

```xml
    <reporting>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-checkstyle-plugin</artifactId>
                    <version>2.11</version>
                    <reportSets>
                        <reportSet>
                            <reports>
                                <report>checkstyle</report>
                            </reports>
                        </reportSet>
                    </reportSets>
                </plugin>
            </plugins>
        </reporting>

```



