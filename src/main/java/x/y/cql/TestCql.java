package x.y.cql;

/**
 * author: ff
 * Date: 2015/8/10
 */
public class TestCql {

    public static void main(String[] args) {
        QuerySelect select = new QuerySelect("cache1",null,false);
        String sql  = select.eq("name1","name1").gt("col1","3").ne("col2","5")
                .OR().gt("name1","name2").OR().ne("col3","col4")
                .le("name5","6").toQueryString();
        System.out.println(sql);
    }

    private static void m1(){
        //
    }

}
