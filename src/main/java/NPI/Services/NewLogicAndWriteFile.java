package NPI.Services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import NPI.App.Frame;
import NPI.Model.CompareModel;

public class NewLogicAndWriteFile {
	
	public static void compare(ArrayList<CompareModel> list) throws UnsupportedEncodingException, FileNotFoundException{
		PrintWriter writerResultShort = new PrintWriter(
				new OutputStreamWriter(new FileOutputStream(Frame.getFilePathSave().replace(".csv", "-short.csv"), true), "UTF-8"));
		for(CompareModel obj: list){
			String toWrite = obj.getFirstNumberEW() + ";"
						   + obj.getFirstStatusEW() + ";"
						   + obj.getFirstRecipientDateTime();;
			//Проверяем 4 стадию
			if(obj.getFourthBasicTypeEW().equals("") == false){
				if(obj.getFourthBasicTypeEW().equals("CargoReturn") && obj.getFourthStatusCodeEw().equals("9")){
					toWrite = obj.getFirstNumberEW() + ";"
							+ "Возврат принят ЦСС" + ";"
							+ obj.getFourthRecipientDateTime();
				}
				else if(obj.getFourthBasicTypeEW().equals("CargoReturn") && obj.getFourthStatusEW().equals("9") == false){
					toWrite = obj.getFirstNumberEW() + ";"
							+ "Возврат на пути на ЦСС" + ";"
							+ obj.getFourthRecipientDateTime();
				}
				else if(obj.getFourthBasicTypeEW().equals("ReturnToWarehousePoltava") && obj.getFourthStatusCodeEw().equals("9")){
					toWrite = obj.getFirstNumberEW() + ";"
							+ "Возврат принят Склад Полтава" + ";"
							+ obj.getFourthRecipientDateTime();
					
				}
				else if(obj.getFourthBasicTypeEW().equals("ReturnToWarehousePoltava") && obj.getFourthStatusCodeEw().equals("9") == false){
					toWrite = obj.getFirstNumberEW() + ";"
							+ "Возврат на пути на Склад Полтава" + ";"
							+ obj.getFourthRecipientDateTime();
								
				}
				else {
					toWrite = obj.getFirstNumberEW() + ";"
							+ obj.getFourthStatusEW() + ";"
							+ obj.getFourthRecipientDateTime();
				}
				
			}
			//Проверяем 3 стадию
			if(obj.getThirdBasicTypeEW().equals("") == false){
				if(obj.getThirdBasicTypeEW().equals("CargoReturn") && obj.getThirdStatusCodeEw().equals("9")){
					toWrite = obj.getFirstNumberEW() + ";"
							+ "Возврат принят ЦСС" + ";"
							+ obj.getThirdRecipientDateTime();
				}
				else if(obj.getThirdBasicTypeEW().equals("CargoReturn") && obj.getThirdStatusEW().equals("9") == false){
					toWrite = obj.getFirstNumberEW() + ";"
							+ "Возврат на пути на ЦСС" + ";"
							+ obj.getThirdRecipientDateTime();
				}
				else if(obj.getThirdBasicTypeEW().equals("ReturnToWarehousePoltava") && obj.getThirdStatusCodeEw().equals("9")){
					toWrite = obj.getFirstNumberEW() + ";"
							+ "Возврат принят Склад Полтава" + ";"
							+ obj.getThirdRecipientDateTime();
					
				}
				else if(obj.getThirdBasicTypeEW().equals("ReturnToWarehousePoltava") && obj.getThirdStatusCodeEw().equals("9") == false){
					toWrite = obj.getFirstNumberEW() + ";"
							+ "Возврат на пути на Склад Полтава" + ";"
							+ obj.getThirdRecipientDateTime();
								
				}
				else {
					toWrite = obj.getFirstNumberEW() + ";"
							+ obj.getThirdStatusEW() + ";"
							+ obj.getThirdRecipientDateTime();
				}
				
			}
			
			//Проверяем 2 стадию
			if(obj.getSecondBasicTypeEW().equals("") == false){
				if(obj.getSecondBasicTypeEW().equals("CargoReturn") && obj.getSecondStatusCodeEw().equals("9")){
					toWrite = obj.getFirstNumberEW() + ";"
							+ "Возврат принят ЦСС" + ";"
							+ obj.getSecondRecipientDateTime();
				}
				else if(obj.getSecondBasicTypeEW().equals("CargoReturn") && obj.getSecondStatusEW().equals("9") == false){
					toWrite = obj.getFirstNumberEW() + ";"
							+ "Возврат на пути на ЦСС" + ";"
							+ obj.getSecondRecipientDateTime();
				}
				else if(obj.getSecondBasicTypeEW().equals("ReturnToWarehousePoltava") && obj.getSecondStatusCodeEw().equals("9")){
					toWrite = obj.getFirstNumberEW() + ";"
							+ "Возврат принят Склад Полтава" + ";"
							+ obj.getSecondRecipientDateTime();
					
				}
				else if(obj.getSecondBasicTypeEW().equals("ReturnToWarehousePoltava") && obj.getSecondStatusCodeEw().equals("9") == false){
					toWrite = obj.getFirstNumberEW() + ";"
							+ "Возврат на пути на Склад Полтава" + ";"
							+ obj.getSecondRecipientDateTime();
								
				}
				else {
					toWrite = obj.getFirstNumberEW() + ";"
							+ obj.getSecondStatusEW() + ";"
							+ obj.getSecondRecipientDateTime();
				}
				
			}
			
			
			writerResultShort.write(toWrite);
			writerResultShort.write(System.lineSeparator());
			writerResultShort.flush();
			
			
			
// Конец метода
		}				
		 
	}
}
