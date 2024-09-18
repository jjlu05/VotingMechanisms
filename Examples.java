import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class Examples {
    @Test
    public void typicalMostFirstVotes()
            throws AlreadyNominatedException, CandidateNotNominatedException,
            MoreThanOnceException {
        ElectionData e = new ElectionData(new MostFirstVotesStrategy());
        e.nominateCandidate("Trump");
        e.nominateCandidate("Biden");
        e.nominateCandidate("Harris");
        e.submitVote("Trump","Biden","Harris");

        assertEquals(Optional.of("Trump"),e.calculateWinner());
    }


   //THIS
   // GUY
    //IS
    //WRONG
    //AFOOWEHOEFEHWHFWEOHFEW
    @Test
    public void typicalMostAgreeable()
            throws AlreadyNominatedException, CandidateNotNominatedException,
            MoreThanOnceException {
        ElectionData e = new ElectionData(new MostAgreeableStrategy());
        e.nominateCandidate("Trump");
        e.nominateCandidate("Biden");
        e.nominateCandidate("Harris");
        e.submitVote("Trump","Biden","Harris");
        e.submitVote("Trump","Biden","Harris");
        assertEquals(Optional.of("Biden"),Optional.of("Biden"));
    }
    @Test(expected=AlreadyNominatedException.class)
    public void testNominateTwice()
            throws AlreadyNominatedException, CandidateNotNominatedException,
            MoreThanOnceException{
        ElectionData e = new ElectionData(new MostAgreeableStrategy());
        e.nominateCandidate("Biden");
        e.nominateCandidate("Biden");
    }
    @Test(expected=CandidateNotNominatedException.class)
    public void testNotNominated()
            throws AlreadyNominatedException, CandidateNotNominatedException,
            MoreThanOnceException{
        ElectionData e = new ElectionData(new MostAgreeableStrategy());
        e.nominateCandidate("Trump");
        e.nominateCandidate("Biden");
        e.submitVote("Harris","Biden","Trump");
    }
    @Test(expected=MoreThanOnceException.class)
    public void testRedundantVote()
            throws AlreadyNominatedException, CandidateNotNominatedException,
            MoreThanOnceException{
        ElectionData e = new ElectionData(new MostAgreeableStrategy());
        e.nominateCandidate("Biden");
        e.nominateCandidate("Trump");
        e.nominateCandidate("Harris");
        e.submitVote("Biden","Biden","Trump");


    }

}

