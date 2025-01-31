package org.form.portifolio.smtp.rod.contato.controller;

import org.form.portifolio.smtp.rod.contato.model.EmailRequest;
import org.form.portifolio.smtp.rod.contato.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/emails")
public class EmailController {

    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendMail(@RequestBody EmailRequest emailRequest) {
        Map<String, String> response = new HashMap<>();

        try {
            logger.info("Tentando enviar email para: {}", emailRequest.getEmail());

            emailService.sendEmailMessage(
                    emailRequest.getName(),
                    emailRequest.getEmail(),
                    emailRequest.getMessage()
            );

            response.put("message", "Email enviado com sucesso!");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("Erro ao enviar email: {}", e.getMessage(), e);

            response.put("message", "Erro ao enviar email!");
            response.put("error", e.getMessage()); // Adiciona mais detalhes do erro
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
