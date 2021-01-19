Program will not run on Repl.it because it relies on a GUI for input

Main Class:
-Graph g is a public static variable
-Instantiate a URLReader class
-Call AddLinks method that parses the text output and adds the stations and links to g
-Instantiate GUI class
-Instantiate Path class

URLReader:
-Connects to the mta site and adds every link on the site into an Element list(from jsoup)
-If the link ends in a variation of line.htm or lin.htm, add to the ArrayList list
-Create the links to each line by adding the mta url + the elements in the list, and then add the elements to ArrayList list2
-Call the scrapeLink method to retrieve the information
-scrapeLink takes in the Url from list2 and the name of the line from list
-First it will add the line name to the ArrayList output
-Then it will look for the span elements labeled "emphasized"
-The elements in the Element list will then be added to output
-By the end of the function we need to call cleanUp which cleans up the elements of the output list
-Lastly we call the output method that passes in the output list
-The output list adds each element to the text file
-There were minor manual adjustments that needed to be made after the cleanUp class was called

Graph Class:
-Used from GraphPrototype
-Changed the HashSet to ArrayList so that I could work with it easier
-Needed a function getList to return an ArrayList<String> to the GUI to display all stations in the list
-Needed to create a function that returns an ArrayList<Station> of stations that are adjacent to the passed in station
-Needed to create a boolean function that returns true if two stations are adjacent

GUI Class:
-ArrayList<String> gets passed in from g.getList
-Will create two prompts that have a list of all stations
-Both prompts will save into two different inputs
-Methods were created to return each input

Path Class:
-Inputs from the GUI and Graph g are passed in
-Assigns the stations using the inputs as keys for the station hashset
-findSP uses a breadth first style traversal to add the path to an ArrayList and returns the ArrayList
-The ArrayList passed in needs to be reversed to get the proper path so I created a function to reverse the order of the elements in the ArrayList
-displayPath adds the elements of the reversedpath ArrayList to a StringBuilder and then displays the StringBuilder elements into a JOptionPane

Link Class:
-Used from GraphPrototype
-Object contains station from, station to, and string line

Station class:
-Used from GraphPrototype
-Object contains the name of the station

cleanUp Class:
-Cleans up the read in data from the site as best as possible

Notes:
Overall, I though that this was the most challenging project of the three(as it should be). I found the shortest path algorithm to be the hardest, 
because at first I was having trouble implementing dijkstra's algorithm. However, I realized that in an unweighted, undirected graph such as mine,
the shortest path from one station to the next was like a breadth first traversal. My favorite part of the project was scraping the information
from the MTA website.