package x.y.service;

import x.y.*;
import x.y.UnsupportedOperationException;

import java.util.List;

/**
 * author: ff
 * Date: 2015/6/22
 */
public interface FooService {

    Foo getFoo(String fooName)throws UnsupportedOperationException;

    Foo getFoo(String fooName, String barName)throws UnsupportedOperationException;

    void insertFoo(Foo foo)throws UnsupportedOperationException;

    void updateFoo(Foo foo)throws UnsupportedOperationException;

    void updateFoo(List<Foo> foos)throws UnsupportedOperationException;

    void insertOrUpdate(Foo foo)throws UnsupportedOperationException;
}
