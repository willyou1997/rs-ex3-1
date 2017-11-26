package EX7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class control {

	static final int MAX = boundary.MAX;
    static final int INF =  boundary.INF;
    static String[] mVexs;       // 顶点集合
    static int[][] mMatrix;  // 邻接矩阵
    static int vlen;
    static int[][] dist;   
    static int[][] path;    
    static List<Integer> result; 
    //static String[] arrays = boundary.arrays;
    static BufferedReader keyboard = boundary.keyboard;
	
    public static void showAdjacencyMatrix() {
        System.out.print("1.Graph\n");
        
        vlen = boundary.vlen;
        mMatrix = boundary.mMatrix;
        for (int i = 0; i < vlen; i++) {
          for (int j = 0; j < vlen; j++) {
            System.out.print(mMatrix[i][j] + " ");
          }
          System.out.print("\n");
        }
      }
    
	public static String querybirdgeWords() {
	      @SuppressWarnings("resource")
	      Scanner input = new Scanner(System.in);
	      System.out.print("\n3.birdge Words\nPlease input two words and"
	      + " follow is the birdge words between the words you input.\n");
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
		  mVexs = boundary.mVexs;
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
	    result = boundary.result;
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
		path = boundary.path;
		dist = boundary.dist;
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
			     
			      blank = "There is no path between \"" + word1
			              + "\" and \"" + word2 + "\".";
			    }

			    else {

				    blank = "\"" + word1 + "\"" + "->" 
				    	    + "\"" + word2 + "\"" + " ,the cheapest path is:";
				   
				    Integer[] pathPos = new Integer[result.size()];
	
				    pathPos     = (Integer[])result.toArray(pathPos);
				    for (int i = 0; i < pathPos.length - 1; i++) {
				      
				    	blank+=mVexs[pathPos[i]] + " --> ";
				    }
				    blank += mVexs[pathPos[pathPos.length - 1]] + "\n";
				    blank += "weight:" + dist[begin][end];
	
					pathPos = (Integer[])result.toArray(pathPos);
					/*for (int i = 0; i < pathPos.length - 1; i++) 
					{
					    blank+=mVexs[pathPos[i]] + " --> ";
					}
					blank += mVexs[pathPos[pathPos.length - 1]] + "\n";
					blank += "weight:" + dist[begin][end];*/
			   }

		    }
		     else{
		       
		        blank = "No \"" + word1 + "\" or \"" + word2 + "\" in graph!\n";
		      }
		    return blank;
		    
		  }

	  public static void printShortestPath()
	  {
		  String s_path = calcShortestPath();
		  System.out.print(s_path);
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




}
