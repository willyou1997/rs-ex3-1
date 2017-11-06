package EX1;

import java.io.*;
import java.util.*;

public class EX1 {
     private static final int MAX = 100; 
     private static final int INF = 99; 
     private static String[] mVexs;       // 顶点集合
     private static int[][] mMatrix;  // 邻接矩阵
     private static int vlen; 
     private static int[][] dist = new int[MAX][MAX];   
     private static int[][] path = new int[MAX][MAX];    
     private static List<Integer> result = new ArrayList<Integer>(); 
     private static String[] arrays;
     static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
     
     public static void Input() throws FileNotFoundException, IOException
     {
    	 BufferedReader  inFile  = new BufferedReader(new FileReader("G:\\Lab12.txt"));
    	    String    str = "";
    	    String    result  = "";
    	    int x1;
    	    int x2;
    	    while ((str = inFile.readLine()) != null) {
    	      result += str + " ";
    	    }
    	    result = result.replaceAll("[^A-Za-z., \n?!:;—_(){}\"\'-]", "")
    	        .replaceAll("[^A-Za-z]", " ").replaceAll(" +", " ").toLowerCase();
    	    arrays = result.split(" ");
    	    List<String> list = new ArrayList<String>();
    	    for (int i = 0; i < arrays.length; i++) {
    	      if (!list.contains(arrays[i])) {
    	        list.add(arrays[i]);
    	      }
    	    }
    	    mVexs = (String[])list.toArray(new String[list.size()]);
    	    vlen  = mVexs.length;
    	    /*
    	     * 初始化"边"
    	     * System.out.print(vlen);
    	     */
    	    mMatrix = new int[vlen][vlen];
    	    for (int i = 0; i < vlen; i++) {
    	      for (int j = 0; j < vlen; j++) {
    	        mMatrix[i][j] = INF;
    	      }
    	      mMatrix[i][i] = 0;
    	    }
    	    for (int i = 0; i < arrays.length - 1; i++) {
    	        x1    = getPos(arrays[i]);
    	        x2    = getPos(arrays[i + 1]);
    	        mMatrix[x1][x2] = 1;
    	      }
     }
     
  public static void showAdjacencyMatrix() {
    System.out.print("1.Graph\n");
    for (int i = 0; i < vlen; i++) {
      for (int j = 0; j < vlen; j++) {
        System.out.print(mMatrix[i][j] + " ");
      }
      System.out.print("\n");
    }
  }


  public static void showDirectedGraph() {
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


  public static String querybirdgeWords() {
      @SuppressWarnings("resource")
      Scanner input = new Scanner(System.in);
      System.out.print("\n3.birdge Words\nPlease input two words and"
      + "follow is the birdge words between the words you input.\n");
      String  word1 = input.next();
      String  word2 = input.next();
      String blank = "";
    if ((getPos(word1) == -1) || (getPos(word2) == -1)) {
     // System.out.print("No \"" + word1 + "\" or \"" + word2 + "\" in graph!\n");
      //return;
    	blank = "No \"" + word1 + "\" or \"" + word2 + "\" in graph!\n";
    }
    else
    {
	    String x = getbirdgeWords(word1, word2);
	    
	    if (x == null || x.length() <= 0) {
	      //System.out.print("No birdge words from \"" + word1 + "\" to \"" + word2 + "\"!\n");
	      blank = "No birdge words from \"" + word1 
	              + "\" to \"" + word2 + "\"!\n";
	    } else {
	      //System.out.print("The birdge words from \"" + word1 + "\" to \"" + word2 + "\" are: " + x);
	      blank = "The birdge words from \"" + word1 
	              + "\" to \"" + word2 + "\" are: " + x;
	    }
    }
    return blank;
    //System.out.print("\n");
  }

  public static void printBridgeWords()
  {
	  String blank = querybirdgeWords();
	  System.out.print(blank);
	  System.out.print("\n");
  }

  public static void getNewWords(String[] arrays) {
    System.out.print("\n4.Generate New Text.\nPlease input a String:\n");
    @SuppressWarnings("resource")
    Scanner s = new Scanner(System.in);
    String str = s.nextLine();
    String[] x,y;
    str = str.replaceAll("[^A-Za-z., \n?!:;—_(){}\"\'-]", "")
        .replaceAll("[^A-Za-z]", " ").replaceAll(" +", " ");
    
    String result = "";
    x = str.split(" ");
    result = x[0] + " ";
    for (int i = 0; i < x.length - 1; i++) {
      String len = getbirdgeWords(x[i], x[i + 1]);
      
      if (str == null || str.length() <= 0) {
        result += x[i + 1] + " ";
      }
      else {
        y = len.split(" ");
        result += y[0] + " " + x[i + 1] + " ";
      }
    }
    System.out.print(result);
  }


  public static int getPos(String x) {
    for (int i = 0; i < mVexs.length; i++) {
      if (mVexs[i].hashCode() == x.hashCode()) {
        return (i);
      }
    }
    return -1;
  }


  public static String getbirdgeWords(String word1, String word2) {
    int x,y;
    String blank = "";
    x = getPos(word1);
    y = getPos(word2);
    
    if(x!=-1 && y!=-1) {
	    for (int i = 0;i < vlen; i++) {
	    if (mMatrix[x][i] == 1 && mMatrix[i][y] == 1) {
	       blank += mVexs[i] + " ";
	        }
	    }
    }
    else {
    	blank="";
    }

    return blank;
  }

  public static void findCheapestPath(int begin, int end) {
    floyd();
    result.clear();
    result.add(begin);
    findPath(begin, end);
    result.add(end);
  }


  public static void findPath(int i, int j) {
    int k = path[i][j];
    if (k == -1) {
      return;
    }
    findPath(i, k);
    result.add(k);
    findPath(k, j);
  }


  public static void floyd() {
    int size = mMatrix.length;
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        path[i][j]  = -1;
        dist[i][j]  = mMatrix[i][j];
      }
    }
    for (int k = 0; k < size; k++) {
      for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
          if (dist[i][k] != 0 && dist[k][j] != 0 
              && dist[i][k] + dist[k][j] < dist[i][j]) {
            dist[i][j]  = dist[i][k] + dist[k][j];
            path[i][j]  = k;
          }
        }
      }
    }
  }


  public static String calcShortestPath() {
	@SuppressWarnings("resource")
	Scanner input = new Scanner(System.in);
    System.out.print("\n\n5.Shortest Path\n");
    System.out.print("Input the beginning word and the ending word:\n");
    String  word1 = input.next();
    String  word2 = input.next();
    int begin = getPos(word1);
    int end = getPos(word2);
    /* System.out.print(begin+"\n"); */
    String blank = "";
    
    
    if(begin!=-1 && end!=-1)
    {
    	findCheapestPath(begin, end);
	    if (dist[begin][end] == 99) {
	      /*System.out.print("There is no path between \"" + word1
	          + "\" and \"" + word2 + "\".");
	      return;*/
	      blank = "There is no path between \"" + word1
	              + "\" and \"" + word2 + "\".";
	    }
<<<<<<< HEAD
=======
	    else {
>>>>>>> lab6w
	    blank = "\"" + word1 + "\"" + "->" 
	    	    + "\"" + word2 + "\"" + " ,the cheapest path is:";
	    /*System.out.println("\"" + word1 + "\"" + "->" 
	    + "\"" + word2 + "\"" + " ,the cheapest path is:");*/
	    /* List<Integer> list=result; */
	    Integer[] pathPos = new Integer[result.size()];
<<<<<<< HEAD
	    pathPos     = (Integer[])result.toArray(pathPos);
	    for (int i = 0; i < pathPos.length - 1; i++) {
	      //System.out.print(mVexs[pathPos[i]] + " --> ");
	    	blank+=mVexs[pathPos[i]] + " --> ";
	    }
	    blank += mVexs[pathPos[pathPos.length - 1]] + "\n";
	    blank += "weight:" + dist[begin][end];
=======
		    pathPos     = (Integer[])result.toArray(pathPos);
		    for (int i = 0; i < pathPos.length - 1; i++) {
		      //System.out.print(mVexs[pathPos[i]] + " --> ");
		    	blank+=mVexs[pathPos[i]] + " --> ";
		    }
		    blank += mVexs[pathPos[pathPos.length - 1]] + "\n";
		    blank += "weight:" + dist[begin][end];
	    }
>>>>>>> lab6w
    }
     else{
        //System.out.print("No \"" + word1 + "\" or \"" + word2 + "\" in graph!\n");
        //return;
        blank = "No \"" + word1 + "\" or \"" + word2 + "\" in graph!\n";
      }
    return blank;
    //System.out.print(mVexs[pathPos[pathPos.length - 1]] + "\n");
    //System.out.println("weight:" + dist[begin][end]);
  }


  public static String randomWalk() throws IOException {
    System.out.print("\n6.Random Walk\n");
    Random  random  = new Random();
    int s = random.nextInt(10);
    int [][]p = new int[vlen][vlen];
    for (int i = 0; i < vlen; i++) {
      for (int j = 0; j < vlen; j++) {
        p[i][j] = 0;
      }
    }


    /* List<String> randomPath=new ArrayList<String>(); */
    String randomPath = mVexs[s];
    /* System.out.print(randomPath); */
    System.out.print("continue?(Y/N):");
    @SuppressWarnings("resource")
    Scanner input = new Scanner(System.in);
    String choice = input.next();

    do {
      if ((choice.equals("Y") || choice.equals("y"))) {
        for (int i = 0; i < vlen; i++) {
          if (mMatrix[s][i] != 0 && mMatrix[s][i] != INF 
              && p[s][i] == 0) {
            randomPath  += " " + mVexs[i];
            p[s][i]   = 1;
            s   = i;
            break;
          } else if (i == vlen - 1) {
            return (randomPath);
          }
        }
      } else if (choice.equals("N") || choice.equals("n")) {
        System.out.println("End");
        break;
      }
      System.out.print("continue?(Y/N):");
      choice = input.next();
    } while (true);
    return (randomPath);
  }


  public static void menu() {
    System.out.println("\n\n*********************************");
    System.out.println("1.Adjacency Matrix");
    System.out.println("2.Show Directed Graph");
    System.out.println("3.Query birdge Words");
    System.out.println("4.Get New Words");
    System.out.println("5.Shortest Path");
    System.out.println("6.Random Walk");
    System.out.println("0.Exit");
    System.out.println("*********************************");
    System.out.println("Input your choice:");
  }


  public static void main(String[] args) throws IOException {
    BufferedReader  inFile  = new BufferedReader(new FileReader("G:\\Lab12.txt"));
    
    Input();
    
    menu();
    @SuppressWarnings("resource")
    Scanner input = new Scanner(System.in);
    String choice = input.next();
    //String choice = input.next();
    do {
      switch (choice) {
        case "1":
        {
          showAdjacencyMatrix();
          break;
        }
        case "2":
        {
          showDirectedGraph();
          break;
        }
        case "3":
        {
          //querybirdgeWords();
        	printBridgeWords();
        	
          break;
        }
        case "4":
        {
          getNewWords(arrays);
          break;
        }
        case "5":
        {
          String s = calcShortestPath();
          System.out.print(s);
          break;
        }
        case "6":
        {
          FileWriter fw = new FileWriter("RandomWalkPath.txt");
          @SuppressWarnings("resource")
          BufferedWriter bw = new BufferedWriter(fw);
          String rpath = randomWalk();
          bw.write(rpath);
          System.out.print("RandomPath:" + rpath);
          bw.close();
          fw.close();
          break;
        }
        case "0":
        {
          System.out.print("End\n");
          System.exit(0);
        }
        default:
        {
          System.out.print("Error");
          break;
        }
      }
      menu();
      choice = input.next();
    } while (choice != "0");
    inFile.close();
  }
}



