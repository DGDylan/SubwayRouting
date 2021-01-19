import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*ISSUES THAT NEED TO BE MANUALLY FIXED:
 * -Unnecessary words in station names
 * -Elements of list that were supposed to be a part of the previous element(Only a couple of these)
 * -Rename the stations (For example threelin to 3)
 * -Forward slashes that were not supposed to be delete
 * -Fixed spacing between hyphens
 * -Missing stations*/

public class URLReader {
	String page = "http://web.mta.info/nyct/service/";
	ArrayList<String> list = new ArrayList<String>();
	ArrayList<String> list2 = new ArrayList<String>();
	ArrayList<String> output = new ArrayList<String>();
	
	public URLReader() {
		Document doc = null;
	
		try {
			doc = Jsoup.connect(page).get();
			
			Elements links = doc.select("a[href]"); //Store every link on the site in a Element list
			
			//If the link name ends in .htm/line.htm/lin.htm/d.htm it is a train line that should be added to the first ArrayList
			for(Element link : links) {
				if(link.attr("href").contains("d.htm") || link.attr("href").contains("line.htm") || link.attr("href").contains("lin.htm")) {
					list.add(link.attr("href")); //Store all the link names to the different routes in an ArrayList
				}
			}
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
		//Create link by adding "http://web.mta.info/nyct/service/" + "LINKNAME" and adds to second ArrayList
		for(int i = 0; i < list.size(); i++) {
			String x = page + list.get(i);
			list2.add(x);
		}
		
		//Call scraper function
		for(int i = 0; i < list.size(); i++) {
			scrapeLink(list2.get(i), list.get(i)); //Will pass in the full link + the name of the link
		}
	}
	
	//Method to scrape the link
	public void scrapeLink(String url, String name) {
		Document doc;
		
		//Add the name of the line to the ArrayList
		String newname = name.replace(".htm", ""); //newname gets rid of the .htm so it is just the route name
		output.add(newname); //Add line name to ArrayList
		
		try {
			doc = Jsoup.connect(url).get();
			Elements stops = doc.select(".emphasized"); //Checks the link for when span = "emphasized" and adds them into an Elements list
			
			//Add each element to the ArrayList output
			for(int i = 0; i < stops.size(); i++) {
				output.add(" " + stops.get(i).text());
			}

			cleanUp c = new cleanUp(output); //Calls class that cleans up the read in data
		}
		
		catch(IOException e) {
			e.printStackTrace();
		}
		
		output(output); //Output the ArrayList
	}
	
	//Outputs the elements of the output ArrayList; Each element is a line of the exported file
	public void output(ArrayList<String> s) {
        BufferedWriter wr = null;
        
        try {
            wr = new BufferedWriter(new FileWriter("Output_unrevised.txt"));
            for (String var : s) {
                wr.write(var);
                wr.newLine();
            }
            
            wr.close();
        } 
        
        catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}
