
package org.nightcode.demo.SkyX.Engine;

import java.util.HashMap;

public class SpriteManager {
	public static SpriteManager getInstance(){
		if(instance==null) instance=new SpriteManager();
		return instance;
	}
	private static SpriteManager instance;
	//////////////////////////////////////////////////////////
	private HashMap<String,ISprite> sprites;
	public void addSprite(ISprite a,String b){
		sprites.put(b,a);
	}
	public void removeSprite(String b){
		sprites.remove(b);
	}
	public ISprite getSprite(String b){
		return sprites.get(b);
	}
}
