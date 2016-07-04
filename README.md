# Business-Logic-Service

Business Logic Service implements all the logics  and get requests that are used in this application. This service has connection with Storage-Service and gets all the information asked by the user and send it to user-service. It uses Rest technologies and JSON type messages. It also has connection with Process-Centric layer. It takes external-data from storage and passes them to the Process-Centric service after obtaining meaningful information. It also make comparison of the current weight and expected weight(coming from external service) and decides the new goal and send this information to Process-Centric layer to set a new goal. 

For more details check wiki: https://github.com/Introsde-Final-Project-2016/Business-Logic-Service/wiki

Heroku adress: https://immense-mountain-93541.herokuapp.com 
