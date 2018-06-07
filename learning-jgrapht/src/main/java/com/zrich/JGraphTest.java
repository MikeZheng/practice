package com.zrich;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.KosarajuStrongConnectivityInspector;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.SingleSourcePaths;
import org.jgrapht.alg.interfaces.StrongConnectivityAlgorithm;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.demo.GraphMLDemo;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

/**
 * @Title: jgraphTest.java
 * @Package
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: zhang shuai
 * @date: Jun 5, 2018 6:01:48 PM
 * @version V1.0
 */

/**
 * @ClassName: jgraphTest
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: zhang shuai
 * @date: Jun 5, 2018 6:01:48 PM
 *
 */
public class JGraphTest {

    public static void main(String[] args) {
        System.out.println("currNode");

        // constructs a directed graph with the specified vertices and edges
        Graph<String, VisibleEdge> directedGraph =
                new DefaultDirectedGraph<String, VisibleEdge>(VisibleEdge.class);
        directedGraph.addVertex("a");
        directedGraph.addVertex("b");
        directedGraph.addVertex("k");
        directedGraph.addVertex("d");
        directedGraph.addVertex("e");
        directedGraph.addVertex("f");
        directedGraph.addVertex("g");
        directedGraph.addVertex("h");
        directedGraph.addVertex("i");

        directedGraph.addEdge("a", "b");
        directedGraph.addEdge("b", "d");
        directedGraph.addEdge("d", "e");
        directedGraph.addEdge("e", "g");
        directedGraph.addEdge("g", "h");
        directedGraph.addEdge("h", "i");
        directedGraph.addEdge("d", "k");
        directedGraph.addEdge("e", "f");

        // a i k f

        // Prints the shortest path from vertex i to vertex c. This certainly
        // exists for our particular directed graph.
        System.out.println("Shortest path from a to k:");


        DijkstraShortestPath<String, VisibleEdge> dijkstraAlg =
                new DijkstraShortestPath<>(directedGraph);
        SingleSourcePaths<String, VisibleEdge> iPaths = dijkstraAlg.getPaths("b");
        System.out.println(iPaths.getPath("k") + "\n");
        System.out.println(iPaths.getSourceVertex() + "\n");

        // Prints the shortest path from vertex c to vertex i. This path does
        // NOT exist for our particular directed graph. Hence the path is
        // empty and the variable "path"; must be null.
        System.out.println("Shortest path from k to a:");
        SingleSourcePaths<String, VisibleEdge> cPaths = dijkstraAlg.getPaths("k");
        System.out.println(cPaths.getPath("a"));

        // computes all the strongly connected components of the directed graph
        StrongConnectivityAlgorithm<String, VisibleEdge> scAlg =
                new KosarajuStrongConnectivityInspector<>(directedGraph);
        List<Graph<String, VisibleEdge>> stronglyConnectedSubgraphs =
                scAlg.getStronglyConnectedComponents();

        // prints the strongly connected components
        System.out.println("Strongly connected components:");
        for (int i = 0; i < stronglyConnectedSubgraphs.size(); i++) {
            System.out.println(stronglyConnectedSubgraphs.get(i));
        }
        System.out.println();

        // DepthFirstIterator
        Iterator<String> iter = new BreadthFirstIterator<String, VisibleEdge>(directedGraph);
        Object vertex;
        while (iter.hasNext()) {
            vertex = iter.next();
            String currNode = (String) vertex;
            System.out.println(currNode);
            System.out.println(
                    "Vertex " + vertex + " is connected to: "
                            + directedGraph.edgesOf(currNode).toString());
            if ("d".equals(currNode)) {
                Set<VisibleEdge> setDE = directedGraph.edgesOf(currNode);
                for (VisibleEdge de : setDE) {
                    System.out.println("aaa " + de.toString());
                    //找到了b的上一个父节点，通过父节点，拿出它的return value，判断
                    //if ("e".equals(currNode)) {
                    //directedGraph.removeEdge(de);
                    //}
                    //directedGraph.removeEdge(de);
                    //directedGraph.removeVertex("e");
                }
            }
        }

        GraphMLDemo.main(args);

        System.out.println(directedGraph.getEdgeSupplier().get());

        AllDirectedPaths<String, VisibleEdge> allDirectedPaths = new AllDirectedPaths<>(directedGraph);

        Set<String> vertexSet = directedGraph.vertexSet();
        Set<String> leafVertexSet = new HashSet<>(vertexSet);
        Set<String> rootVertexSet = new HashSet<>(vertexSet);

        directedGraph.edgeSet().forEach(a -> {
            leafVertexSet.remove(a.getSource().toString());
            rootVertexSet.remove(a.getTarget().toString());
        });

        System.out.println(leafVertexSet);
        System.out.println(rootVertexSet);

        SingleSourcePaths<String, VisibleEdge> aPaths = dijkstraAlg.getPaths("a");


        System.out.println(allDirectedPaths.getAllPaths(rootVertexSet, leafVertexSet, false, 1000));

    }


}
