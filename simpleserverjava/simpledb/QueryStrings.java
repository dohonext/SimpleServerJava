package simpledb;

public final class QueryStrings {
	private static final String userGetByIdQuery = "SELECT * from User WHERE UserId = ?";
	
	private QueryStrings () {
		
    }
	public static String getUserGetByIdQuery(){
		return userGetByIdQuery;
	}
	
}