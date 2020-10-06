package wordPlay.util;
import java.io.FileWriter;
import java.util.ArrayList;
public class Results implements FileDisplayInterface, StdoutDisplayInterface {


	private String output;
	private String metricsfile;
	private ArrayList result;

	/**
		* This constructor takes the two filename as an inputs and assigns them to class variables.
	*/
	public Results(String outtxt, String metricsFile){
		output = outtxt;
		metricsfile = metricsFile;
	}


	/**
		* This methods takes the string and append flag as input and writes the string into the outputfile
	*/
	public void writeOutput(String s, boolean appendFlag){
		try {
			// System.out.println(output);
			FileWriter filewriter = new FileWriter(output, appendFlag);
			filewriter.write(s);
			filewriter.flush();
			filewriter.close();
			// System.out.println(s);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}


	/**
		* This methods takes the string as an input and writes the string into the metricsfile
	*/

	public void writeMetrics(String s){
		try {
			FileWriter filewriter = new FileWriter(metricsfile);
			filewriter.write(s);
			filewriter.flush();
			filewriter.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}


}
