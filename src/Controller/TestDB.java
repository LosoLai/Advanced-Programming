package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.hsqldb.Server;

import Model.Athlete;
import Model.Official;
import Model.Participant;
import Model.SuperAthlete;

public class TestDB {
	
	public static void main(String[] args) {
		
		Server hsqlServer = null;
		Connection connection = null;
		ResultSet rs = null;
		
		hsqlServer = new Server();
		hsqlServer.setLogWriter(null);
		hsqlServer.setSilent(true);
		hsqlServer.setDatabaseName(0, "OzlympicDB");
		hsqlServer.setDatabasePath(0, "file:MYDB");
		hsqlServer.start();
		Participant temp = null;
		ArrayList<Athlete> athleteList = new ArrayList<Athlete>();
		HashMap<String, Participant> participant = new HashMap<String, Participant>();
		ArrayList<Official> officialList = new ArrayList<Official>();
		
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:OzlympicDB", "sa", "123");
			connection.prepareStatement("drop table participants if exists;").execute();
			connection.prepareStatement("drop table results if exists;").execute();
			connection.prepareStatement("create table participants (id varchar(7) not null, type varchar(10) not null, extratype varchar(10), name varchar(50) not null, age integer not null, state varchar(20) not null, primary key(id));").execute();
			connection.prepareStatement("create table results (gameID varchar(10), officialID varchar(10), athleteID varchar(10), time double, points integer);").execute();
			connection.prepareStatement("insert into results values ('Test','Test','Test',200.865,6);").execute();
			
			int cy_index = 0, sp_index = 0, sw_index = 0, su_index = 0, of_index = 0;
			int age = 15;
			String id = "", type = "", name = "", state = "";
			for(int i=0 ; i<40 ; i++)
			{
				id = "Oz000" + Integer.toString(i);
				age += i;
				
				if (i%5 == 0) {
					type = Participant.CYCLIST;
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
				connection.prepareStatement("insert into participants values ('" + id + "', '" + type + "', '" + name + "', " + age + ", '" + state + "');").execute();
			}
			
			rs = connection.prepareStatement("select * from participants").executeQuery();
			connection.commit();
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
			
			System.out.println(athleteList.size());
			for (int i=0; i<32; i++){
				System.out.println(athleteList.get(i).getName());
			}
			
			} catch (SQLException e2) {
				e2.printStackTrace();
			} catch (ClassNotFoundException e2) {
				e2.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}}