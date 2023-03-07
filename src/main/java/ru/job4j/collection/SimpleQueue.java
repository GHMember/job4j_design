package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int sizeIn;
    int sizeOut;

    public T poll() {
        if (sizeOut == 0) {
            for (; sizeIn > 0; sizeIn--) {
                out.push(in.pop());
                sizeOut++;
            }
        }
        sizeOut--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        sizeIn++;
    }
}
