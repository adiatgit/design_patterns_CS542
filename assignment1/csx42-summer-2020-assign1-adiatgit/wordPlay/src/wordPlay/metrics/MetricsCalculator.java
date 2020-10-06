package wordPlay.metrics;

public class MetricsCalculator{

  /**
    * This method is simply  used for the calculating the length of string given
    * @return returns the length of string
  */
  public int calStrLen(String s){
      return s.length();
  }


  /**
    * This function return the value of avg number of words per sentence given the count of words and number of sentences
    * @return returns the float value rounded to 2 decimal point for avg number of words per sentence.
  */
  public float avgNumWordPerSentence(int count, int numberOfSentence){
      return ((int)(((float)count / numberOfSentence + 0.005f) * 100)) / 100f ;
  }


  /**
    * This function return the value of avg number word length given the count of words and total length of each string
    * @return returns the avg word length of the string rounded to 2 decimal point.
  */
  public float avgWordLength(int totalLengthOfString, int count){
      return ((int)(((float)totalLengthOfString / count + 0.005f) * 100)) / 100f;
  }

}
