package src.ioagent;

public class InputValidator {

    public boolean userCommand(String command) {
        if (command.equals("u") || command.equals("d")) {
            return true;
        }
        throw new IllegalArgumentException("입력 값이 잘못 되었습니다. 파일 업/다운로드 명령어는 u,d 로만 입력 가능합니다.");
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
