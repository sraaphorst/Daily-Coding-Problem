#!/usr/bin/env python3
# day 182.py
# By Sebastian Raaphorst, 2019.


from typing import Dict, List

# Vertices are represented by integers, amd graphs are undirected, represented by lists of vertices.
Vertex = int
Graph = Dict[Vertex, List[Vertex]]


def is_undirected(graph: Graph) -> bool:
    """
    Determine if a graph is undirected, i.e. there are no loops and all edges are bidirectional.
    :param graph: the graph
    :return: True if undirected, False if directed

    >>> g1 = {1: [2, 3], 2: [1, 3], 3: [1, 2]}
    >>> is_undirected(g1)
    True
    >>> g2 = {1: []}
    >>> is_undirected(g2)
    True
    >>> g3 = {1: [1]}
    >>> is_undirected(g3)
    False
    """
    for (vertex, adjacencies) in graph.items():
        for adjacency in adjacencies:
            if vertex in graph[vertex]:
                return False
            if vertex not in graph[adjacency]:
                return False
    return True


def is_connected(graph: Graph) -> bool:
    """
    Determine if a graph (either directed or undirected) is connected.
    :param graph: the graph, as a dictionary of vertices (represented by int) and lists of adjacent vertices
    :return: True if connected, and False otherwise
    >>> g1 = {1: [2, 9], 2: [2, 3, 6], 3: [2, 4, 5], 4: [3], 5: [3, 6, 7], 6: [2, 5], 7: [5], 8: [9, 10], 9: [1, 8, 10], 10: [9, 8]}
    >>> is_connected(g1)
    True
    >>> g2 = {1: [2], 2: [2, 3, 6], 3: [2, 4, 5], 4: [3], 5: [3, 6, 7], 6: [2, 5], 7: [5], 8: [9, 10], 9: [8, 10], 10: [9, 8]}
    >>> is_connected(g2)
    False
    >>> g3 = {}
    >>> is_connected(g3)
    True
    >>> g4 = {0:[]}
    >>> is_connected(g4)
    True
    >>> g5 = {0:[], 1:[]}
    >>> is_connected(g5)
    False
    """
    vertices = list(graph.keys())
    if len(vertices) == 0:
        return True

    num_vertices = len(vertices)

    # Keep in track of what is visited.
    visited = {vertex: False for vertex in vertices}

    # Start the DFS to determine if connected.
    def dfs(stack: List[int], visited: Dict[int, bool]) -> None:
        if len(stack) == 0:
            return

        # Process the top node of the stack.
        vertex = stack[-1]
        visited[vertex] = True

        # Visit the unvisited neighbours of the graph.
        for nbr in [nbr for nbr in graph[vertex] if not visited[nbr]]:
             dfs(stack + [nbr], visited)

        # Pop the node.
        stack.pop()

    dfs([vertices[0]], visited)

    # Determine if all nodes have been visited.
    num_visited = len([v for v in vertices if visited[v]])
    return num_visited == num_vertices


def count_undirected_edges(graph: Graph) -> int:
    """
    Count the number of edges in an (assumed) undirected graph.
    :param graph: the graph
    :return: the number of undirected edges

    >>> g1 = {1: [2, 9], 2: [1, 3, 6], 3: [2, 4, 5], 4: [3], 5: [3, 6, 7], 6: [2, 5], 7: [5], 8: [9, 10], 9: [1, 8, 10], 10: [9, 8]}
    >>> count_undirected_edges(g1)
    11
    >>> g2 = {1: [2], 2: [1, 3, 6], 3: [2, 4, 5], 4: [3], 5: [3, 6, 7], 6: [2, 5], 7: [5], 8: [9, 10], 9: [8, 10], 10: [9, 8]}
    >>> count_undirected_edges(g2)
    10
    >>> g3 = {}
    >>> count_undirected_edges(g3)
    0
    >>> g4 = {0:[]}
    >>> count_undirected_edges(g4)
    0
    >>> g5 = {0:[], 1:[]}
    >>> count_undirected_edges(g5)
    0
    """
    assert is_undirected(graph)
    return sum([len(g) for g in graph.values()]) // 2


def is_minimally_connected(graph: Graph) -> bool:
    """
    An undirected graph is minimally connected if:
    1. The graph is connected; and
    2. Removing any edge from the graph would result in it not being connected.

    Note that a minimally connected graph MUST be a tree: if not:
    1. It is either not connected; or
    2. It contains a cycle such that removing one of the edges would retain connectedness.

    :param graph: the graph
    :return: True if the graph is connected and a tree

    >>> g1 = {1: [2, 9], 2: [1, 3, 6], 3: [2, 4, 5], 4: [3], 5: [3, 6, 7], 6: [2, 5], 7: [5], 8: [9, 10], 9: [1, 8, 10], 10: [9, 8]}
    >>> is_minimally_connected(g1)
    False
    >>> g2 = {1: [2, 3], 2: [1], 3: [1]}
    >>> is_minimally_connected(g2)
    True
    """
    return is_connected(graph) and count_undirected_edges(graph) == len(graph) - 1
