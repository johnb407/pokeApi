# Pokemon

# Run project

## Requirements

Java 11 JDK

ActiveMQ
docker pull rmohr/activemq
docker run -p 61616:61616 -p 8161:8161 rmohr/activemq

## Compile
Open console in root folder, if your jdk isn't not default Java 11 

JAVA -version
set JAVA_HOME=C:\path\to\jdk11
.\mvnw clean install


## Run
then go to \target
To Run in console and rest api
.\mvnw spring-boot:run -Dspring-boot.run.arguments="<pokemon name>"

To Run in rest api
.\mvnw spring-boot:run

## Test
Se ha habilitado el swagger, ingresar a la url
http://localhost:8080/swagger-ui/index.html

### get-types-rest-controller  (Obtener los tipos de un pokemon)
Dar click en Try it out, ingresar el nombre del pokemon en el campo PokemonName y despues Execute

### user-controller  (crear un usuario)
Dar click en Try it out y despues Execute, en la respuesta se obtendra el uuid del usuario creado

### add-favorite-pokemon-controller (Agregar un pokemon favorito)
Dar click en Try it out, ingresar el usuario creado en el endpoint anterior en el header user y el nombre del pokemon en el body
{
"pokemonId": "string"
}
y despues Execute


