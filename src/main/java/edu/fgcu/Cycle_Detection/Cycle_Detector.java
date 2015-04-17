package edu.fgcu.Cycle_Detection;

import java.lang.reflect.Field;
import java.util.Set;

import org.jgrapht.alg.CycleDetector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;


public class Cycle_Detector {
	private ListenableDirectedGraph g;
	private CycleDetector detector;

	public Cycle_Detector(){
		g = new ListenableDirectedGraph(DefaultEdge.class);
		detector = new CycleDetector(g);
	}

	public void createTree(String className) {

		Class aClass = null;
		try {
			aClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			
		}
		Field[] fields = aClass.getDeclaredFields();
		for(Field f : fields){
			Class c = f.getType();
			addEdge(aClass.getName(), c.getName());
			//createTree(c.getName(), count-1);
		}
		//return false;
	}
	
	public void addEdge(Object start, Object end){
		if(!g.containsVertex(start)){ g.addVertex(start); }
		if(!g.containsVertex(end)){ g.addVertex(end); }
		g.addEdge(start, end);
	}
	
	public boolean detectCycle(){
		return detector.detectCycles();
	}
	
	public boolean detectCycleContaining(Object className){
		return detector.detectCyclesContainingVertex(className);
	}
	
	public Set findCycles(){
		return detector.findCycles();
	}
	
	public Set findCyclesContaining(Object className){
		return detector.findCyclesContainingVertex(className);
	}
	
	public ListenableDirectedGraph getG(){
		return g;
	}
	
	public CycleDetector getDetector(){
		return detector;
	}
	
//	@SuppressWarnings("unchecked")
//	public static void main(String[] args) {
//		String aName = "edu.fgcu.Cycle_Detection.A";
//		String[] classNames = {	"edu.fgcu.Cycle_Detection.A",
//								"edu.fgcu.Cycle_Detection.B",
//								"edu.fgcu.Cycle_Detection.C" };
//		Cycle_Detector cycle = new Cycle_Detector();
//		for(String className : classNames){
//			cycle.createTree(className);
//		}
//		
//		if(cycle.detectCycleContaining(aName)){
//			Set edgeSet = cycle.getG().edgesOf(aName);
//			for(Object edge : edgeSet){
//				System.out.println(edge);
//			}
//		}
//	}
}
