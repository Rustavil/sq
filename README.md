# SQ
SQ(search questions) for Stack Exchange

## Interactive Demo
Checkout the Interactive Demo [here](https://sq-heroku.herokuapp.com/).

## Configuration
Search engine settings are SearchConfiguration
Properties:
* **stack.exchange.application.site** - Search site
* **stack.exchange.application.key** - Stack Exchange API application key

## REST Endpoints
 SearchEndpoint - endpoint for searching questions
```
    GET: /query?value={search_string}&page={page}&size{size}
    search_string - search string from the question
    page - page number
    size - number of items per page
```

## Notes
When building a project, you need to build the stackoverflow-java-sdk dependency

```
    git clone https://github.com/sanjivsingh/stackoverflow-java-sdk.git  
    cd stackoverflow-java-sdk  
    mvn clean install  
```
because the remote repository contains an obsolete build