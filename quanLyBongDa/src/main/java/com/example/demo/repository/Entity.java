package com.example.demo.repository;

public abstract class Entity implements Cloneable{

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
