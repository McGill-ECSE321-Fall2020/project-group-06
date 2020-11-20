package ca.mcgill.ecse321.artgallery.rest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ca.mcgill.ecse321.artgallery.dao.ArtworkRepository;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.services.AmazonClient;

/**
 * Art Gallery REST controller class
 * 
 * @author Sen Wang
 * @author Noah Chamberland
 * @author Justin Legrand
 * @author Olivier Normandin
 * @author Andre-Walter Panzini
 */

@RestController
@RequestMapping("/api/storage")
public class PictureUploadRestTest {

    @Autowired
    private AmazonClient amazonClient;

    @Autowired
    private ArtworkRepository artworkRepository;

    @PostMapping("/uploadFile/{artworkId}")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file,
            @PathVariable("artworkId") int artworkId) {
        String url = amazonClient.uploadFile(file);
        Artwork artwork = artworkRepository.findArtworkById(artworkId);
        artwork.setUrl(url);
        artworkRepository.save(artwork);
        return amazonClient.uploadFile(file);
    }

    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return amazonClient.deleteFileFromS3Bucket(fileUrl);
    }

}
