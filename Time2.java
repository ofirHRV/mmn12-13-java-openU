
/**
 * class Time2 represents time objects by hh:mm format. 
 *
 * @author Ofir Haruvi
 * @version ID 313362006
 */
public class Time2
{
    //declarations
    private int _minFromMid; //the total number of minutes of the time
    
    private final int DEFAULT_HOUR_MINUTE = 0; //the default value of minute and hour
    private final int MAX_HOUR = 23; // maximum hour allowed in hh:mm format
    private final int MAX_MIN = 59; //maximum minute allowed in hh:mm format
    private final String ADD_0 = "0"; //adding 0 to hh:mm format presentation
    private final int MAX_1_DIGIT_NUM = 9; //the highest 1 digit number 
    private final int MIN_IN_HOUR = 60; //minutes in one hour
    private final int HOUR_IN_DAY = 24; // hours in one day
    private final int MIN_IN_DAY = MIN_IN_HOUR * HOUR_IN_DAY; //minutes in one day = 1440
    
    //constructors
    /**
     * Constructs a Time2 object. 
     * Construct a new time instance with the specified hour and minute .   
     * hour should be between 0-23, otherwise it would be set to 0. 
     * minute should be between 0-59, otherwise it would be set to 0.  
     * @param h The hour of the time
     * @param m The minute of the time
     */
    public Time2 (int h, int m)
    {
        if (h >= DEFAULT_HOUR_MINUTE && h <= MAX_HOUR) //if hour between 0-23
            _minFromMid = h * MIN_IN_HOUR;
        else
            _minFromMid = DEFAULT_HOUR_MINUTE;
        if (m >= DEFAULT_HOUR_MINUTE && m <= MAX_MIN) //if minute between 0-59
            _minFromMid += m;
        else
            _minFromMid += DEFAULT_HOUR_MINUTE;
    }
    
    /**
     * Copy constructor for Time2. 
     * Constructs a time with the same variables as another time.
     * @param other The Time2 object from which to construct the new time
     */
    public Time2 (Time2 other)
    {
        _minFromMid = other._minFromMid;
    }
    
    //methods
    /**
     * Returns the hour of the time.
     * @return The hour of the time
     */
    public int getHour()
    {
        return _minFromMid / MIN_IN_HOUR;
    }
    
    /**
     * Returns the minute of the time.
     * @return The minute of the time
     */
    public int getMinute()
    {
        return _minFromMid % MIN_IN_HOUR;
    }
    
    /**
     * Sets the time's hour.
     * If the given num is not in 0-23 range,than the hour will remain unchanged.
     * @param num The new hour
     */
    public void setHour (int num)
    {
        if (num >= DEFAULT_HOUR_MINUTE && num <= MAX_HOUR)
            _minFromMid = this.getMinute() + num * MIN_IN_HOUR;
    }
    
    /**
     * Sets the time's minute.
     * If the given num is not in 0-59 range,than the minute will remain unchanged.
     * @param num The new minute.
     */
    public void setMinute (int num)
    {
        if (num >= DEFAULT_HOUR_MINUTE && num <= MAX_MIN)
            _minFromMid = this.getHour() * MIN_IN_HOUR + num;
    }
    
    /**
     * Return the amount of minutes since midnight.
     * @return amount of minutes since midnight.
     */
    public int minFromMidnight()
    {
        return (_minFromMid);
    }
    
    /**
     * Check if the received time is equal to this time.
     * @param other The time to be compared with this time
     * @return True if the received time is equal to this time. otherwise returns false
     */
    public boolean equals(Time2 other)
    {
        return (other._minFromMid == _minFromMid);
    }
    
    /**
     * Check if this time is before a received time.
     * @param other The time to check if this time is before
     * @return True if this time is before other time. otherwise returns false
     */
    public boolean before (Time2 other)
    {
        if (_minFromMid >= other._minFromMid)
            return false;
        else //if _minFromMid < other._minFromMid
            return true;
    }
    
    /**
     * Check if this time is after a received time.
     * @param other The time to check if this point is after
     * @return True if this time is after other time. otherwise returns false
     */
    public boolean after (Time2 other)
    {
        return other.before(this);            
    }
    
    /**
     * Calculates the difference (in minutes) between two times. 
     * Assumption: this time is after other time.
     * @param other  The time to check the difference with. 
     * @return The minute difference between this time and other time in minutes.
     */
    public int difference(Time2 other)
    {
        return _minFromMid - other._minFromMid ; 
    }
    
    /**
     * Returns a string represention of this time (hh:mm).
     * @return String representation of this time (hh:mm)
     */
    public String toString()
    {  
        if (getHour() <= MAX_1_DIGIT_NUM && getMinute() > MAX_1_DIGIT_NUM)
        //if there is one digit in the hour and two digits in the minute
            return (ADD_0 + getHour() + ":" + getMinute());
        else if (getHour() > MAX_1_DIGIT_NUM && getMinute() <= MAX_1_DIGIT_NUM)
        // if there are two digits in the hour and one digit in the minute 
            return (getHour() + ":" + ADD_0 + getMinute());
        else if (getHour() <= MAX_1_DIGIT_NUM && getMinute() <= MAX_1_DIGIT_NUM)
        //if there is one digit in the hour and one digit in the minute
            return (ADD_0 + getHour() + ":" + ADD_0 + getMinute());
        else  //if there are two digits in the hour and two digits in the minute
            return (getHour() + ":" + getMinute());
    }
    
    /**
     * Returns a new time which made of the given time and and requested additional minutes.
     * @param num The minutes that need be added to the time.
     * @return New time which made of the given time and and requested additional minutes
     */
    public Time2 addMinutes(int num)
    {
        int minInNew = (_minFromMid + num) % MIN_IN_DAY; //the amount of minutes in the new time, after removing full days
        int newHour = 0, newMin = 0;
        if (minInNew >= DEFAULT_HOUR_MINUTE) //if the new time is in the same day or next days
        {
            newHour = minInNew / MIN_IN_HOUR; 
            newMin = minInNew % MIN_IN_HOUR;
            return new Time2(newHour, newMin);
        }
        else // if the new time is in the previous days
        {
            minInNew = MIN_IN_DAY + minInNew;   //minutes left in the new time.
            newHour = minInNew / MIN_IN_HOUR;
            newMin = minInNew % MIN_IN_HOUR;
            return new Time2(newHour, newMin);
        }
    }
}//end of class Time2
