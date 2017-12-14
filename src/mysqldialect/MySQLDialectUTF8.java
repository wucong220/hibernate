package mysqldialect;

import org.hibernate.dialect.MySQLInnoDBDialect;

public class MySQLDialectUTF8 extends MySQLInnoDBDialect {

	@Override
	public String getTableTypeString() {
		// TODO Auto-generated method stub
		return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
	}
	
}
