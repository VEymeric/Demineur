package histogram.main;

import histogram.Histogram;
import histogram.Orientation;
import java.util.Scanner;

public class Main {

    private static final String ORIENTATION_OPTION = "-o";
    private static final Orientation DEFAULT_ORIENTATION = Orientation.HORIZONTAL;
    private static final String SYMBOL_OPTION = "-s";
    private static final char DEFAULT_SYMBOL = '*';
    private static final String VALUES_OPTION = "-v";

    private static Orientation extractOrientation(String[] args) {
        boolean readNext = false;

        for (String arg : args) {
            if (readNext) {
                return Orientation.getOrientation(arg);
            } else if (arg.equals(ORIENTATION_OPTION)) {
                readNext = true;
            }
        }

        return DEFAULT_ORIENTATION;
    }

    private static char extractSymbol(String[] args) {
        boolean readNext = false;

        for (String arg : args) {
            if (readNext) {
                return arg.charAt(0);
            } else if (arg.equals(SYMBOL_OPTION)) {
                readNext = true;
            }
        }

        return DEFAULT_SYMBOL;
    }

    private static void extractValues(String[] args, Histogram h) {
        if (null == h) {
            return;
        }

        boolean readNext = false;

        for (String arg : args) {
            if (readNext) {
                try {
                    int i = Integer.parseInt(arg);
                    if (i >= 0) {
                        h.add(i);
                    }
                } catch (NumberFormatException ex) {
                    return; // Quit when a conversion problem occurs
                }
            } else if (arg.equals(VALUES_OPTION)) {
                readNext = true;
            }
        }
    }

    private static void readValues(Histogram histo) {
        if (null == histo) {
            return;
        }

        Scanner s = new Scanner(System.in);
        String line;
        do {
            line = s.nextLine();
            try {
                int i = Integer.parseInt(line);
                if (i >= 0) {
                    histo.add(i);
                }
            } catch (NumberFormatException ex) {
            }
        } while (!line.isEmpty());
    }

    public static void main(String[] args) {
        Histogram histo = new Histogram(extractSymbol(args), extractOrientation(args));

        extractValues(args, histo);
        if (!histo.hasValue()) {
            readValues(histo);
        }

        System.out.println(histo);
    }
}
