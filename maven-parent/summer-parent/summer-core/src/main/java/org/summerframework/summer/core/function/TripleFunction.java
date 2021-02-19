package org.summerframework.summer.core.function;

@FunctionalInterface
public interface TripleFunction<T, U, R, V> {
    V apply(T t, U u, R r);
}
