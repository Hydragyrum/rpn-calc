package com.accenture.cfe.dev.rpncalc.utils

class StackImpl<T> : Stack<T> {
  private val internalList: MutableList<T> = mutableListOf()

  override fun isEmpty(): Boolean = internalList.isEmpty()

  override fun count(): Int = internalList.size

  override fun push(item: T) {
    internalList += item
  }

  override fun pop(): T? {
    return if (isEmpty()) {
      null
    } else {
      internalList.removeAt(internalList.size - 1)
    }
  }

  override fun peek(): T? = internalList.lastOrNull()

  override fun clear() = internalList.clear()
}

fun <T> Stack<T>.push(items: Collection<T>) = items.forEach { push(it) }
