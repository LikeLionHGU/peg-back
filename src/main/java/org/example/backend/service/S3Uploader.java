package org.example.backend.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

  public String upload(MultipartFile multipartFile, String dirName) throws IOException {
    File uploadFile =
        convert(multipartFile)
            .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File 전환 실패"));

    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentLength(multipartFile.getSize());
    metadata.setContentType(multipartFile.getContentType());

    return upload(uploadFile, dirName, metadata);
  }

  private String upload(File uploadFile, String dirName, ObjectMetadata metadata) {
    UUID uuid = UUID.randomUUID();
    String fileName = dirName + uuid + "_" + uploadFile.getName();
    String uploadImageUrl = putS3(uploadFile, fileName, metadata);

    removeNewFile(uploadFile);

    return uploadImageUrl;
  }

  private String putS3(File uploadFile, String fileName, ObjectMetadata metadata) {
    amazonS3Client.putObject(
        new PutObjectRequest(bucket, fileName, uploadFile)
            .withCannedAcl(CannedAccessControlList.PublicRead)
            .withMetadata(metadata));
    return amazonS3Client.getUrl(bucket, fileName).toString();
  }

  private void removeNewFile(File targetFile) {
    if (targetFile.delete()) {
      // 삭제 성공처리
    } else {
      System.out.println("삭제 실패");
    }
  }

  //    public void deleteFile(String fileName) {
  //        amazonS3Client.deleteObject(bucket, fileName);
  //    }

  public void deleteFile(String dirName, String fileName) {
    amazonS3Client.deleteObject(bucket, dirName + "/" + fileName);
  }

  private Optional<File> convert(MultipartFile file) throws IOException {
    File convertFile = new File(file.getOriginalFilename());
    if (convertFile.createNewFile()) {
      file.transferTo(convertFile);
      //      try (FileOutputStream fos = new FileOutputStream(convertFile)) {
      //        fos.write(file.getBytes());
    }

    return Optional.of(convertFile);
  }

  public String getImageUrl(String fileName) {
    if (fileName == null) {
      return null;
    }
    return amazonS3Client.getUrl(bucket, fileName).toString();
  }
}
