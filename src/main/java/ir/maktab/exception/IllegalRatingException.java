package ir.maktab.exception;

public class IllegalRatingException  extends RuntimeException{
    public IllegalRatingException(){
        super("Rating value must be between 1 and 5.");
    }
}
