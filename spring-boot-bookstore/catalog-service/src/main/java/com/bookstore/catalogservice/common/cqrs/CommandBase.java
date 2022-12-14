package com.bookstore.catalogservice.common.cqrs;

import lombok.Getter;

@Getter
public abstract class CommandBase<T> {
    private T identify;

    public CommandBase(T identify){
        this.identify = identify;
    }
}
