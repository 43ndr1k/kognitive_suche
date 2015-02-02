package komplexeSuche;
import java.util.ArrayList;

//@author Franz Schwarzer

public class allMeta {

Tag_Meta[] meta=new Tag_Meta[9];
ArrayList<String[]> keys;

allMeta(ArrayList<String> urls){
	
	for(int i=0;i<9;i++){
		meta[i]=new Tag_Meta(urls.get(i),i);
		meta[i].start();
		
	}

	while(ready()==false){
		
	}
	//System.out.println(Statics.keys[0]);
	
/*	for(int i=0;i<9;i++){
		keys.add(meta[i].keys);
	}*/
	

}

public boolean ready(){
	for(int i=0;i<9;i++){
		if(meta[i].isAlive()==false){return false;}
	}
	return true;
	
}
	
}
