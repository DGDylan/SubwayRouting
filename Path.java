import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Stack;

public class Path {
	public static Graph g;
	Station from;
	Station to;

	Path(String from, String to, Graph g) {
		this.g = g;
		this.from = g.stations.get(from);
		this.to = g.stations.get(to);

		ArrayList<Station> path = findSP(this.from, this.to);
		ArrayList<Station> pathReversed = reverse(path); //Path ArrayList needs to be reversed to get the correct order
		displayPath(pathReversed);
	}

	//Function to find the shortest path
	public static ArrayList<Station> findSP(Station from, Station to) {
		ArrayList<Station> path = new ArrayList<Station>(); //Will store the shortest path
		HashMap<Station, Boolean> visited = new HashMap<Station, Boolean>(); //HashMap that uses a Station key and Boolean object to tell if a station has been visited
		Queue<Station> queue = new LinkedList<Station>(); //Queue for stations
		Stack<Station> stack = new Stack<Station>(); //Stack that will be used to find the shortest path

		//If the same station is requested twice return null
		if(from.equals(to)) {
			return null;
		}

		//Initialize with from station
		queue.add(from);
		stack.add(from);
		visited.put(from, true);

		while(!queue.isEmpty()) {
			Station current = queue.poll();
			ArrayList<Station> adjacentVertices = g.adjacentStations(current); //ArrayList to hold all neighbors of the current station
			//Add stations to the queue, stack and visited using the adjacent stations until the final station is met
			for(Station st : adjacentVertices) {
				if(!visited.containsKey(st)) {
					queue.add(st);
					visited.put(st, true);
					stack.add(st);
					if(st.equals(to)) { //Last station has been met
						break;
					}
				}
			}
		}

		//Will go through the stack and add the shortest path from the to station to the from station into the path ArrayList
		Station st;
		Station current = to;
		path.add(to);
		while(!stack.isEmpty()) {
			st = stack.pop();
			if(g.isAdjacent(current, st)) {
				path.add(st);
				current = st;
				if(st.equals(from)) { //First station has been met
					break;
				}
			}
		}

		return path;
	}

	//Method to reverse an ArrayList
	public ArrayList<Station> reverse(ArrayList<Station> path) {
		ArrayList<Station> reversed = new ArrayList<Station>();

		for(int i = path.size() - 1; i >= 0; i--) {
			reversed.add(path.get(i));
		}

		return reversed;
	}

	//Display shortest path to JOptionPane
	public void displayPath(ArrayList<Station> path) {
		StringBuilder sb = new StringBuilder();
		sb.append("Shortest Path: " + "\n");
		for(Station st : path) {
			sb.append(st + "\n");
		}
		JFrame f=new JFrame();
		JOptionPane.showMessageDialog(f,sb.toString(),"Subway Routing",JOptionPane.DEFAULT_OPTION);
	}

}
