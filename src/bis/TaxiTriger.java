package bis;

import client.Taxi;
import client.TaxiEnum;

public class TaxiTriger extends Thread{	
	private int src_x;
	private int src_y;
	private int dst_x;
	private int dst_y;
	private int x;
	private int y;
	private int ID;
	 
	
	public TaxiTriger(int src_x, int src_y, int dst_x, int dst_y, int x, int y, int ID){
		this.x = x;
		this.y = y;
		this.src_x = src_x;
		this.src_y = src_y;
		this.dst_x = dst_x;
		this.dst_y = dst_y;	
		this.ID = ID;
	}
	
	public void move() throws Exception{
		Taxi taxi = new Taxi(this.x, this,y, this.ID);
		taxi.setRunTime(Math.abs((this.src_x - this.dst_x)) + Math.abs((this.src_y - this.dst_y)) * 200);  
		taxi.setX(this.dst_x);
		taxi.setY(this.dst_y);
		TaskManager tm = new TaskManager();
		String record = "Taxi ID is : " + this.ID + " ,want to " + "("+ this.dst_x + "," + this.dst_y + ")" + " , Used Time : " + (Math.abs((this.src_x - this.dst_x)) + Math.abs((this.src_y - this.dst_y)) * 200);
		tm.OutPut(record);
		taxi.setStatus(TaxiEnum.Pending);
	}
	
	public void run(){
		try {
			move();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
