package com.dcmind.dicom.parse;

// DICOM 파싱 중 필수 태그가 누락되었을때 발생하는 예외 클래스
public class MissingRequiredTagException extends DicomParseException {
    
    // 생성자: 누락된 태그의 이름을 받아 예외 메시지를 생성
    public MissingRequiredTagException(String tagName) {
        super("필수 DICOM 태그가 없습니다: " + tagName);
    }
    
}
