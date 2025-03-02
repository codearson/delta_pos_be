package com.pos_main.Transformer;

public interface BaseTransformer<T, I> {

	I transform(T type);

	T reverseTransform(I type);

}
