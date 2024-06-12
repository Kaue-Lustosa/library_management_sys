package interfaces;

public interface InfoLoans {
	
	public abstract String getUser();
	public abstract void setUser(String user);
	public abstract String getBook();
	public abstract void setBook(String book);
	public abstract String getLoanDate();
	public abstract void setLoanDate(String loanDate);
	public abstract String getReturnDate();
	public abstract void setReturnDate(String returnDate);
	public abstract int getActiveLoans();
	public abstract void setActiveLoans(int activeLoans);
}