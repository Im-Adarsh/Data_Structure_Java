package com.dsj.gp;

import com.dsj.graphs.Graph_Creation_Adj_List;

public class Prim_MST extends MST_Utils {

	final static int INITIALIZE_VALUE = Integer.MAX_VALUE;
	Graph_Creation_Adj_List graph_to_be_traversed;

	int[] weight;
	String[] parent;
	
	int numOfVertices;
	
	public Prim_MST(Graph_Creation_Adj_List graph_to_be_traversed) {
		this.graph_to_be_traversed = graph_to_be_traversed;
		numOfVertices = graph_to_be_traversed.numberOfVertices;
		weight = new int[numOfVertices];
		parent = new String[numOfVertices];
	}

}