package com.bookstore.catalogservice.common.cqrs;

public abstract class QueryBase<T> {
    private T identify;

    public QueryBase(T identify){
        this.identify = identify;
    }
}
