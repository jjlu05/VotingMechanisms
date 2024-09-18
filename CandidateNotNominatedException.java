public class CandidateNotNominatedException extends Exception{
    String candName;
    public CandidateNotNominatedException(String candidateName){
        super(candidateName+" has not been nominated.");
        candName = candidateName;
    }
    public String getCandidate(){
        return candName;
    }

}
