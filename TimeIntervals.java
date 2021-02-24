import java.util.*;

class TimeIntervals {

   private static final int START = 0;
   private static final int END = 1;

   private static int intervalToMinutes(String interval) {
      String[] separate = interval.split(":");
      int hours = Integer.valueOf(separate[0]);
      int minutes = Integer.valueOf(separate[1]);
      return (hours * 60) + minutes;
   }
   
   private static String intervalToString(int interval) {
      String hours = String.valueOf(interval/60);
      String minutes = String.valueOf(interval % 60);
      return hours + ":" + minutes;
   }
   
   public static void main(String[] args) {
      String[][] myMeetingTimes = new String[][] {new String[]{"9:00", "10:30"}, new String[]{"12:00", "13:00"}, new String[]{"16:00", "18:00"}};
      String[][] yourMeetingTimes = new String[][] {new String[]{"10:00", "11:30"}, new String[]{"12:30","14:30"}, new String[]{"14:30","15:00"}, new String[]{"16:00", "17:00"}};
      String[] myBounds = new String[]{"9:00", "20:00"};
      String[] yourBounds = new String[]{"10:00", "18:30"};
      ArrayList<String[]> ourMeetingTimes = ourMeetingTimes(myMeetingTimes, yourMeetingTimes, myBounds, yourBounds);
      for(int i = 0; i < ourMeetingTimes.size(); i++) {
         printInterval(ourMeetingTimes.get(i));
      }
   }
   
   private static void printInterval(String[] interval) {
      System.out.println(interval[START] + " - " + interval[END]);
   }
   
   private static ArrayList<String[]> ourMeetingTimes(String[][] myMeetingTimes, String[][] yourMeetingTimes, String[] myBounds, String[] yourBounds) {
      ArrayList<String[]> ourTimes = new ArrayList();
      int j = 0;
      int yourStart = intervalToMinutes(yourBounds[START]);
      int yourEnd = intervalToMinutes(yourMeetingTimes[0][START]);
      for(int i = 0; i <= myMeetingTimes.length; i++) {
         int myStart = i == 0? intervalToMinutes(myBounds[START]) : intervalToMinutes(myMeetingTimes[i - 1][END]);
         int myEnd = i == myMeetingTimes.length ? intervalToMinutes(myBounds[END]) : intervalToMinutes(myMeetingTimes[i][START]);
         System.out.print("Mine " + i + ": ");
         printInterval(new String[]{intervalToString(myStart), intervalToString(myEnd)});
         while(yourStart < myEnd && j < yourMeetingTimes.length) {
            if(yourEnd > myStart) {
               int ourStart = myStart > yourStart ? myStart : yourStart;
               int ourEnd = myEnd < yourEnd ? myEnd : yourEnd;
               if(ourEnd - ourStart >= 30) {
                  ourTimes.add(new String[]{intervalToString(ourStart), intervalToString(ourEnd)});
               }
            }
            j++;
            yourStart = j == 0 ? intervalToMinutes(yourBounds[START]) : intervalToMinutes(yourMeetingTimes[j - 1][END]);
            yourEnd = j == yourMeetingTimes.length ? intervalToMinutes(yourBounds[END]): intervalToMinutes(yourMeetingTimes[j][START]);
            System.out.print("Yours " + j + ": ");
            printInterval(new String[]{intervalToString(yourStart), intervalToString(yourEnd)});
         }
         if(yourEnd > myStart && yourStart < myEnd) {
               int ourStart = myStart > yourStart ? myStart : yourStart;
               int ourEnd = myEnd < yourEnd ? myEnd : yourEnd;
               if(ourEnd - ourStart >= 30) {
                  ourTimes.add(new String[]{intervalToString(ourStart), intervalToString(ourEnd)});
               }
            }
      }
      return ourTimes;
   }
}