package wordPlay.driver;
import wordPlay.util.FileProcessor;
import wordPlay.util.Results;
import java.io.FileNotFoundException;
import java.util.Arrays;
import wordPlay.handler.WordRotator;
import wordPlay.metrics.MetricsCalculator;
import java.math.BigDecimal;
import java.io.IOException;
/**
 * @author John Doe
 *
 */
public class Driver {

	/**
		* This function checks for if the string has any other character than alphanumberic character.
		* @return true if the string has any character other than alphanumberic characters.
	*/
	public static boolean isNotAlphaNumeric(String str){
		 return str.matches("^.*[^a-zA-Z0-9. ].*$");
	}


	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 3) || (args[0].equals("${input}")) || (args[1].equals("${output}")) || (args[2].equals("${metrics}"))) {
			   System.err.println("Error: Incorrect number of arguments. Program accepts 3 arguments.");
			   System.exit(0);
		}
		// System.out.println(args[0]);
		// try {
				Results result = new Results(args[1], args[2]);


				result.writeOutput("", false);
				// System.out.println(args[0]);
				int count = 0, shift =0;
			  int stringLen = 0;
			  int numberOfSentence = 0;
				FileProcessor x =null;
				try{
					 x = new FileProcessor(args[0]);
				}
				catch (FileNotFoundException e){
					System.out.println(e);
					System.exit(0);
				}
				catch (IOException e){
					System.out.println(e);
					System.exit(0);
				}
				finally {

				}

				WordRotator wordrotator = new WordRotator();
				MetricsCalculator metricscalculator = new MetricsCalculator();
				while (true){
					/**
					 * looping for each word in the input file
					*/

					String t = null;
			      try {
							t = x.poll();
						}
						catch (IOException e){
							System.out.println(e);
							System.exit(0);
						}
						finally {

						}

			      if(t == null || t.equals(" ")){
							if(count == 0) {
								//checking if the file is empty
								System.out.println("The input file is empty");
								System.exit(0);
							}
			          break;
							}
						boolean flag =false;

						try{
							isNotAlphaNumeric(t);
						}
						catch (Exception e){
							System.out.println("Input file contain some special character");
						}

			      if(t.indexOf('.') != -1){
								/**
								 * checking for last word in the sentence.
								*/
			          t = t.replace(".","");
			          flag = true;
			          numberOfSentence +=1;
						}
						String b = null;
						count++;
						shift++;
						try {
						 /**
						 	* passing the word and shift variable to wordrotator
						 */
						 b = wordrotator.rotate(t, shift);

						 /**
						 	* saving the total of length of all string in inputfile
						*/
						stringLen += metricscalculator.calStrLen(t);
 			      if(flag == true){
 							b = b.concat(".\n"); shift = 0;}
							try {
								result.writeOutput(b.concat(" "), true);
							}
							catch (NullPointerException e){
								System.out.println(e);
								System.out.println("Cannot write empty character to file");

							}
						}
						catch (ArrayIndexOutOfBoundsException e){
							System.out.println("Empty line found in the input file");
							count--;
							/**
								* adjusting the value of count when there is an empty line in the input file
							*/
						}
				}

				/**
				 * calculating the metrics and rounding them off to 2 decimal place and then writing the metrics
				 * to the metrics.txt
				*/
				float avgWordLength = metricscalculator.avgWordLength(stringLen, count);
				float avgNumWordPerSentence = metricscalculator.avgNumWordPerSentence(count, numberOfSentence);
				System.out.println(avgWordLength);
				System.out.println(avgNumWordPerSentence);

				String metrics = "AVG_NUM_WORDS_PER_SENTENCE - ".concat(String.valueOf(avgNumWordPerSentence)).concat("\n AVG_WORD_LENGTH - ").concat(String.valueOf(avgWordLength).concat("\n"));
				result.writeMetrics(metrics);
		}

}
