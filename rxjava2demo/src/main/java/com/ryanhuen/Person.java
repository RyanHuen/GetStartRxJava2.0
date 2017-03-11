package com.ryanhuen;

/**
 * Created by RyanHuen on 2017/3/11.
 */


public enum Person {
    INSTANCE;

    Person() {
        //单例构造,默认私有
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

class test {
    public void go() {
        //直接使用枚举类型调用单例
        Person.INSTANCE.toString();
    }
}
