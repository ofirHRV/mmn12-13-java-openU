
/**
 * class Airport represents an Airport of a city
 *
 * @author Ofir Haruvi
 * @version ID 313362006
 */
public class Airport
{
    //declarations
    private Flight[] _flightsSchedule; //the flight schedule of the airport. An array of flights.
    private int _noOfFlights; //the number of flights in the the schedule of the airport
    private String _city; //The city of the airport

    private final int MAX_FLIGHTS = 200; //the maximum number of flights in a day
    private final int DEFAULT_NO_OF_FLIGHTS = 0; //the default number of flights - no flights.

    //constructors
    /**
     * Constructor for an Airport object.
     * the flight schedule is automaticly initalize to 200 cells.
     * the number of flights is automaticly initialized to 0.
     * @param city The city of the airport
     */
    public Airport(String city)
    {
        _flightsSchedule = new Flight[MAX_FLIGHTS];
        _noOfFlights = DEFAULT_NO_OF_FLIGHTS;
        _city = new String(city);   
    }
    
    //methods
    /**
     * Adds a flight to the airport schedule.
     * @param f The flight to add to the flight schedule
     * @return True if the flight added to the flight schedule. otherewise (if the flight schedule 
     * is full or nither the origin of the flight nor its destination are the city of the airport) returns false.
     */
    public boolean addFlight(Flight f)
    {
        if (_noOfFlights == MAX_FLIGHTS)
            return false;
        if (!f.getOrigin().equals(_city) && !f.getDestination().equals(_city))
            return false;
        _flightsSchedule[_noOfFlights] = new Flight(f);
        _noOfFlights++;
        return true;
    }

    /**
     * Removes a flight from the airport schedule
     * @param f The flight to be removed from the flight schedule
     * @return True if the flight removed from the flight schedule. 
     * Otherwise (if the flight doesn't exist in the flight schedule) returns false.
     */
    public boolean removeFlight(Flight f)
    {
        for (int i = 0; i<_noOfFlights; i++)
        {
            if (_flightsSchedule[i].equals(f))// find the flight to be removed
            {
                for (int j=i; j<_noOfFlights-1; j++) // moves the rest of the flights 1 step back
                    _flightsSchedule[j] = _flightsSchedule[j+1];
                _flightsSchedule[_noOfFlights-1] = null;
                _noOfFlights--;
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the earliest departure time of a flight from a given place
     * If there isn't a flight from the given place - returns null
     * @param place The departure place 
     * @return The earliest departure time of a flight from a given place
     * If there isn't a flight from the given place - returns null.
     */
    public Time1 firstFlightFromOrigin (String place)
    {
        boolean flagIsFlight = false; //a flag which will indicate if there is a flight from the given place
        Time1 firstFlightTime = null; // the earliest time of a flight from the given place
        int firstOriginIndex = 0; // the index of the first flight in the array from the given place (default)
        for (int i=0; i<_noOfFlights && flagIsFlight == false; i++) //check if there is flight from the given place
            if (_flightsSchedule[i].getOrigin().equals(place)) // if the origin of this flight is the same as the given place
            {
                firstFlightTime = new Time1(_flightsSchedule[i].getDeparture()); 
                //initialized firstFlightTime to be the first hour in the array of a flight from the given place
                flagIsFlight = true; //a flight from the given place exists
                firstOriginIndex = i; //initialize firstOriginIndex to the index of the first flight in the array from the given place
            }                
        for (int i=firstOriginIndex+1; i<_noOfFlights && flagIsFlight == true; i++) 
        //check if there is an earlier departure from the given place, 
        //starting in the index that follows the index of the first flight in the array from the given place 
            if (_flightsSchedule[i].getOrigin().equals(place)) // if the origin of the flight is the same as the given place
                if (_flightsSchedule[i].getDeparture().before(firstFlightTime))
                // if the departure of this flight is earlier than the deaprture of firstFlightTime
                    firstFlightTime = new Time1(_flightsSchedule[i].getDeparture()); //initialize firstFlightTime to be this flight's deparute time
        if (flagIsFlight == true) // if a flight from the given place exists
            return firstFlightTime;
        else // if there isn't a flight from the given place
            return null;
    }

    /**
     * Returns the number of full flights in the flights Schedule.
     * @return The number of full flights in the flights Schedule.
     */
    public int howManyFullFlights()
    {
        int counterFullFlights = 0; //count the number of full flights
        for (int i=0; i<_noOfFlights; i++)
            if (_flightsSchedule[i].getIsFull() == true)
                counterFullFlights ++ ;
        return counterFullFlights;
    }

    /**
     * Returns the number of flights between a given city and the city of the airport
     * @param city The destination/origin city of the flight
     * @return The number of flights between a given city and the city of the airport
     */
    public int howManyFlightsBetween(String city)
    {
        int counterCityFlights = 0; //count the number of flights between the given city and the city of the airport
        for (int i=0; i<_noOfFlights; i++)
            if (_flightsSchedule[i].getOrigin().equals(city) || _flightsSchedule[i].getDestination().equals(city))
                counterCityFlights++;
        return counterCityFlights;
    }

    /**
     * Returns the most expensive flight in the flights Schedule.
     * If there are no flights at the flights Schedule, returns null.
     * If there are more than one most expensive flight, ruturns the first one on the flights Schedule.
     * @return The most expensive flight in the flights Schedule.
     * if there are no flights at the flights Schedule, returns null.
     * if there are more than one most expensive flight, ruturns the first one on the flights Schedule.
     */
    public Flight mostExpensiveTicket()
    {
        if (_noOfFlights == 0)
            return null;
        Flight mostExpensive = new Flight(_flightsSchedule[0]);
        for (int i=1; i<_noOfFlights; i++)
            if (_flightsSchedule[i].getPrice() > mostExpensive.getPrice())
                mostExpensive = new Flight(_flightsSchedule[i]);
        return mostExpensive;
    }

    /**
     * Returns the longest flight in the flights Schedule.
     * If there are no flights on the flights Schedule, returns null.
     * If there are more than one longest flight, ruturns the first one on the flights Schedule.
     * @return the longest flight in the flights Schedule
     * if there are no flights at the flights Schedule, returns null.
     * if there are more than one longest flight, ruturns the first one on the flights Schedule.
     */
    public Flight longestFlight()
    {
        if (_noOfFlights == 0)
            return null;
        Flight longest = new Flight(_flightsSchedule[0]);
        for (int i=1; i<_noOfFlights; i++) 
            if (_flightsSchedule[i].getFlightDuration() > longest.getFlightDuration())
                longest = new Flight(_flightsSchedule[i]);
        return longest; 
    }

    /**
     * Returns the most popular destination on the flights Schedule in this day.
     * If there are no flights on the flights Schedule, returns null.
     * If there are more than one most popular destination, ruturns the first one on the flights Schedule.
     * @return The most popular destination on the flights Schedule in this day.
     * If there are no flights on the flights Schedule, returns null.
     * If there are more than one most popular destination, ruturns the first one on the flights Schedule.
     */
    public String mostPopularDestination()
    {
        if (_noOfFlights == 0)
            return null;
        String mostPopular = ""; //the name of the most popular destination (default)
        int counterLast = 0; //the number of flights to the last destination checked (default)
        int counterMostPopular = 0; //the number of flights to the most popular destination (default)
        for (int i=0; i<_noOfFlights; i++)
        { 
            String city = new String(_flightsSchedule[i].getDestination()); //a destination of a flight
            for (int j=0; j<_noOfFlights; j++)
                if (_flightsSchedule[j].getDestination().equals(city)) //if this flight has city as it's destination
                    counterLast ++;
            if (counterLast > counterMostPopular) //if the last destination checked is more popular than the current most popular destination
            {
                mostPopular = new String(_flightsSchedule[i].getDestination()); //mostPopular is now the last destination
                counterMostPopular = counterLast; //the number of flights to the most popular destination is now the last destination number of flights
            }       
            counterLast = 0; //reinitialize the counter of the last flight to zero
        }
        return mostPopular;
    }

    /**
     *  Return a string representation of this airport.
     *  If there are no flights on the flights Schedule, returns null.
     *  @return a string representation of this airport.
     *  If there are no flights on the flights Schedule, returns null.
     */
    public String toString()
    { 
        if (_noOfFlights == 0)
            return null;
        String fullSchedule = "The flights for airport " + _city + " today are:\n";
        for (int i=0; i<_noOfFlights; i++)
        {
            fullSchedule += _flightsSchedule[i] + "\n";
        }
        return fullSchedule;
    } 
} // end of class Airport
