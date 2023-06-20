# Rich domain model

What is Rich domain ? 

Let's first define what is the opposite of rich domain

## Anaemic Domain Model

- Is an Anti-pattern
- Looks like a real domain object
  - Named after the nouns of the domain (order, payment, billing, project..)
  - Are connected to a rich relationships (deliveryMethods, products, paymentMethods...)
  - They are no business rules implemented inside
  - Bag of setters and getters [example](https://github.com/zakariabpifrance/rich-domain-orders/blob/b43ac64df5f88304b50219c75c06e9aecad6d9f9/src/main/java/fr/bpifrance/crafts/orders/model/Order.java#L5)
- Connected to a set of services that captures all data an apply business logic to those
