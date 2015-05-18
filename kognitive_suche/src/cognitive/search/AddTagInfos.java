package cognitive.search;

import java.util.ArrayList;

/**
 * 
 * @author Tobias Lenz
 * 
 *         Klasse, um verschiedene Tag-Informationen zu übergeben bzw. einzufügen. Es genügt die
 *         Funktion addInfo mit verschiedenen Parametern aufzurufen.
 *
 */

public class AddTagInfos {
  private ReturnTagList tags;

  public AddTagInfos(String searchWord) {
    tags = new ReturnTagList(searchWord);
  }

  public void addInfo(Tag info) {
    if (info != null) {
      for (int i = 0; i < info.getsize(); i++) {
        tags.addTagObject(info.gettag(i), info.getTextBlocNumber());
      }
    }
  }

  public void addInfo(Tag info, String function, double[] values) {
    if (info != null) {
      for (int i = 0; i < info.getsize(); i++) {
        tags.addTagObject(info.gettag(i), info.getTextBlocNumber(),
            getFunctionPriority(function, values, i));
      }
    }
  }

  public void addInfo(ArrayList<Tag> info) {

    if (info != null) {

      for (int i = 0; i < info.size(); i++) {
        addInfo(info.get(i));
      }
    }
  }

  public void addInfo(ReturnTagList info) {
    for (int i = 0; i < info.getSize(); i++) {
      addInfo(info.getTagObject(i));
    }
  }

  public void addInfo(ReturnTagObject info) {
    tags.addTagObject(info.getTag(), info.getBlocNumbers(), info.getPriority());
  }

  public void addInfo(ArrayList<Tag> info, String function, double[] values) {
    for (int i = 0; i < info.size(); i++) {
      addInfo(info.get(i), function, values);
    }
  }

  public void addInfo(String[] info, int textBlocNumber, Double priority) {
    for (int i = 0; i < info.length; i++) {
      addInfo(info[i], textBlocNumber, priority);
    }
  }

  private void addInfo(String string, int textBlocNumber, Double priority) {
    tags.addTagObject(string, textBlocNumber, priority);
  }

  private double getFunctionPriority(String function, double[] values, int i) {
    double retValue;
    switch (function) {
      case "mx+n":
        retValue = i * values[0] + values[1];
        if (retValue > 0) {
          return retValue;
        }
      case "ax²+bx+c":
        retValue = i * i * values[0] + i * values[1] + values[2];
        if (retValue > 0) {
          return retValue;
        }
      case "e^x":
        retValue = Math.exp(i);
        if (retValue > 0) {
          return retValue;
        }
      default:
        break;

    }
    return 0;

  }

  public ReturnTagList getReturnTagList() {
    return tags;
  }
}
