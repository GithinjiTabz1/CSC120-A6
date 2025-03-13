interface TrainRequirements {
    
    Engine getEngine();
    Car getCar(int i); 
    int getMaxCapacity();
    int seatsRemaining();
    void printManifest(); 

}
