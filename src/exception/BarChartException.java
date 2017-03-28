package exception;

public class BarChartException extends Exception {
    public BarChartException() {
        super();
    }

    public BarChartException(String message) {
        super(message);
    }

    public BarChartException(String message, Throwable cause) {
        super(message, cause);
    }

    public BarChartException(Throwable cause) {
        super(cause);
    }

    protected BarChartException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
