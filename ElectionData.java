import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Optional;
public class ElectionData {
    //Lab 6 stubs

    HashMap<String, Votes> gameHash;
    I3VoteStrategy newStrategy;
    public ElectionData(I3VoteStrategy strategy){
        newStrategy = strategy;
        gameHash = new HashMap<>();
    }
    public void setStrategy(I3VoteStrategy strategy){
        newStrategy = strategy;

    }
    public Set<String> getCandidates(){

        return new HashSet<String>(gameHash.keySet());

        //add some sort of encapsulation here


    }

    public void nominateCandidate(String person) throws AlreadyNominatedException{
        if(gameHash.containsKey(person)){
            throw new AlreadyNominatedException(person);

        }
        else{
            gameHash.put(person,new Votes(0,0,0));
        }

    }

    public void submitVote(String first, String second, String third) throws CandidateNotNominatedException, MoreThanOnceException {
        if(!gameHash.containsKey(first)){
            throw new CandidateNotNominatedException(first);
        }
        else if(!gameHash.containsKey(second)){
            throw new CandidateNotNominatedException(second);
        }
        else if(!gameHash.containsKey(third)){
            throw new CandidateNotNominatedException(third);
        }
        else if(first.equals(second)||first.equals(third)){
            throw new MoreThanOnceException(first);
        }
        else if(third.equals(second)){
            throw new MoreThanOnceException(third);
        }
        else {
            gameHash.get(first).voteFirst();
            gameHash.get(second).voteSecond();
            gameHash.get(third).voteThird();
        }


    }

    public Optional<String> calculateWinner(){
        HashMap<String, Votes> testHash = gameHash;
        if(newStrategy.calculateWinner(testHash).equals(Optional.empty())){
            return Optional.empty();
        }
        return newStrategy.calculateWinner(testHash);


    }






}
