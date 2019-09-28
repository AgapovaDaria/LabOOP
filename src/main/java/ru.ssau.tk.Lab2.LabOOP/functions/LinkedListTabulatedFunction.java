package ru.ssau.tk.Lab2.LabOOP.functions;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
    private Node head; // первый элемент "голова"
    private Node last; //последний узел списка "хвост"
    private int count;

    protected void addNode(double x, double y) {
        Node newNode = new Node();
        if (head == null) {
            head = new Node();
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

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        this.count = xValues.length; // Длина массива совпадает с полем
        for (int i = 1; i < count; i++) {
            this.addNode(xValues[i], yValues[i]); // используется для ссылки на текущий класс с учетом метода
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        this.count = count;
        if (xFrom > xTo) {
            xFrom = xFrom - xTo;
            xTo = xFrom + xTo;
            xFrom = -xFrom + xTo;
        }
        double hx = (xTo - xFrom) / (count + 1); //шаг разбиения
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

    public double getX(int index) {
        return getNode(index).x;
    }

    public double getY(int index) {
        return getNode(index).y;
    }

    public void setY(int index, double value) {
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

    public int floorIndexOfX(double x) {
        Node a;
        if (x < head.x) {
            return 0;
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

    private static class Node {
        Node next; // ссылка на следующий узел в списке
        Node prev; // ссылка предыдущий узел в списке
        double x;
        double y;
    }

    @Override
    protected double extrapolateLeft(double x) {
        if (head.x == last.x) {
            return head.y;
        }
        return head.y + (head.next.y - head.y) / (head.next.x - head.x) * (x - head.x);
    }


    @Override
    protected double extrapolateRight(double x) {
        if (head.x == last.x) {
            return head.y;
        }
        return last.prev.y + (last.y - last.prev.y) * (x - last.prev.x) / (last.x - last.prev.x);

    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (head.x == last.x) {
            return head.y;
        }
        Node left = getNode(floorIndex);
        Node right = left.next;
        return left.y + (right.y - left.y) / (right.x - left.x) * (x - left.x);
    }
}
