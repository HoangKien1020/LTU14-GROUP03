package sql.table;

public class Acc {
	public static final String name = "Account";
	public static final String id = "id";
	public static final String id_type = "int(10)";
	public static final String card = "card_no";
	public static final String card_type = "varchar(50)";
	public static final String pin = "pin";
	public static final String pin_type = "varchar(255)";
	public static final String tien = "balance";
	public static final String tien_type = "decimal(13,4)";
}
/*
`id` int(10),
`card_no` varchar(50) PRIMARY KEY,
`pin` varchar(255),
`balance` decimal(13,4)
*/