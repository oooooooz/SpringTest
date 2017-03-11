package x.y.cql;

import org.springframework.util.CollectionUtils;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * author: ff
 * Date: 2015/8/9
 */
public class QuerySelect {

    private final StringBuilder sql;
    private final String cache;
    private final Object key;
    private final boolean refresh;
    private String dataSourceImplClassPath;
    private static final String SPACE = " ";
    private static final String LIKE = " like ";
    private static final String EQ = "=";
    private static final String GE = ">=";
    private static final String NE = "!=";
    private static final String GT = ">";
    private static final String LT = "<";
    private static final String LE = "<=";
    private   List<String> AND;
    private   List<String> OR;
    private boolean isOr = false;

    public QuerySelect(String cache,Object key,boolean refresh){
        sql = new StringBuilder("from").append(SPACE).append(cache).append(SPACE);
        this.cache = cache;
        this.key = key;
        this.refresh = refresh;
        AND =  new ArrayList<String>(4);

    }

    private void resetOr(){
        this.isOr = false;
    }

    public  QuerySelect OR(){
        this.isOr = true;
        return this;
    }

    private QuerySelect or(String str){
        if(OR == null){
            OR = new ArrayList<String>(4);
        }
        OR.add(str);
        return this;
    }


    private QuerySelect and(String str){
        if(AND == null){
            AND = new ArrayList<String>(4);
        }
        AND.add(str);
        return this;
    }

    public  QuerySelect iLike(String name,String expression){
       append(name,expression,LIKE);
        return this;
    }

    private void append(String name,String expression,String relation){
        if(isOr){
            this.or(name.concat(SPACE).concat(relation).concat(SPACE).concat(expression)).resetOr();
            return;
        }
        this.and(name.concat(SPACE).concat(relation).concat(SPACE).concat(expression));
    }

    public QuerySelect gt(String name,String expression){
        append(name,expression,GT);
        return this;
    }

    public QuerySelect lt(String name ,String expression){
        append(name,expression,LT);
        return this;
    }

    public QuerySelect eq(String name,String expression){
        append(name,expression,EQ);
        return this;
    }

    public QuerySelect ne(String name,String expression){
        append(name,expression,NE);
        return this;
    }

    public QuerySelect ge(String name,String expression){
        append(name,expression,GE);
        return this;
    }

    public QuerySelect le(String name,String expression){
        append(name,expression,LE);
        return this;
    }

    public String getCacheName(){
        return this.cache;
    }

    public boolean isRefresh(){
        return this.refresh;
    }

    public Object getKey(){
        return this.key;
    }

    public List<String> getOR(){
        return OR;
    }

    public List<String> getAND(){
        return AND;
    }

    public String toQueryString(){
        boolean bool = !CollectionUtils.isEmpty(AND) || !CollectionUtils.isEmpty(OR);
        if(bool){
            sql.append("where").append(SPACE);
        }
        if(!CollectionUtils.isEmpty(AND)){
            for(String str : AND){
                sql.append(str).append(SPACE).append("and").append(SPACE);
            }
            sql.delete(sql.length() - "and".concat(SPACE).length(), sql.length());
        }
        if(!CollectionUtils.isEmpty(OR)){
            if(sql.indexOf("and") != -1){
                sql.append(SPACE).append("or").append(SPACE);
            }
            for(String str : OR){
                sql.append(SPACE).append(str).append(SPACE).append("or").append(SPACE);
            }
            sql.delete(sql.length() - "or".concat(SPACE).length(),sql.length());
        }
        return sql.toString();
    }



}
