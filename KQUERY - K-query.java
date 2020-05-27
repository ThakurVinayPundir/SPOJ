import java.util.*;
import java.io.*;
public class Main{
    static ArrayList<Long> t[];
    static long a[];
    public static void main(String args[]) throws IOException{
        Reader sc=new Reader();
        PrintWriter pw=new PrintWriter(System.out);
        int n=sc.nextInt();
        a=new long[n];
        //String s[]=sc.readLine().trim().split(" ");
        for(int i=0;i<n;i++)a[i]=sc.nextLong();//Long.parseLong(s[i]);
        t=new ArrayList[4*n+1];
        for(int i=0;i<=4*n;i++)t[i]=new ArrayList<Long>();
        build(0,0,n-1);
        
        StringBuilder sb=new StringBuilder();
        int m=sc.nextInt();//Integer.parseInt(sc.readLine().trim());
        for(int i=0;i<m;i++){
            //String ss[]=sc.readLine().trim().split(" ");
            int l=sc.nextInt()-1;//Integer.parseInt(ss[0])-1;
            int r=sc.nextInt()-1;//Integer.parseInt(ss[1])-1;
            long k=sc.nextLong();///Long.parseLong(ss[2]);
            sb.append(query(0,0,n-1,l,r,k)+"\n");
        }
        pw.println(sb.toString().trim());
        pw.flush();
        pw.close();
        sc.close();
    }
    public static void build(int v,int l,int r){
        if(l>r)return;
        if(l==r){
            t[v].add(a[l]);
        }else{
            int mid=(l+r)>>1;
            build(2*v+1,l,mid);
            build(2*v+2,mid+1,r);
            int lst=0,lend=t[2*v+1].size(),rst=0,rend=t[2*v+2].size();
            while(lst<lend&&rst<rend){
                if(t[2*v+1].get(lst)<=t[2*v+2].get(rst)){
                    t[v].add(t[2*v+1].get(lst));lst++;
                }else{
                    t[v].add(t[2*v+2].get(rst));rst++;
                }
            }
            while(lst<lend){
                t[v].add(t[2*v+1].get(lst));lst++;
            }
            while(rst<rend){
                t[v].add(t[2*v+2].get(rst));rst++;
            }
        }
    }
    public static int query(int v,int tl,int tr,int l,int r,long k){
        if(tl>r||tr<l)return 0;
        if(l>r)return 0;
        //if(tl==tr)return t[v].get(0)>k?1:0;
        if((tl>=l&&tr<=r)){
            int st=0,end=t[v].size()-1;
            int ans=-1;
            while(st<=end){
                int mid=(st+end)>>1;
                if(t[v].get(mid)>k){
                    ans=mid;
                    end=mid-1;
                }else{
                    st=mid+1;
                }
            }
            if(ans==-1)return 0;
            return t[v].size()-ans;
        }
        int res=0;
        int mid=(tl+tr)>>1;
        res+=query(2*v+1,tl,mid,l,r,k);
        res+=query(2*v+2,mid+1,tr,l,r,k);
        return res;
    }
    
    
    static class Reader 
    { 
        final private int BUFFER_SIZE = 1 << 16; 
        private DataInputStream din; 
        private byte[] buffer; 
        private int bufferPointer, bytesRead; 
  
        public Reader() 
        { 
            din = new DataInputStream(System.in); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public Reader(String file_name) throws IOException 
        { 
            din = new DataInputStream(new FileInputStream(file_name)); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public String readLine() throws IOException 
        { 
            byte[] buf = new byte[64]; // line length 
            int cnt = 0, c; 
            while ((c = read()) != -1) 
            { 
                if (c == '\n') 
                    break; 
                buf[cnt++] = (byte) c; 
            } 
            return new String(buf, 0, cnt); 
        } 
  
        public int nextInt() throws IOException 
        { 
            int ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do
            { 
                ret = ret * 10 + c - '0'; 
            }  while ((c = read()) >= '0' && c <= '9'); 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public long nextLong() throws IOException 
        { 
            long ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public double nextDouble() throws IOException 
        { 
            double ret = 0, div = 1; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
  
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
  
            if (c == '.') 
            { 
                while ((c = read()) >= '0' && c <= '9') 
                { 
                    ret += (c - '0') / (div *= 10); 
                } 
            } 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        private void fillBuffer() throws IOException 
        { 
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); 
            if (bytesRead == -1) 
                buffer[0] = -1; 
        } 
  
        private byte read() throws IOException 
        { 
            if (bufferPointer == bytesRead) 
                fillBuffer(); 
            return buffer[bufferPointer++]; 
        } 
  
        public void close() throws IOException 
        { 
            if (din == null) 
                return; 
            din.close(); 
        } 
    }
}