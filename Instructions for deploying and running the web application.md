When the project is unzipped, in order to test the web application, do following:
- Open the terminal.
- Navigate to the directory where docker files in this project are located.
- To build the docker image run the following command: docker build -t hiqtextprocessor .
- When the image is created, run the following command: docker-compose up
- Open Postman (or some similar tool).
- Choose GET request.
- Use following URL: http://localhost:6001/fetchFile
- Click on "body".
- In Key part, type: file
- Choose "File" instead of "Text".
- For "Value", choose file that contains the text you want to be processed.
- Click on "Send". 

PS. If you would like to see and test this web application on the AWS cloud, let me know :). 