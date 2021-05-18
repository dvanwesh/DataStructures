package com.leetcode.utils;

import lombok.Value;

@Value
public class Pair<T, U> {
    private final T key;
    private final U value;
}
