import java.io.*;

public class Support {



   private static InputStreamReader inReader = new InputStreamReader(System.in);
   private static BufferedReader bufferInput = new BufferedReader(inReader);
   private static String currentLine;



   private static void getNextLine () {
     /* This method reads a line from the terminal and stores the value
        in currentLine. The result is interned in order to make it
        possible to compare currentLine with a literal by means of ==
        rather than the equals method. */

     try {
       currentLine = bufferInput.readLine();
       currentLine = currentLine.intern();
     }
     catch (IOException i) {
       System.out.println("    Input error. Execution aborted.");
       System.exit(0);
     }
   }


   public static int readInt () {
     /* This method waits for input from the keyboard terminated by a
        return. If the first characters on the line are an integer,
        then that value is returned, otherwise an error message is
        printed and execution is aborted. */

     int i;

     getNextLine();

     try {
       i = Integer.parseInt(currentLine);
     }
     catch (NumberFormatException e) {
       System.out.println("    Input error, you have to input an int. Execution aborted.");
       System.exit(0);
       return 0;   // Just for the form, statement never reached
     }
     return i;
   }



   public static double readDouble () {

     /* This method waits for input from the keyboard terminated by a
        return. If the first characters on the line are a double, then
        that value is returned, otherwise an error message is printed
        and execution is aborted. Note that a double entered as an
        integer (e.g. 6) is returned as double (e.g. 6.0). */

     double d;

     getNextLine();

     try {
       d = Double.valueOf(currentLine).doubleValue();
     }
     catch (NumberFormatException e) {
       System.out.println("    Input error, you have to input a double. Execution aborted.");
       System.exit(0);
       return 0.0;   // Just for the form, statement never reached
     }
     return d;
   }



   public static String readString () {

     /* This method waits for input from the keyboard terminated by a
        return. The line is returned as a String. Note that if there
        are blank spaces anywhere on the input line, they are also
        included in the returned String. If the input line is empty,
        the empty string "" is returned. */
     getNextLine();
     return currentLine;
   }



   public static char readChar () {

     /* This method waits for input from the keyboard, terminated by a
        return. The first character on the line is returned as a char.
        If the input line is empty, the space character is returned. */
     getNextLine();
     if (currentLine.length() == 0)
       return ' ';

     else
       return currentLine.charAt(0);
   }
 }
