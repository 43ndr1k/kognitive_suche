package de.leipzig.htwk.search.history.tags;

import java.io.Serializable;

import de.leipzig.htwk.cognitive.search.ReturnTagList;
import de.leipzig.htwk.searchApi.Results;

public class TagListHistoryObject implements Serializable{
	
	private ReturnTagList tagList;
	private Results results;

	public TagListHistoryObject(ReturnTagList tagList, Results results) {
		
		this.tagList = tagList;
		this.results = results;
	
	}

	public ReturnTagList getTagList() {
		return tagList;
	}

	public void setTagList(ReturnTagList tagList) {
		this.tagList = tagList;
	}

	public Results getResults() {
		return results;
	}

	public void setResults(Results results) {
		this.results = results;
	}

}
