public class MoreThanOnceException extends Exception{
    public MoreThanOnceException(String candidateName) {
        super("This candidate has already been voted for.");
    }
}
