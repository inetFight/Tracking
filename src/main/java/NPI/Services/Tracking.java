package NPI.Services;

import java.awt.Point;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JScrollBar;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import NPI.App.Frame;
import NPI.Helpers.SmartScroller;
import NPI.Model.ResponseModel;

public class Tracking {

	public static HashMap<String, ResponseModel> getTrackingStatus(List<String> List) throws URISyntaxException, ClientProtocolException, IOException, ParseException, BadLocationException{
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HashMap<String, ResponseModel> respons = new HashMap<String, ResponseModel>();
		
		
		int countJ = (List.size()/100) + 2;
		int countI = 0;
		int countTotal = 0;
		int countforI = 0;
		
	
		
		for (int j = 1; j < countJ; j++) {		
			if((List.size() - countI) >= 100){
				countforI = j*100;
			}
			else {
				countforI =  ((j*100-100)+List.size()-countforI);
			}
//			System.out.println(countforI);
		
			
//			Frame.textArea.append("" + countforI + "\n");
			Frame.textArea.getDocument().insertString(0, "" + countforI + "\n", null);
			
//			Frame.scroll.getVerticalScrollBar().setValue(Frame.scroll.getVerticalScrollBar().getMaximum());
			Frame.scroll.getViewport().setView(Frame.textArea);
			Frame.frame.update(Frame.frame.getGraphics());
			
			
			
		URIBuilder builder = new URIBuilder("https://api.novaposhta.ua/v2.0/json/");
		//URIBuilder builder = new URIBuilder("http://webclient.sb.np.ua/data/get/container/JSON/");
		URI uri = builder.build();
		HttpPost request = new HttpPost(uri);
		String forTracking = "";
		for (int i = countI; i < countforI; i++) {
			
			if(i == (countforI-1)){
				forTracking += "{\"DocumentNumber\":\"" + List.get(countI) + "\",\"Phone\":\"\"}";
			}
			
			else {
				
				forTracking += "{\"DocumentNumber\":\"" + List.get(countI) + "\",\"Phone\":\"\"},";
			}
			
			countI +=1;
			
		}

		//System.out.println(forTracking);
		StringEntity reqEntity = new StringEntity("{\"apiKey\": \"\","
				+ "\"modelName\": \"TrackingDocument\","
				+ "\"calledMethod\": \"getStatusDocuments\","
				+ "\"methodProperties\": {"
				+ "\"Documents\": ["
				+ forTracking
				+ "],\"Language\": \"UA\"}}");
		request.setEntity(reqEntity);
		HttpResponse response = httpclient.execute(request);
		
		String otvet = EntityUtils.toString(response.getEntity(), "UTF-8");
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(otvet);

		JSONArray data = (JSONArray) jsonObject.get("data");
		Iterator i = data.iterator();
	
		 while (i.hasNext()) {
			 countTotal +=1;
			JSONObject innerObj = (JSONObject) i.next();
			String number = (String)innerObj.get("Number");
			String status = (String)innerObj.get("Status");
			String statusCode = (String)innerObj.get("StatusCode");
			
			String lastCreatedOnTheBasisDocumentType; 
			String lastCreatedOnTheBasisNumber;  
			String recipientDateTime;
			if(innerObj.get("LastCreatedOnTheBasisNumber") == null){
				lastCreatedOnTheBasisNumber = "";
			}
			else {
				lastCreatedOnTheBasisNumber = (String)innerObj.get("LastCreatedOnTheBasisNumber");  
			}
			if(innerObj.get("LastCreatedOnTheBasisDocumentType") == null){
				lastCreatedOnTheBasisDocumentType = "";
			}
			else {
				lastCreatedOnTheBasisDocumentType = (String)innerObj.get("LastCreatedOnTheBasisDocumentType");  
			}
			if(innerObj.get("RecipientDateTime") == null){
				recipientDateTime = "";
			}
			else {
				recipientDateTime = (String)innerObj.get("RecipientDateTime");  
			}
			
			ResponseModel responseModel = new ResponseModel(number, status, statusCode, lastCreatedOnTheBasisDocumentType, lastCreatedOnTheBasisNumber, recipientDateTime);
			respons.put((String)innerObj.get("Number"), responseModel);
	}
		
		}
		httpclient.close();
		
		return respons;
	}
}
