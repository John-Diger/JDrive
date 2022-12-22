package src.ioagent;

public class InputValidator {

    public boolean userCommand(String command) {
        if (command.equals("u") || command.equals("d")) {
            return true;
        }
        throw new IllegalArgumentException("입력 값이 잘못 되었습니다. 파일 업/다운로드 명령어는 u,d 로만 입력 가능합니다.");
    }

    public Long convertInputToLong(String input) {
        try {
            Long.parseLong(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("인덱스 타입(Long)으로 변환할 수 없는 입력값입니다.");
        }
        return Long.parseLong(input);
    }
}
