# omega

Below are the steps to configure the omega application on AWS 

1. Create a Elastic BeanStalk application
2. Create a new envionment under the Elastic BeanStalk application
3. Use Tomcat platform for the environment
4. Let the environment build up
5. Go to Configuration -> DB -> Modify
6. Create MySQL DB v 8.0.x with configuration of 10 GB, t2.micro
7. Let the DB get created
8. Once created, open local IDE setup
9. Update the DB name in build.gradle and context.xml
10. Run the application locally to check if DB can be connected
11. Once connected, run the CreateDB gradle command to create the DB
12. Run gradle buildWar command to build a new war with the new DB name
13. Upload the war to Elastic BeanStalk evnironment
14. Let it deploy and then test the application
