import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

//Edge-List structure (from GraphPrototype)
public class Graph {
	public static HashMap<String, Station> stations;
	public static ArrayList<Link> links;

	public Graph() {
		stations = new HashMap<>();
		links = new ArrayList<>();
	}

	//Add stations to the HashMap if they do not exist already
	private Station addStation(String stationName) {
		Station query = stations.get(stationName);
		
		if(query == null) {
			Station newStation = new Station(stationName);
			stations.put(stationName, newStation);
			return newStation;
		}
		
		else {
			return query;
		}
	}

	//Established a from station and a to station, with it's specific line
	public void addLink(String from, String to, String line) {
		Station f = this.addStation(from);
		Station t = this.addStation(to);
		this.addDoubleLink(f, t, line);
	}

	//Creates the link(undirected) and adds then to the links ArrayList
	private void addDoubleLink(Station from, Station to, String line) {
		links.add(new Link(from, to, line));
		links.add(new Link(to, from, line));
	}

	//Method to print all stations
	public void printAllStations() {
		for(Station st: stations.values()) {
			System.out.println(st);
		}
	}

	//Method to print all links
	public void printAllLinks() {
		for (Link l : links) {
			System.out.println(l);
		}
	}

	//Method used to get the stations for the GUI
	public ArrayList<String> getList() {
		Object[] s = stations.values().toArray();
		ArrayList<String> x = new ArrayList<String>();
		
		for(int i = 0; i < s.length; i++) {
			x.add(s[i].toString());
		}
		
		return x;
	}

	//Method that returns an ArrayList of all stations that are adjacent to the passed in station
	public ArrayList<Station> adjacentStations(Station st) {
		ArrayList<Station> neighbors = new ArrayList<Station>();
		for(Link l : links) {
			if(l.from.equals(st)) {
				neighbors.add(l.to);
			}

			if(l.to.equals(st)) {
				neighbors.add(l.from);
			}
		}
		return neighbors;
	}

	//Method that returns true if there is a link between the two stations(both ways)
	public boolean isAdjacent(Station from, Station to) {
		for(Link l : links) {
			if(l.from.equals(from) && l.to.equals(to)) {
				return true;
			}

			if(l.from.equals(to) && l.to.equals(from)) {
				return true;
			}
		}

		return false;
	}

}
