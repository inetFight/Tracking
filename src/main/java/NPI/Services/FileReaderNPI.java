package NPI.Services;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;

import javax.swing.JOptionPane;
import javax.swing.JScrollBar;

import NPI.App.Frame;

public class FileReaderNPI {
	static boolean check = true;
	public static LinkedHashSet readFileToListByLine(String name){
		String csvFile = name;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		LinkedHashSet tmp = new LinkedHashSet<String>();
	    int cnt = 0;
	    Frame.textArea.append("Начинаю читать файл\n");
//	    Frame.textArea.update(Frame.textArea.getGraphics());
	    Frame.scroll.getViewport().setView(Frame.textArea);
		Frame.scroll.revalidate(); 
		
		Frame.scroll.repaint();	
		Frame.scroll.update(Frame.scroll.getGraphics());
		
		try {
			
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				
				
			        // use comma as separator
				String[] EH = line.split(cvsSplitBy);
				
				tmp.add(EH[0]);
				cnt += 1;
			}
	   
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
		Frame.label_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Frame.label_2.setText("" + tmp.size());
		Frame.panel.update(Frame.panel.getGraphics());
//		Frame.label_2.update(Frame.label_2.getGraphics());
//		Frame.textArea.update(Frame.textArea.getGraphics());
		
		
		  
		return tmp;
		}
}
