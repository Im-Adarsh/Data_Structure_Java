package com.dsj.graphs;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

import com.dsj.gp.Prim_MST;
import com.dsj.gp.WeightedNode;

/**
 * Implementation of an undirected-cyclic-graph using adjacency-lists.
 * 
 * IMPORTANT: This code assumes that the name of the vertices are unique.
 */
public class Graph_Creation_Adj_List extends Graph_Utils {

	public LinkedList<WeightedNode>[] adjList;

	@SuppressWarnings("unchecked")
	public Graph_Creation_Adj_List() {
		super();
		adjList = new LinkedList[numberOfVertices];
		getVertices();
		initializeLinkedLists();
	}

	private void initializeLinkedLists() {
		for (int i = 0; i < numberOfVertices; i++) {
			adjList[i] = new LinkedList<WeightedNode>();
		}
	}

	/**
	 * Add an edge or establish a relationship between two vertices.
	 * 
	 * @param from
	 *            Vertex 1
	 * @param to
	 *            Vertex 2
	 */
	public void addAnEdge(String from, String to, int weight) {
		int v1 = getIndexForThis(from);
		int v2 = getIndexForThis(to);

		if (doesListContainThisNode(v1, to)) {
			System.out.println(MessageFormat.format("{0} and {1} this are friends already.", from, to));
			return;
		}

		adjList[v1].add(new WeightedNode(to, weight));
		adjList[v2].add(new WeightedNode(from, weight));

		System.out.println(MessageFormat.format("{0}, you are now friends with {1}. Your friendship is {2} years old.", from, to, weight));

	}

	private boolean doesListContainThisNode(int v1, String to) {
		return adjList[v1].stream().anyMatch(connectedNode -> connectedNode.getVertexId().equals(to));
	}

	/**
	 * Check if there is an edge or relationship(assumed to be friends) between
	 * supplied vertices.
	 * 
	 * @param from
	 *            Vertex 1
	 * @param to
	 *            Vertex 1
	 */
	void isAnEdgeFromTo(String from, String to) {
		int v1 = getIndexForThis(from);

		if (doesListContainThisNode(v1, to)) {
			System.out.println("These two persons are friends.");
		} else {
			System.out.println("These two persons are not friends..");
		}
	}

	/**
	 * Remove the edge or relationship between the supplied nodes.
	 * 
	 * @param from
	 *            Vertex 1
	 * @param to
	 *            Vertex 2
	 */
	public void removeEdge(String from, String to) {
		int v1 = getIndexForThis(from);
		int v2 = getIndexForThis(to);

		if (!doesListContainThisNode(v1, to)) {
			System.out.println(MessageFormat.format("{0} and {1} aren't friends.)", from, to));
			return;
		} else {
			adjList[v1].remove(getAddressToThisNode(v1, to));
			adjList[v2].remove(getAddressToThisNode(v2, from));
			System.out.println("Unfriended.");
		}
	}

	private WeightedNode getAddressToThisNode(int index, String to) {
		List<WeightedNode> tempList = adjList[index];
		WeightedNode requireVal = null;

		for (int i = 0; i < tempList.size(); i++) {
			if (tempList.get(i).getVertexId().equals(to)) {
				requireVal = adjList[index].get(i);
			}
		}
		return requireVal;
	}

	/**
	 * SHow all the vertices(friends) connected to this vertex and all the
	 * indirectly connected(mutual friends) of this node.
	 * 
	 * @param vertex
	 * 
	 */
	public void showVertexInfo(String vertex) {
		int index = getIndexForThis(vertex);
		if (adjList[index].isEmpty()) {
			System.out.println(MessageFormat.format("{0}, you have not added any friends.", vertex));
			return;
		}

		showConnectedVertices(vertex);
		showAllMutualVertices(vertex);
	}

	/**
	 * Show all vertices connected to this vertex.
	 * 
	 * @param index
	 */
	void showConnectedVertices(String vertex) {
		int index = getIndexForThis(vertex);
		final String[] separator = { "" };

		System.out.println(MessageFormat.format("{0}, your friends are:", vertex));

		adjList[index].forEach(friend -> {
			System.out.print(separator[0]);
			System.out.print(friend.getVertexId());
			separator[0] = ", ";
		});
		System.out.println();
	}

	/**
	 * Show all mutual vertices for the supplied vertex and all other vertices.
	 * 
	 * @param index
	 *            The index from the map for the supplied vertex.
	 */
	void showAllMutualVertices(String vertex) {
		int index = getIndexForThis(vertex);

		for (int i = 0; i < numberOfVertices; i++) {
			if (index == i) {
				continue;
			}
			getMutualForTheseTwo(index, i);
		}
	}

	private void getMutualForTheseTwo(int index, int i) {
		System.out.println(
				MessageFormat.format("{0}, your mutual friends with {1} are:", arrIndexToVertexMap.get(index), arrIndexToVertexMap.get(i)));

		final String[] separator = { "" };
		adjList[index].forEach(friend -> {
			if (doesListContainThisNode(i, friend.getVertexId())) {
				System.out.print(separator[0] + friend.getVertexId());
				separator[0] = ", ";
			}
		});
		System.out.println();
	}

	/**
	 * Return the path that traverses all nodes at minimum cost.
	 */
	public void findMSTUsingPrim() {
		new Prim_MST(this);
	}
}
