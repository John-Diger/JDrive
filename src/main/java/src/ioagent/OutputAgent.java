package src.ioagent;

import java.util.List;

public class OutputAgent {

    public void loadSharedFolderList(List<?> files, int filesLength) {
        System.out.println("\n                  [!] 공유 폴더 내 파일 목록을 불러옵니다. [!]\n");
        System.out.println("| INDEX |                                | FilePath |");
        for (int i = 0; i < filesLength; i++) {
            System.out.println("| " + (i + 1) + " | " + files.get(i) + " | ");
            System.out.println("-----");
        }
    }

    public void inputRequestFile() {
        System.out.print("전송 받으실 파일 인덱스를 입력해주세요 : ");
    }
}