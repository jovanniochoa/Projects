import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Bot {
	public static void main(String args[]) throws Exception{
		JDA jda = JDABuilder.createDefault("NzY3MTI5OTA3NTc4MTQyNzQx.X4tbZg.JrBP74TmNwwld4Kd__RlEFchW4c").build();
		
		jda.addEventListener(new eventing());
	}
}

class eventing extends ListenerAdapter{
	public static String a = "";
	public void onMessageReceived(MessageReceivedEvent event)
    {
		String[] userMessage = event.getMessage().getContentRaw().split(" ");
		String userName = event.getMember().getUser().getName();
		
		//hello command
		if (userMessage[0].equalsIgnoreCase("!hello")) {
			if (!event.getMember().getUser().isBot()) {
				event.getChannel().sendMessage("hello " + userName).queue();
			}
		}
		
		//total commands command
		if (userMessage[0].equalsIgnoreCase("!cmd") || userMessage[0].equalsIgnoreCase("!help")) {
			if (!event.getMember().getUser().isBot()) {
				EmbedBuilder command = new EmbedBuilder();
				command.setTitle("List of commands");
				command.setColor(Color.MAGENTA);
				command.addField("List of commands", "!cmd\n" + 
				"!help\n" + 
				"!link\n" + 
				"!hello", true);
				command.addField("What it does", "gets list of commands\n" + 
				" gets list of commands\n" + 
				" gets a sharable discord link\n" + 
				" echo's hello\n", true);
				command.addField("How to use", "!cmd\n" + 
				"!help\n" + 
				"!link\n" +
				"!hello\n", true);
				event.getChannel().sendMessage(command.build()).queue();
			}
		}
		
		//link command
		if (userMessage[0].equalsIgnoreCase("!link")) {
			if (!event.getMember().getUser().isBot()) {
				String inviteURL = event.getTextChannel().createInvite().complete().getUrl();
				event.getChannel().sendMessage("Share this link " + inviteURL).queue();
			}
		}
		
		//time command
		if (userMessage[0].equalsIgnoreCase("!time")) {
			if (!event.getMember().getUser().isBot()) {
				
				//concact the rest of the string to get a place in mind
				for(int i = 1; i < userMessage.length; i++){
					eventing.a = eventing.a + userMessage[i] + "+"; // time to
				}
				
				eventing.a = "http://api.worldweatheronline.com/premium/v1/tz.ashx?key=a20484169552454283d01042201710&q=" + eventing.a + "&format=json";
				String timeString = "";
				try {
					timeString = parsingone.parsingtwo().toString();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String localTime;
				String zone;
				JsonObject dataArray;
				JsonObject zoneArray;
				
				JsonObject object = new JsonParser().parse(timeString).getAsJsonObject();
				dataArray = object.getAsJsonObject("data");
				zoneArray = dataArray.getAsJsonArray("time_zone").get(0).getAsJsonObject();
				localTime = zoneArray.get("localtime").getAsString();
				zone = zoneArray.get("zone").getAsString();
		        event.getChannel().sendMessage("The local time is " + localTime + " and the palce is " + zone).queue();
				
			}
		}
		
		//meme command doesn't work for some reason ;-;
		/*
		if (userMessage[0].equalsIgnoreCase("!meme")) {
			if (!event.getMember().getUser().isBot()) {
				eventing.a = "https://meme-api.herokuapp.com/gimme";
				String memeString = "";
				try {
					memeString = parsingone.parsingtwo().toString();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String title;
				String url;
				
				JsonObject object = new JsonParser().parse(memeString).getAsJsonObject();
				title = object.get("title").getAsString();
				url = object.get("url").getAsString();
				event.getChannel().sendMessage(title);
			}
		}
		*/
		
		if (userMessage[0].equalsIgnoreCase("!weather")) {
			if (!event.getMember().getUser().isBot()) {
				
				eventing.a =userMessage[1]; // time to
				
				eventing.a = "http://api.openweathermap.org/data/2.5/weather?q=" + eventing.a + "&&units=imperial&&appid=fb7d4d2d104b2feed9c557cd4ac4d7c7";
				String weatherString = "";
				try {
					weatherString = parsingone.parsingtwo().toString();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String weather;
			    double temperature;
			    double tempLow;
			    double tempHigh;
			    JsonObject weatherArray;
			    JsonObject mainArray;

				JsonObject object = new JsonParser().parse(weatherString).getAsJsonObject();
				
				weatherArray = object.getAsJsonArray("weather").get(0).getAsJsonObject();
				weather = weatherArray.get("description").getAsString();
				weather = weather.substring(0, 1).toUpperCase() + weather.substring(1);
				
				
				mainArray = object.getAsJsonObject("main");
		        temperature = mainArray.get("temp").getAsDouble(); // element from temp list
		        tempLow = mainArray.get("temp_min").getAsDouble(); // element from temp_min
		        tempHigh = mainArray.get("temp_max").getAsDouble();
		        event.getChannel().sendMessage("The weather is " + weather + " with a temperature of " 
		    	+ temperature + "F and a high of " + tempHigh + "F and a low of " + tempLow + "F").queue();
				
			}
		}
		
    }
}

class parsingone{
	public static String parsingtwo()throws Exception{
		URL url = new URL (eventing.a);
		
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