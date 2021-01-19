//Dylan Rambarran
//CS313 Jinqiu Liu
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	public static Graph g;
	
	public static void main(String[] args) {
		URLReader x = new URLReader();
		AddLinks();
		GUI gui = new GUI(g.getList());
		Path p = new Path(gui.getI1(), gui.getI2(), g);
	}
	  
	//Read the text file and add the information to the Graph
	public static void AddLinks() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("Output_revised.txt"));
			g = new Graph();
			String line = br.readLine();
			String currentLine = "";
			String previousStation = "";
			String currentStation = "";
		  
			while(line != null) {	  
				if(line.trim().equals("")) {
					line = br.readLine();
					continue;
				}
				  
				else if(line.matches("\\s+.*")) {
					if(currentStation.equals("")) {
						currentStation = line.trim();
					}
					  
					else {
						previousStation = currentStation;
						currentStation = line.trim();
						g.addLink(previousStation, currentStation, currentLine);
					}
				}
				  
				else {
					currentLine = line.trim();
					currentStation = "";
				}
				  
				line = br.readLine();
			}

		  }
		  
		 catch(IOException e) {
		 	System.out.println("Error!");
		 }
	  }
	
}
