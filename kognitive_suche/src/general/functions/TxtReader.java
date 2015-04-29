package general.functions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * 
 * @author Tobias Lenz
 * 
 * Klasse um einen String aus einer txt-Datei zu bekommen.
 *
 */
public class TxtReader {


    public String readFile(String fileName) throws IOException {
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      try {
          StringBuilder sb = new StringBuilder();
          String line = br.readLine();

          while (line != null) {
              sb.append(line);
              sb.append("\n");
              line = br.readLine();
          }
          return sb.toString();
      } finally {
          br.close();
      }
  }
  }

