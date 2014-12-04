package main;
import java.util.ArrayList;
import java.util.HashMap;

import Faroo.API;

public class Main {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		API a = new API("2CJIbhzsHU4nlSqBVZ2OP3fimb4_","foobar", 2);
		ArrayList<HashMap<String,String>> foo = a.getCompleteResults();
		for(HashMap<String,String> result: foo){
			System.out.println(result.get("title"));	
		}
		
		System.out.println("Bye");

	}
}

