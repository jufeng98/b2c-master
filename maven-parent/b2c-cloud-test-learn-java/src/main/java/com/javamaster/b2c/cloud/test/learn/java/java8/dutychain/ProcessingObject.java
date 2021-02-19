package com.javamaster.b2c.cloud.test.learn.java.java8.dutychain;

/**
 * Created on 2019/1/7.<br/>
 *
 * @author yudong
 */
public abstract class ProcessingObject<T> {

    protected ProcessingObject<T> successor;

    public void setSuccessor(ProcessingObject<T> successor) {
        this.successor = successor;
    }

    public T handle(T input) {
        T r = handleWork(input);
        if (successor != null) {
            return successor.handle(r);
        }
        return r;
    }

    abstract protected T handleWork(T input);
}
