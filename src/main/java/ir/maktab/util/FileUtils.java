package ir.maktab.util;

import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public final class FileUtils {
    private FileUtils(){}

    public static String convertFileToBase64(Part file) throws IOException {
        try (InputStream fileStream = file.getInputStream()) {
            byte[] fileBytes = fileStream.readAllBytes();
            return Base64.getEncoder().encodeToString(fileBytes);
        }
    }
}
