package ru.stqa.pft.mantis.appmanager;

import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MailHelper {

    private ApplicationManager app;
    private final Wiser wiser;

    public MailHelper(ApplicationManager app) {
        this.app = app;
        wiser = new Wiser();
    }
    public void start() {
        wiser.start();
    }

    public void stop() {
        wiser.stop();
    }

    public List<MailMessage> waitForMail(int count, long timeout) throws MessagingException, IOException{
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() <start + timeout) {
            if (wiser.getMessages().size() >= count) {
                return wiser.getMessages().stream().map((wiserMessage) -> toModelMail(wiserMessage)).collect(Collectors.toList());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        throw new Error("No mail :(");
    }

    public static MailMessage toModelMail(WiserMessage m) {
        try {
            MimeMessage mm = m.getMimeMessage();
            return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent());
        } catch (MessagingException mexc) {
            mexc.printStackTrace();
            return null;
        } catch (IOException iexc) {
            iexc.printStackTrace();
            return null;
        }
    }
}
