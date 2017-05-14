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

import org.hsqldb.Server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import Model.Athlete;
import Model.Game;
import Model.Official;
import Model.Participant;
import Model.SuperAthlete;

/**Author: Arion
 * The role of the Data class is to implement data reading/writing
 * 1. ozlympicDB() <Modified by Loso>
 * 2. initialParticipantList() <Modified by Loso>
 * 3. writeToFile()
 * 4. fileNotFoundRecovery() <Added by Loso>
 */

public class Data {
	
	private String writePath = this.getClass().getResource("gameResults.txt").getFile();
	private boolean appendToFile = true;
	private ArrayList<String[]> fileList = new ArrayList<String[]>();
	private static Connection connection = null;
	//Modified by Loso 10/05/17------------------------------------------
	public static HashMap<String, Participant> participant = new HashMap<String, Participant>();
	private static ArrayList<Official> officialList = new ArrayList<Official>();
	private static ArrayList<Athlete> athleteList = new ArrayList<Athlete>();
	private ArrayList<Game> gameList;
	//Modified by Loso 14/05/17------------------------------------------
	//DEFINE PARTICIPANT FORTMAT
	public final int ID_INDEX   	 = 0;
	public final int TYPE_INDEX 	 = 1;
	public final int EXTRATYPE_INDEX = 2;
	public final int NAME_INDEX 	 = 3;
	public final int AGE_INDEX 		 = 4;
	public final int STATE_INDEX 	 = 5;
	private final int[] TABLE_COL_FORTMAT = new int[]{ID_INDEX,
													  TYPE_INDEX,
													  EXTRATYPE_INDEX,
													  NAME_INDEX,
													  AGE_INDEX,
													  STATE_INDEX};
	//-------------------------------------------------------------------
	
	public ArrayList<Official> getOfficialList() {
		return officialList;
	}
	public ArrayList<Athlete> getAthletelList() {
		return athleteList;
	}
	//-------------------------------------------------------------------
	
	//Initialize DB and import data
	public static boolean ozlympicDB() {
		
		Server hsqlServer = null;
		ResultSet rs = null;
		Participant temp = null;
		
		hsqlServer = new Server();
		hsqlServer.setLogWriter(null);
		hsqlServer.setSilent(true);
		hsqlServer.setDatabaseName(0, "OzlympicDB");
		hsqlServer.setDatabasePath(0, "file:MYDB");
		hsqlServer.start();
		
		try {
			
			//Setting up connection and creating participants table
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/OzlympicDB", "sa", "");//jdbc:hsqldb:
			connection.prepareStatement("drop table participants if exists;").execute();
			connection.prepareStatement("drop table results if exists;").execute();
			connection.prepareStatement("create table participants (id varchar(7) not null, type varchar(10) not null, extratype varchar(10) , name varchar(50) not null, age integer not null, state varchar(20) not null, primary key(id));").execute();
			connection.prepareStatement("create table results (gameID varchar(10), officialID varchar(10), athleteID varchar(10), time double, points integer);").execute();
			
			int cy_index = 0, sp_index = 0, sw_index = 0, su_index = 0, of_index = 0;
			int age = 15;
			String id = ""; 
			String type = ""; 
			String name = ""; 
			String state = "";
			String extratype = "";
			for(int i=0 ; i<40 ; i++)
			{
				id = "Oz000" + Integer.toString(i);
				age += i;
				
				if (i%5 == 0) {
					type = Participant.CYCLIST;
					extratype = Participant.SPRINTER;
					state = "VIC";
					name = type + Integer.toString(cy_index++);
				}
				if (i%5 == 1) {
					type = Participant.SPRINTER;
					state = "SA";
					name = type + Integer.toString(sp_index++);
				}
				if (i%5 == 2) {
					type = Participant.SWIMMER;
					state = "QLD";
					name = type + Integer.toString(sw_index++);
				}
				if (i%5 == 3) {
					type = Participant.SUPERATHLETE;
					state = "WA";
					name = type + Integer.toString(su_index++);
				}
				if (i%5 == 4) {
					type = Participant.OFFICIAL;
					state = "NSW";
					name = type + Integer.toString(of_index++);
				}
				String str = "insert into participants values ('" + id + "', '" + type + "', '" + extratype + "', '" + name + "', " + age + ", '" + state + "');";
				extratype = "";
				connection.prepareStatement(str).execute();
			}
			
			rs = connection.prepareStatement("select * from participants;").executeQuery();
			connection.commit();
			//Reading table data into arraylists
			while (rs.next()) {
			if (rs.getString("type").equalsIgnoreCase(Participant.SUPERATHLETE)) {
	        	temp = new SuperAthlete(rs.getString("id"), rs.getString("type"), rs.getString("extratype"), rs.getString("name"),rs.getInt("age"),rs.getString("state"));
	        	athleteList.add((Athlete)temp);
	       	}
			else if(rs.getString("type").equalsIgnoreCase(Participant.OFFICIAL)) {
				temp = new Official(rs.getString("id"), rs.getString("name"),rs.getInt("age"),rs.getString("state"));
				officialList.add((Official)temp);
			}
	        else{
	        	temp = new Athlete(rs.getString("id"), rs.getString("type"), rs.getString("extratype"), rs.getString("name"),rs.getInt("age"),rs.getString("state"));
	        	athleteList.add((Athlete)temp);
	       	}
			participant.put(temp.getPersonID(), temp);
       	}
        //-------------------------------------------

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
		Participant temp = null;	
		//Modified by Loso
		String rootPath = this.getClass().getResource("Participants.txt").getFile();
		File fileToBeFound = new File(rootPath);
		
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
            	if (fileList.get(i).length != 6) {
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
            	if (fileList.get(i)[TYPE_INDEX].equalsIgnoreCase("superathlete")) {
            		temp = new SuperAthlete(fileList.get(i)[ID_INDEX], fileList.get(i)[TYPE_INDEX], fileList.get(i)[EXTRATYPE_INDEX],fileList.get(i)[NAME_INDEX],Integer.parseInt(fileList.get(i)[AGE_INDEX]),fileList.get(i)[STATE_INDEX]);
            		athleteList.add((Athlete)temp);
            	}
            	else if (fileList.get(i)[TYPE_INDEX].equalsIgnoreCase("official")) {
            		temp = new Official(fileList.get(i)[ID_INDEX], fileList.get(i)[NAME_INDEX],Integer.parseInt(fileList.get(i)[AGE_INDEX]),fileList.get(i)[STATE_INDEX]);       
            		officialList.add((Official)temp);
            	}
            	else {
            		temp = new Athlete(fileList.get(i)[ID_INDEX], fileList.get(i)[TYPE_INDEX], fileList.get(i)[EXTRATYPE_INDEX],fileList.get(i)[NAME_INDEX],Integer.parseInt(fileList.get(i)[AGE_INDEX]),fileList.get(i)[STATE_INDEX]);
            		athleteList.add((Athlete)temp);
            	}
            	participant.put(temp.getPersonID(), temp);
            }
		            
            //-------------------------------------------	

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
	public void writeToFile(ArrayList<Game> gameList){
	    	try 
	    	{
	    	 FileWriter write = new FileWriter(writePath, appendToFile);
	    	 PrintWriter printLine = new PrintWriter(write);
	    	 
	    	 this.gameList = gameList;
	    	 Game game;
	    	 
	    	 for (int i=0; i<gameList.size(); i++) {
	    		 game = gameList.get(i);
	    		 printLine.printf("%s" + "%n", game.getGameResult());
	    	 }
	    	
	    	 printLine.close();
	    	}
	    	catch(IOException e) {
	    		e.printStackTrace();
	    	}
	    }
	public void writeToDB(ArrayList<Game> gameList){
		this.gameList = gameList;
		ArrayList<Athlete> candidates;
		Participant referee;
		Game game;
		
		for (int i=0; i<gameList.size(); i++) {
			game = gameList.get(i);
			candidates = game.getCandidate();
			referee = game.getReferee();
			Athlete record;
			String str;
			Collections.sort(candidates);
			
			for (int j=0; j<candidates.size(); i++) {
				record = candidates.get(j);
				try {
					if(j == 0){
						str = "insert into results values ('" + game.getGameID() + "', '" + referee.getPersonID() + "', '" + record.getPersonID() + "', " + record.getExecuteTime() + ", 5);";
						connection.prepareStatement(str).execute();
					}
					else if(j == 1) {
						str = "insert into results values ('" + game.getGameID() + "', '" + referee.getPersonID() + "', '" + record.getPersonID() + "', " + record.getExecuteTime() + ", 3);";
						connection.prepareStatement(str).execute();
					}
					else if(j == 2) {
						str = "insert into results values ('" + game.getGameID() + "', '" + referee.getPersonID() + "', '" + record.getPersonID() + "', " + record.getExecuteTime() + ", 1);";
						connection.prepareStatement(str).execute();
					}
					else {
						str = "insert into results values ('" + game.getGameID() + "', '" + referee.getPersonID() + "', '" + record.getPersonID() + "', " + record.getExecuteTime() + ", 0);";
						connection.prepareStatement(str).execute();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	private boolean fileNotFoundRecovery()
	{
		//setting dummy data here for testing	
		for(int i=0 ; i<6 ; i++)
		{
			//setting athlete
			//name format using (personType + id)
			String name = "ATH-" + Participant.SWIMMER 
							     + Integer.toString(i);
			int j = 0;
			int age = 20 + i;
			String id = "Oz000" + Integer.toString(j++);
			Participant swimmer = new Athlete(id, name, Participant.SWIMMER, "",age, "VIC");
			athleteList.add((Athlete)swimmer);
		
			id = "Oz000" + Integer.toString(j++);		
			name = "ATH-" + Participant.CYCLIST
				          + Integer.toString(i);
			Participant cyclist = new Athlete(id, name, Participant.CYCLIST, "",age, "VIC");
			athleteList.add((Athlete)cyclist);
			
			id = "Oz000" + Integer.toString(j++);		
			name = "ATH-" + Participant.SPRINTER
			              + Integer.toString(i);
			Participant sprinter = new Athlete(id, name, Participant.CYCLIST, "",age, "VIC");
			athleteList.add((Athlete)sprinter);
			
			id = "Oz000" + Integer.toString(j++);
			name = "ATH-" + Participant.SUPERATHLETE 
		                  + Integer.toString(i);
			Participant superAthlete = new SuperAthlete(id, name, Participant.SUPERATHLETE, "",age, "VIC");
			athleteList.add((Athlete)superAthlete);
				
			//setting offical
			id = "Oz000" + Integer.toString(j++);
			name = "OFF-" + Integer.toString(i);
			Participant offical = new Official(id, name, age, "VIC");
			officialList.add((Official)offical);
			
			participant.put(swimmer.getPersonID(), swimmer);
			participant.put(cyclist.getPersonID(), cyclist);
			participant.put(sprinter.getPersonID(), sprinter);
			participant.put(superAthlete.getPersonID(), superAthlete);
			participant.put(offical.getPersonID(), offical);
		}
		return true;
	}
}
