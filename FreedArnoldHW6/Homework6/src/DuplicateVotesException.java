
class DuplicateVotesException extends Exception{
	private String candidate;
	
	DuplicateVotesException(String candidate){
		this.candidate = candidate;
	}
	
	public String getCandidate() {
		return this.getCandidate();
	}
}
