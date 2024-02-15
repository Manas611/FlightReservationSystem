import java.util.HashMap;
import java.util.Scanner;

class Flight
{
    private int flightNumber;
    private String departureCity;
    private String destinationCity;
    private int availableSeats;
    
    // Constructor of Flight class to set the values of the flight 
    public Flight(int flightNumber, String departureCity, String destinationCity, int availableSeats) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.availableSeats = availableSeats;
    }

    // Getter of the flight class
    public int getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }   
}

// Class for handle the exceptions
class Reservation extends Exception
{
    Reservation(String msg)
    {
        super(msg);
    }
}

class ReservationSystem
{
    // Flightnumber, Fligth
    // Map to store the flights with FlightNumber and Flight in the map
    public HashMap<Integer,Flight> data = new HashMap<>();

    //Cid, flightNumber
    // Map to store bookings with CustomerId and FlightNumber in it
    public HashMap<Integer,Integer> bookings = new HashMap<>();

    // Method to Add the flights in the system which add the Flight in Data Named Map
    public void addingFlight(Flight f)
    {
        try {

            data.put(f.getFlightNumber(), f);
            System.out.println("Success");
            
        } catch (Exception e) {
            System.out.println("Unsuccessfull");
        }
    }

    // Method for the reservation of flights which decrease the seats available in the flight 
    // It also handle the exception when enter Incorrect flight number which is not present in the Map
    public void reservation(int CId,int FlightNum)
    {
        try {
            bookings.put(CId, FlightNum);
            Flight f = data.get(FlightNum);
            if(f == null)
            {
                throw new Reservation("Enter correct Flight Number");
            }
            else
            {
                int val = f.getAvailableSeats()-1;
                f.setAvailableSeats(val);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Method to cancel the seat which increase the seat avalability that the customer reserved
    // It also handle the exception if customer Id is not present
    public void cancelling(int CId)
    {
        try {
            int Fnum = bookings.get(CId);
            bookings.remove(CId);
            int val = data.get(Fnum).getAvailableSeats() + 1;
            Flight f = data.get(Fnum);
            f.setAvailableSeats(val);
        } catch (Exception e) {
            System.out.println("Enter the correct Customer ID for seact Cancellation");
            System.out.println();
        }
    }

    // Method to show the flight detials which show the available seats with its flight number
    public void showFlightDetails()
    {
        try {
            if(data.size() != 0)
            {
                for(Flight f:data.values())
                {
                    System.out.println(" The Flight Number is "+f.getFlightNumber() +" with available seats as "+ f.getAvailableSeats());
                    System.out.println();
                }
            }
            else
                throw new Reservation(null);
            
        } catch (Exception e) {
            System.out.println("No Flight Available");
            System.out.println();
        }
    }
}

public class FlightManagement {
    public static void main(String[] args) {

        // Creating object of the ReservationSystem class so that it can manage there other functionalities
        ReservationSystem head = new ReservationSystem();
        Scanner sc = new Scanner(System.in);
       
        boolean flag = true;

        do {
            System.out.println();
            System.out.println("********* FLIGHT RESERVATION SYSTEMM **********");
            System.out.println("1.Add flights\n2.Show available flights\n3.Reserve seat\n4.Cancel seat\n5.Exit");
            System.out.print("Enter the choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("Enter the flight Number: ");
                    int n = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter the departure city: ");
                    String departure = sc.nextLine();
                    System.out.print("Enter the destination city: ");
                    String destination = sc.nextLine();
                    System.out.print("Enter the available seats: ");
                    int seats = sc.nextInt();
                    Flight f = new Flight(n, departure, destination, seats);
                    head.addingFlight(f);
                    break;
                case 2:
                    head.showFlightDetails();
                    break;
                case 3:
                   System.out.print("Enter the flight number in which you want to reserve seat: ");
                   int flightNum = sc.nextInt();
                   System.out.print("Enter your Customer ID ");
                   int CID = sc.nextInt();
                   head.reservation(CID, flightNum);
                   break;

                case 4:
                   System.out.print("Enter your Customer ID: ");
                   int a = sc.nextInt();
                   head.cancelling(a);
                   break;

                case 5:
                   flag = false;
                   break;

                default:
                    System.out.println("Invalid input!!");
                    break;
            }
        } while (flag);
        sc.close();
    }
}
