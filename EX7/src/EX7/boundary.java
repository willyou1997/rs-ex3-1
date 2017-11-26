package EX7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class boundary {
	public static final int MAX = 100; 
	public static final int INF = 99; 
	public static String[] mVexs;       // 顶点集合
	public static int[][] mMatrix;  // 邻接矩阵
	public static int vlen; 
	public static int[][] dist = new int[MAX][MAX];   
	public static int[][] path = new int[MAX][MAX];    
	public static List<Integer> result = new ArrayList<Integer>(); 
	public static String[] arrays;
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
   	     *System.out.print(vlen+"\n");
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
    
    public static int getPos(String x) {
        for (int i = 0; i < mVexs.length; i++) {
          if (mVexs[i].hashCode() == x.hashCode()) {
            return (i);
          }
        }
        return -1;
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
        control con = new control();
        entity en = new entity();
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
              con.showAdjacencyMatrix();
              break;
            } 
            case "2":
            {
              en.showDirectedGraph();
              break;
            }
            case "3":
            {
              //querybirdgeWords();
            	con.printBridgeWords();
            	
              break;
            }
            case "4":
            {
              con.getNewWords(arrays);
              break;
            }
            case "5":
            {
              con.printShortestPath();
              break;
            }
            case "6":
            {
              FileWriter fw = new FileWriter("RandomWalkPath.txt");
              @SuppressWarnings("resource")
              BufferedWriter bw = new BufferedWriter(fw);
              String rpath = con.randomWalk();
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
