package cc.noxiuam.titanic.client.util.zip;

import cc.noxiuam.titanic.client.util.Logger;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * garbage but works
 */
@UtilityClass
public class ZipUtil {

    private static final Logger LOGGER = new Logger("ZipUtil");

    @SneakyThrows
    public void unzip(String zipPath, String extractionPoint) {

        byte[] buffer = new byte[1024];
        ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(Paths.get(zipPath)));
        ZipEntry zipEntry = zipInputStream.getNextEntry();

        while (zipEntry != null) {
            while (zipEntry != null) {

                File newFile = newFile(new File(extractionPoint), zipEntry);
                if (zipEntry.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
                        LOGGER.error("Failed to mkdir " + newFile);
                    }
                } else {
                    File parent = newFile.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        LOGGER.error("Failed to mkdir " + parent);
                    }

                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zipInputStream.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }

                zipEntry = zipInputStream.getNextEntry();
            }
        }

        zipInputStream.closeEntry();
        zipInputStream.close();
    }

    @SneakyThrows
    private File newFile(File destinationDir, ZipEntry zipEntry) {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            LOGGER.error("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }

}
