package bis;

import java.util.ArrayList;
import client.MapsRoad;
public class MapTriger extends Thread{
	ArrayList<MapsRoad> mapsRoadList;
	int src_x = 0;
	int src_y = 0;
	int dst_x = 0;
	int dst_y = 0;
	String opt = null; 

	//construction function
	public MapTriger(ArrayList<MapsRoad> mapsRoadList, int src_x, int src_y, int dst_x, int dst_y, String opt_flag){
		this.mapsRoadList =  mapsRoadList;
		this.src_x = src_x;
		this.src_y = src_y;
		this.dst_x = dst_x;
		this.dst_y = dst_y;
		this.opt = opt_flag;
	} 
	
	//open or close roads 
	public void optRoad() throws Exception{
		if(this.opt.equals("close")){
			for(int i = 0; i < this.mapsRoadList.size(); i++){
				if(this.mapsRoadList.get(i).getX() == this.src_x && this.mapsRoadList.get(i).getY() == this.src_y){
					for(int j = this.src_x; j < this.dst_x; j++){
						this.mapsRoadList.get(j).setStatus(1);
					}
					for(int k = this.src_y; k < this.dst_y; k++){
						this.mapsRoadList.get(k).setStatus(2);
					}
				}
				
			}
		}else if(this.opt.equals("open")){
			for(int i = 0; i < this.mapsRoadList.size(); i++){
				if(this.mapsRoadList.get(i).getX() == this.src_x && this.mapsRoadList.get(i).getY() == this.src_y){
					for(int j = this.src_x; j < this.dst_x; j++){
						this.mapsRoadList.get(j).setStatus(2);
					}
					for(int k = this.src_y; k < this.dst_y; k++){
						this.mapsRoadList.get(k).setStatus(1);
					}
				}
			}
		}
		TaskManager taskManager_obj = new TaskManager();
		String content = "Has " + this.opt + " the road " + "from (" + this.src_x + ", " + this.src_y + ") to (" + this.dst_x + ", " + this.dst_y + ")"; 
		taskManager_obj.OutPut(content);
	}
	
	public void run(){
		try {
			optRoad();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
