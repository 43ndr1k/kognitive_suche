package kognitver.algorithmus;

import snowballstemmer.GermanStemmer;

public class EditTags {
  ReturnTagList tags;

  public EditTags(ReturnTagList returnTagList) {
    this.tags = returnTagList;
  }

  public ReturnTagList getTags() {
    return tags;
  }

  public void stem() {
    // create a new instance of PorterStemmer
    GermanStemmer stemmer = new GermanStemmer();

    for (int i = 0; i < tags.getsize(); i++) {
      // set the word to be stemmed
      stemmer.setCurrent(tags.getTagObject(i).gettag());


      // call stem() method. stem() method returns boolean value.
      if (stemmer.stem()) {
        tags.renameTag(tags.getTagObject(i).gettag(), stemmer.getCurrent());
        // If stemming is successful obtain the stem of the given word
      }
    }
  }
}
