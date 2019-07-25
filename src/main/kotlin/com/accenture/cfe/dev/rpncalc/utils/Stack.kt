package com.accenture.cfe.dev.rpncalc.utils

interface Stack<T> {
  fun isEmpty(): Boolean
  fun count(): Int
  fun push(item: T)
  fun pop(): T?
  fun peek(): T?
}
