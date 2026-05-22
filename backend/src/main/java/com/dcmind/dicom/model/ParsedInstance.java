package com.dcmind.dicom.model;

// DICOM에서 파싱된 데이터를 담는 레코드 클래스
public record ParsedInstance (
    String patientId,
    String patientName,
    String studyInstanceUid,
    String seriesInstanceUid,
    String sopInstanceUid,
    String modality,
    String transferSyntaxUid,
    int rows,
    int columns,
    int bitsAllocated
) {

}
