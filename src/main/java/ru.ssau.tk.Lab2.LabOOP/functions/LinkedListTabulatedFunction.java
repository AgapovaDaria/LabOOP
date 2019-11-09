package ru.ssau.tk.Lab2.LabOOP.functions;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Serializable {
    private static final long serialVersionUID = 3418284856807134116L;
    private Node head; // первый элемент "голова"
    private Node last; //последний узел списка "хвост"
    private int count = 0;

    protected void addNode(double x, double y) {
        Node newNode = new Node();
        count++;
        if (head == null) {
            head = newNode;
            newNode.next = head;
            newNode.prev = head;
            newNode.x = x;
            newNode.y = y;
            last = newNode;
        } else {
            newNode.next = head;
            newNode.prev = last;
            head.prev = newNode;
            last.next = newNode;
            newNode.x = x;
            newNode.y = y;
            last = newNode;
        }
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) throws IllegalArgumentException {
        if (xValues.length < 2) {
            throw new IllegalArgumentException("Длина массива меньше минимальной длины");
        }
        for (int i = 0; i < xValues.length; i++) {
            this.addNode(xValues[i], yValues[i]); // используется для ссылки на текущий класс с учетом метода
        }
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("Количество шагов меньше минимального");
        }
        if (xFrom > xTo) {
            double z = xTo;
            xTo = xFrom;
            xFrom = z;
        }
        double hx = (xTo - xFrom) / (count - 1); //шаг разбиения
        double a = xFrom;
        if (xFrom != xTo) {
            for (int i = 0; i < count; i++) {
                this.addNode(a, source.apply(a));
                a += hx;
            }
        } else {
            for (int i = 0; i < count; i++) {
                this.addNode(xFrom, source.apply(xFrom));
            }
        }
    }

    public int getCount() {
        return count;
    }

    public double leftBound() {
        return head.x;
    }

    public double rightBound() {
        return last.x;
    }

    private Node getNode(int index) {
        Node a;
        if (index > (count / 2)) {
            a = last;
            for (int i = count - 1; i > 0; i--) {
                if (i == index) {
                    return a;
                } else {
                    a = a.prev;
                }
            }
        } else {
            a = head;
            for (int i = 0; i < count; i++) {
                if (i == index) {
                    return a;
                } else {
                    a = a.next;
                }
            }
        }
        return null;
    }

    public double getX(int index) throws IllegalArgumentException {
        if (index < 0 || index >= count ) {
            throw new IllegalArgumentException("Индекс не соотвествует");
        }
        return getNode(index).x;
    }

    public double getY(int index) throws IllegalArgumentException {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Индекс не соотвествует");
        }
        return getNode(index).y;
    }

    public void setY(int index, double value) throws IllegalArgumentException {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Индекс не соотвествует");
        }
        getNode(index).y = value;
    }

    public int indexOfX(double x) {
        Node a;
        a = head;
        for (int i = 0; i < count; i++) {
            if (a.x == x) {
                return i;
            } else {
                a = a.next;
            }
        }
        return -1;
    }

    public int indexOfY(double y) {
        Node a;
        a = head;
        for (int i = 0; i < count; i++) {
            if (a.y == y) {
                return i;
            } else {
                a = a.next;
            }
        }
        return -1;
    }

    public int floorIndexOfX(double x) throws IllegalArgumentException {
        Node a;
        if (x < head.x) {
            throw new IllegalArgumentException("Аргумент x меньше минимального");
        }
        a = head;
        for (int i = 0; i < count; i++) {
            if (a.x < x) {
                a = a.next;
            } else {
                return i - 1;
            }

        }
        return getCount();
    }

    private static class Node implements Serializable {
        private static final long serialVersionUID = -5387911250300571287L;
        public Node next; // ссылка на следующий узел в списке
        public Node prev; // ссылка предыдущий узел в списке
        public double x;
        public double y;
    }

    @Override
    protected double extrapolateLeft(double x) {
        if (head.x == last.x) {
            return head.y;
        }
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }


    @Override
    protected double extrapolateRight(double x) {
        if (head.x == last.x) {
            return head.y;
        }
        return interpolate(x, last.prev.x, last.x, last.prev.y, last.y);

    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (head.x == last.x) {
            return head.y;
        }
        Node left = getNode(floorIndex);
        Node right = left.next;
        return interpolate(x, left.x, right.x, left.y, right.y);
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private Node node = head;
            public void remove() {
                throw new UnsupportedOperationException("remove");
            }
            public boolean hasNext() {
                return (node != null);
            }
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Point point = new Point(node.x, node.y);
                if (node != head.prev) {
                    node = node.next;
                } else {
                    node = null;
                }
                return point;
            }
        };
    }
}
