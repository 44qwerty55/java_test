package mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FtpHelper {
  private final ApplicationManager app;
  private FTPClient ftp;

  // инициализация клиента, для установки соединения\передачи файлов
  public FtpHelper(ApplicationManager app){
    this.app = app;
    ftp = new FTPClient();
  }

  // загружает новый файл и переименовывает старый
  public void upload(File file, String target, String backup) throws IOException {
    ftp.connect(app.getProperty("ftp.host"));
    ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
    // удаляем старую резервную копию
    ftp.deleteFile(backup);
    // переименовываем реальный файл
    ftp.rename(target, backup);
    // режим передачи данных тех функция
    ftp.enterLocalPassiveMode();
    // передаеться новый файл
    ftp.storeFile(target, new FileInputStream(file));
    ftp.disconnect();
  }
// востанавливает файл обратно
  public void restore(String backup, String target) throws IOException {
    ftp.connect(app.getProperty("ftp.host"));
    ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
    ftp.deleteFile(target);
    ftp.rename(backup, target);
    ftp.disconnect();
  }
}
