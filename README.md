\# Bajaj Finserv Health - Java Qualifier (Varun Kumar)



\## Overview

This project is part of the \*\*Bajaj Finserv Health hiring challenge\*\* for PES University.  

It performs the following steps automatically when the Spring Boot app starts:



1\. Sends a POST request to the Bajaj Finserv API endpoint `/hiring/generateWebhook/JAVA` to generate a webhook URL and access token.  

2\. Prepares and executes the assigned SQL Query (Question 1 - Highest Salary Not on 1st Day).  

3\. Submits the final SQL query to the provided webhook using the received JWT token in the Authorization header.



---



\## Tech Stack

\- Java 17  

\- Spring Boot 3.2.2  

\- WebClient (Spring Reactive HTTP Client)  

\- Maven



---



\## How to Run

```bash

mvn clean package

java -jar target/bajaj-java-test-varun-1.0.0.jar

```



---



\## Example Output

```

🚀 Starting Bajaj Finserv Health Test for Varun...

✅ Webhook Response: {webhook=https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA, accessToken=eyJhbGciOi...}

✅ Final Response: {success=true, message=Webhook processed successfully}

```



---



\## Submission Details

\- \*\*GitHub Repository:\*\* \[https://github.com/YOUR\_USERNAME/bajaj-java-test-varun.git]  

\- \*\*Downloadable JAR:\*\* \[https://github.com/YOUR\_USERNAME/bajaj-java-test-varun/raw/main/bajaj-java-test-varun-1.0.0.jar]



