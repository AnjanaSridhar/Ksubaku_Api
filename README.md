# Ksubaku_Api
Java console tool which uses at least 2 different public websites API to retrieve informations about a given movie or music album
The jar in this folder contains the class with the api call.

Two apis are supported - IMDB, SPOTIFY

Using the below commands, information can be retrieved on a movie or an artist. The first parameter is the name of the api - IMDB or SPOTIFY and the second parameter is the name of the movie or the artist. If the movie name has more than one word, the name needs to enclosed in a double quote

Example: java -Dapi=spotify -DsearchString="Katy Perry" -jar Ksubaku_Exercise-0.0.1-SNAPSHOT-jar-with-dependencies.jar

Output: Katy Perry's albums in spotify api:
album	Katy Perry - Teenage Dream: The Complete Confection
album	PRISM (Deluxe)
album	One Of The Boys
album	Rise
album	PRISM
album	Teenage Dream

java -Dapi=imdb -DsearchString=aviator -jar Ksubaku_Exercise-0.0.1-SNAPSHOT-jar-with-dependencies.jar

aviator movies in imdb api:
2004	The Aviator
1985	The Aviator
2004	Howard Hughes: The Real Aviator
