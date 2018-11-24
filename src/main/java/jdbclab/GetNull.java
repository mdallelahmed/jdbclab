package jdbclab;

public class GetNull {
	static String nullfiled;
public static String ColumnNull(Integer s) {
	//rs.getInt("NULLABLE")
	if(s==0) {
		nullfiled="NOT NULL";
	}else {nullfiled="NULL";}
	return nullfiled;
}
}
