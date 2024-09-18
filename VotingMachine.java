import java.util.Optional;
import java.util.Scanner;

public class VotingMachine {
    public VotingMachine(){}

    public static void main(String[] args) {
        ElectionData testElection = new ElectionData(new MostFirstVotesStrategy());
        Scanner keyboard = new Scanner(System.in);
        char action = 'p';
        boolean nException = false;
        boolean nBool = false;
        boolean isException = false;
        boolean sException=true;
        boolean wLoop = true;
        String notNominatedCand="";
        System.out.println(testElection.getCandidates());
        while (action != 'q') {
            System.out.println("Do you want to [n]ominate someone, " +
                    "[v]ote for someone, change winner [s]trategy, " +
                    "see who [w]on, or [q]uit?");
            String input = keyboard.nextLine();
            action = input.charAt(0);


            if (action == 'n') {
                    System.out.println("Please enter a name");

                    String nomination = keyboard.nextLine();
                    try {
                        testElection.nominateCandidate(nomination);
                    } catch (AlreadyNominatedException e) {
                        System.out.println(e.getMessage());
                        nException=true;
                    }

                    /*
                    nBool=true;
                    System.out.println("Please enter a name");

                    String nomination01 = keyboard.nextLine();
                    try {
                        testElection.nominateCandidate(nomination01);
                    } catch (AlreadyNominatedException e) {
                        System.out.println(e.getMessage());
                        nBool=false;
                    }

                    if(nBool){
                        nException=false;
                    }

                     */



            }




            else if (action == 'v') {

                System.out.println("Who is your first vote: ");

                String first = keyboard.nextLine();
                System.out.println("Who is your second vote: ");
                String second = keyboard.nextLine();
                System.out.println("Who is your third vote: ");
                String third = keyboard.nextLine();
                try {
                    testElection.submitVote(first, second, third);
                } catch (CandidateNotNominatedException n) {

                    System.out.println(n.getMessage());
                    notNominatedCand=n.getCandidate();
                    isException = true;
                } catch (MoreThanOnceException x) {
                    System.out.println(x.getMessage());
                }
                while(isException) {

                        System.out.println("Would like to nominate the candidate [y]es/[n]o:");
                        String newNominate = keyboard.nextLine();
                        char answer = newNominate.charAt(0);
                        if (answer == 'y') {

                            //System.out.println("Please enter a name");
                            //String nomination = keyboard.nextLine();

                            isException=false;
                            try {
                                testElection.nominateCandidate(notNominatedCand);
                            } catch (AlreadyNominatedException a) {
                                System.out.println(a.getMessage());
                                isException=true;
                            }
                        }
                        else{
                            isException=false;
                        }
                }
            }





            else if(action=='s'){
                {

                    System.out.println("Which strategy would you like to use? most [f]irst votes or most [a]greeable?: ");
                    String whichStrategy = keyboard.nextLine();
                    char firstOfStrat = whichStrategy.charAt(0);
                    if (firstOfStrat == 'f') {

                        testElection.setStrategy(new MostFirstVotesStrategy());
                    } else if (firstOfStrat == 'a') {

                        testElection.setStrategy((new MostAgreeableStrategy()));
                    } else {
                        System.out.println("Please enter either 'f' or 'a' ");
                    }
                }
            }





            else if(action=='w') {
                {
                    if (testElection.calculateWinner().equals(Optional.empty())) {
                        System.out.println("No clear winner under the current strategy.");
                    }
                    else{
                        wLoop=false;
                        System.out.println(testElection.calculateWinner());
                    }
                }
            }




        }
    }

}
