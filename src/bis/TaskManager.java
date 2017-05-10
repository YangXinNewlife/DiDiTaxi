package bis;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import client.Light;
import client.TaxiEnum;
import client.Passenger;
import client.Taxi;

public class TaskManager {
	//
	public void TaskDis(ArrayList<Taxi> taxiList, Passenger passenger, ArrayList<Light> lightList) throws Exception{
		int count = 0;
		int flag = 0;
		for(int j = 0; j < taxiList.size(); j++){
			if(((passenger.getSrc_X() + 4) > taxiList.get(j).getX() && (passenger.getSrc_y() + 4) > taxiList.get(j).getY())){
				if(taxiList.get(j).getStatus() == TaxiEnum.Pending){
					flag = 1;
					count += 1;
					if(count == 1){
						taxiList.get(j).setIsSelect("Yes");
					}
					Thread t = new TaxiTriger(passenger.getSrc_X(), passenger.getSrc_y(), passenger.getDst_X(), passenger.getDst_y(), taxiList.get(j).getX(), taxiList.get(j).getY(), taxiList.get(j).getID(), taxiList.get(j).getCredit(), taxiList.get(j).getStatus(), taxiList.get(j).getIsSelect(), lightList);
					taxiList.get(j).setStatus(TaxiEnum.Servering);
					t.start();
				}
			}
				
		}
		if(flag == 0){
			String content = "Sorry, the nearby passenger request 4 X 4, No Taxi is Pending!";
			this.noTaxi(content);
		}
	}
	
	//
	public void noTaxi(String record) throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
		Date curDate = new Date(System.currentTimeMillis());
		String nowtime = formatter.format(curDate); 
		String content2 = "[INFO]NowTime : " + nowtime + " [No Taxi]: " + record;
		File f=new File("F:\\Java\\DiDiTaxi\\output\\Output.txt");
		FileWriter fw = new FileWriter(f, true);
		PrintWriter pw = new PrintWriter(fw);
		pw.println(content2);
		pw.flush();
	}
	
	//
	public void OutPut(String record) throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
		Date curDate = new Date(System.currentTimeMillis());
		String nowtime = formatter.format(curDate); 
		String content2 = "[INFO]NowTime : " + nowtime + " [Detail information]: " + record;
		File f=new File("F:\\Java\\DiDiTaxi\\output\\Output.txt");
		FileWriter fw = new FileWriter(f, true);
		PrintWriter pw = new PrintWriter(fw);
		pw.println(content2);
		pw.flush();
	}
	
	//
	public void RecordTask(String record) throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
		Date curDate = new Date(System.currentTimeMillis());
		String nowtime = formatter.format(curDate); 
		String content2 = "[INFO]NowTime : " + nowtime + " [Passenger Request]: " + record;
		File f=new File("F:\\Java\\DiDiTaxi\\recordFile\\RequestRecord.txt");
		FileWriter fw = new FileWriter(f, true);
		PrintWriter pw = new PrintWriter(fw);
		pw.println(content2);
		pw.flush();
	}
	
	//
	public void ErrorRecordTask(String ErrorRecord) throws Exception{ 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
		Date curDate = new Date(System.currentTimeMillis());
		String nowtime = formatter.format(curDate);
		String content2 = "[ERROR]NowTime : " + nowtime + " [Error Request]: " + ErrorRecord;
		File f=new File("F:\\Java\\DiDiTaxi\\recordFile\\RequestRecord.txt");
		FileWriter fw = new FileWriter(f, true);
		PrintWriter pw = new PrintWriter(fw);
		pw.println(content2);
		pw.flush();
	}
	
	//
	public void LightChangeRecord(String Record) throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
		Date curDate = new Date(System.currentTimeMillis());
		String nowtime = formatter.format(curDate); 
		String content2 = "[INFO]NowTime : " + nowtime + " [Light Change]: " + Record;
		File f=new File("F:\\Java\\DiDiTaxi\\output\\lightRun.txt");
		FileWriter fw = new FileWriter(f, true);
		PrintWriter pw = new PrintWriter(fw);
		pw.println(content2);
		pw.flush();
	} 
}
