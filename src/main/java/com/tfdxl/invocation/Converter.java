package com.tfdxl.invocation;

public interface Converter<S,T> {

    /**
     * Convert the source obj of type S to target t
     * @param source
     * @return
     */
    T convert(S source);
}
