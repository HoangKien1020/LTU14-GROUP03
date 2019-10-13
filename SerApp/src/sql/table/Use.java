package sql.table;

public class Use {
	public static final String name = "Users";
	public static final String id = "id";
	public static final String id_type = "int(10)";
	public static final String fn="full_name";
	public static final String fn_type="char(50)";
	public static final String add="address";
	public static final String add_type="varchar(255)";
	public static final String phone="phone";
	public static final String phone_type="char(10)";
}

/*
`id` int(10) PRIMARY KEY,
`full_name` char(50),
`address` varchar(255),
`phone` char(10)
*/