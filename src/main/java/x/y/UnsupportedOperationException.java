package x.y;

/**
 * author: ff
 * Date: 2015/6/22
 */
public class UnsupportedOperationException extends Exception{


    public UnsupportedOperationException(){
        super();
    }

    public UnsupportedOperationException(String msg){
        super(msg);
    }

    public UnsupportedOperationException(Throwable cause){
        super(cause);
    }

    public UnsupportedOperationException(String msg,Throwable cause){
        super(msg,cause);
    }

}
