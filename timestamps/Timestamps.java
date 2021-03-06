import java.util.Scanner;
import java.lang.StringBuilder;
import java.util.StringTokenizer;
import java.util.Arrays;

/**
 * COSC326, February 2017, Etude 9
 * Java 7
 * Reads two timestamps and calculates the difference in milliseconds.
 * Used formulas from Wikipedia when converting to Julian Day number:
 * https://en.wikipedia.org/wiki/Julian_day
 * @author Frida Israelsson, Johnny Flame Lee, Max Jardine, Callum Grimmer
 */

public class Timestamps{
    public static int[] stamp1;
    public static int[] stamp2;

    public static double addLeapSec(double mjd){
        double leap =9;
        long[] leapSecs = new long[]{2272060800L, 2287785600L, 2303683200L,
                                     2335219200L, 2366755200L, 2398291200L,
                                     2429913600L, 2461449600L, 2492985600L,
                                     2524521600L, 2571782400L, 2603318400L,
                                     2634854400L, 2698012800L, 2776982400L,
                                     2840140800L, 2871676800L, 2918937600L,
                                     2950473600L, 2982009600L, 3029443200L,
                                     3076704000L, 3124137600L, 3345062400L,
                                     3439756800L, 3550089600L, 3644697600L,
                                     3692217600L};
        
        for(long ls:leapSecs){
            if(ls/86400+15020<=mjd){
                leap++;
            } else {
                break;
            }
        }
        return leap;
    }

    public static long convert(int[] ts){
        double totalMs,mjd,jd;
        int jdn,a,y,m;

        a=(14-ts[1])/12;
       
        y = ts[0]+4800-a;

        m = ts[1]+12*a-3;

        jdn = ts[2] + ((153*m+2)/5) + 365*y + y/4 - y/100 + y/400 -32045;

        jd = jdn +((double)ts[3]-12)/24+(double)ts[4]/1440+(double)ts[5]/86400;

        mjd = jd-2400000.5;

        totalMs = jd*86400*1000 + ts[6];

        totalMs += addLeapSec(mjd)*1000;
        
        return (long)totalMs;
    }

    public static boolean checkRange(){
        if(stamp1[0]<1972 || stamp1[0]>2017){
            return false;
        }
        if(stamp2[0]<1972 || stamp2[0]>2017){
            return false;
        }
        return true;
    }

    public static String checkFormat(String stamp){
        StringTokenizer st = new StringTokenizer(stamp, "[ ./-:,TZ]", true);
        char[] dateSeperator = new char[2];
        char[] timeSeperator = new char[2];
        String year,month,day,t,hour,min,sec,milliSec,z;
        month = day = min = sec = "";
        
        year = st.nextToken();

        if(year.length()<4){
            return "not 4 year digits";
        } else {
            if(year.length()==8){ //date without seperators
                month = year.substring(4,6);
                day = year.substring(6);
                year = year.substring(0,4);
            } else if(year.length()==6){ //short year or wrong seperator?
                month = year.substring(4);
                year = year.substring(0,4);
            }
            if(Integer.parseInt(year)<1901 || Integer.parseInt(year)>2099){
                return "year out of range";
            }
        }

        if(month==""){

            dateSeperator[0] = st.nextToken().charAt(0);

            month = st.nextToken();

            if(month.length()<2 || month.length()==3){
                return "not 2 month digits";
	    }else if(month.length()==4){
                day = month.substring(2);
                month = month.substring(0,2);
            }
        }

        if(Integer.parseInt(month)==0 || Integer.parseInt(month)>12){
            return "month out of range";
        }

        if(day==""){

            dateSeperator[1] = st.nextToken().charAt(0);            

            day = st.nextToken();
        
            if(day.length()<2){
                return "not 2 day digits";
            } else if(day.length()>2){
		return "T missing";
	    }
        }
        
        if(Integer.parseInt(day)==0 || Integer.parseInt(day)>31){
            return "day out of range";
        }

        if(dateSeperator[0] != dateSeperator[1]){
            return "date separators don't match";
        }

        //check if date is valid
        if(day.equals("31")){
            if(month.equals("02") || month.equals("04") || month.equals("06") ||
               month.equals("09") || month.equals("11")){
                return "invalid date";
            }
        } else if(day.equals("30") && month.equals("02")){
            return "invalid date";
        } else if(day.equals("29") && month.equals("02")){
            if(Integer.parseInt(month)%4!=0){ //if not leap year
                return "invalid date";
            }
        }

        t = st.nextToken();
                
        if(!t.equals("T")){
            return "T missing";
        } else {
            hour = st.nextToken();
        }

        if(hour.length()<2){
            return "not 2 hour digits";
        } else {
            
            if(hour.length()==6){
                min = hour.substring(2,4);
                sec = hour.substring(4,6);
                hour = hour.substring(0,2);
            } else if(hour.length()>6){
		return "missing millisecond decimal";
	    }

            if(Integer.parseInt(hour)>23){
                return "hour out of range";
            }
        }

        if(min==""){

            timeSeperator[0] = st.nextToken().charAt(0);

            min = st.nextToken();

            if(min.length()!=2){
                return "not 2 minute digits";
            }
        }

        if(Integer.parseInt(hour)>59){
            return "minute out of range";
        }

        if(sec==""){

            timeSeperator[1] = st.nextToken().charAt(0);

            sec = st.nextToken();

            if(sec.length()<2){
                return "not 2 second digits";
            } else if(sec.length()>2){
		return "missing millisecond decimal";
	    }

        }

        if(Integer.parseInt(hour)>59){
            return "second out of range";
        }

        if(timeSeperator[0] != timeSeperator[1]){
            return "time seperators don't match";
        }

        milliSec = "0";

        int remaining = st.countTokens();
        
        if(remaining==0){
            return "no Z after timestamp";
        } else if(remaining>=1){
            z =  st.nextToken();
            if(z=="." || z==","){
                return "no digits after decimal point";
            }
        }
        if(remaining>1){
            milliSec = st.nextToken();
            
            if(milliSec=="Z"){
                return "no digits after decimal point";
            } else if(milliSec.length()>3){
                return "more than 3 millisecond digits";
            } else if(milliSec.length()==2){
                milliSec+="0";
            } else if(milliSec.length()==1){
                milliSec+="00";
            }

            z = st.nextToken();

            if(!z.equals("Z")){
                return "no Z after timestamp";
            }   
        }

        if(stamp1==null){
            stamp1 =new int[]{Integer.parseInt(year),
                              Integer.parseInt(month),Integer.parseInt(day),
                              Integer.parseInt(hour),Integer.parseInt(min),
                              Integer.parseInt(sec),
                              Integer.parseInt(milliSec)};
        } else {
            stamp2 =new int[]{Integer.parseInt(year),
                              Integer.parseInt(month),Integer.parseInt(day),
                              Integer.parseInt(hour),Integer.parseInt(min),
                              Integer.parseInt(sec),
                              Integer.parseInt(milliSec)};
        }
        
        return "";
    }
    
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String[] input = new String[6];

        do{
            input = s.nextLine().split("\\s");

            if(input.length<2){
                return;
            } else if(input.length>2){ //if the date seperators are spaces
                StringBuilder stamp = new StringBuilder();
                String [] stampParts = Arrays.copyOf(input, input.length);
                int y =0;
                for(String x:stampParts){
                    stamp.append(x);
                    if (stamp.length()<16){
                        stamp.append(" ");
                    } else {
                        input[y] = stamp.toString();
                        stamp.delete(0,stamp.length());
                        y++;
                    }
                }
            }

            StringBuilder error = new StringBuilder("Error");
            StringBuilder result = new StringBuilder();
            
            if(!s.hasNextLine()){
                error.append(": missing line terminator\n");
            } else if(input.length==1){
                error.append(": missing space between timestamps\n");
            } else if(input[1].charAt(input[1].length()-1)!='Z'){ //??
                error.append(": junk after second timestamp\n");
            }       

            //if there was an error when reading timestamps
            if(error.length()>8){
                System.out.println(error.toString());
            } else {
            
                //check format of timestamps
                String errorStamp1 = checkFormat(input[0]);
                String errorStamp2 = checkFormat(input[1]);

                //if there was format errors
                if(errorStamp1.length()!=0 || errorStamp2.length()!=0){
                    if(errorStamp1.length()!=0){
                        error.append(" in first timestamp: "+errorStamp1);
                    } else if(errorStamp2.length()!=0){
                        error.append(" in second timestamp: "+errorStamp2);
                    }
                    System.out.println(error.toString());
                    
                } else {
                    //check if within range of exact leap seconds
                    if(checkRange()){
                        result.append("Exactly ");
                    } else {
                        result.append("About ");
                    }

                    result.append(convert(stamp1) - convert(stamp2));
                    System.out.println(result.toString());
                }
            }
            
            //reset timestamp arrays
            stamp1 = null;
            stamp2 = null;
           
        } while(s.hasNextLine());
    }    
}
