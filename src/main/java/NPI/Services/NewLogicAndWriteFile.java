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
						   + obj.getFirstStatusCodeEw() + ";"
						   + obj.getFirstRecipientDateTime();;
			//Проверяем 4 стадию
			if(obj.getFourthStatusCodeEw().equals("") == false){
				if(obj.getThirdBasicTypeEW().equals("CargoReturn") && obj.getFourthStatusCodeEw().equals("9")){
					toWrite = obj.getFirstNumberEW() + ";"
							+ "Возврат принят ЦСС" + ";"
							+ obj.getFourthStatusCodeEw() + ";"
							+ obj.getFourthRecipientDateTime();
				}
				else if(obj.getThirdBasicTypeEW().equals("CargoReturn") && obj.getFourthStatusCodeEw().equals("9") == false){
					toWrite = obj.getFirstNumberEW() + ";"
							+ "Возврат на пути на ЦСС" + ";"
							+ obj.getFourthStatusCodeEw() + ";"
							+ obj.getFourthRecipientDateTime();
				}
				else if(obj.getThirdBasicTypeEW().equals("ReturnToWarehousePoltava") && obj.getFourthStatusCodeEw().equals("9")){
					toWrite = obj.getFirstNumberEW() + ";"
							+ "Возврат принят Склад Полтава" + ";"
							+ obj.getFourthStatusCodeEw() + ";"
							+ obj.getFourthRecipientDateTime();
					
				}
				else if(obj.getThirdBasicTypeEW().equals("ReturnToWarehousePoltava") && obj.getFourthStatusCodeEw().equals("9") == false){
					toWrite = obj.getFirstNumberEW() + ";"
							+ "Возврат на пути на Склад Полтава" + ";"
							+ obj.getFourthStatusCodeEw() + ";"
							+ obj.getFourthRecipientDateTime();
								
				}
				else {
					toWrite = obj.getFirstNumberEW() + ";"
							+ obj.getFourthStatusEW() + ";"
							+ obj.getFourthStatusCodeEw() + ";"
							+ obj.getFourthRecipientDateTime();
				}
				writerResultShort.write(toWrite);
				writerResultShort.write(System.lineSeparator());
				writerResultShort.flush();
				continue;
				
			}
			//Проверяем 3 стадию
			if(obj.getThirdStatusCodeEw().equals("") == false){
				if(obj.getSecondBasicTypeEW().equals("CargoReturn") && obj.getThirdStatusCodeEw().equals("9")){
					toWrite = obj.getFirstNumberEW() + ";"
							+ "Возврат принят ЦСС" + ";"
							+ obj.getThirdStatusCodeEw() + ";"
							+ obj.getThirdRecipientDateTime();
				}
				else if(obj.getSecondBasicTypeEW().equals("CargoReturn") && obj.getThirdStatusCodeEw().equals("9") == false){
					toWrite = obj.getFirstNumberEW() + ";"
							+ "Возврат на пути на ЦСС" + ";"
							+ obj.getThirdStatusCodeEw() + ";"
							+ obj.getThirdRecipientDateTime();
				}
				else if(obj.getSecondBasicTypeEW().equals("ReturnToWarehousePoltava") && obj.getThirdStatusCodeEw().equals("9")){
					toWrite = obj.getFirstNumberEW() + ";"
							+ "Возврат принят Склад Полтава" + ";"
							+ obj.getThirdStatusCodeEw() + ";"
							+ obj.getThirdRecipientDateTime();
					
				}
				else if(obj.getSecondBasicTypeEW().equals("ReturnToWarehousePoltava") && obj.getThirdStatusCodeEw().equals("9") == false){
					toWrite = obj.getFirstNumberEW() + ";"
							+ "Возврат на пути на Склад Полтава" + ";"
							+ obj.getThirdStatusCodeEw() + ";"
							+ obj.getThirdRecipientDateTime();
								
				}
				else {
					toWrite = obj.getFirstNumberEW() + ";"
							+ obj.getThirdStatusEW() + ";"
							+ obj.getThirdStatusCodeEw() + ";"
							+ obj.getThirdRecipientDateTime();
				}
				writerResultShort.write(toWrite);
				writerResultShort.write(System.lineSeparator());
				writerResultShort.flush();
				continue;
				
			}
			//проверяем вторую стадию
			if(obj.getSecondStatusCodeEw().equals("") == false){
				if(obj.getFirstBasicTypeEW().equals("CargoReturn") && obj.getSecondStatusCodeEw().equals("9")){
					toWrite = obj.getFirstNumberEW() + ";"
							+ "Возврат принят ЦСС" + ";"
							+ obj.getSecondStatusCodeEw() + ";"
							+ obj.getSecondRecipientDateTime();
				}
				else if(obj.getFirstBasicTypeEW().equals("CargoReturn") && obj.getSecondStatusCodeEw().equals("9") == false){
					toWrite = obj.getFirstNumberEW() + ";"
							+ "Возврат на пути на ЦСС" + ";"
							+ obj.getSecondStatusCodeEw() + ";"
							+ obj.getSecondRecipientDateTime();
				}
				else if(obj.getFirstBasicTypeEW().equals("ReturnToWarehousePoltava") && obj.getSecondStatusCodeEw().equals("9")){
					toWrite = obj.getFirstNumberEW() + ";"
							+ "Возврат принят Склад Полтава" + ";"
							+ obj.getSecondStatusCodeEw() + ";"
							+ obj.getSecondRecipientDateTime();
					
				}
				else if(obj.getFirstBasicTypeEW().equals("ReturnToWarehousePoltava") && obj.getSecondStatusCodeEw().equals("9") == false){
					toWrite = obj.getFirstNumberEW() + ";"
							+ "Возврат на пути на Склад Полтава" + ";"
							+ obj.getSecondStatusCodeEw() + ";"
							+ obj.getSecondRecipientDateTime();
								
				}
				else {
					toWrite = obj.getFirstNumberEW() + ";"
							+ obj.getSecondStatusEW() + ";"
							+ obj.getSecondStatusCodeEw() + ";"
							+ obj.getSecondRecipientDateTime();
				}
				writerResultShort.write(toWrite);
				writerResultShort.write(System.lineSeparator());
				writerResultShort.flush();
				continue;
				
			}
			
			writerResultShort.write(toWrite);
			writerResultShort.write(System.lineSeparator());
			writerResultShort.flush();
			
			
			
			
			
// Конец метода
		}				
		 
	}
}
