import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MostAgreeableStrategy implements I3VoteStrategy{
    @Override
    public Optional<String> calculateWinner(HashMap<String, Votes> votes) {
        double recurringVar = 0.0;
        String candName = "nothing";
        for(Map.Entry<String, Votes> candidate : votes.entrySet()){
            double a=candidate.getValue().getFirstVotes();
            double b=candidate.getValue().getSecondVotes();
            double c=candidate.getValue().getThirdVotes();
            double mostInCateg=0.0;
            if(a>b&&a>c){
                mostInCateg = a;
            }

            if(b>a&&b>c){
                mostInCateg = b;
            }

            if(c>a&&c>b){
                mostInCateg = c;
            }
            if(mostInCateg>recurringVar){
                recurringVar=mostInCateg;
                candName = candidate.getKey();
            }
            if(mostInCateg==recurringVar){
                recurringVar=mostInCateg;
                candName = candidate.getKey();

            }
        }
        if(candName.equals("nothing")) {
            return Optional.empty();
        }
        else{
            return Optional.of(candName);
        }
    }
}

