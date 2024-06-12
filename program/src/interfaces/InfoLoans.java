package interfaces;

public interface InfoLoans {
	
	public String getUser();
	public void setUser(String user);
	public String getBook();
	public void setBook(String book);
	public String getLoanDate();
	public void setLoanDate(String loanDate);
	public String getReturnDate();
	public void setReturnDate(String returnDate);
	public abstract int getActiveLoans();
	public abstract void setActiveLoans(int activeLoans);
}