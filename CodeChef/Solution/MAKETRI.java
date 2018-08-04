/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 
/**
 *
 * @author Vinay
 */
import java.util.*;
import java.io.*;
class MAKETRI {
static int min=Integer.MAX_VALUE;
 public static void main(String[] args)
	{
		InputReader in = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
                int n=in.nextInt();
                long l=in.nextLong();
                long r=in.nextLong();
                long a[]=in.nextLongArray(n);
                long ans=0;
                Arrays.sort(a);
                Record rec[]=new Record[n-1];
                for(int i=1;i<n;i++){
                    rec[i-1]=new Record(a[i]-a[i-1], a[i]+a[i-1]); 
                }
                Arrays.sort(rec);
                Boundary ob=new Boundary(r, l);
                //System.out.println(rec.length);
                for(int i=0;i<n-1 && !ob.flag;i++){
                   // System.out.println(rec[i].min+" "+rec[i].max);
                    ans+=ob.evaluate(rec[i].min+1, rec[i].max-1);
                }
                
                pw.println(ans);
                pw.close();
	} 
        static class Boundary{
            long r,l;
            boolean flag;
            public Boundary(long r,long l){
                this.r=r;
                this.l=l;
                flag=false;
            }
            public long evaluate(long min,long max){
                long ans=0;
                if(flag){
                    return 0;
                }
                if(max<l || min>r){
                    ans=0;
                }
                else if(min<=l && max>=r){
                    ans=r-l+1;
                    flag=true;
                }
                else if(max>=r){
                    ans=r-min+1;
                    flag=true;
                }
                else if(min<=l){
                    ans=max-l+1;
                    l=max+1;
                }
                else if(min>l && max<r){
                    ans=max-min+1;
                    l=max+1;                    
                }
                return ans;
            }
        }
        static class Record implements Comparable<Record>{
            long min,max;
            public Record(long min, long max){
                this.min=min;
                this.max=max;
            }
            public int compareTo(Record ob){
                if(this.min!=ob.min){
                    return new Long(min).compareTo(ob.min);
                }
                return new Long(max).compareTo(ob.max);
            }
        }
       	static class InputReader
	{
 
		private InputStream stream;
		private byte[] buf = new byte[8192];
		private int curChar, snumChars;
		private SpaceCharFilter filter;
 
		public InputReader(InputStream stream)
		{
			this.stream = stream;
		}
 
		public int snext()
		{
			if (snumChars == -1)
				throw new InputMismatchException();
			if (curChar >= snumChars)
			{
				curChar = 0;
				try {
					snumChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (snumChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}
 
		public int nextInt()
		{
			int c = snext();
			while (isSpaceChar(c))
				c = snext();
			int sgn = 1;
			if (c == '-')
			{
				sgn = -1;
				c = snext();
			}
			int res = 0;
			do
			{
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = snext();
			} while (!isSpaceChar(c));
			return res * sgn;
		}
 
		public long nextLong() {
			int c = snext();
			while (isSpaceChar(c))
				c = snext();
			int sgn = 1;
			if (c == '-')
			{
				sgn = -1;
				c = snext();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = snext();
			} while (!isSpaceChar(c));
			return res * sgn;
		}
 
		public int[] nextIntArray(int n)
		{
			int a[] = new int[n];
			for (int i = 0; i < n; i++)
				a[i] = nextInt();
			return a;
		}
 
                public long[] nextLongArray(int n)
		{
			long a[] = new long[n];
			for (int i = 0; i < n; i++)
				a[i] = nextLong();
			return a;
		}
 
		public String readString()
		{
			int c = snext();
			while (isSpaceChar(c))
				c = snext();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = snext();
			} while (!isSpaceChar(c));
			return res.toString();
		}
 
		public boolean isSpaceChar(int c)
		{
			if (filter != null)
				return filter.isSpaceChar(c);
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
 
		public interface SpaceCharFilter
		{
			public boolean isSpaceChar(int ch);
		}
	}
}