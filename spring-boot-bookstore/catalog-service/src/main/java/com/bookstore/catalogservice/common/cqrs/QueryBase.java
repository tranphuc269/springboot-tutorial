package com.bookstore.catalogservice.common.cqrs;

public abstract class QueryBase<T> {
    private T identity;

    public QueryBase(T identity){
        this.identity = identity;
    }
}
