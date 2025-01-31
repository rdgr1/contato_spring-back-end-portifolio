package org.form.portifolio.smtp.rod.contato.service;

import jakarta.servlet.http.PushBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailMessage(String name,String email,String message){
       try {
           SimpleMailMessage mailmessage = new SimpleMailMessage();
           mailmessage.setTo("rodger.dev777@gmail.com");
           mailmessage.setSubject("Novo Contato do Portifolio:" + " " + name );
           mailmessage.setText("Você recebeu um novo email de: \n \n"
                   + "Nome: " + name + "\n" + "E-mail: " + email + "\n"
                   + "Mensagem: \n" + message);
           System.out.println("Enviando mensagem....");
           mailSender.send(mailmessage);
           System.out.println("Mensagem enviada!!!");
       } catch (Exception e){
           e.printStackTrace();
       }
    }
}
