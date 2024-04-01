# strategy-pattern-spring-boot
This project gives a step by step explanation about implementing strategy pattern for a notification service, Making our code less populated with conditions and using the dynamic auto-wiring of classes as strategies.

Make sure to remove: assets-remove-in-usage folder it's there only for README.md usage

# Author
Ankit Mahala
ankitmahala07@gmail.com
https://www.linkedin.com/in/ankitmahala07/

# How to use
1. Change the Strategy interface name to suit your needs
2. Change the Strategy classes name and implementation as per your needs
3. Change the Strategy context name as per your need
4. Change the implementation of controller as per your need
5. No setup is needed just configure the project with basic pom sync and everything will work on go

# Configuration
Java 17 (If you want to use it with other version of Java then just change the configuration from POM.xml to your desired Java version and it should work fine)

# Point of interests
1. PaymentStrategy - Interface : This interface just exposes the 'pay' method which is overrided by each implementing strategy and dynamically invoked by context class.

2. <StrategyName>PaymentStrategy - @Component Class : Class that implements the PaymentStrategy interface and provides its own implementation for the pay function by overriding it. It's declared as a Component so that Spring Boot can scan them prior to the execution. Also keep note of the component name that we pass, This will the same that we will use to dynamically call the strategy from our Map.

3. PaymentContext - @Service Class : Payment context is basically the abstraction class which is completely optional but for a cleaner code it has been implemented this class exposes 2 methods - one to set the payment strategy before calling the next method that invokes the pay method of the set payment strategy.

4. PaymentController - @Controller Class : This class is basically a rest controller where all your rest calls will come, here I have combined the service layer and controller layer in same class but in production you would like to separate the class implementation on a serviceImplementation layer and only the endpoints in the controller layer.

The only confusion you may get in entire project is in this class where we are querying the Map for getting a strategy by using the strategyName(paymentMethod::string) but we are not initialising the paymentStrategies Map anywhere in our code.

This magic is all thanks to the Component scanning and Autowiring, Spring boot auto populated our map with the "Component Name::string" as Key and "<StrategyName>PaymentStrategy::Class" as Value in the Constructor of PaymentController which is annotated with @Autowired. That is why we can't see a separate function being called to one by one populate the Map.

# Why to use?
Well it makes your code beautiful, Supports SOLID principle Open for extension Closed for modification - Now you don't have to modify your code again and again, whenever you need to add a new strategy you just need to add a new ComponentClass implementing the interface with it's own implementation of pay method and voila everything works perfectly fine.

![Invalid - Cash Payment](./assets-remove-in-usage/Screenshot%201.png)
![Valid - UPI Payment](./assets-remove-in-usage/Screenshot%202.png)
![Valid - Apple Wallet Payment](./assets-remove-in-usage/Screenshot%203.png)
![Valid - Credit Card payment](./assets-remove-in-usage/Screenshot%204.png)
![Invalid - Paytm Payment](./assets-remove-in-usage/Screenshot%205.png)