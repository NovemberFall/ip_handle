# IP handle

1. if an user input valid ip, then it will post the information of this ip (By using Postman)
    - for example, via postman, you input: `localhost:8080/api/127.0.0.1`
    
2. if an user input invalid ip, then it will do nothing and don't store this data into DB

3. Only this IP is valid and not exist in my DB, it will store to my DB

4. I am using a time-based (set at 1 minute) expiring cache to return the data before querying the
   database again      
   - import the `guava` dependency 
   - Using `CacheBuilder` to build the cache

# Spring Boot - EhCache (Java Configuration) with CacheManager Configurations, Expiry, etc

@Cacheable
@CacheEvit

5. For DB, using hibernate dependency
    - 1. You can use H2 in-memory Database, and you don't need to do anything, just run this App.
    
    - 2. You can use your local MySQL DB, but create a database named `ip_address`, and it will 
      automatically create tables for you.
      
---

# How to run this app?

- on terminal, you input `mvn clean install`
- run `mvn spring-boot:run`



