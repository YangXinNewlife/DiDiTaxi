package client;
import bis.TaxiTriger;
import client.TaxiEnum;
public class Taxi extends Thread{
	
	private Enum status = TaxiEnum.Pending;
	private int credit = 0;
	private int X;
	private int Y;
	private int runTime = 0;
	private int ID;
	private String isSelect = "No";
	
	
	public void run(){
		
	}
	
	//construction function
	public Taxi(int X, TaxiTriger taxiTriger, int Y, int ID){
		this.X = X;
		this.Y = Y;
		this.ID = ID;
		this.credit = 0;
	}
	
	public int move(int src_x, int src_y, int dst_x, int dst_y){
		this.runTime  = Math.abs((src_x - dst_x)) + Math.abs((src_y - dst_y)) * 200;  
		this.X = dst_x;
		this.Y = dst_y;
		return this.runTime;
	}
	
	public Enum getStatus() {
		return status;
	}

	public void setStatus(Enum status) {
		this.status = status;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	public String getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(String isSelect) {
		this.isSelect = isSelect;
	}

	
	public int getRunTime() {
		return runTime;
	}
	public void setRunTime(int runTime) {
		this.runTime = runTime;
	}
	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}
	
	//test implement
	public Enum QueryStatus(){
		return status;
	}
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
}
