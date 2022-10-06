package wolf.sql.insert;

public class InsertBuilder {

    public static String build(String group, int debts) {
        StringBuilder statment = new StringBuilder();

        statment.append("INSERT INTO T_GroupSelected (id_Student, firstName, lastName, id_Group)\n")
                .append("SELECT id_Student, firstName, lastName, id_Group FROM T_Student\n")
                .append("WHERE T_Student.id_Group = '").append(group)
                .append("' AND T_Student.dolgCount > ").append(debts)
                .append(";");
                
        return new String(statment);
    }
}