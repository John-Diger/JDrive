package server;

import java.util.Scanner;

public class InputAgent {

    public String inputOption() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public int convertInputToInteger(String input, int maxFileNumber) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("정수형을 벗어난 입력값 입니다.");
        }
        if (Integer.parseInt(input) < 1 || Integer.parseInt(input) > maxFileNumber) {
            throw new IllegalArgumentException("공유 폴더 내의 파일 인덱스를 벗어났습니다.");
        }
        return Integer.parseInt(input);
    }
}
