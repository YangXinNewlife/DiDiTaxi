package bin;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import bis.TaskManager;
import client.Passenger;
import client.Taxi;


public class Initial {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Boot Taxi System . . .");
		ArrayList<Taxi> taxiList = new ArrayList<Taxi>();
		ArrayList<Passenger> passengerList = new ArrayList<Passenger>();
		int[][] maps = new int[80][80];;
		String MapsFile = "F:\\Java\\DiDiTaxi\\Maps\\map.txt";
		FileReader reader = new FileReader(MapsFile);
		BufferedReader  br = new BufferedReader(reader);
		System.out.println("upload Maps");
		String str = null;
		while((str = br.readLine()) != null){
			for(int i = 0; i < 80; i++){
				for(int j = 0; j < 80; j++){
					maps[i][j] = Integer.parseInt((str.substring(j,j+1)));
				} 
			}
		}
		br.close();
		reader.close();
		System.out.println("Finished!");
		System.out.println("Upload Taxi");
		for(int i = 0; i < 100; i++){
			Taxi t_obj = new Taxi((int)(Math.random() * 80), null, (int)(Math.random() * 80), i);
			taxiList.add(t_obj);
		}
		System.out.println("Finished!");
		System.out.println("Maps and Taxis Ready, Please Passengers Type where are you and you want!");
		System.out.println("For Example: [CR,src,dst], [CR,(1,1),(2,2)]");
		Scanner sc = new Scanner(System.in);
		TaskManager taskManager = new TaskManager();
		while(true){
			str = sc.nextLine();
			if(str.substring(0,1).equals("[") == false || str.substring(str.length() - 1, str.length()).equals("]") == false){
				System.out.println("----输入格式错误-----"); 
				taskManager.ErrorRecordTask(str);
			}else{
				Passenger passenger = new Passenger();
				String str_content_temp = str.split(",")[0];
				String str_content = str_content_temp.replace("[", "");
				String src_x_temp = str.split(",")[1];
				int src_x = Integer.parseInt(src_x_temp.replace("(", ""));
				String src_y_temp = str.split(",")[2];
				int src_y = Integer.parseInt(src_y_temp.replace(")", ""));
				String dst_x_temp = str.split(",")[3];
				int dst_x = Integer.parseInt(dst_x_temp.replace("(", ""));
				String dst_y_temp = str.split(",")[4];
				int dst_y = Integer.parseInt(dst_y_temp.replace(")]", ""));
				if(str_content.equals("CR") == false){
					taskManager.ErrorRecordTask(str);
				}else if(src_x < 0 || src_x > 79){
					taskManager.ErrorRecordTask(str);
				}else if(src_y < 0 || src_y > 79){
					taskManager.ErrorRecordTask(str);
				}else if(dst_x < 0 || dst_x > 79){
					taskManager.ErrorRecordTask(str);
				}else if(dst_y < 0 || src_y > 79){
					taskManager.ErrorRecordTask(str);
				}else{
					passenger.setSrc_X(src_x);
					passenger.setSrc_y(src_y);
					passenger.setDst_X(dst_x);
					passenger.setDst_y(dst_y);
					taskManager.RecordTask(str);
					taskManager.TaskDis(taxiList, passenger);
				}
			}
		}
	}
}
