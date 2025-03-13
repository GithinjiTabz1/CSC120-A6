interface CarRequirements {
    
    int getCapacity();
    int seatsRemaining();
    Boolean addPassenger(Passenger p);
    Boolean removePassenger(Passenger p); 
    void printManifest(); 

}
