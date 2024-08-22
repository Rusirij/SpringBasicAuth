# SpringBoot Basic Authentication Sample Project

## Introduction
Basic Authentication is a method for an HTTP user agent (e.g., a web browser) to provide a username and password when making a request. 
This project provides a foundational setup where users are authenticated through username and password credentials, stored in-memory. By leveraging Spring Security, 
the project ensures that only authorized users can access protected resources, showcasing essential security practices for Java-based web applications. 

### Step 1
Using the spring initializer https://start.spring.io/ create a new spring boot application and add Spring security, web and dev-tools dependencies.

### Step 2
Now open a suitable IDE and clone the project to your workspace and observe the code.

### Step 3
Observe the pom.xml file 
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
This dependency brings Spring Security, a powerful and customizable authentication and access control framework for Java applications. 
Adding this to the project enables Spring Boot application with built-in security features like authentication, authorization, and protection against common vulnerabilities (e.g., CSRF). 
The spring-boot-starter-security starter simplifies the configuration and setup of security features, allowing to secure applications with minimal effort easily.

### Step 4
Go to src > main > java > demo folder, Add the annotation @EnableWebSecurity to the class containing the main method
@EnableWebSecurity enables security in the application. This has to be applied on the class level and should be either on Main class or the Configuration class

Go to src > main > java > demo > config, and observe the class named Config inside it. We will be adding the security configuration inside this class.
Annotate the class with @Configuration
Extend Config class with WebSecurityConfigurerAdapter interface which allows customizing HTTP security for authentication and authorization.

```
@Configuration // Represents configuration class in spring boot
// Extending WebSecurityConfigurerAdapter class allows customizing HTTP security for features, such as endpoints authorization or the authentication manager configuration
public class Config extends WebSecurityConfigurerAdapter
```

- Override configure method containing AuthenticationManagerBuilder as a method argument.
- This method will be used to provide our security implementation. Here we will be configuring users that will be able to access the application via Basic authentication (Username and password)
- Override configure method containing AuthenticationManagerBuilder as a method argument.
- Add as many users as you like with inMemoryAuthentication, a username, password, and a role.

### Step 5

- Override configure method containing HttpSecurity as a method argument.
- This method will be used to provide additional features on http security.
- Here for example, we will be disabling the csrf token check. We will also be allowing one particular URL to be unauthenticated, that can be accessed without credentials.
- Here /rest/public/** will be permitted without any security while all other URLs will be secured.

```
@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

     // We added one public url, which will be accessible to all without security (starting with /rest/public . all other requests will be authenticated
        http.authorizeRequests().antMatchers("/rest/public/**").permitAll().
                anyRequest().authenticated().and().
                httpBasic();
    }

```


### Step 6

- Now, we need to add Rest endpoints to test our security mechanism. Create a class named Controller in controller package.
- Annotate the class with @RestController
- Add a class level request mapping using @RequestMapping("/rest")
- Create two methods, one with the public URL we defined in config. Another is with any other URL which we will test for security.
- Here the method greeting with url /Msg returns a string. Another method greetingPublic is created with url /public/Msg (pattern that is present in config to be allowed without authentication)

Now, try to access http://localhost:8080/rest/Msg
It should prompt you for a credentials window like below


![image](https://github.com/user-attachments/assets/8f04dc28-c0a3-4a47-b82b-9ede224fd749)


After login, you should be able to see the message returned by the endpoint
![image](https://github.com/user-attachments/assets/00967260-5954-445e-981b-e19051d056cf)











