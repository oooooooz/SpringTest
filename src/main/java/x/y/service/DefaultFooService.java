package x.y.service;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import x.y.UnsupportedOperationException;
import x.y.dao.SpringJdbcDao;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * author: ff
 * Date: 2015/6/22
 */
public class DefaultFooService implements FooService {

    @Resource
    private SpringJdbcDao dao;

    public void setDao(SpringJdbcDao dao){
        this.dao = dao;
    }

    @Override
    public Foo getFoo(String fooName)throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    @Override
    public Foo getFoo(String fooName, String barName)throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    @Override
    public void insertFoo(Foo foo)throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateFoo(Foo foo)throws UnsupportedOperationException{
        //throw new UnsupportedOperationException();
        System.out.println("updateFoo invoke");
        try{
           //dao.getJdbcTemplate().update("insert into test_chf2(id) values (1)");
            System.out.println("dataSource=" + dao.getJdbcTemplate().getDataSource().getConnection());
            throw new UnsupportedOperationException();
        }catch (Exception ex){
            throw new UnsupportedOperationException(ex);
        }
    }

    @Override
    public void updateFoo(List<Foo> foos) throws UnsupportedOperationException {
        BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                System.out.println("----------i=" + i);
                ps.setObject(1,i);
            }

            @Override
            public int getBatchSize() {
                return 5;
            }
        };
        dao.getJdbcTemplate().batchUpdate("insert into test_chf2(id) " + "values(?)",setter);
        //throw new UnsupportedOperationException();
    }

    public void insertOrUpdate(Foo foo)throws UnsupportedOperationException{
        updateFoo(foo);
        //insertFoo(foo);
    }

}
