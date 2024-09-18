import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MostFirstVotesStrategy implements I3VoteStrategy{
    @Override
    public Optional<String> calculateWinner(HashMap<String, Votes> votes) {
        double a = 0.0;
        String name = "nothing yet";
        double numOfTotalFirstVotes=0.0;
        for(Map.Entry<String, Votes> candidate : votes.entrySet()){
            numOfTotalFirstVotes += candidate.getValue().getFirstVotes();
        }
        for(Map.Entry<String, Votes> candidate : votes.entrySet()){

            Votes test = candidate.getValue();
            double b = test.getFirstVotes();
            // BEWARE INTEGER DIVISION HOMEWORK 6 DESCRIPTION

                if(b>(numOfTotalFirstVotes/2)) {
                    if(b>a) {

                        a = b;
                        name = candidate.getKey();
                    }

                }


        }
        if(name.equals("nothing yet"))
        {
            return Optional.empty();
        }
        else {
            return Optional.of(name);
        }
    }
}
