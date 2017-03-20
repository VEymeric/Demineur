package histogram;

public enum Orientation {
    HORIZONTAL("h"),
    VERTICAL("v");

    private final String symbol;

    private String getSymbol() {
        return this.symbol;
    }

    public static Orientation getOrientation(String symbol) {
        if (symbol.equals(Orientation.VERTICAL.getSymbol())) {
            return Orientation.VERTICAL;
        }

        return Orientation.HORIZONTAL;
    }

    private Orientation(String symbol) {
        this.symbol = symbol;
    }
}
