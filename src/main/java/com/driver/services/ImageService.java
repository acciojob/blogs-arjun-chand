package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image = new Image();
        image.setDescription(description);
        image.setDimension(dimensions);

        Blog blog = blogRepository2.findById(blogId).orElse(null);
        image.setBlog(blog);
        imageRepository2.save(image);
        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image = imageRepository2.findById(id).orElse(null);
        String [] screenDimension = screenDimensions.split("x");

        String [] imgDimension = new String[0];
        if (image != null) {
            imgDimension = image.getDimensions().split("x");
            int integerScreen = Integer.parseInt(screenDimension[0]) * Integer.parseInt(screenDimension[1]);
            int integerImage = Integer.parseInt(screenDimension[0]) * Integer.parseInt(imgDimension[1]);
            return integerScreen / integerImage;
        }
        return 0;
    }
}
