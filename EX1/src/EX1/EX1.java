package EX1;

import java.io .*;
import java.util.*;

public class EX1 {
   private static int MAX = 100; 
   private static int INF = 99; 
   private static String[] mVexs;       // 顶点集合
   private static int[][] mMatrix;  // 邻接矩阵
   private static int vlen; 
   private static int[][] dist=new int[MAX][MAX];   
   private static int[][] path = new int[MAX][MAX];    
   private static List<Integer> result=new ArrayList<Integer>(); 
   static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
  
   
   public static void ShowAdjacencyMatrix()
   {
     System.out.print("1.Graph\n");
       for (int i = 0; i < vlen; i++)
       {
         for(int j = 0; j < vlen; j++)
           {
            System.out.print(mMatrix[i][j]+" ");
           }
           System.out.print("\n");
       }
   }
   
   public static void showDirectedGraph() 
   {
     System.out.print("\n2.showDirctedGraph\n");       
     GraphViz gViz=new GraphViz("G:\\", "F:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe");
            gViz.start_graph();
           // gViz.addln("A->B;");
            for(int i=0;i<vlen;i++)
            {
              for(int j=0;j<vlen;j++)
              {
                if(mMatrix[i][j]!=0 && mMatrix[i][j]!=INF)
                {
                  gViz.addln(mVexs[i]+"->"+mVexs[j]+";");
                }
              }
            }
            gViz.end_graph();
            try {
                gViz.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
  
   private static void queryBridgeWords(String[] arrays)
    {
      System.out.print("\n3.Bridge Words\nPlease input two words and follow is the bridge words between the words you input.\n");
      @SuppressWarnings("resource")
      Scanner input = new Scanner(System.in);
      String word1 = input.next();
      String word2 = input.next();
      if((getPos(word1) == -1) || (getPos(word2) == -1))
      {
        System.out.print("No \""+word1+"\" or \""+word2+"\" in graph!\n");
        return;
      }
      int[] x = getBridgeWords(word1, word2, arrays);
      int len = x[0];
      int pos1 = x[1];
      int pos2 = x[2];
      
      if (len == MAX)
      {
        System.out.print("No bridge words from \""+word1+"\" to \""+word2+"\"!\n");
      }
      else
      {
        System.out.print("The bridge words from \""+word1+"\" to \""+word2+"\" are: ");
        if (len == 2)
          System.out.print(arrays[pos1+1]);
        else if(len == 3)
          System.out.print(arrays[pos1+1]+" and "+arrays[pos1+2]);
        else
        {
          for (int i = pos1+1; i <= pos2-3; i++) 
            {
            System.out.print(arrays[i]+", ");
            }
          System.out.print(arrays[pos2-2]+" and "+arrays[pos2-1]);
        }
      }
      System.out.print("\n");
    }
  
  private static void getNewWords(String[] arrays)
  {
    System.out.print("\n4.Generate New Text.\nPlease input a String:\n");
    @SuppressWarnings("resource")
    Scanner s = new Scanner(System.in);
    String str = s.nextLine();
    str = str.replaceAll("[^A-Za-z., \n?!:;—_(){}\"\'-]","").replaceAll("[^A-Za-z]"," ").replaceAll(" +"," ");
    String[] x;
    String result = "";
    x = str.split(" ");
    for(int i=0; i<x.length-1;i++)
    {
      
      result = result + x[i] +" ";
      int len[] = getBridgeWords(x[i],x[i+1],arrays);
      if(len[0] != MAX)
      {
        for(int j = len[1]+1; j <= len[2]-1; j++)
        {
          result = result + arrays[j] + " ";
        }
      }
      if(i == x.length-2)
        result += x[i+1];
    }
    System.out.print(result);
  }
  
    private static int getPos(String x) {
        for(int i=0; i<mVexs.length; i++)
        { 
            if(mVexs[i].hashCode() == x.hashCode())
            {
                return i;
            }
        }
        return -1;
    }
    
    private static int[] getBridgeWords(String word1, String word2, String[] arrays)
    {
      int[] x1 = getArrayPos(word1, arrays);
    int[] x2 = getArrayPos(word2, arrays);
    int[] Bridge = new int[3];
    Bridge[0] = MAX;
    Bridge[1] = -1;
    Bridge[2] = -1;
    for(int i = 1; i < x2[0]; i++)
    {
      for(int j = 1; j < x1[0]; j++)
      {
        if((x2[i] - x1[j] < Bridge[0]) && (x2[i] -x1[j] > 1) && (x2[i] - x1[j] < 4))
        {
          Bridge[1] = x1[j];
          Bridge[2] = x2[i];
          Bridge[0] = Bridge[2] - Bridge[1];
        }
      }
    }
    return Bridge;
    }
    
  private static int[] getArrayPos(String x, String[] arrays) {
        int arrayPos[] = new int[10];
        int num = 1;
      for(int i=0; i<arrays.length; i++)
        { 
            if(arrays[i].hashCode() == x.hashCode())
            {
                arrayPos[num] = i;
                num++;
            }
        }
      arrayPos[0] = num;
        return arrayPos;
    }
  
  public static  void findCheapestPath(int begin,int end)
  {  
        floyd();
        result.add(begin);  
        findPath(begin,end); 
        result.add(end);  
    }  
      
    public static void findPath(int i,int j)
    {  
        int k=path[i][j];  
        if(k==-1)return;  
        findPath(i,k);  
        result.add(k);  
        findPath(k,j);  
    }  
    public static void floyd()
    {  
        int size=mMatrix.length;    
        for(int i=0;i<size;i++){  
            for(int j=0;j<size;j++)
            {
                path[i][j]=-1;  
                dist[i][j]=mMatrix[i][j];
            }  
        }  
        for(int k=0;k<size;k++){  
            for(int i=0;i<size;i++){  
                for(int j=0;j<size;j++){  
                    if(dist[i][k]!=0&&  dist[k][j]!=0&&dist[i][k]+dist[k][j]<dist[i][j])
                    {
                        dist[i][j]=dist[i][k]+dist[k][j];  
                        path[i][j]=k;  
                    }  
                }  
            }  
        }  
    }  
      
    
  
    public static void calcShortestPath()
    {  
      System.out.print("\n\n5.Shortest Path\n");
      System.out.print("Input the beginning word and the ending word:\n");
    @SuppressWarnings("resource")
    Scanner input = new Scanner(System.in);
    String word1 = input.next();
    String word2 = input.next();
      int begin = getPos(word1);
    int end = getPos(word2);
    //System.out.print(begin+"\n");
    if((begin == -1) || (end == -1))
    {
      System.out.print("No \""+word1+"\" or \""+word2+"\" in graph!\n");
      return;
    }
    findCheapestPath(begin,end);
    if(dist[begin][end]==99)
    {
      System.out.print("There is no path between \""+word1+"\" and \""+word2+"\".");
      return;
    }
      
    System.out.println("\""+word1+"\""+"->"+"\""+word2+"\""+" ,the cheapest path is:"); 
        //List<Integer> list=result;  
        Integer[] pathPos = new Integer[result.size()];
        pathPos = (Integer[])result.toArray(pathPos);
        for(int i=0;i<pathPos.length-1;i++)
        {
          System.out.print(mVexs[pathPos[i]]+" --> ");
        }
        
        System.out.print(mVexs[pathPos[pathPos.length-1]]+"\n");
        System.out.println("weight:"+ dist[begin][end]);  
        
    }  
  
  
    
  public static String randomWalk() throws IOException
  {
    System.out.print("\n6.Random Walk\n");
    Random random = new Random();
        int s=random.nextInt(10);
    int p[][] = new int[vlen][vlen];
    for(int i=0;i<vlen;i++)
    {
      for(int j=0;j<vlen;j++)
      {
        p[i][j]=0;
      }
    }
    

    //List<String> randomPath=new ArrayList<String>(); 
    String randomPath = mVexs[s];
    //System.out.print(randomPath);
    System.out.print("continue?(Y/N):");
    @SuppressWarnings("resource")
    Scanner input = new Scanner(System.in);
    String choice = input.next();
    
    do
    {
      
      if((choice.equals("Y") || choice.equals("y")))
      {
        for(int i=0;i<vlen;i++)
        {
          if(mMatrix[s][i]!=0 && mMatrix[s][i]!=INF && p[s][i]==0)
          {
            randomPath+=" "+mVexs[i];
            p[s][i]=1;
            s=i;
            break;
          }
          else if(i==vlen-1)
          {
            return randomPath;
          }
          
        }
        
        
      }
      else if (choice.equals("N") || choice.equals("n"))
      {
        System.out.println("End");
        break;
      }
      System.out.print("continue?(Y/N):");
      choice = input.next();
    }while(true);
    return randomPath;
  }
  
  public static void Menu()
  {
    System.out.println("\n\n*********************************");
    System.out.println("1.Adjacency Matrix");
    System.out.println("2.Show Directed Graph");
    System.out.println("3.Query Bridge Words");
    System.out.println("4.Get New Words");
    System.out.println("5.Shortest Path");
    System.out.println("6.Random Walk");
    System.out.println("0.Exit");
    System.out.println("*********************************");
    System.out.println("Input your choice:");
    
  }
    
  public static void main(String[] args) throws IOException
  {
    BufferedReader inFile = new BufferedReader(new FileReader("G:\\Lab1.txt"));
    String str = "";
    String result = "";
    while((str = inFile.readLine()) != null)
    {
      result += str + " ";
    }
    result = result.replaceAll("[^A-Za-z., \n?!:;—_(){}\"\'-]","").replaceAll("[^A-Za-z]"," ").replaceAll(" +"," ").toLowerCase();
    String[] arrays;
    arrays = result.split(" ");
    List<String> list = new ArrayList<String>();  
        for (int i=0; i<arrays.length; i++) {  
            if(!list.contains(arrays[i])) {  
                list.add(arrays[i]);  
            }  
        }  
        mVexs = (String[])list.toArray(new String[list.size()]);
    vlen = mVexs.length;
        // 初始化"边"
    //System.out.print(vlen);
        mMatrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++)
        {
          for(int j = 0; j < vlen; j++)
          {
            mMatrix[i][j] = INF;
          }
          mMatrix[i][i] = 0;
        }
        int x1,x2;
        for (int i = 0; i < arrays.length-1; i++) 
        {
          x1 = getPos(arrays[i]);
          x2 = getPos(arrays[i+1]);
          mMatrix[x1][x2] = 1;
        }
        Menu();
        @SuppressWarnings("resource")
    Scanner input = new Scanner(System.in);
    String choice = input.next();
    do
    {
      
      switch(choice)
      {
        case "1":
        {
          ShowAdjacencyMatrix();
          break;
        }
        case "2":
        {
          showDirectedGraph();
          break;
        }
        case "3":
        {
          queryBridgeWords(arrays);
          break;
        }
        case "4":
        {
          getNewWords(arrays);
          break;
        }
        case "5":
        {
          calcShortestPath();
          break;
        }
        case "6":
        {
          FileWriter fw = new FileWriter("RandomWalkPath.txt");    
            @SuppressWarnings("resource")
          BufferedWriter bw = new BufferedWriter(fw);
          String rPath = randomWalk();
          bw.write(rPath);
          System.out.print("RandomPath:"+rPath);
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
      Menu();
      choice = input.next();
    }while(choice!="0");
    inFile.close();
  }
}
  
  
  

