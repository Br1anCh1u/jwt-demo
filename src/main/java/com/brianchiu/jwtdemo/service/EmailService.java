package com.brianchiu.jwtdemo.service;

import com.brianchiu.jwtdemo.entity.EmailDetail;

public interface EmailService {

    String sendSimpleMail(EmailDetail detail);

    String sendMailWithAttachment(EmailDetail detail);

}
