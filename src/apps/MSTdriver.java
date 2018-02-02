package apps;

import java.io.IOException;
import java.util.ArrayList;
import structures.Graph;

public class MSTdriver {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Graph g = new Graph("graph2.txt");
		PartialTreeList ptl = MST.initialize(g);
		ArrayList<PartialTree.Arc> mst = MST.execute(ptl);
		System.out.println(mst);
	}

}
