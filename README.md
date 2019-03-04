# Address-Book-RESTful-API

A RESTful API for an address book using Spark, JUnit, and Elasticsearch data store.  

Quang Vo, George Mason University, Fairfax, VA

Endpoints:
- GET /contact?pageSize={}&page={}&query={} - provide a listing of all contacts, need to allow for a defined pageSize (number of results allowed back), and the ability to offset by page number to get multiple pages. Query also should be a query for queryStringQuery as defined by Elasticsearch that you can pass directly in the Elasticsearch call.

- GET - return the contact by a unique name
GET http://localhost:4567/contact/:name

POST - create a contact with a unique name
POST http://localhost:4567/contact

PUT - update a contact by a unique name 
PUT http://localhost:4567/contact/:name

DELETE - delete the contact by a unique name
DELETE http://localhost:4567/contact/:name

Dependencies:
Spark
elasticsearch datastore
https://www.elastic.co/downloads/elasticsearch

Testing
The API was tested using JUnit.
