package src.ioagent;

import src.ExtractedContent;
import src.ResponseForm;

import java.util.List;

public class OutputAgent {

    public void printInputGuideDownloadOrUpload() {
        System.out.println("교수님과 연결되었습니다. 공유 폴더에 파일을 업로드 할 것인지, 다운로드 할 것인지 입력해주세요");
        System.out.println("업로드 : u");
        System.out.print("다운로드 : d : ");
    }

    public void printUploadGuide() {
        System.out.print("업로드 할 파일의 절대 경로를 입력해주세요 : ");
    }

    public void printSharedFolder(ResponseForm responseForm, int filesLength) {
        System.out.println("\n                  [!] 공유 폴더 내 파일 목록을 불러옵니다. [!]\n");
        System.out.println("| INDEX |                                | FilePath |");
        List<ExtractedContent> extractedContents = responseForm.getExtractedContents();
        for (int i = 0; i < extractedContents.size(); i++) {
            System.out.println("| " + extractedContents.get(i).getId() + " | " + extractedContents.get(i).getName() + " | ");
            System.out.println("-----");
        }
    }

    public void printInputGuideRequestFile() {
        System.out.print("전송 받으실 파일 인덱스를 입력해주세요 : ");
    }
}