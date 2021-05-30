package Persitience;

import java.io.Serializable;

public class TestData  implements Serializable {
    int number;
    public TestData child;
    private static final long serialVersionUID = 1L;
    public TestData(int x) {
        this.number = x;
        child = null;
    }

    public void createChild() {
        this.child = new TestData(number + 1);
    }

    @Override
    public String toString() {
        return "TestData{" +
                "number=" + number +
                ", child=" + child +
                '}';
    }
}
