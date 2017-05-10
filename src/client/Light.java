package client;


public class Light {
	private int isExists;
	private int flag;
	private String n_s_color;
	private String e_w_color;
	
	//construction function
	public Light(int isExists, int flag){
		this.isExists = isExists;
		this.n_s_color = "green";
		this.e_w_color = "red";
		this.flag = flag;
	}
	
	public int getIsExists() {
		return isExists;
	}

	public void setIsExists(int isExists) {
		this.isExists = isExists;
	}

	public String getN_s_color() {
		return n_s_color;
	}

	public void setN_s_color(String n_s_color) {
		this.n_s_color = n_s_color;
	}

	public String getE_w_color() {
		return e_w_color;
	}

	public void setE_w_color(String e_w_color) {
		this.e_w_color = e_w_color;
	}
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
}
