package client;

public class MapsRoad {
	private int x;
	private int y;
	private int status;
	private int flowstatistics;

	//construction function
	public MapsRoad(){
		x = 0;
		y = 0;
		status = 0;
		flowstatistics = 0;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
		if(this.status == 0){
			
		}
	}
	
	public int getFlowstatistics() {
		return flowstatistics;
	}

	public void setFlowstatistics(int flowstatistics) {
		this.flowstatistics = flowstatistics;
	}
}
