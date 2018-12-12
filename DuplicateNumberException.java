package Phonebook;

public class DuplicateNumberException extends Throwable {
    public DuplicateNumberException(String number) {
        super(String.format("Duplicate number: [%s]",number));
    }
}
