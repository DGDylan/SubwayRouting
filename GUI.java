import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GUI {
	String[] s;
	String input1;
	String input2;

	//Class displays two JOptionPanes that have a list of all stations in the graph
	public GUI(ArrayList<String> stations) {
		s = new String[stations.size()];
		
		for(int i = 0; i < stations.size(); i++) {
			s[i] = stations.get(i);
		}
		
	    input1 = (String) JOptionPane.showInputDialog(null, "Please select your origin: ",
	            "Subway Routing", JOptionPane.QUESTION_MESSAGE, null, s, s[0]);
	    
	    input2 = (String) JOptionPane.showInputDialog(null, "Please select your destination: ",
	            "Subway Routing", JOptionPane.QUESTION_MESSAGE, null, s, s[0]);
	}

	//Return the from input
	public String getI1() {
		return input1;
	}

	//Returns the to input
	public String getI2() {
		return input2;
	}
	
}
