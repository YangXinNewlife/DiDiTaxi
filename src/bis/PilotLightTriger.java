package bis;

import java.util.ArrayList;

import client.Light;

public class PilotLightTriger extends Thread{
	ArrayList<Light> lightList;

	//construction function
	public PilotLightTriger(ArrayList<Light> lightList){
		this.lightList = lightList;
	}
	
	//lightchange function
	public void LightRun() throws Exception{
		while(true){
			Thread.sleep(100000);
			TaskManager tm = new TaskManager();
			for(int i = 0; i < lightList.size(); i++){
				if(lightList.get(i).getIsExists() == 1){
					if(lightList.get(i).getE_w_color().equals("red") == true){
						lightList.get(i).setE_w_color("green");
						String content = "The East, West Light " + lightList.get(i).getFlag()+ " light is red change to green";
						tm.LightChangeRecord(content);
					}else if(lightList.get(i).getE_w_color().equals("green") == true){
						lightList.get(i).setE_w_color("red");
						String content = "The East, West Light " + lightList.get(i).getFlag() + " light is green change to red";
						tm.LightChangeRecord(content);
					}
				}
				if(lightList.get(i).getN_s_color().equals("red") == true){
					lightList.get(i).setN_s_color("green");
					String content = "The North, South Light " + lightList.get(i).getFlag() + " light is red change to green";
					tm.LightChangeRecord(content);
				}else if(lightList.get(i).getN_s_color().equals("green") == true){
					lightList.get(i).setN_s_color("red");
					String content = "The North, South Light " + lightList.get(i).getFlag() + " light is green change to red";
					tm.LightChangeRecord(content);
				}
			}
			Thread.sleep(100000);
		}
	}
	
	public void run(){
		try {
			LightRun();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
