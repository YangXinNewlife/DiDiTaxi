package bis;


import java.awt.Point;
import java.util.ArrayList;

import client.Light;
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
	private int credit;
	private Enum status;
	private String isSelect;
	ArrayList<Light> lightList; 
	
	//construction function
	public TaxiTriger(int src_x, int src_y, int dst_x, int dst_y, int x, int y, int ID, int credit, Enum status, String isSelect, ArrayList<Light> lightList){
		this.x = x;
		this.y = y;
		this.src_x = src_x;
		this.src_y = src_y;
		this.dst_x = dst_x;
		this.dst_y = dst_y;	
		this.ID = ID;
		this.credit = credit;
		this.status = status;
		this.isSelect = isSelect;
		this.lightList = lightList;
	}

	
	//taxi move function
	public void move() throws Exception{
		Taxi taxi = new Taxi(this.x, this,y, this.ID);
		int time_tmp1 = ((Math.abs((this.src_x - this.dst_x)) + Math.abs((this.src_y - this.dst_y))) * 200);
		int time_tmp2 = 0;
		for(int i = this.src_x; i < this.dst_x; i++){
			if(lightList.get(i).getE_w_color().equals("red") == true){
				time_tmp2 += 100;
			}
		}
		for(int j = this.src_y; j < this.dst_y; j++){
			if(lightList.get(j).getN_s_color().equals("red") == true){
				time_tmp2 += 100;
			}
		}
		int red_color_time = time_tmp2/100;
		taxi.setRunTime(time_tmp1 + time_tmp2);
		taxi.setX(this.dst_x);
		taxi.setY(this.dst_y);
		TaskManager tm = new TaskManager();
		String record = "Taxi [ID] is : " + this.ID + ", [credit] is : " + this.credit + ", [status] is : "+ this.status + ", [isSelected] : " + this.isSelect + ", [want to] :" + " ("+ this.dst_x + "," + this.dst_y + ")" + " , Used Time : " + (time_tmp1 + time_tmp2) + " ms" + " the red light number is " + red_color_time;
		tm.OutPut(record);
		taxi.setStatus(TaxiEnum.Pending);
		taxi.setCredit(taxi.getCredit() + 3);
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
