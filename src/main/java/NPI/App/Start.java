package NPI.App;

import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;

import org.apache.http.client.ClientProtocolException;
import org.json.simple.parser.ParseException;

import NPI.Model.CompareModel;
import NPI.Model.ResponseModel;
import NPI.Services.FileReaderNPI;
import NPI.Services.NewLogicAndWriteFile;
import NPI.Services.Tracking;

public class Start {
	public static ArrayList<CompareModel> newLogicCompare = new ArrayList<CompareModel>();
	public static void track() throws ClientProtocolException, URISyntaxException, IOException, ParseException, BadLocationException {
		
		PrintWriter writerResult = new PrintWriter(
				new OutputStreamWriter(new FileOutputStream(Frame.getFilePathSave(), true), "UTF-8"));
		NewLogicAndWriteFile.compare(newLogicCompare);
		LinkedHashSet file = FileReaderNPI.readFileToListByLine(Frame.getFilePath());
		Iterator i = file.iterator();
		List<String> firstEW = new ArrayList<String>();
		//перегоняем линкед в лист
		while (i.hasNext()) {
			String tmp = (String) i.next();
			firstEW.add(tmp);
		}
		//Нужно ли трекать дальше
		
		boolean needTracking = false;
		//Трекаем
		DefaultCaret caret = (DefaultCaret) Frame.textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
//		Frame.textArea.append("Начинаем веселиться...\n");
		Frame.textArea.getDocument().insertString(0,"Начинаем веселиться...\n",null);
		Frame.label_4.setText("1");
		Frame.label_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Frame.scroll.getViewport().setView(Frame.textArea);
		Frame.panel.update(Frame.panel.getGraphics());

		HashMap<String, ResponseModel> map1 = Tracking.getTrackingStatus(firstEW);
		//Проверяем, есть ли ЕН на основании и нужно ли дальше трекать, так же формируем лист для трекинга
		ArrayList<String> EW2 = new ArrayList<String>();
		for(Map.Entry<String, ResponseModel> entry : map1.entrySet()) {
		    String key = entry.getKey();
		    ResponseModel value = entry.getValue();
		   
		    if(value.getOnTheBasisNumber().equals("") == false){
		    	needTracking = true;
		    	EW2.add(value.getOnTheBasisNumber());
		    	
		    } 
		    
		    
		}
		
		HashMap<String, ResponseModel> map2 = null;
		ArrayList<String> EW3 = new ArrayList<String>();
		
//		System.out.println("Второй раз трекаем? " + needTracking);
	
		if(needTracking == true){
//			Frame.textArea.append("Второй раз веселимся...\n");
			Frame.textArea.getDocument().insertString(0,"Второй раз веселимся...\n",null);
			Frame.textArea.getDocument().insertString(0,"Номеров для 2 этапа: " + EW2.size() +" \n",null);
			Frame.label_4.setText("2");
			Frame.label_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
			Frame.label_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
			Frame.label_2.setText("" + EW2.size());
			Frame.scroll.getViewport().setView(Frame.textArea);	
			Frame.panel.update(Frame.panel.getGraphics());
//			System.out.println("Номеров " + EW2.size());
			 map2 = Tracking.getTrackingStatus(EW2);
			 needTracking = false;	
		for(Map.Entry<String, ResponseModel> entry : map2.entrySet()) {
			String key = entry.getKey();
			ResponseModel value = entry.getValue();
			if(value.getOnTheBasisNumber().equals("") == false){
				needTracking = true;
				EW3.add(value.getOnTheBasisNumber());
				    	
				    } 
				}
		}
		
		HashMap<String, ResponseModel> map3 = null;
		ArrayList<String> EW4 = new ArrayList<String>();
		if(needTracking){
//			Frame.textArea.append("Третий раз веселимся...\n");
			Frame.textArea.getDocument().insertString(0,"Третий раз веселимся...\n",null);
			Frame.textArea.getDocument().insertString(0,"Номеров для 3 этапа: " + EW3.size() +" \n",null);
			Frame.label_4.setText("3");
			Frame.label_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
			Frame.label_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
			Frame.label_2.setText("" + EW3.size());
			Frame.scroll.getViewport().setView(Frame.textArea);
//			Frame.scroll.revalidate(); 
//			Frame.scroll.repaint();	
			Frame.panel.update(Frame.panel.getGraphics());

		}
//		System.out.println("Третий раз трекаем? " + needTracking);
		
		if(needTracking == true){
//			System.out.println("Номеров " + EW3.size());
			 map3 = Tracking.getTrackingStatus(EW3);
			 needTracking = false;	
		for(Map.Entry<String, ResponseModel> entry : map3.entrySet()) {
			String key = entry.getKey();
			ResponseModel value = entry.getValue();
			if(value.getOnTheBasisNumber().equals("") == false){
				needTracking = true;
				EW4.add(value.getOnTheBasisNumber());
				    	
				    } 
				}
		}
		HashMap<String, ResponseModel> map4 = null;
		if(needTracking){
//			Frame.textArea.append("Четвертый раз веселимся...\n");
			Frame.textArea.getDocument().insertString(0,"Четвертый раз веселимся...\n",null);
			Frame.textArea.getDocument().insertString(0,"Номеров для 4 этапа: " + EW4.size() +" \n",null);
			Frame.label_4.setText("4");
			Frame.label_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
			Frame.label_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
			Frame.label_2.setText("" + EW4.size());
			Frame.scroll.getViewport().setView(Frame.textArea);

			Frame.panel.update(Frame.panel.getGraphics());
	
		}
//		System.out.println("Четвертый раз трекаем? " + needTracking);
		
		if(needTracking == true){
//			System.out.println("Номеров " + EW4.size());
			 map4 = Tracking.getTrackingStatus(EW4);

			
		}
		
		
//		Frame.textArea.append("Начинаем колдовство\nСобираем файл\n");
		Frame.textArea.getDocument().insertString(0,"Собираем файл\nНачинаем колдовство\n",null);
		Frame.scroll.getViewport().setView(Frame.textArea);

		Frame.scroll.update(Frame.scroll.getGraphics());
//		Frame.textArea.update(Frame.textArea.getGraphics());
		
		for(Map.Entry<String, ResponseModel> entry : map1.entrySet()) {
			String key = entry.getKey();
			ResponseModel value = entry.getValue();
			CompareModel compare = new CompareModel(value.getNumber(), value.getStatus(), value.getStatusCode(), value.getOnTheBasisDocumentType(), value.getOnTheBasisNumber(), value.getRecipientDateTime(), 
													"", "", "", "", "", 
													"", "", "", "", "", 
													"", "", "", "", "");
			String map2r = ";;;;;;";
			String map3r = ";;;;;;";
			String map4r = ";;;;;;";
			String BasisNumber1 = null;
			String BasisNumber2 = null;
			String BasisNumber3 = null;
			if(value.getOnTheBasisNumber().equals("") == false){
				BasisNumber1 = value.getOnTheBasisNumber();
				map2r =  map2.get(BasisNumber1).getStatus() + ";"
					  + map2.get(BasisNumber1).getStatusCode() + ";"
					  + map2.get(BasisNumber1).getOnTheBasisDocumentType() + ";"
					  + map2.get(BasisNumber1).getOnTheBasisNumber() + ";"
					  + map2.get(BasisNumber1).getRecipientDateTime() + ";";
				//Для новой логики хуярим все в объект
				compare.setSecondStatusEW(map2.get(BasisNumber1).getStatus());
				compare.setSecondStatusCodeEw(map2.get(BasisNumber1).getStatusCode());
				compare.setSecondRecipientDateTime(map2.get(BasisNumber1).getRecipientDateTime());
				compare.setSecondBasicTypeEW(map2.get(BasisNumber1).getOnTheBasisDocumentType());
				compare.setSecondBasicNubmerEw(map2.get(BasisNumber1).getOnTheBasisNumber());
				if(map2.get(BasisNumber1).getOnTheBasisNumber().equals("") == false){
					BasisNumber2 = map2.get(BasisNumber1).getOnTheBasisNumber();
					map3r =  map3.get(BasisNumber2).getStatus() + ";"
							  + map3.get(BasisNumber2).getStatusCode() + ";"
							  + map3.get(BasisNumber2).getOnTheBasisDocumentType() + ";"
							  + map3.get(BasisNumber2).getOnTheBasisNumber() + ";"
					 		  + map3.get(BasisNumber2).getRecipientDateTime() + ";";
				//Для новой логики хуярим дальше
					compare.setThirdStatusEW(map3.get(BasisNumber2).getStatus());
					compare.setThirdStatusCodeEw(map3.get(BasisNumber2).getStatusCode());
					compare.setThirdRecipientDateTime(map3.get(BasisNumber2).getRecipientDateTime());
					compare.setThirdBasicTypeEW(map3.get(BasisNumber2).getOnTheBasisDocumentType());
					compare.setThirdBasicNubmerEw(map3.get(BasisNumber2).getOnTheBasisNumber());
					if(map3.get(BasisNumber2).getOnTheBasisNumber().equals("") == false){
						BasisNumber3 = map3.get(BasisNumber2).getOnTheBasisNumber();
					
						map4r =  map4.get(BasisNumber3).getStatus() + ";"
								  + map4.get(BasisNumber3).getStatusCode() + ";"
								  + map4.get(BasisNumber3).getOnTheBasisDocumentType() + ";"
								  + map4.get(BasisNumber3).getOnTheBasisNumber() + ";"
								  + map4.get(BasisNumber3).getRecipientDateTime() + ";";
						//Для новой логики хуярим дальше
						compare.setFourthStatusEW(map4.get(BasisNumber3).getStatus());
						compare.setFourthStatusCodeEw(map4.get(BasisNumber3).getStatusCode());
						compare.setFourthRecipientDateTime(map4.get(BasisNumber3).getRecipientDateTime());
						compare.setFourthBasicTypeEW(map4.get(BasisNumber3).getOnTheBasisDocumentType());
						compare.setFourthBasicNubmerEw(map4.get(BasisNumber3).getOnTheBasisNumber());
						
					}
				}
			}
			
			writerResult.write(value.getNumber() + ";"
							+ value.getStatus() + ";"
							+ value.getStatusCode() + ";"
							+ value.getOnTheBasisDocumentType() + ";"
							+ value.getOnTheBasisNumber() + ";"
							+ value.getRecipientDateTime() + ";"
							+ map2r
							+ map3r
							+ map4r);
							
			writerResult.write(System.getProperty("line.separator"));
			writerResult.flush();
			newLogicCompare.add(compare);
			
		}
		
		writerResult.close();
		
		CompareModel compare = new CompareModel("20600000467159", "Відправлення отримано", "9", "additional", "123", "123123", 
				"", "", "", "", "", 
				"", "", "", "", "", 
				"", "9", "CargoReturn", "", "");
		newLogicCompare.add(compare);
		NewLogicAndWriteFile.compare(newLogicCompare);
//		Frame.textArea.append("Закончили колдовство!\n");
		Frame.textArea.getDocument().insertString(0,"Закончили колдовство!\n",null);
//		Frame.textArea.update(Frame.textArea.getGraphics());
		Frame.btnNewButton.setEnabled(true);
		Frame.button.setEnabled(true);
		Frame.button_1.setEnabled(true);
		Frame.label_2.setFont(new Font("Tahoma", Font.ITALIC, 11));
		Frame.label_4.setFont(new Font("Tahoma", Font.ITALIC, 11));
		Frame.label_2.setText("Трекинг не запущен");
		Frame.label_4.setText("Трекинг не запущен");
		Frame.scroll.getViewport().setView(Frame.textArea);
		Frame.scroll.update(Frame.scroll.getGraphics());
		
	}
	

}
