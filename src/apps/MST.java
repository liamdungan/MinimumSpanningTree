package apps;

import structures.*;
import java.util.ArrayList;

public class MST {
	
	/**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {
	
		PartialTreeList ptl = new PartialTreeList();
		
		for (int i = 0; i < graph.vertices.length; i++){
				PartialTree pt = new PartialTree(graph.vertices[i]);
				MinHeap<PartialTree.Arc> arcs = pt.getArcs();
				
					for (structures.Vertex.Neighbor n = graph.vertices[i].neighbors; n != null ; n = n.next){
						arcs.insert(new apps.PartialTree.Arc(graph.vertices[i],n.vertex,n.weight));
					}
			
				ptl.append(pt);
		}
		
		return ptl;
	}

	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree list
	 * 
	 * @param ptlist Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
	 */
	public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist) {
		
		ArrayList<PartialTree.Arc> mstAL = new ArrayList<PartialTree.Arc>();
		
			while (ptlist.size() > 1){
					PartialTree pt = ptlist.remove();
					
					if (pt.getArcs().isEmpty())
						continue;
					
					PartialTree.Arc arc = pt.getArcs().deleteMin();
					Vertex ver = pt.getRoot();
					
					if (arc.v2.getRoot() == ver){
						while (arc.v2.getRoot() == ver){
							arc = pt.getArcs().deleteMin();
						}
					}
		
					mstAL.add(arc);
					PartialTree pt2 = ptlist.removeTreeContaining(arc.v2);
					pt2.merge(pt);
					ptlist.append(pt2);
					
					//ptlist = executeHelper(ptlist);
			}
		return mstAL;
	}
}
