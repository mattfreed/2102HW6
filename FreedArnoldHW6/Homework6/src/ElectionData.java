import java.util.LinkedList;
import java.util.Scanner;
import java.util.HashMap;


class ElectionData {
	  //LinkedList<String> ballot = new LinkedList<String>();
	  //LinkedList<Vote> votes = new LinkedList<Vote>();
	  Scanner keyboard = new Scanner(System.in);
	  //Vote voterInput;
	  
	  private HashMap<String, Integer> firstHash = new HashMap<String, Integer>();
	  private HashMap<String, Integer> secondHash = new HashMap<String, Integer>();
	  private HashMap<String, Integer> thirdHash = new HashMap<String, Integer>();

	  
	  
	  ElectionData() {
		  /*first round
		  firstHash.put("Gompei", 0);
		  firstHash.put("Husky", 0);
	    candidateVotes.put("Husky", new LinkedList<Integer>());
		 second round
		  secondHash.put("Gompei", 0);
		  secondHash.put("Husky", 0);
		  third round
		  thirdHash.put("Gompei", 0);
		  thirdHash.put("Husky", 0);
		this.ballot.add("Gompei");
	    this.ballot.add("Husky");
	    */
	  }
	  
	  //returns the ballet as a linked list
	  public LinkedList<String> getBallot(){
		  return new LinkedList<String>(firstHash.keySet());
	  }
	
	  //returns void, processes the vote by adding the votes to the correct key, takes in parameters for 1st, 2nd, and 3rd vote
	  public void processVote (String first, String second, String third) throws UnknownCandidateException, DuplicateVotesException{
		  LinkedList<String> checkNames = new LinkedList<>();
		  checkNames.add(first);
		  checkNames.add(second);
		  checkNames.add(third);
		  
		  for (String names: checkNames) {
			  
			  if (firstHash.containsKey(names) == false ) {
				  
				  throw new UnknownCandidateException(names);
			  }
		  }
		  if (first.equals(second)) {
			  throw new DuplicateVotesException(first);		  
		  }
		  else if (first.equals(third)) {
			  throw new DuplicateVotesException(first);		  
		  }
		  else if (second.equals(third)) {
			  throw new DuplicateVotesException(first);		  
		  }
		  else {
			  firstHash.replace(first, (firstHash.get(first)+1));
			  secondHash.replace(second, (secondHash.get(second)+1));
			  thirdHash.replace(third, (thirdHash.get(third)+1));
	  		}
	  }
	  //adds a new cadidate by making a new key in the hashmap
	  public void addCandidate(String candidate) throws CandidateExistsException{
		  if (firstHash.containsKey(candidate) == false){
			  firstHash.put(candidate,0);
			  secondHash.put(candidate,0);
			  thirdHash.put(candidate,0);
		  }
		  else {
			  throw new CandidateExistsException(candidate);
		  }
	  }
	  // finds the winner who has the most votes as longn as they have above 50% of the first vote total. returns winner or "Reunoff required" if no winner
	  public String findWinnerMostFirstVotes() {
		  double totalFirstVotes = 0;
		  double highestFirstVotes = 0;
		  String topCandidate50 = "start";
		  boolean tie = false;
		  
		  LinkedList<String> keys = new LinkedList<String>(firstHash.keySet());
		  
		  for (String name:keys) {
			  totalFirstVotes = totalFirstVotes + firstHash.get(name);
			  
			  if (firstHash.get(name) == highestFirstVotes) {
				  tie = true;
			  }
			  if (firstHash.get(name)> highestFirstVotes) {
				  highestFirstVotes = firstHash.get(name);
				  topCandidate50 = name;
				  tie = false;
			  }
			  
		  }
		  if (tie == true) {
			  return "Runoff required";
		  }
		  if ((highestFirstVotes/totalFirstVotes) >= .5) {
			  return topCandidate50;
		  }
		  return "Runoff required";
	  }
	  
	  //returns the winner with the most number of points
	  public String findWinnerMostPoints() {
		  LinkedList<String> keys = new LinkedList<String>(firstHash.keySet());	  
		  String topCandidatePoints = "start";
		  int mostPoints = 0;
		  
		  for(String name:keys) {
			  int total = 0;
			  total = ((firstHash.get(name)*3)+(secondHash.get(name)*2)+(thirdHash.get(name)));
			  if (total>mostPoints) {
				  mostPoints = total;
				  topCandidatePoints = name;
			  }  
		  }
		 return topCandidatePoints; 		  
	  }
	  
}
