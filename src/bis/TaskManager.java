package bis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import client.TaxiEnum;
import client.Passenger;
import client.Taxi;

public class TaskManager {
	public void TaskDis(ArrayList<Taxi> taxiList, ArrayList<Passenger> passengerList) throws Exception{
		for(int i = 0; i < passengerList.size(); i++){
			if(passengerList.get(i).getSrc_X() == passengerList.get(i).getDst_X() && passengerList.get(i).getSrc_y() == passengerList.get(i).getDst_y()){
				String content = "Sorry, the input paraments error!";
				this.ErrorPut(content);
			}else if(passengerList.get(i).getSrc_X() >= 80 || passengerList.get(i).getSrc_X() < 0 || passengerList.get(i).getSrc_y() >= 80 || passengerList.get(i).getSrc_y() < 0 || passengerList.get(i).getDst_X() >= 80 || passengerList.get(i).getDst_X() < 0 || passengerList.get(i).getDst_y() >= 80 || passengerList.get(i).getDst_y() < 0){
				String content = "Sorry, the input paraments break bounds error!";
				this.ErrorPut(content);
			}else{
				for(int j = 0; j < taxiList.size(); j++){
					if(taxiList.get(j).getX() <= passengerList.get(i).getSrc_X() + 4 && taxiList.get(j).getY() <= passengerList.get(i).getSrc_y() + 4){
						if(taxiList.get(j).getStatus() == TaxiEnum.Pending){
							taxiList.get(j).setStatus(TaxiEnum.Servering);
							Thread thread = new TaxiTriger(passengerList.get(i).getSrc_X(), passengerList.get(i).getSrc_y(), passengerList.get(i).getDst_X(), passengerList.get(i).getDst_y(), taxiList.get(j).getX(), taxiList.get(j).getY(), taxiList.get(j).getID());
							thread.start();
						}
					}
				}
			}
		}
	}
	
	public void ErrorPut(String record) throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
		Date curDate = new Date(System.currentTimeMillis());
		String nowtime = formatter.format(curDate);
		String content = "[INFO]NowTime : " + nowtime + " Error: " + record;
		File f = new File("F:\\Java\\DiDiTaxi\\output\\Output.txt");
		PrintStream pstream = new PrintStream(f); 
		pstream.append(content);
	}
	
	public void OutPut(String record) throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
		Date curDate = new Date(System.currentTimeMillis());
		String nowtime = formatter.format(curDate);
		String content = "[INFO]NowTime : " + nowtime + " ,"+ record;
		File f = new File("F:\\Java\\DiDiTaxi\\output\\Output.txt");
		PrintStream pstream = new PrintStream(f); 
		pstream.append(content);
	}
	
	public void RecordTask(String record) throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
		Date curDate = new Date(System.currentTimeMillis());
		String nowtime = formatter.format(curDate);
		String content = "[INFO]NowTime : " + nowtime + " Passenger Request: " + record;
		File f = new File("F:\\Java\\DiDiTaxi\\recordFile\\RequestRecord.txt");
		PrintStream pstream = new PrintStream(f); 
		pstream.append(content);
	}
}
