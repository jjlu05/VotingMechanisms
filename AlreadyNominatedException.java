public class AlreadyNominatedException extends Exception{
    public AlreadyNominatedException(String candidateName){
        super(candidateName + "has already been nominated.");
    }
}
