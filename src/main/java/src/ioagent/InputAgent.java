package src.ioagent;

import java.util.Scanner;

public class InputAgent {

    private static final Scanner scanner = new Scanner(System.in);
    private final InputValidator inputValidator;

    public InputAgent(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public String execute() {
        return scanner.nextLine();
    }

    public Long executeLong(String inputValue) {
        return inputValidator.convertInputToLong(inputValue);
    }
}
