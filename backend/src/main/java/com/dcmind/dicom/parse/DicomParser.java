package com.dcmind.dicom.parse;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.io.DicomInputStream;
import org.dcm4che3.io.DicomStreamException;

import com.dcmind.dicom.model.ParsedInstance;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

/*
    * dcm4che3 라이브러리를 직접 사용하지 않고 래핑하여 사용하는 이유
        만약 래핑 클래스가 아닌 라이브러리 클래스를 직접 사용하게 되면, DICOM 파싱 라이브러리를 교체하였을 때,
        DICOM을 파싱하는 모든 곳에서 라이브러리 클래스를 변경해 줘야하는 문제가 발생하기 때문에
        파싱 전용 래핑클래스를 만들어서 다른곳에서의 라이브러리 의존을 최소화 하기 위함.
*/

// DICOM 파일을 파싱하여 ParsedInstance 객체로 변환하는 클래스
public class DicomParser {

    // 지원되는 전송 구문 UID를 Set으로 정의. Little Endian만 지원.
    private static final Set<String> SUPPORTED_TRANSFER_SYNTAXES = Set.of(
        "1.2.840.10008.1.2",      // Implicit VR Little Endian
        "1.2.840.10008.1.2.1"    // Explicit VR Little Endian
    );

    // DICOM 파일을 파싱하여 ParsedInstance 객체로 반환하는 메서드
    public ParsedInstance parse(InputStream input) throws IOException {

        // DICOM 스트림 생성
        try (DicomInputStream dicom = new DicomInputStream(input)) {

            // 파일메타 정보를 먼저 읽어서 전송구문 확정
            // * DicomInputStream은 파일 메타를 Lazy로 읽음. 따라서 먼저 파일 메타 정보를 읽어줘야 전송구문을 확인할 수 있음.
            dicom.readFileMetaInformation();

            // 전송 구문 UID 가져오기
            String transferSyntaxUid = dicom.getTransferSyntax();

            // 지원되지 않는 전송 구문이면 'UnsupportedTransferSyntaxException'예외 발생
            if (!SUPPORTED_TRANSFER_SYNTAXES.contains(transferSyntaxUid)) {
                throw new UnsupportedTransferSyntaxException(transferSyntaxUid);
            }

            // 본문 파싱 -> 태그 맵
            Attributes attrs = dicom.readDataset(); 

            // 필수 태그 검증 (없으면 거절)
            requireTag(attrs, Tag.StudyInstanceUID, "StudyInstanceUID");
            requireTag(attrs, Tag.SeriesInstanceUID, "SeriesInstanceUID");
            requireTag(attrs, Tag.SOPInstanceUID, "SOPInstanceUID");

            // 파싱 된 데이터를 기반으로 ParsedInstance 객체 생성 후 리턴
            return new ParsedInstance(
                attrs.getString(Tag.PatientID),
                attrs.getString(Tag.PatientName),
                attrs.getString(Tag.StudyInstanceUID),
                attrs.getString(Tag.SeriesInstanceUID),
                attrs.getString(Tag.SOPInstanceUID),
                attrs.getString(Tag.Modality),
                transferSyntaxUid,
                attrs.getInt(Tag.Rows, 0),
                attrs.getInt(Tag.Columns, 0),
                attrs.getInt(Tag.BitsAllocated, 0)
            );
        } catch (DicomStreamException e) {
            // DICOM 스트림 예외 발생 시, NotADicomFileException으로 래핑하여 던짐
            throw new NotADicomFileException(e);
        }
    }

    // 필수 태그가 존재하는지 확인하는 유틸리티 메서드
    private static void requireTag(Attributes attrs, int tag, String tagName) {
        
        // 확인하고자 하는 태그의 값을 가져온다.
        String value = attrs.getString(tag);

        // 값이 null이거나 공백이면 MissingRequiredTagException 예외 발생
        // isEmpty()를 사용하면 공백(" ")검증이 실패하므로, isBlank()를 사용하여 공백까지 포함하여 검증을 수행
        if(value == null || value.isBlank()) {
            throw new MissingRequiredTagException(tagName);
        }
    }
    
}
