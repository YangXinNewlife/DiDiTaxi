package bin;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import bis.MapTriger;
import bis.PilotLightTriger;
import bis.TaskManager;
import client.Light;
import client.MapsRoad;
import client.Passenger;
import client.Taxi;


public class Initial {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Boot Taxi System . . .");
		//define taxiList
		ArrayList<Taxi> taxiList = new ArrayList<Taxi>();
		//define passengerList
		ArrayList<Passenger> passengerList = new ArrayList<Passenger>();
		//define mapsRoadList
		ArrayList<MapsRoad> mapsRoadList = new ArrayList<MapsRoad>();
		//define lightList
		ArrayList<Light> lightList = new ArrayList<Light>(); 
		//upload maps
		String MapsFile = "F:\\Java\\DiDiTaxi\\Maps\\map.txt";
		FileReader reader = new FileReader(MapsFile);
		BufferedReader  br = new BufferedReader(reader);
		System.out.println("upload Maps");
		String str = null;
		while((str = br.readLine()) != null){
			for(int i = 0; i < 80; i++){
				for(int j = 0; j < 80; j++){
					MapsRoad mapRoad_obj = new MapsRoad();
					mapRoad_obj.setX(i);
					mapRoad_obj.setY(j);
					mapRoad_obj.setFlowstatistics(0);
					mapRoad_obj.setStatus(Integer.parseInt((str.substring(j,j+1))));
					mapsRoadList.add(mapRoad_obj);
				} 
			}
		}
		br.close();
		reader.close();
		System.out.println("Finished!");
		//upload Light
		String light_file = "F:\\Java\\DiDiTaxi\\light\\light.txt";
		FileReader reader1 = new FileReader(light_file);
		BufferedReader  br1 = new BufferedReader(reader1);
		System.out.println("upload light");
		String str1 = null;
		
		while((str1 = br1.readLine()) != null){
			for(int i = 0; i < 80; i++){
				for(int j = 0; j < 80; j++){
					Light light_obj = new Light(Integer.parseInt((str1.substring(j,j+1))), j);
					lightList.add(light_obj);
				} 
			}
		}
		Thread light_thread = new PilotLightTriger(lightList);
		light_thread.start();
		
		//upload taxi
		System.out.println("Upload Taxi");
		for(int i = 0; i < 100; i++){
			Taxi t_obj = new Taxi((int)(Math.random() * 80), null, (int)(Math.random() * 80), i);
			taxiList.add(t_obj);
		}
		System.out.println("Finished!");
		System.out.println("Maps and Taxis Ready, Please Passengers Type where are you and you want!");
		System.out.println("For Example: [CR,src,dst], [CR,(1,1),(2,2)] or open(1,1)(2,2), close(1,1)(2,2)");
		//passenger input
		Scanner sc = new Scanner(System.in);
		TaskManager taskManager = new TaskManager();
		while(true){
			str = sc.nextLine();
			if(str.substring(0,1).equals("o") == true || str.substring(0,1).equals("c") == true)
			{	String opt_flag = null;
				if(str.substring(0,1).equals("o") == true){
					opt_flag = "open";
				}else if(str.substring(0,1).equals("c") == true){
					opt_flag = "close";
				}
				//parser turn load opt parameter
				int opt_src_x = Integer.parseInt(str.substring(str.indexOf("(") + 1, str.indexOf(",")));
				int opt_src_y = Integer.parseInt(str.substring(str.indexOf(",") + 1, str.indexOf(")")));
				int opt_dst_x = Integer.parseInt(str.substring(str.indexOf(")(") + 2, str.lastIndexOf(",")));
				int opt_dst_y = Integer.parseInt(str.substring(str.lastIndexOf(",") + 1, str.lastIndexOf(")")));
				String content = opt_flag + " the load from (" + opt_src_x + ", " + opt_src_y + ") to (" + opt_dst_x + ", " + opt_dst_y + ")";
				taskManager.RecordTask(content);
				Thread maptriger_obj = new MapTriger(mapsRoadList, opt_src_x, opt_src_y, opt_dst_x, opt_dst_y, opt_flag);
				maptriger_obj.start();
			}
			//input format error
			else if(str.substring(0,1).equals("[") == false || str.substring(str.length() - 1, str.length()).equals("]") == false){
				System.out.println("----输入格式错误-----"); 
				taskManager.ErrorRecordTask(str);
			}
			//parser input parameter
			else{
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
					taskManager.TaskDis(taxiList, passenger, lightList);
				}
			}
		}
	}
}
