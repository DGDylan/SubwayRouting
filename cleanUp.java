import java.util.ArrayList;

public class cleanUp {
	
	public cleanUp(ArrayList<String> s) {
		//Gets rid of ArrayList values that are just "/" or null, or random strings that aren't stations
		for(int i = 0; i < s.size(); i++) {
			if(s.get(i).equals("/") || s.get(i).equals("") || s.get(i).contains("EXPRESS") || s.get(i).contains("Stops") || s.get(i).contains("( Only)") 
			   || s.get(i).contains("BROOKLYN") || s.get(i).contains("BRONX") || s.get(i).contains("MANHATTAN") || s.get(i).contains("QUEENS")
			   || s.get(i).contains("Subway Transfers") || s.get(i).contains("Stations /") || s.get(i).equals(" ")) {
				s.remove(i);
			}	
		}
		
		removeFSlash(s);
	}
	
	//Removes the forward slashes within each element of the ArrayList
	public void removeFSlash(ArrayList<String> s) {
		for(int i = 0; i < s.size(); i++) {
			if(s.get(i).contains("/")) { 
				String x = s.get(i).replace("/", "");
				s.set(i, x);
			}
		}
	}
	
}
