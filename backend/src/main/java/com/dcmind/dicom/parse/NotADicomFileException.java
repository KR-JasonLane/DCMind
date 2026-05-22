package com.dcmind.dicom.parse;

// DICOM 파일이 아닌 파일을 처리하려고 할때 발생하는 예외 클래스
public class NotADicomFileException extends DicomParseException {

    // 생성자: 원인을 받아 상위 클래스(RuntimeException) 생성자 호출
    public NotADicomFileException(Throwable cause) {
        super("유효한 DICOM 파일이 아닙니다", cause);
    }
    
}
