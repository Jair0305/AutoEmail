package org.example;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Main {
    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.office365.com");
        properties.put("mail.smtp.port", "587");

        final String username = "chamaco_03@hotmail.es";
        final String password = "Jair0305";

        String rutaArchivoHTML = "C:\\Users\\chama\\Desktop\\autoemail\\autemail\\src\\main\\resources\\index.html";

        try {
            String contenidoHTML = new String(Files.readAllBytes(Paths.get(rutaArchivoHTML)));

            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("chamaco_03@hotmail.es"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("j.chavezislas@ugto.mx"));
            message.setSubject("Correo con Contenido HTML desde Archivo");

            Multipart multipart = new MimeMultipart();

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(contenidoHTML, "text/html");

            multipart.addBodyPart(htmlPart);

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Correo enviado con contenido HTML desde archivo.");

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
