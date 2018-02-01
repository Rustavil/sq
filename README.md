# SQ
SQ(search questions) for Stack Exchange

##Configuration
Search engine settings are SearchConfiguration
Properties:
* **stack.exchange.application.site** - Search site
* **stack.exchange.application.key** - Stack Exchange API application key


##Notes
When building a project, you need to build the stackoverflow-java-sdk dependency

```
    git clone https://github.com/sanjivsingh/stackoverflow-java-sdk.git  
    cd stackoverflow-java-sdk  
    mvn clean install  
```
because the remote repository contains an obsolete build