package com.hmtmcse.ocb

import grails.util.Holders
import org.springframework.web.multipart.MultipartFile

class FileUtil {

    public static String getRootPath(){
        return Holders.servletContext?.getRealPath("")
    }


    public static File makeDirectory(String path){
        File file = new File(path)
        if (!file.exists()){
            file.mkdirs()
        }
        return file
    }

//    request.getFile("productFile")
    public static String uploadContactImage(Integer contactId, MultipartFile multipartFile){
        if (contactId && multipartFile){
            String contactImagePath = "${getRootPath()}contact-image/"
//            print(contactImagePath)
            makeDirectory(contactImagePath)
            multipartFile.transferTo(new File(contactImagePath, contactId + "-" + multipartFile.originalFilename))
            return multipartFile.originalFilename
        }
        return ""
    }

    public static boolean deleteContactImage(String imagePath){
        if (imagePath){
            File file = new File("${getRootPath()}contact-image/${imagePath}")
            if (file.exists()){
                file.delete()
                return true
            }
        }
        return false
    }
}
