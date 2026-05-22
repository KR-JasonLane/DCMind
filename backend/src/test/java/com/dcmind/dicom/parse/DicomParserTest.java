package com.dcmind.dicom.parse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.dcmind.dicom.model.ParsedInstance;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// DICOM 파서 기능 테스트
class DicomParserTest {

    @ParameterizedTest
    @CsvSource({
        "/dicom/penguin-explicit-vr-le.dcm, 1.2.840.10008.1.2.1", // Explicit VR Little Endian
        "/dicom/penguin-implicit-vr-le.dcm, 1.2.840.10008.1.2" // Implicit VR Little Endian
    })
    void 비압축_DICOM_파일_파싱_테스트(String resourcePath, String expectedTransferSyntax) throws Exception {

        // DICOM 파서 생성
        DicomParser dicomParser = new DicomParser();

        // 리소스 경로에서 DICOM 파일을 읽어 InputStream 생성
        try (InputStream input = getClass().getResourceAsStream(resourcePath)) {

            // 파싱된 인스턴스 추출
            ParsedInstance instance = dicomParser.parse(input);

            // 사전에 정의된 값과 비교하여 검증
            assertEquals("TUX-1991", instance.patientId());
            assertEquals("Penguin^Tux", instance.patientName());
            assertEquals("DX", instance.modality());
            assertEquals(expectedTransferSyntax, instance.transferSyntaxUid());
            assertEquals("2.25.19910917000000000000000000001", instance.studyInstanceUid());
            assertEquals("2.25.19910917000000000000000000002", instance.seriesInstanceUid());
            assertEquals("2.25.19910917000000000000000000003", instance.sopInstanceUid());
            assertEquals(467, instance.rows());
            assertEquals(374, instance.columns());
            assertEquals(8, instance.bitsAllocated());
        }
    }

    @Test
    void DICOM이_아닌_파일은_거절() throws Exception {

        // DICOM 파서 생성
        DicomParser dicomParser = new DicomParser();

        // 문자열 바이트를 입력으로 생성
        InputStream input = new ByteArrayInputStream("DICOM 파일이 아닌 문자열 바이트".getBytes());

        assertThrows(
            NotADicomFileException.class,
            () -> dicomParser.parse(input)
        );
    }

    @Test
    void 필수_태그_누락_파일은_거절() throws Exception {

        // DICOM 파서 생성
        DicomParser dicomParser = new DicomParser();

        // sop 태그가 빠진 DICOM 리소스 읽어오기
        try (InputStream input = getClass().getResourceAsStream("/dicom/penguin-missing-sop.dcm")) {

            assertThrows(
                MissingRequiredTagException.class,
                () -> dicomParser.parse(input)
            );
        }
    }

    @Test
    void 미지원_압축_전송구문은_거절() throws Exception {

        // DICOM 파서 생성
        DicomParser dicomParser = new DicomParser();

        try (InputStream input = getClass().getResourceAsStream("/dicom/penguin-jpeg-baseline.dcm")) {

            assertThrows(
                UnsupportedTransferSyntaxException.class,
                () -> dicomParser.parse(input)
            );
        }
    }
}