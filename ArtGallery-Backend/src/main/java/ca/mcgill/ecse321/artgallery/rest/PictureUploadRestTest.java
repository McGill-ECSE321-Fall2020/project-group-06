package ca.mcgill.ecse321.artgallery.rest;

import java.net.URL;

import com.amazonaws.services.s3.AmazonS3Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ca.mcgill.ecse321.artgallery.services.AmazonClient;

@RestController
@RequestMapping("/storageTest")
public class PictureUploadRestTest {

    @Autowired
    private AmazonClient amazonClient;

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
        return amazonClient.uploadFile(file);
    }

    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return amazonClient.deleteFileFromS3Bucket(fileUrl);
    }

}
