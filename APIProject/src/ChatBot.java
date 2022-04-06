/* This program gets the information form the user to output the weather
 given the place or zip code, as well, as it gets the location and converts
 //it to the time of that place
  * 
  * Jovanni Ochoa
 */

import org.jibble.pircbot.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChatBot
{
	//defines global integers to be called in other functions
   public static String tempLow;
   public static String tempHigh;
   public static String from;
   public static String to;
   

   public static void main(String[] args) throws Exception
   {
	   //Outputs beginning messages to get the user started
       bot ChatBot = new bot();
       ChatBot.setVerbose(true);
       ChatBot.connect("irc.freenode.net");
       ChatBot.joinChannel("#realminger");
       ChatBot.sendMessage("#realminger", "Hello! Welcome to Jovanni's API project");
       ChatBot.sendMessage("#realminger", "Please use the following formats for the project");
       ChatBot.sendMessage("#realminger", Colors.RED + "For weather, type \"!weather\", state code, and/or country code");
       ChatBot.sendMessage("#realminger", Colors.RED + "For conversion, type \"!localTime\", place");
       ChatBot.sendMessage("#realminger", Colors.RED + "To turn me off type \"close\"");
			
   }
}

//starts pircbot class to set a bot up with name etc.
class bot extends PircBot
{
public String userInput;
	//constructor 
	public static String a = "";
	public static String b = "";
	public static String wordss = " ";
	public static String wordssss = " ";
	public bot(){
       		this.setName("BestBotNaN");
	}
	//starts the msg function
	public void onMessage(String channel, String sender, String login, String hostname, String message) {
		String[] words = message.split(" ");

			//if it's weather, it parses the 
		if (words[0].contains("!weather")) {
			bot.a = words[1]; // for city

			try {
				wordss = parsing.parsing().toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//sets parameters for jsonObjects
			String weather;
		    double temperature;
		    double tempLow;
		    double tempHigh;
		    JsonObject weatherArray;
		    JsonObject mainArray;

			JsonObject object = new JsonParser().parse(wordss).getAsJsonObject();
			
			weatherArray = object.getAsJsonArray("weather").get(0).getAsJsonObject();
			weather = weatherArray.get("description").getAsString();
			weather = weather.substring(0, 1).toUpperCase() + weather.substring(1);
			
			
			mainArray = object.getAsJsonObject("main");
	        temperature = mainArray.get("temp").getAsDouble(); // element from temp list
	        tempLow = mainArray.get("temp_min").getAsDouble(); // element from temp_min
	        tempHigh = mainArray.get("temp_max").getAsDouble();
	        
	        //displays all the information I got
	        this.sendMessage("#realminger", Colors.PURPLE + "The weather is " + weather + " with a temperature of " 
	        + temperature + "F and a high of " + tempHigh + "F and a low of " + tempLow + "F");
	        
			
			
		}
		
		//if it's local time, it parses the
		if (words[0].contains("!localTime")) {
			for(int i = 1; i < words.length; i++){
			bot.a = bot.a + words[i] + "+"; // time to
			}
			
			try {
				wordss = parsingone.parsingtwo().toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//sets parameters for Json Object
			bot.a = "";
			String localTime;
			String zone;
			JsonObject dataArray;
			JsonObject zoneArray;
			
			JsonObject object = new JsonParser().parse(wordss).getAsJsonObject();
			dataArray = object.getAsJsonObject("data");
			zoneArray = dataArray.getAsJsonArray("time_zone").get(0).getAsJsonObject();
			localTime = zoneArray.get("localtime").getAsString();
			zone = zoneArray.get("zone").getAsString();
	        
			//display all the information I got
	        this.sendMessage("#realminger", Colors.PURPLE + "The local time is " + localTime + " and the palce is " + zone);
	        
		}
			
		//closes the program is I say close
		if (message.contains("close")) {
			this.disconnect();
		}
	}
}

//parsing fo the weather api
class parsing{
	public static String parsing()throws Exception{
		URL url = new URL ("http://api.openweathermap.org/data/2.5/weather?q=" + bot.a + "&&units=imperial&&appid=fb7d4d2d104b2feed9c557cd4ac4d7c7");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");
		
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		
		con.setDoOutput(true);
		
		//JSON String need to be constructed for the specific resource. 
		//We may construct complex JSON using any third-party JSON libraries such as jackson or org.json
		String jsonInputString = "{\"name\": \"Upendra\", \"job\": \"Programmer\"}";
		
		try(OutputStream os = con.getOutputStream()){
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);			
		}
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))){
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			//ChatBot.sendMessage("#realminger", response.toString());
			return response.toString();
		}
	}
}

//parsing for the time api
class parsingone{
	public static String parsingtwo()throws Exception{
		String temp1 = bot.a;
		URL url = new URL ("http://api.worldweatheronline.com/premium/v1/tz.ashx?key=a20484169552454283d01042201710&q=" + temp1 + "&format=json");
		
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("GET");
		
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		
		con.setDoOutput(true);
		
		//JSON String need to be constructed for the specific resource. 
		//We may construct complex JSON using any third-party JSON libraries such as jackson or org.json
		String jsonInputString = "{\"name\": \"Upendra\", \"job\": \"Programmer\"}";
		
		try(OutputStream os = con.getOutputStream()){
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);			
		}
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))){
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			return response.toString();
			//return temp1;
		}
	}
	
}







