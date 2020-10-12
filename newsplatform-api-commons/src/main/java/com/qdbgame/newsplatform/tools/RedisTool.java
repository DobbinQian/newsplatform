package com.qdbgame.newsplatform.tools;



public abstract class RedisTool<T>{

    public abstract boolean hasKey(String key);

    public abstract void delete(String key);

    public abstract void set(String key,T t);

    public abstract T get(String key);

}
