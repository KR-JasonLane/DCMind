package com.dcmind.dicom.parse;

// 지원되지 않는 전송 구문이 발견될 때 발생하는 예외 클래스
public class UnsupportedTransferSyntaxException extends DicomParseException {
    
    // 생성자: 지원하지 않는 전송 구문의 UID를 받아 예외 메시지를 생성
    public UnsupportedTransferSyntaxException(String transferSyntaxUid) {
        super("지원하지 않는 전송구문입니다: " + transferSyntaxUid);
    }
    
}
