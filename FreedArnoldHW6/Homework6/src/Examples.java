//Matt Freed and Dillon Arnold
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.LinkedList;

public class Examples {
	
	public Examples() {
		
		
	}
	
	 ElectionData Setup1 () throws UnknownCandidateException, DuplicateVotesException, CandidateExistsException{
		    ElectionData ED = new ElectionData();
		    // put candidates on the ballot
		    ED.addCandidate("gompei");
		    ED.addCandidate("husky");
		    ED.addCandidate("ziggy");
		    // cast votes
		    try {
		      ED.processVote("gompei", "husky", "ziggy");
		      ED.processVote("gompei", "ziggy", "husky");
		      ED.processVote("husky", "gompei", "ziggy");
		    } catch (Exception e) {}
		    return(ED);
		  }
	 ElectionData Setup2 () throws UnknownCandidateException, DuplicateVotesException, CandidateExistsException{
		    ElectionData ED1 = new ElectionData();
		    // put candidates on the ballot
		    ED1.addCandidate("gompei");
		    ED1.addCandidate("husky");
		    ED1.addCandidate("ziggy");
		    // cast votes
		    try {
		      ED1.processVote("gompei", "asdjaslkdj", "ziggy");
		      ED1.processVote("gompei", "ziggy", "husky");
		      ED1.processVote("husky", "gompei", "ziggy");
		      ED1.processVote("husky", "gompei", "ziggy");
		    } catch (Exception e) {
		    	System.out.println("error, unknown cadidate. please vote again");
		    	throw e;
		    }
		    return(ED1);
		  }
	 ElectionData Setup3 () throws UnknownCandidateException, DuplicateVotesException, CandidateExistsException{
		    ElectionData ED2 = new ElectionData();
		    // put candidates on the ballot
		    ED2.addCandidate("gompei");
		    ED2.addCandidate("husky");
		    ED2.addCandidate("ziggy");
		    // cast votes
		    try {
		      ED2.processVote("gompei", "ziggy", "ziggy");
		      ED2.processVote("husky", "gompei", "ziggy");
		    } catch (Exception e) {
		    	System.out.println("error, cadidate voted twice. please vote again");
		    	throw e;
		    }
		    return(ED2);
		  }
	 ElectionData Setup4 () throws UnknownCandidateException, DuplicateVotesException, CandidateExistsException{
		    ElectionData ED3 = new ElectionData();
		    // put candidates on the ballot
		    ED3.addCandidate("gompei");
		    ED3.addCandidate("husky");
		    ED3.addCandidate("ziggy");
		    ED3.addCandidate("ziggy");
		    // cast votes
		    try {
		      ED3.processVote("gompei", "Ziggy", "ziggy");
		      ED3.processVote("husky", "gompei", "ziggy");
		    } catch (Exception e) {
		    	throw e;
		    }
		    return(ED3);
		  }
	 ElectionData Setup5 () throws UnknownCandidateException, DuplicateVotesException, CandidateExistsException{
		    ElectionData ED4 = new ElectionData();
		    // put candidates on the ballot
		    ED4.addCandidate("gompei");
		    ED4.addCandidate("husky");
		    ED4.addCandidate("ziggy");
		    // cast votes
		    try {
		      ED4.processVote("gompei", "ziggy", "husky");
		      ED4.processVote("husky", "gompei", "ziggy");
		    } catch (Exception e) {
		    	throw e;
		    }
		    return(ED4);
		  }
	 // now run a test on a specific election
	 //setup 1 tests find winner most first votes
	  @Test
	  public void testMostFirstWinner () throws UnknownCandidateException, DuplicateVotesException, CandidateExistsException{
	    assertEquals ("gompei", Setup1().findWinnerMostFirstVotes());
	  }
	  //tests find winner most points
	  @Test
	  public void testFindWinnerMostPoints () throws UnknownCandidateException, DuplicateVotesException, CandidateExistsException{
	    assertEquals ("gompei", Setup1().findWinnerMostPoints());
	  }
	
	  @Test //tests to see if all the names are on the ballot, any order of the ballot works
	  public void testGetBallot () throws UnknownCandidateException, DuplicateVotesException, CandidateExistsException{
		  LinkedList<String> ballot = new LinkedList<>();
		  ballot.add("husky");
		  ballot.add("ziggy");
		  ballot.add("gompei");
		 
		 boolean allContained = true;
		 for (String name: Setup1().getBallot()) {
			 if (ballot.contains(name) == false) {
				allContained = false;
			 }
			
		 }
		  
	    assertTrue(allContained);
	  }
	  // test UnknownCandidateException
	  @Test(expected= UnknownCandidateException.class)
	  public void testUnknown() throws UnknownCandidateException, DuplicateVotesException, CandidateExistsException
	  {
	  		  Setup2().findWinnerMostPoints();
	  }
	  
	  
	  //test Duplicate votes
	  @Test(expected= DuplicateVotesException.class)
	  public void testDup() throws UnknownCandidateException, DuplicateVotesException, CandidateExistsException
	  {
	  		  Setup3().findWinnerMostPoints();
	  }
	  //test candidate exists
	  @Test(expected= CandidateExistsException.class)
	  public void testCand() throws UnknownCandidateException, DuplicateVotesException, CandidateExistsException
	  {
	  		  Setup4().findWinnerMostPoints();
	  }
	  //tests runoff required
	  @Test
	  public void testMostFirstWinner2 () throws UnknownCandidateException, DuplicateVotesException, CandidateExistsException{
		    assertEquals ("Runoff required", Setup5().findWinnerMostFirstVotes());
		  }
}


