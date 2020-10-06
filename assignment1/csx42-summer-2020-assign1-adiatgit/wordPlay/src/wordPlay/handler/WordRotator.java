package wordPlay.handler;
import wordPlay.util.FileProcessor;

public class WordRotator {
  /**
  * Following constructor takes the string and shift as the input.
  * @return the string shifted right by 'shift places'
  */
  public String rotate(String t, int shift){
    char[] A = t.toCharArray();
    for (int i = 0; i < shift; i++) {
         char temp_var = A[A.length - 1];
         for (int j = A.length - 1; j > 0; j--) {
             A[j] = A[j - 1];}
        A[0] = temp_var;}
    return new String(A);
  }
}
