package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.hsqldb.Server;

import Model.Cyclist;
import Model.Official;
import Model.Participant;
import Model.Sprinter;
import Model.SuperAthlete;
import Model.Swimmer;

public class Data {
	
	private String writePath = this.getClass().getResource("gameResults.txt").getFile();
	private boolean appendToFile = true;
	private ArrayList<String[]> fileList = new ArrayList<String[]>();
	public Connection connection = null;
	private ArrayList<Participant> swimmerList = new ArrayList<Participant>();
	private ArrayList<Participant> cyclistList = new ArrayList<Participant>();
	private ArrayList<Participant> sprinterList = new ArrayList<Participant>();
	private ArrayList<Participant> superAthList = new ArrayList<Participant>();
	private ArrayList<Participant> officialList = new ArrayList<Participant>();
	public static HashMap<String, ArrayList<Participant>> participantList = new HashMap<String, ArrayList<Participant>>();

	public ArrayList<Participant> getSwimmerList() {
		return swimmerList;
	}
	public ArrayList<Participant> getCyclistList() {
		return cyclistList;
	}
	public ArrayList<Participant> getSprinterList() {
		return sprinterList;
	}
	public ArrayList<Participant> getSuperAthList() {
		return superAthList;
	}
	public ArrayList<Participant> getOfficialList() {
		return officialList;
	}
	public boolean ozlympicDB() {
		
		Server hsqlServer = null;
		ResultSet rs = null;
		Participant temp;
		
		hsqlServer = new Server();
		hsqlServer.setLogWriter(null);
		hsqlServer.setSilent(true);
		hsqlServer.setDatabaseName(0, "OzlympicDB");
		hsqlServer.setDatabasePath(0, "file:MYDB");
		hsqlServer.start();
		
		try {
			
			//Setting up connection and creating participants table
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:OzlympicDB", "sa", "123");
			connection.prepareStatement("drop table participants if exists;").execute();
			connection.prepareStatement("drop table participants if exists;").execute();
			connection.prepareStatement("create table participants (id varchar(7) not null, type varchar(10) not null, name varchar(50) not null, age integer not null, state varchar(20) not null, primary key(id));").execute();
			connection.prepareStatement("create table results (gameID varchar(10), officialID varchar(10), athleteID varchar(10), time double, points integer);").execute();
			
			for(int i=0 ; i<40 ; i++)
			{
				String id = "Oz000" + Integer.toString(i);
				String type = "", name = "", state = "";
				int age = 15 + i;
				int cy_index = 0, sp_index = 0, sw_index = 0, su_index = 0, of_index = 0;
				
				if (i%5 == 0) {
					type = Participant.CYCLIST;
					state = "VIC";
					name = type + Integer.toString(cy_index);
				}
				if (i%5 == 1) {
					type = Participant.SPRINTER;
					state = "SA";
					name = type + Integer.toString(sp_index);
				}
				if (i%5 == 2) {
					type = Participant.SWIMMER;
					state = "QLD";
					name = type + Integer.toString(sw_index);
				}
				if (i%5 == 3) {
					type = Participant.SUPERATHLETE;
					state = "WA";
					name = type + Integer.toString(su_index);
				}
				if (i%5 == 4) {
					type = Participant.OFFICIAL;
					state = "NSW";
					name = type + Integer.toString(of_index);
				}
				connection.prepareStatement("insert into participants values ('" + id + "', '" + type + "', '" + name + "', " + age + ", '" + state + "');").execute();
			}
			
			rs = connection.prepareStatement("select * from participants;").executeQuery();
			connection.commit();
			
			//Reading table data into arraylists
			while (rs.next()) {
				
			if (rs.getString("type").equalsIgnoreCase("swimmer")) {
        		temp = new Swimmer(rs.getString("id"), rs.getString("name"),rs.getInt("age"),rs.getString("state"));
       			swimmerList.add(temp);
       		}
       		else if (rs.getString("type").equalsIgnoreCase("sprinter")) {
       			temp = new Sprinter(rs.getString("id"), rs.getString("name"),rs.getInt("age"),rs.getString("state"));
       			sprinterList.add(temp);
       		}
       		else if (rs.getString("type").equalsIgnoreCase("cyclist")) {
       			temp = new Cyclist(rs.getString("id"), rs.getString("name"),rs.getInt("age"),rs.getString("state"));
       			cyclistList.add(temp);
       		}
       		else if (rs.getString("type").equalsIgnoreCase("official")) {
       			temp = new Official(rs.getString("id"), rs.getString("name"),rs.getInt("age"),rs.getString("state"));       
       			officialList.add(temp);
        	}
        	else if (rs.getString("type").equalsIgnoreCase("superathlete")) {
        		temp = new SuperAthlete(rs.getString("id"), rs.getString("name"),rs.getInt("age"),rs.getString("state"));
       			superAthList.add(temp);
       			swimmerList.add(temp);
       			sprinterList.add(temp);
       			cyclistList.add(temp);
       		}
       	}
        
        //-------------------------------------------
        
		participantList.put(Participant.SWIMMER, swimmerList);
		participantList.put(Participant.CYCLIST, cyclistList);
		participantList.put(Participant.SPRINTER, sprinterList);
		participantList.put(Participant.OFFICIAL, officialList);
			
		
			} catch (SQLException e2) {
				e2.printStackTrace();
			} catch (ClassNotFoundException e2) {
				e2.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return true;
	}
	public boolean initialParticipantList()
	{
		
		//Modified by Arion--------------------------
				Participant temp;	
				final int ID_INDEX = 0, TYPE_INDEX = 1, NAME_INDEX = 2, AGE_INDEX = 3, STATE_INDEX = 4;
				//Modified by Loso
				String rootPath = this.getClass().getResource("Participants.txt").getFile();
				File fileToBeFound = new File(rootPath);
				if(fileToBeFound == null)
					return fileNotFoundRecovery();
				//-------------------------------------------
			    BufferedReader br = null;
			    String line = "";
			    String cvsSplitBy = ",";
			    
				try {

		            br = new BufferedReader(new FileReader(fileToBeFound.getAbsolutePath()));
		            while ((line = br.readLine()) != null) {

		                // use comma as separator
		                fileList.add(line.split(cvsSplitBy));
		            }
		            
		            Set<String> unique_id = new HashSet<String>();
		            
		            for (int i=0; i<fileList.size(); i++){
		            	if (fileList.get(i).length != 5) {
		            		fileList.remove(i--);
		            	}
		            	else if (!unique_id.add(fileList.get(i)[ID_INDEX])) {
		            		fileList.remove(i--);
		            	}
		            	else if (fileList.get(i)[ID_INDEX].isEmpty() || fileList.get(i)[TYPE_INDEX].isEmpty() || fileList.get(i)[NAME_INDEX].isEmpty() || fileList.get(i)[AGE_INDEX].isEmpty() || fileList.get(i)[STATE_INDEX].isEmpty()) {
		            		fileList.remove(i--);
		            	}
		            }
		            
		            for (int i=0; i<fileList.size(); i++) {
		       
		            	if (fileList.get(i)[TYPE_INDEX].equalsIgnoreCase("swimmer")) {
		            		temp = new Swimmer(fileList.get(i)[ID_INDEX], fileList.get(i)[NAME_INDEX],Integer.parseInt(fileList.get(i)[AGE_INDEX]),fileList.get(i)[STATE_INDEX]);
		           			swimmerList.add(temp);
		           		}
		           		else if (fileList.get(i)[TYPE_INDEX].equalsIgnoreCase("sprinter")) {
		           			temp = new Sprinter(fileList.get(i)[ID_INDEX], fileList.get(i)[NAME_INDEX],Integer.parseInt(fileList.get(i)[AGE_INDEX]),fileList.get(i)[STATE_INDEX]);
		           			sprinterList.add(temp);
		           		}
		           		else if (fileList.get(i)[TYPE_INDEX].equalsIgnoreCase("cyclist")) {
		           			temp = new Cyclist(fileList.get(i)[ID_INDEX], fileList.get(i)[NAME_INDEX],Integer.parseInt(fileList.get(i)[AGE_INDEX]),fileList.get(i)[STATE_INDEX]);
		           			cyclistList.add(temp);
		           		}
		           		else if (fileList.get(i)[TYPE_INDEX].equalsIgnoreCase("official")) {
		           			temp = new Official(fileList.get(i)[ID_INDEX], fileList.get(i)[NAME_INDEX],Integer.parseInt(fileList.get(i)[AGE_INDEX]),fileList.get(i)[STATE_INDEX]);       
		           			officialList.add(temp);
		            	}
		            	else if (fileList.get(i)[TYPE_INDEX].equalsIgnoreCase("superathlete")) {
		            		temp = new SuperAthlete(fileList.get(i)[ID_INDEX], fileList.get(i)[NAME_INDEX],Integer.parseInt(fileList.get(i)[AGE_INDEX]),fileList.get(i)[STATE_INDEX]);
		           			superAthList.add(temp);
		           			swimmerList.add(temp);
		           			sprinterList.add(temp);
		           			cyclistList.add(temp);
		           		}
		           	}
		            
		            //-------------------------------------------
		            
		    		participantList.put(Participant.SWIMMER, swimmerList);
		    		participantList.put(Participant.CYCLIST, cyclistList);
		    		participantList.put(Participant.SPRINTER, sprinterList);
		    		participantList.put(Participant.OFFICIAL, officialList);		

		        } catch (FileNotFoundException e) {
		        	fileNotFoundRecovery();
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        } finally {
		            if (br != null) {
		                try {
		                    br.close();
		                } catch (IOException e) {
		                    e.printStackTrace();
		                }
		            }
		        }
				
				return true;
	}
	public void writeToFile(String text){
	    	try 
	    	{
	    	 FileWriter write = new FileWriter(writePath, appendToFile);
	    	 PrintWriter printLine = new PrintWriter(write);
	    	
	    	 printLine.printf("%s" + "%n", text);
	    	 printLine.close();
	    	}
	    	catch(IOException e) {
	    		e.printStackTrace();
	    	}
	    }
	private boolean fileNotFoundRecovery()
	{
		//setting dummy data here for testing	
		for(int i=0 ; i<6 ; i++)
		{
			//setting athlete
			//name format using (personType + id)
			String name = "ATH-" + Swimmer.SWIMMER 
							     + Integer.toString(i);
			int j = 0;
			int age = 20 + i;
			String id = "Oz000" + Integer.toString(j++);
			Participant swimmer = new Swimmer(id, name, age, "VIC");
			swimmerList.add(swimmer);
		
			id = "Oz000" + Integer.toString(j++);		
			name = "ATH-" + Cyclist.CYCLIST
				          + Integer.toString(i);
			Participant cyclist = new Cyclist(id, name, age, "VIC");
			cyclistList.add(cyclist);
			
			id = "Oz000" + Integer.toString(j++);		
			name = "ATH-" + Sprinter.SPRINTER
			              + Integer.toString(i);
			Participant sprinter = new Sprinter(id, name, age, "VIC");
			sprinterList.add(sprinter);
			
			id = "Oz000" + Integer.toString(j++);
			name = "ATH-" + SuperAthlete.SUPERATHLETE 
		                  + Integer.toString(i);
			Participant superAthlete = new SuperAthlete(id, name, age, "VIC");
			superAthList.add(superAthlete);
					
			//for implement candidate list
			//adding superAth into each list
			swimmerList.add(superAthlete);
			cyclistList.add(superAthlete);
			sprinterList.add(superAthlete);
					
			//setting offical
			id = "Oz000" + Integer.toString(j++);
			name = "OFF-" + Integer.toString(i);
			Participant offical = new Official(id, name, age, "VIC");
			officialList.add(offical);
		}
				
		participantList.put(Participant.SWIMMER, swimmerList);
		participantList.put(Participant.CYCLIST, cyclistList);
		participantList.put(Participant.SPRINTER, sprinterList);
		participantList.put(Participant.OFFICIAL, officialList);
		return true;
	}
}
