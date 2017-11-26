package EX7;

import EX7.GraphViz;

public class entity {
	 static String[] mVexs;       // 顶点集合
	 static int[][] mMatrix;  // 邻接矩阵
	 static int vlen;
	 static final int INF =  boundary.INF;
	 public static void showDirectedGraph() {
		 mVexs = boundary.mVexs;
		 mMatrix = boundary.mMatrix;
		 vlen = boundary.vlen;
	    System.out.print("\n2.showDirctedGraph\n");
	    GraphViz gviz = new GraphViz("D:\\",
	        "D:\\Graphviz\\bin\\dot.exe");
	    gviz.startgraph();
	    for (int i = 0; i < vlen; i++) {
	      for (int j = 0; j < vlen; j++) {
	        if (mMatrix[i][j] != 0 && mMatrix[i][j] != INF) {
	          gviz.addln(mVexs[i] + "->" + mVexs[j] + ";");
	        }
	      }
	    }
	    gviz.endgraph();
	    try {
	      gviz.run();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
}
