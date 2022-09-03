import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String args[]) {
        String zipPath = "D://Games//savegames//zip.zip";
        String unXipPath = "D://Games//savegames//";
        String saveGamePath = "D://Games//savegames//save2.dat";
        openZip(zipPath, unXipPath);
        System.out.println(openProgress(saveGamePath));
    }

    public static void openZip(String zipPath, String unZipPath) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipPath))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(unZipPath + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static GameProgress openProgress(String saveGamePath) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(saveGamePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return gameProgress;
    }
}
