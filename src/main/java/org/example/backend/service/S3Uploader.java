package org.example.backend.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.exception.ImageSizeLimitException;
import org.example.backend.exception.NotImageException;
import org.example.backend.exception.S3ImageUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@Component
@Service
public class S3Uploader {

  private final AmazonS3Client amazonS3Client;

  @Value("${cloud.aws.s3.bucket}")
  private String bucket;

  private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB

  public String upload(MultipartFile multipartFile, String dirPath) throws S3ImageUploadException {
    if (multipartFile == null || multipartFile.isEmpty()) {
      return null;
    }
    validImageType(multipartFile);
    validFileSize(multipartFile);
    String fileName = getUuidFileName(multipartFile.getOriginalFilename());
    String filePath = dirPath + "/" + fileName;
    try {
      amazonS3Client.putObject(
              new PutObjectRequest(bucket, filePath, multipartFile.getInputStream(), null)
                      .withCannedAcl(CannedAccessControlList.PublicRead) // PublicRead 권한으로 업로드 됨
      );
    } catch (IOException e) {
      throw new S3ImageUploadException();
    }
    return getImageUrl(filePath);
  }

  private void validImageType(MultipartFile multipartFile) {
    if (!Objects.requireNonNull(multipartFile.getContentType()).startsWith("image")) {
      throw new NotImageException();
    }
  }

  private void validFileSize(MultipartFile multipartFile) {
    if (multipartFile.getSize() > MAX_FILE_SIZE) {
      throw new ImageSizeLimitException(MAX_FILE_SIZE);
    }
  }

  public String getImageUrl(String filePath) {
    return amazonS3Client.getUrl(bucket, filePath).toString();
  }

  private static String getUuidFileName(String originalFileName) {
    return UUID.randomUUID() + "_" + originalFileName;
  }
}
