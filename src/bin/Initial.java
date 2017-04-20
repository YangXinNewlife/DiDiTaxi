package bin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bis.TaskManager;
import client.Maps;
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
			Taxi obj = new Taxi((int)(Math.random() * 80), null, (int)(Math.random() * 80), i);
			obj.setCredit(0);
			taxiList.add(obj);
		}
		System.out.println("Finished!");
		System.out.println("Maps and Taxis Ready, Please Passengers Type where are you and you want!");
		System.out.println("For Example: [CR,src,dst], [CR,(1,1),(2,2)], and end of character of 'END'");
		String context;
		Scanner sc = new Scanner(System.in);
		TaskManager taskManager = new TaskManager();
		while(true){
			str = sc.nextLine();
			if(str.equals("END")){
				break;
			}
			else{
				int src_x = 0, src_y = 0, dst_x = 0, dst_y = 0;
				Passenger passenger = new Passenger();
				str = str.substring(4,str.length() - 1);
				Pattern p = Pattern.compile("[0-9A-Z]+");
				Matcher m = p.matcher(str);
				int counter = 0;
				while(m.find()){
					if(counter == 0){
						src_x = Integer.parseInt(m.group());
						counter += 1;
					}else if(counter == 1){
						src_y = Integer.parseInt(m.group());
						counter += 1;
					}else if(counter == 2){
						dst_x = Integer.parseInt(m.group());
						counter += 1;
					}else{
						dst_y = Integer.parseInt(m.group());
						counter += 1;
					}
				}  
				passenger.setSrc_X(src_x);
				passenger.setSrc_y(src_y);
				passenger.setDst_X(dst_x);
				passenger.setDst_y(dst_y);
				taskManager.RecordTask(str);
				passengerList.add(passenger);
			}
		}
		taskManager.TaskDis(taxiList, passengerList);
	}
}
