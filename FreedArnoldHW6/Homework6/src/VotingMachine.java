import java.util.Scanner;

class VotingMachine {
	Scanner keyboard = new Scanner(System.in);
	ElectionData electionData;
	VotingMachine(){
		electionData = new ElectionData();
	}
	
	//prints out the candidates on the ballot
	  public void printBallot() {
		  System.out.println("The candidates are ");
		  System.out.println(electionData.getBallot());  
		  }
	  
	  
	public void screen() throws UnknownCandidateException, DuplicateVotesException {
	    this.printBallot();
	    System.out.println("Who do you want to vote for round 1?");
	    String candidate1 = keyboard.next();
	    System.out.println("You voted for " + candidate1);
	    
	    System.out.println("Who do you want to vote for round 2?");
	    String candidate2 = keyboard.next();
	    System.out.println("You voted for " + candidate2);
	    
	    System.out.println("Who do you want to vote for round 3?");
	    String candidate3 = keyboard.next();
	    System.out.println("You voted for " + candidate3);
	    
	    electionData.processVote(candidate1,candidate2,candidate3);
	  }
}
