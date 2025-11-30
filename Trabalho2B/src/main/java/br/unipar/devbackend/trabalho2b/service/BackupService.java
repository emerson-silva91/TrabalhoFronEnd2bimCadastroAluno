package br.unipar.devbackend.trabalho2b.service;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BackupService {
    @Value("${app.backup.folder}")
    private String backupFolder;

    @Value("${app.pgdump.path}")
    private String pgDumpPath;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Value("${spring.datasource.url}")
    private String dbUrl;

//
//    @Scheduled(fixedRate = 60000)
      @Scheduled(cron = "0 0 * * * *")
    public void executarBackup() {
        try {

            String dbName = dbUrl.substring(dbUrl.lastIndexOf("/") + 1);

            File pasta = new File(backupFolder);
            if (!pasta.exists()) pasta.mkdirs();


            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String filePath = backupFolder + "/backup_" + timestamp + ".sql";

            String host = "localhost";
            String port = "5432";

            String[] cmd = {
                    pgDumpPath,
                    "-h", host,
                    "-p", port,
                    "-U", dbUser,
                    "-F", "p",
                    "-f", filePath,
                    dbName
            };


            ProcessBuilder pb = new ProcessBuilder(cmd);
            pb.environment().put("PGPASSWORD", dbPassword);

            Process process = pb.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Backup gerado com sucesso: " + filePath);
            } else {
                System.err.println("Erro ao gerar backup. Código: " + exitCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
