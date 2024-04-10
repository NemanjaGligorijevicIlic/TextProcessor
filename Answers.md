1. How much time did you end up spending on this coding test?
- 6,5h 

2. Explain why you chose the code structure(s) you used in your solution and any other specific choices you made. Please list of all your technical choises and motivate them.
- I chose to have one API class which would be used for communication and fetching the text file. That class is responsible only for taking the file text and
sending back the processed text. That method will just check if the file type is supported or not e.g. it is not possible to use JPEG. API class is aimed to be as simple as possible
and responsible only for one thing. In order to keep API class as simple as possible, there is one helper class which will do required actions for the API class. 
One enum class will contain all supported type of file. It is easy to go through the code and see what types are supported and to change (add/remove) types if needed.
Then there is a class for text processing, where the most frequent word will be found and prefix and suffix will be added.

I chose Java and Spring Boot. Spring Boot was chosen for its simplicity, extensive documentation, and robust features like auto-configuration and dependency management.

To deploy and run the web application, Docker wa chosen. Docker was selected for its lightweight, isolated containers, facilitating consistent deployment across environments, scalability, and efficient workflow management.

3. What would you add to your solution if you had more time? This question is especially important if you did not spend much time on the coding test - use this as an opportunity to explain what your solution is missing.
- Words like "you're", "I'm" etc. should be processed in certain way like to convert to "I am". Furthermore, if the most frequent word is "Sweden" and somewhere in the text, it is written in some way like "SweDen", "SweDeN" etc. 
then these words will not get prefix and suffix (but we can assume that none writes in that way). Furthermore, if there is the equal number of occurrences for two words,
then the application itself just chooses which one will get prefix and suffix. In real life, the "customer" should decide what would be done. To keep it simple, I assumed that it is not so important for this case. 

4. What did you think of this recruitment test?
- I like this task very much. 