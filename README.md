# Project Name: tlias-web-management
The project is a backend management platform for educational institutions, focused on staff management, and includes the basic use of Spring Boot, Maven, and MySQL.
## How to use 
1. Create a new file called: "application-secret.yml"
```yml
#数据库连接信息
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/tlias
    username: ${local_mysql_username}
    password: ${local_mysql_password}

#阿里云OSS配置
aliyun:
  oss:
    endpoint: https://oss-cn-beijing.aliyuncs.com
    accessKeyId: ${OSS_ACCESS_KEY_ID}
    accessKeySecret: ${OSS_ACCESS_KEY_SECRET}
    bucketName: com-jason-web-tlias
```
2. Import sql file in "tlias-web-management/src/main/resources/sql/tlias-dump.sql"
3. Run "tlias-web-management/src/main/resources/nginx-1.22.0-tlias/nginx.exe"
4. Run "tlias-web-management/src/main/java/com/jason/TliasWebManagementApplication.java" 