# Query by Example (QBE) with GraphQL and Standard Approach  

## Introduction  

Query by Example (QBE) is an intuitive query technique that allows the dynamic creation of queries without the need to write statements containing field names. With a simple interface, this approach eliminates the dependency on query languages specific to each data repository, making the process more flexible and accessible. Instead, it uses an example object to automatically build dynamic queries, simplifying data retrieval.  

In the context of this project, we will explore the implementation of this approach using both GraphQL and a more standard approach.  

## Implementation with GraphQL and Standard Approach  

To demonstrate this approach, we use repository interfaces with `MongoRepository` and `QueryByExampleExecutor`. Below, we have two repository examples for retrieving data from the `Athlete` entity:  

### With GraphQL  

```java
@GraphQlRepository
public interface AthleteGraphqlQBERepository extends MongoRepository<Athlete, String>,  
        QueryByExampleExecutor<Athlete> {  
}
```  

### Standard Approach  

```java
public interface AthleteQBERepository extends MongoRepository<Athlete, String>,  
        QueryByExampleExecutor<Athlete> {  
}
```  

Additionally, this project includes test cases that exemplify the practical application of this technique, demonstrating its effectiveness and reliability in different scenarios.  

## Conclusion  

The Query by Example (QBE) approach offers a practical and flexible way to perform dynamic queries without directly relying on complex query languages. Its integration with `MongoRepository` and `QueryByExampleExecutor` simplifies development, enabling greater code reuse and easier maintenance.  

By applying this technique in both the GraphQL context and a standard approach, we demonstrate its versatility and efficiency. This project provides a solid foundation for further exploring the use of QBE in different scenarios and enhancing data retrieval in modern applications.  
