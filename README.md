# People Web Service

> Note: I took sometime and deployed the code. You can check it using this [link](http://142.93.222.251:3000/api/requiredUsers). Please excuse my scrappy front end design. :sweat_smile:	 

## Steps to run the app using Ubuntu:
1. Install java using this command: `sudo apt-get update && sudo apt-get install openjdk-8-jdk`

2. Install node using this [link](https://tecadmin.net/install-latest-nodejs-npm-on-ubuntu/).

3. cd into the root directory and run `mvn clean install`.

4. cd into the frontend directory and run `npm i`.

5. cd into root directory and run `nohup java -jar target/people-0.0.1-SNAPSHOT.jar &`.

6. cd into frontend directory and run `nohup npm start &`.

7. Go to localhost:3000/api/requiredUsers on your web browser.

> Note: Remember to run `ps aux | grep <PID>` to find the PIDs of the nohup commands and kill it using the command: `kill -9 <PID>`

## Project structure:

`src` contains all the backend code. It is a Spring boot project 
powered by Java and other 3rd party libraries for 
json mapping, logging, data accessors etc. which can be seen
as dependencies in the `pom.xml` file. 

`pom.xml` is used by 
Maven for build automation and adding dependencies.

`frontend` contains all the code for the frontend. It was bootstrapped
using create-react-app. I added some basic styling using css. The `package.json`
defines all the dependencies that will be used by the Node Package Manager.

## Extra credit (Some basic ideas)
#### Automatically test app
• We can use an open source testing framework like JUnit, Mockito etc. to write 
unit tests and integration tests that can check for different kinds of things like rejecting invalid data, making 
sure that the methods are working as they should by checking actual output value and expected
value.

#### How the design of the end-to-end service and app should change if the dataset were three orders of magnitude larger:
• When it comes to frontend, we will need to use pagination and and loading skeleton so that people know that their request is being processed.

• Add multiple servers for the backend and use load balancing techniques to make sure that one server doesn't get overloaded with incoming requests.

• Use a cache to store the results and respond.

• Add a button to load the images instead of loading them by default.

## What I would have added with more time:
• Proper logging.

• Unit tests.

• Better error handling.

• Better project structure.