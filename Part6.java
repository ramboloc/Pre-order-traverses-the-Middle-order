import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import ods.ArrayQueue;


public class Part6 {

    /**
     * Read lines one at a time from r.  Outputs to w according to the
     * lab specifications.
     * Assumes every line is an integer; otherwise it throws a NumberFormatException.
     * @param r the reader to read from
     * @param w the writer to write to
     * @throws IOException, NumberFormatException
     */
    public static void execute(BufferedReader r, PrintWriter w) throws IOException, NumberFormatException {
        // TODO(student): Your code goes here.
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        for (String line = r.readLine(); line != null; line = r.readLine()){
            arrayQueue.add(Integer.parseInt(line));
        }
        Integer[] arr = arrayQueue.toArray(new Integer[0]);
        if(arr.length == 0){
            return;
        }
        int levels = (int) Math.ceil(Math.log(arr.length+1)/Math.log(2));
        int cur_index, gen_index;
        int num;
        int split;
        for (int i = levels; i >0 ; i--) {
            num = (int) Math.pow(2,levels-i);
            cur_index = -1;
            split = (1+arr.length)/(int) Math.pow(2,levels-i+1);
            while(num>0){
                gen_index = cur_index+split;
                w.println(arr[gen_index]);
                cur_index = cur_index+2*split;
                num--;
            }

        }
    }

    

    // YOU SHOULD NOT NEED TO MODIFY BELOW THIS LINE

    /**
     * The driver.  Open a BufferedReader and a PrintWriter, either from System.in
     * and System.out or from filenames specified on the command line, then call doIt.
     * @param args
     */
    public static void main(String[] args) {
        try {
            BufferedReader r;
            PrintWriter w;
            if (args.length == 0) {
                r = new BufferedReader(new InputStreamReader(System.in));
                w = new PrintWriter(System.out);
            } else if (args.length == 1) {
                r = new BufferedReader(new FileReader(args[0]));
                w = new PrintWriter(System.out);
            } else {
                r = new BufferedReader(new FileReader(args[0]));
                w = new PrintWriter(new FileWriter(args[1]));
            }
            long start = System.nanoTime();
            try {
                execute(r, w);
            } catch (NumberFormatException e) {
                System.err.println( "Your input must be integer only");
                System.err.println(e);
                System.exit(-1);
            }
            w.flush();
            long stop = System.nanoTime();
            System.out.println("Execution time: " + 1e-9 * (stop-start));
        } catch (IOException e) {
            System.err.println(e);
            System.exit(-2);
        }
    }
}
