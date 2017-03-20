package histogram;

public class Histogram {

    private char printChar = '*';
    private Orientation orientation = Orientation.HORIZONTAL;
    private DynamicArray values;

    public Histogram() {
        this.values = new DynamicArray();
    }

    public Histogram(Orientation orientation) {
        this();
        this.orientation = orientation;
    }

    public Histogram(char printChar, Orientation orientation) {
        this();
        this.printChar = printChar;
        this.orientation = orientation;
    }

    public boolean hasValue() {
        return values.getLength() > 0;
    }

    public void add(int value) {
        values.add(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int iMin = 0;

        while (iMin < values.getLength() && 0 == values.get(iMin)) {
            iMin++;
        }

        if (Orientation.HORIZONTAL == orientation) {
            for (int i = iMin; i < values.getLength(); i++) {
                sb.append(i);
                sb.append(' ');
                for (int j = 0; j < values.get(i); j++) {
                    sb.append(printChar);
                }
                sb.append('\n');
            }
        } else if (Orientation.VERTICAL == orientation) {
            for (int i = values.get(values.getiMax()); i > 0; i--) {
                for (int j = iMin; j < values.getLength(); j++) {
                    if (values.get(j) >= i) {
                        sb.append(printChar);
                    } else {
                        sb.append(' ');
                    }
                    sb.append('\t');
                }
                sb.append('\n');
            }
            for (int j = iMin; j < values.getLength(); j++) {
                sb.append(j);
                sb.append('\t');
            }
        }

        return sb.toString();
    }
}
