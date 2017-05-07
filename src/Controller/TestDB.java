package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hsqldb.Server;

import Model.Participant;

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
		
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:OzlympicDB", "sa", "123");
			connection.prepareStatement("drop table participants if exists;").execute();
			connection.prepareStatement("create table participants (id varchar(7) not null, type varchar(10) not null, name varchar(50) not null, age integer not null, state varchar(20) not null, primary key(id));").execute();
			
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
			
			rs = connection.prepareStatement("select * from participants").executeQuery();
			
			while (rs.next()){
				System.out.println(rs.getString("id"));
			}
			connection.commit();
			} catch (SQLException e2) {
				e2.printStackTrace();
			} catch (ClassNotFoundException e2) {
				e2.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}}