import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileReader {
  String filePath;
  FileReader(String filePath) {
    this.filePath = filePath;
  }

  /**
   * This is the method which returns the data read from the file, in the format of hashmap.
   * @return a map from each Process ID to the Process object.
   * @throws IOException
   */
  Map<Integer, Process> loadData() throws IOException  {
    Map<Integer, Process> ret = new HashMap<>();
    File file = new File(this.filePath);
    Scanner myReader = new Scanner(file);
    while (myReader.hasNextLine()) {
      String data = myReader.nextLine();
      String[] info = data.split(" ");
      Process p = new Process(
          Integer.parseInt(info[0]),
          Integer.parseInt(info[1]),
          Integer.parseInt(info[2]),
          Integer.parseInt(info[3])
      );
      ret.put(p.id, p);
    }
    myReader.close();
    return ret;
  }
}
