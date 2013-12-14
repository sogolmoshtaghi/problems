package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Bishops {

	// Problem: Given an 8x8 chess board, you have a bishop that moves from the current position
	// to the target position. Write a method to find the minimum path from the current position
	// to the target position.

	// Okay, cool. So we have to find a minimum path -- this sounds like something we can solve
	// with a graph algorithm. Can we represent the board as nodes on a graph? Yes, we can have
	// each location on the board be a node on the graph, and there are paths from each node to
	// each of its neighboring diagonal nodes.

	// Awesome. So we've established that this can be solved via a graph algorithm such as A*
	// or Dijkstra's algorithm. How do we want to solve this? A* is pretty easy to code up if
	// we have an effective heuristic -- euclidean distance is a good heuristic here. So, lets
	// code this up using A*.

	public static void bishops(int size, int sourceX, int sourceY, int targetX, int targetY) {
		// okay, so we have a source and target and we need to solve from source to target.
		// how does a* work again? add the root node to priority queue, explore all neighbors,
		// add each of them to the priority queue, and poll priority queue for the next one.
		// sounds fair enough.
		PriorityQueue<AStarNode> explore = new PriorityQueue<AStarNode>();
		List<List<AStarNode>> board = buildBoard(size, targetX, targetY);

		explore.add(board.get(sourceX).get(sourceY));
		while(!explore.isEmpty()) {
			AStarNode current = explore.poll();
			System.out.println("exploring (" + current.x + "," + current.y + ").");
			if (current.x == targetX && current.y == targetY) {
				System.out.println("done!");
				return;
			} else {
				// okay, this is tricky. we need to add the neighboring diagonal nodes to
				// the queue, so how can we just grab those four?
				for (int i = current.x - 1; i < current.x + 2; i++) {
					for (int j = current.y - 1; j < current.y + 2; j++) {
						if (i >= 0 && i < size && j >= 0 && j < size ) {
							if (i != current.x && j != current.y) {
								explore.add(board.get(i).get(j));
							}
						}
					}
				}
			}
		}
		System.out.println("no path from source to target found.");
	}

	public static List<List<AStarNode>> buildBoard(int size, int targetX, int targetY) {
		List<List<AStarNode>> board = new ArrayList<List<AStarNode>>();
		for (int i = 0; i < size; i++) {
			List<AStarNode> row = new ArrayList<AStarNode>();
			for (int j = 0; j < size; j++) {
				row.add(new AStarNode(i, j, targetX, targetY));
			}
			board.add(row);
		}
		return board;
	}

	public static void main(String[] args) {
		bishops(8, 1, 1, 1, 7);
	}
}
