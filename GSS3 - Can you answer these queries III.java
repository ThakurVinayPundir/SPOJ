import java.util.*;
import java.io.*;
public class Main{
    static Node t[];
    static int a[];
    static int INF=-(int)1e6;
    static class Node{
        int pmax,smax,sum,ans;
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        a=new int[n];
        t=new Node[4*n];
        for(int i=0;i<n;i++)a[i]=sc.nextInt();
        build(0,0,n-1);
        int m=sc.nextInt();
        for(int i=0;i<m;i++){
            int q=sc.nextInt();
            if(q==0){
                int p=sc.nextInt()-1;
                int val=sc.nextInt();
                update(0,0,n-1,p,val);
            }else{
                int l=sc.nextInt()-1;
                int r=sc.nextInt()-1;
                Node res=query(0,0,n-1,l,r);
                System.out.println(res.ans);
            }
        }
    }
    public static void build(int v,int l,int r){
        if(l>r)return;
        if(l==r){
            t[v]=new Node();
            t[v].sum=a[l];
            t[v].pmax=t[v].smax=t[v].ans=a[l];
        }else{
            int mid=(l+r)>>1;
            build(2*v+1,l,mid);
            build(2*v+2,mid+1,r);
            t[v]=combine(t[2*v+1],t[2*v+2]);
        }
    }
    
    public static Node combine(Node a,Node b){
        Node tmp=new Node();
        tmp.sum=a.sum+b.sum;
        tmp.pmax=Math.max(a.pmax,a.sum+b.pmax);
        tmp.smax=Math.max(a.smax+b.sum,b.smax);
        tmp.ans=Math.max(Math.max(a.ans,b.ans),a.smax+b.pmax);
        return tmp;
    }
    
    public static void update(int v,int tl,int tr,int p,int d){
        if(tl>tr)return;
        if(tl==tr){
            t[v]=new Node();
            t[v].sum=d;
            t[v].pmax=t[v].smax=t[v].ans=d;
        }
        else{
            int mid=(tl+tr)>>1;
            if(p<=mid)update(2*v+1,tl,mid,p,d);
            else update(2*v+2,mid+1,tr,p,d);
            t[v]=combine(t[2*v+1],t[2*v+2]);
        }
    }
    
    
    public static Node query(int v,int tl,int tr,int l,int r){
        if(tl>tr||(tl>r||tr<l)){
            Node tmp=new Node();
            tmp.sum=INF;
            tmp.pmax=tmp.smax=tmp.ans=INF;
            return tmp;
        }
        if(tl>=l&&tr<=r){
            return t[v];
        }
        int mid=(tl+tr)>>1;
        Node a=query(2*v+1,tl,mid,l,r);
        Node b=query(2*v+2,mid+1,tr,l,r);
        Node c=combine(a,b);
        return c;
    }
}