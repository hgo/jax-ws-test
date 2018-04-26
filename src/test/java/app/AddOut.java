package app;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class AddOut {

    private int value;


    public AddOut() {
    }

    public AddOut(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AddOut{" +
                "value=" + value +
                '}';
    }

    public int getValue() {
        return value;
    }
}
