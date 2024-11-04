import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("Enter number from 1 to 5");
	
	int num = myObj.nextInt();  // Read user input
	    
	
	switch(num) {
	case 1:
		  try{

			  Class.forName("org.postgresql.Driver");
		    	Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/airportdb1","postgres","tassos1204");
	            Statement stm = con.createStatement();
	            ResultSet rs = stm.executeQuery("SELECT t.passenger_name,B.book_date\r\n"
	            		+ "FROM tickets as t \r\n"
	            		+ "INNER JOIN flights as f\r\n"
	            		+ "ON t.flight_id=f.id \r\n"
	            		+ "INNER JOIN book as B\r\n"
	            		+ "ON B.book_ref=t.book_ref\r\n"
	            		+ "INNER JOIN ticket_sections as t2\r\n"
	            		+ "ON t2.ticket_number=t.ticket_no\r\n"
	            		+ "WHERE f.flight_number='PG34' \r\n"
	            		+ "AND f.actual_departure_time < CAST((CURRENT_TIMESTAMP-INTERVAL '1 DAY') AS text)\r\n"
	            		+ "AND t2.seat_no='21D';" );
	            while ( rs.next() ) {
	              
	                String pass_name= rs.getString(1);
	                String  bo_date = rs.getString(2);
	               
	                System.out.println( pass_name );
	                System.out.println(  bo_date );
	                

	            }
	            con.close();
	        }catch(Exception e){
	            System.out.println(e);
	        }
	    break;
	case 2:
		  try{

	            Class.forName("org.postgresql.Driver");
	            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/airportdb1","postgres","tassos1204");
	            Statement stm = con.createStatement();
	            ResultSet rs = stm.executeQuery("SELECT\r\n"
	            		+ "(SELECT a.capacity\r\n"
	            		+ "from aircrafts_data as a\r\n"
	            		+ "INNER JOIN flights as f\r\n"
	            		+ "ON f.aircraft_code=a.aircraft_code\r\n"
	            		+ "WHERE f.flight_number='PG34')-\r\n"
	            		+ "(SELECT COUNT (passenger_name)\r\n"
	            		+ "FROM tickets \r\n"
	            		+ "WHERE flight_id IN (SELECT id FROM flights WHERE flight_number='PG34')) as available_seats;");
	            while ( rs.next() ) {
	                int cap  = rs.getInt(1);
	               
	                
	                
	                System.out.println(  cap);
	           
	                
	                System.out.println();

	            }
	            con.close();
	        }catch(Exception e){
	            System.out.println(e);
	        }
	    break;
	case 3:
		  try{

	            Class.forName("org.postgresql.Driver");
	            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/airportdb1","postgres","tassos1204");
	            Statement stm = con.createStatement();
	            ResultSet rs = stm.executeQuery("SELECT id FROM flights \r\n"
	            		+"WHERE shedule_departure_time like '2022%'\r\n"
	            		+"ORDER BY (CAST(actual_departure_time AS timestamp) - CAST(shedule_departure_time AS timestamp)) desc LIMIT 5;"); 
	            		
	            while ( rs.next() ) {
	                int id  = rs.getInt(1);
	               
	                
	                
	                System.out.println(id);
	           
	                
	                System.out.println();

	            }
	            con.close();
	        }catch(Exception e){
	            System.out.println(e);
	        }
	    break;
	case 4:
		  try{

	            Class.forName("org.postgresql.Driver");
	            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/airportdb1","postgres","tassos1204");
	            Statement stm = con.createStatement();
	            ResultSet rs = stm.executeQuery(" SELECT t.passenger_name,sum(f.distance) FROM tickets as t \r\n"
	            		+"INNER JOIN flights as f \r\n"
	            		+"  ON f.id=t.flight_id\r\n"
	            		+"  WHERE actual_departure_time like '2022%'\r\n"
	            		+"GROUP BY (t.passenger_name)\r\n"
	            		+"ORDER BY (sum (f.distance)) desc LIMIT 5;"); 
	            		
	            while ( rs.next() ) {
	                String pass_name  = rs.getString(1);
	                int sum = rs.getInt(2);
	                
	        
	                System.out.println(pass_name);
	                System.out.println(sum);
	           
	                
	                System.out.println();

	            }
	            con.close();
	        }catch(Exception e){
	            System.out.println(e);
	        }
	    break;
	case 5:
		  try{

	            Class.forName("org.postgresql.Driver");
	            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/airportdb1","postgres","tassos1204");
	            Statement stm = con.createStatement();
	            ResultSet rs = stm.executeQuery(" SELECT f.arrival_airport,a.airport_city,COUNT(a.airport_city)  \r\n"
	            		+"FROM flights as f  \r\n"
	            		+" INNER JOIN airport as a  \r\n"
	            		+" ON a.airport_name=f.arrival_airport   \r\n"
	            		+"GROUP BY f.arrival_airport,a.airport_city\r\n"
	            		+"ORDER BY COUNT(a.airport_city) desc LIMIT 5;"); 
	            		
	            while ( rs.next() ) {
	                String ar_air  = rs.getString(1);
	                String air_city = rs.getString(2);
	                int a1=rs.getInt(3);
	                
	               
	                
	                
	             
	               
	                
	                
	                System.out.println(ar_air);
	                System.out.println(air_city);
	                System.out.println(a1);
	                         
	                System.out.println();

	            }
	            con.close();
	        }catch(Exception e){
	            System.out.println(e);
	        }
	    break;
	default:
	    System.out.println("wrong input");
	}
		
	myObj.close();

		

	}

}
