/**
 * class Flight represents a flight. 
 *
 * @author Ofir Haruvi
 * @version ID 313362006
 */
public class Flight
{
    //declarations
    String _origin; //the name of the city from which the flight takes off.
    String _destination; //the name of the city where the flight lands.
    Time1 _departure; //departure time of the flight
    int _flightDuration; //duration of flight in minutes
    int _noOfPassengers; //number of passengers on the flight
    boolean _isFull; //is the flight full?
    int _price; //flight ticket price per person

    private final int MAX_CAPACITY = 250;
    private final int DEFAULT_NO_OF_PASSENGERS = 0;
    private final int DEFAULT_FLIGHT_DURATION = 0;
    private final int DEFAULT_PRICE = 0;

    //constructors
    /**
     * Constructor for a Flight object. 
     * If the number of passengers exceeds the maximum capacity the number of
     * passengers will be set to the maxmum capacity.
     * If the number of passengers is negative the number of passengers
     * will be set to zero. 
     * If the flight duration is negative the flight duration will be 
     * set to zero. 
     * If the price is negative the price will be set to zero.
     * @param origin The city the flight leaves from.
     * @param dest The city the flight lands at.
     * @param depHour The departure hour (should be between 0-23).
     * @param depMinutes The departure minute (should be between 0-59).
     * @param durTimeMinutes The duration time in minutes(should not be negative).
     * @param noOfPass The number of passengers (should be between 0-maximum capacity).
     * @param price The price (should not be negative).
     */
    public Flight (String origin, String dest, int depHour, 
    int depMinute,int durTimeMinutes,int noOfPass, int price)
    {
        _origin = new String(origin);
        _destination = new String(dest);
        _departure = new Time1(depHour,depMinute);
        validDur(durTimeMinutes);
        validNoOfPass(noOfPass);
        validIsFull();
        validPrice(price);
    }
    
    /**
     * Copy constructor for a Flight object. Construct a Flight 
     * object with the same attributes as another Flight object.
     * @param other The Flight object from which to construct the new Flight.
     */
    public Flight (Flight other)
    {
    _origin = new String(other._origin);
    _destination = new String(other._destination);
    _departure = new Time1(other._departure); 
    _flightDuration = other._flightDuration;
    _noOfPassengers = other._noOfPassengers;
    _isFull = other._isFull; 
    _price = other._price;
    }
    
    //methods
    /**
     * Returns the flight origin.
     * @return The flight origin.
     */
    public String getOrigin()
    {
        return new String(_origin);
    }
    
    /**
     * Returns the flight destination.
     * @return The flight destination.
     */
    public String getDestination()
    {
        return new String(_destination);
    }
    
    /**
     * Returns the flight departure time.
     * @return A copy of the flight departure time.
     */
    public Time1 getDeparture()
    {
          return new Time1(_departure);
    }
    
    /**
     * Returns the flight duration time in minutes.
     * @return The flight duration.
     */
    public int getFlightDuration()
    {
        return _flightDuration;
    }
    
    /**
     * Returns the number of passengers on the flight.
     * @return The number of passengers.
     */
    public int getNoOfPassengers()
    {
        return _noOfPassengers;
    }
    
    /**
     * Returns whether the flight is full or not.
     * @return True if the flight is full. otherwise returns false
     */
    public boolean getIsFull()
    {
        return _isFull;
    }
    
    /**
     * Returns the price of the flight.
     * @return The price.
     */
    public int getPrice()
    {
        return _price;
    }
    
    /**
     * Changes the flight's destination.
     * @param dest The flight's new destination.
     */
    public void setDestination(String dest)
    {
        _destination = new String(dest);  
    }
    
    /**
     * Changes the flight's origin.
     * @param origin The flight's new origin.
     */
    public void setOrigin​(String origin)
    {
        _origin = new String(origin);
    }
    
    /**
     * Changes the flight's departure time.
     * @param departureTime The flight's new departure time.
     */
    public void setDeparture(Time1 departureTime)
    {
        _departure = new Time1(departureTime);
    }
    
    /**
     * Changes the flight's duration time. If the parameter is negative 
     * the duration time will remain unchanged.
     * @param durTimeMinutes The flight's new duration time.
     */
        public void setFlightDuration​(int durTimeMinutes)
        {
            if (durTimeMinutes >= DEFAULT_FLIGHT_DURATION)
                _flightDuration = durTimeMinutes;   
        }
        
    /**
     * Changes the number of passengers. If the parameter is negative or larger than
     * the maximum capacity the number of passengers will remain unchanged.
     * @param noOfPass The new number of passengers.
     */
    public void setNoOfPassengers​(int noOfPass)
    {
        if (noOfPass >= DEFAULT_NO_OF_PASSENGERS && noOfPass <= MAX_CAPACITY)    
            _noOfPassengers = noOfPass;  
        validIsFull();
    }
    
    /**
     * Changes the flight price. 
     * If the parameter is negative the price will remain unchanged.
     * @param price The new price.
     */
    public void setPrice​(int price)
    {
        if (price >= DEFAULT_PRICE)
            _price = price; 
    }
        
    /**
     * Return a string representation of this flight 
     * (for example: "Flight from London to Paris departs at 09:24. Flight is full.").
     * @return String representation of this flight 
     * (for example: "Flight from London to Paris departs at 09:24. Flight is full.").
     */
    public String toString()
    {
        String fullOrNot; //a String which will contain a sentence says if the flight is full or not
        if (_isFull) // if the flight is full
            fullOrNot = "Flight is full."; 
        else// if the flight isn't full
            fullOrNot = "Flight is not full.";
        return ("Flight from " + _origin + " to " + _destination + " departs at " + _departure + ". " + fullOrNot);
    }
    
    /**
     * Check if the received flight is equal to this flight. 
     * Flights are considered equal if the origin, destination and departure times are the same.
     * @param other The flight to be compared with this flight.
     * @return True if the received flight is equal to this flight. otherwise retuens false
     */
    public boolean equals(Flight other)
    {  
      return (_origin.equals(other._origin) && _destination.equals(other._destination) && _departure.equals(other._departure));  
    }
    
    /**
     * Returns the arrival time of the flight.
     * @return The arrival time of this flight.
     */
    public Time1 getArrivalTime()
    {
          return _departure.addMinutes(_flightDuration);
    }
    
    /**
     * Add passengers to this flight. 
     * If the number of passengers exceeds he maximum capacity, no passengers are added and alse is returned. 
     * If the flight becomes full, the boolean attribute describing whether the flight if full becomes true.
     * @param num The number of passengers to be added to this flight.
     * @return True if the passengers were added to the flight. otherwise returns false
     */
    public boolean addPassengers​(int num)
    {
        if (_noOfPassengers + num <= MAX_CAPACITY) 
        //if the current number of passenger plus the added amount of passengers is smaller or equal to the maxinmum capacity of the flight
        {
            _noOfPassengers = _noOfPassengers + num;
            validIsFull();
            return true;
        }
        return false;        
    }
    
    
    /**
     * Check if this flight is cheaper than another flight.
     * @param other The flight whose price is to be compared with this flight's price.
     * @return True if this flight is cheaper than the received flight. otherwise returns false
     */
    public boolean isCheaper​(Flight other)
    {
        if (_price < other._price)
            return true;
        return false;            
    }
    
    /**
     * Calculate the total price of the flight.
     * @return The total price of the flight.
     */
    public int totalPrice()
    {
       return (_noOfPassengers * _price); 
    }

    /**
     * Check if this flight lands before another flight. 
     * @param other The flight whose arrival time to be compared with this flight's arrival time.
     * @return True if this flight arrives before the received flight. otherwise retuens false
     */ 
    public boolean landsEarlier​(Flight other)
    {
        Time1 thisTimeCopy = new Time1(this._departure); // a copy of the departure time of this flight
        Time1 otherTimeCopy = new Time1(other._departure); // a copy of the departure time of other flight
        thisTimeCopy = thisTimeCopy.addMinutes(this._flightDuration); // uptdates this time copy to the time of landing of this flight
        otherTimeCopy = otherTimeCopy.addMinutes(other._flightDuration); //updates other time copy to the time of landing of other flight
        return (thisTimeCopy.before(otherTimeCopy));
    }
    
    private void validDur(int durTimeMinutes) //Initialize the duration of the flight
    {
        if (durTimeMinutes < DEFAULT_FLIGHT_DURATION)
            _flightDuration = DEFAULT_FLIGHT_DURATION;
        else
            _flightDuration = durTimeMinutes;  
    }
    
    private void validNoOfPass(int noOfPass) //Initialize the number of passengers of the flight
    {
        if (noOfPass > MAX_CAPACITY)
            _noOfPassengers = MAX_CAPACITY;    
        else if (noOfPass < DEFAULT_NO_OF_PASSENGERS)
            _noOfPassengers = DEFAULT_NO_OF_PASSENGERS; 
        else
            _noOfPassengers = noOfPass;  
    }
    
    private void validIsFull() //checks if the flight is full according to the number of passengers.
    {
        if (_noOfPassengers < MAX_CAPACITY)
            _isFull = false;
        else // if (_noOfPassengers = MAX_CAPACITY)
            _isFull = true;
    }
    
    private void validPrice(int price) //Initialize the price of the flight
    {
       if (price < DEFAULT_PRICE)
            _price = DEFAULT_PRICE;
        else
            _price = price; 
    }
} //end of class Flight
