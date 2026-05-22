package com.dcmind.dicom.parse;

/*
    * 런타임 예외를 사용하는 이유
        런타임 예외는 체크 예외가 아니라 언체크 예외이기 때문에, 해당 예외를 처리하지 않아도 컴파일러가 오류를 발생시키지 않는다.
        따라서 파싱 메서드 호출 체인 전부에 try-catch 블록 작성을 강제하지 않아 코드 오염을 막을 수 있다.
        대신 호출 체인 최상위에서 꼭 예외처리를 하도록 한다.
*/

// DICOM 파싱 중 발생하는 모든 예외의 최상위 타입
public class DicomParseException extends RuntimeException {
    
    // 생성자: 예외 메시지와 원인을 받아 상위 클래스(RuntimeException) 생성자 호출
    public DicomParseException(String message, Throwable cause) {
        super(message, cause);
    }
    
    // 생성자: 예외 메시지만 받아 상위 클래스(RuntimeException) 생성자 호출
    public DicomParseException(String message) {
        super(message);
    }
}
