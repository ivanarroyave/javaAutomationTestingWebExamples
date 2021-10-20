package co.com.sofka.util;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtilities {

    private static final Logger LOGGER= Logger.getLogger(FileUtilities.class);

    private FileUtilities() {
    }

    public static boolean verifyIfFileExist(String filePath){
        File file = new File(filePath);
        LOGGER.info("\n\r****Verificación de archivo:****\r");
        LOGGER.info("Ruta suministrada: " + filePath + "\r");
        LOGGER.info("¿Es un archivo o directorio existente?:" + file.exists() + "\n");
        LOGGER.info("¿Es un archivo?:" + file.isFile() + "\n\r");
        return file.isFile();
    }

    public static boolean deleteFileOrDirectory(String filePath) {
        File object = new File(filePath);

        LOGGER.info("\n\r****Verificación de eliminación de archivo o directorio:****\r");
        LOGGER.info("Ruta suministrada: " + filePath + "\r");
        LOGGER.info("¿Es un archivo o directorio existente?:" + object.exists() + "\n\r");

        boolean deleted = false;

        if(object.exists()) {
            deleted = object.delete();
        }

        LOGGER.info("¿Fue eliminado?:" + deleted + "\n\r");

        return deleted;
    }

    public static String readFile(String filePath) {
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            while ((line = br.readLine()) != null)
                stringBuilder.append(line + "\n");
        } catch (IOException ioException) {
            LOGGER.info("\n\n****Hay problemas con la ruta especificada para la lectura de archivos****");
            LOGGER.info(ioException.getMessage() + "\r\n");
            LOGGER.info(ioException);
        }

        return stringBuilder.toString();
    }

}
