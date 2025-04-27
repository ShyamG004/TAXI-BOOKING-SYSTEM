import java.util.*;
class Taxi{
    int tn;
    int cp=1;
    int tot=0;
    List<Booking> booking =new ArrayList<>();
    Taxi(int t){
        tn=t;
    }
    boolean isAvailable(int hr,int min){
        if(booking.isEmpty())return true;
        Booking temp=booking.get(booking.size()-1);
        return (temp.drophr*60+temp.dropmin<hr*60+min);
    }
    void addBooking(Booking b){
        booking.add(b);
        tot+=b.amt;
        cp=b.drop;
    }
    int calculateEarning(int dist,int I,int k,int x){
        return I+(Math.max(0,(dist-k)*x));
    }
}
class Booking{
    int drophr,dropmin,drop,amt;
    Booking(int c,int d,int f,int a){
        drophr=c;
        dropmin=d;
        drop=f;
        amt=a;
    }
}
public class Hello {
    static int pd[];
    static int pm[];
    static int calculatedist(int st,int end){
        int s=Math.min(st,end);
        int e=Math.max(st,end);
        s--;
        e--;
        int t=0;
        for(int i=s;i<e;i++){
            t+=pd[i];
        }
        return t;
    }
    static int calculatetime(int st,int end){
        int s=Math.min(st,end);
        int e=Math.max(st,end);
        s--;
        e--;
        int t=0;
        for(int i=s;i<e;i++){
            t+=pm[i];
        }
        return t;
    }
    public static void main(String[] args) {
		//Your Code Here
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		List<Taxi> taxi=new ArrayList<>();
		for(int i=1;i<=N;i++){
		    Taxi temp=new Taxi(i);
		    taxi.add(temp);
		}
		int P = sc.nextInt();
		pd=new int[P-1];
		for(int i=0;i<P-1;i++){
		    pd[i]=sc.nextInt();
		}
		pm=new int[P-1];
		for(int i=0;i<P-1;i++){
		    pm[i]=sc.nextInt();
		}
		int k=sc.nextInt(),I=sc.nextInt(),X=sc.nextInt(),Y=sc.nextInt();
		int n=sc.nextInt();
		for(int i=0;i<n;i++){
		    String name=sc.next();
		    int st=sc.nextInt(),dr=sc.nextInt();
		    String time[]=sc.nextLine().split(":");
		    int pickhr=Integer.parseInt(time[0].trim());
		    int pickmin=Integer.parseInt(time[1].trim());
		    Taxi selectedTaxi=null;
		    int dist=1000000;
		    int distance;
		    int traveltime=0;
		    for(Taxi tt:taxi){
		        if(tt.isAvailable(pickhr,pickmin)){
		            int tempDp=Math.abs(tt.cp-st);
		            int tempD=calculatedist(tt.cp,st);
		            int temptime=calculatetime(tt.cp,st);
		            if(selectedTaxi==null || tempDp<dist ||(tempDp==dist && tt.tot<selectedTaxi.tot) ||(tt.tot==selectedTaxi.tot && selectedTaxi.tn>tt.tn)){
		                distance=tempD;
		                dist=tempDp;
		                selectedTaxi=tt;
		                traveltime=temptime;
		            }
		        }
		    }
		    if(distance>Y || selectedTaxi==null){
		        System.out.println(name+" REJECTED");
		        continue;
		    }
		    int Distance=calculatedist(st,dr);
		    int amount=selectedTaxi.calculateEarning(Distance,I,k,X);
		    traveltime+=calculatetime(st,dr);
		    pickhr+=(traveltime/60);
		    pickmin+=(traveltime%60);
		    pickhr+=(pickmin/60);
		    pickhr%=24;
		    pickmin%=60;
		    Booking tempbooking=new Booking(pickhr,pickmin,dr,amount);
		    selectedTaxi.addBooking(tempbooking);
		    System.out.printf("%s Taxi-%d %d %02d:%02d\n",name,selectedTaxi.tn,amount,pickhr,pickmin);
		}
	}
}