    import java.util.*;
    import java.io.*;
    public class Main{
        static long tree[][];
        static int n,m;
        public static void main(String args[]) throws IOException{
            BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw=new PrintWriter(System.out);
            String s1[]=sc.readLine().trim().split(" ");
            n=Integer.parseInt(s1[0]);
            m=Integer.parseInt(s1[1]);
            int q=Integer.parseInt(s1[2]);
            int a[][]=new int[n+1][m+1];
            tree=new long[n+1][m+1];
            for(int i=1;i<=n;i++){
                String s2[]=sc.readLine().trim().split(" ");
                    for(int j=1;j<=m;j++){a[i][j]=Integer.parseInt(s2[j-1]);
                    update(i,j,a[i][j]);
                    }
            }
            for(int i=0;i<q;i++){
                String s3[]=sc.readLine().trim().split(" ");
                int t=Integer.parseInt(s3[0]);
                if(t==1){
                    int x=Integer.parseInt(s3[1]);
                    int y=Integer.parseInt(s3[2]);
                    int p=Integer.parseInt(s3[3]);
                    a[x][y]+=p;
                    update(x,y,p);
                }else{
                    int x1=Integer.parseInt(s3[1]);
                    int y1=Integer.parseInt(s3[2]);
                    int x2=Integer.parseInt(s3[3]);
                    int y2=Integer.parseInt(s3[4]);
                    long v1=read(x2,y2);
                    long v2=read(x2,y1-1);
                    long v3=read(x1-1,y2);
                    long v4=read(x1-1,y1-1);
                    pw.println(v1-v2-v3+v4);
                    pw.flush();
                }
            }
            sc.close();
            pw.close();
        }
        public static long read(int r,int c){
            long ret=0;
            for(int i=r;i>0;i-=(i&-i))
            for(int j=c;j>0;j-=(j&-j))ret+=tree[i][j];
            return ret;
        }
        public static void update(int r,int c,long val){
            for(int i=r;i<=n;i+=(i&-i))
            for(int j=c;j<=m;j+=(j&-j))tree[i][j]+=val;
        }
    }
     