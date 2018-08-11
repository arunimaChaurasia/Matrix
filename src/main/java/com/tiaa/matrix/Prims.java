package com.tiaa.matrix;
import java.math.BigDecimal;
//Ashish D'sa
//Prims O(V^2)
//Modified Prims O(ElogV)
import java.util.*;

public class Prims {
	static class EDGE{
	    int from, to;
	    Double weight;
	 
	    EDGE(int f, int t, Double double1){
	        from = f;
	        to = t;
	        weight = double1;
	    }
	}
	 
	public static ArrayList<EDGE> prims(ArrayList<ArrayList<EDGE>> G){
	    if(G.isEmpty())throw new NullPointerException("The Graph is empty");
	 
	    ArrayList<EDGE> mst = new ArrayList<>();
	    PriorityQueue<EDGE> pq = new PriorityQueue<>((Object o1, Object o2) -> {
	        EDGE first = (EDGE)o1;
	        EDGE second = (EDGE)o2;
	 
	        if(first.weight.compareTo(second.weight)<0)return -1;
	        else if(first.weight.compareTo(second.weight)>0)return 1;
	        else return 0;
	    });
	 
	    for(EDGE e:G.get(0)){
	        pq.add(e);
	    }
	 
	    boolean[] marked = new boolean[G.size()];
	    marked[0] = true;
	    while(!pq.isEmpty()){
	        EDGE e = pq.peek();
	 
	        pq.poll();
	        if(marked[e.from] && marked[e.to])continue;
	        marked[e.from] = true;  
	        for(EDGE edge:G.get(e.to)){
	            if(!marked[edge.to]){
	                pq.add(edge);  
	            }
	        }
	        marked[e.to] = true; 
	        mst.add(e);
	 
	    }
	    return mst;
	}
	 
	public static ArrayList<ArrayList<EDGE>> createGraph(EDGE[] edges){
	    ArrayList<ArrayList<EDGE>> G = new ArrayList<>();
	    int length = edges.length*2;
	    for(int i=0;i<length;++i){
	        G.add(new ArrayList<>());
	    }
	 
	    for(EDGE e:edges){
	        EDGE other = new EDGE(e.to, e.from, e.weight);
	        G.get(e.from).add(e);
	        G.get(e.to).add(other);
	        //System.out.println("Added edge ["+e.from+", "+e.to+" : "+e.weight+"] "+"["+e.to+", "+e.from+" : "+e.weight+"]");
	    }
	 
	    return G; 
	}
}
