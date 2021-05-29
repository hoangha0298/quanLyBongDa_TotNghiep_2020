package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageService {

    @Autowired
    private ResourceBundleMessageSource messageSource;

//    @Autowired
//    MessageService(ResourceBundleMessageSource messageSource) {
//        MessageService.messageSource = messageSource;
//    }

    public String getMessage(String msgCode) {
        Locale locale = LocaleContextHolder.getLocale();
        if(locale.getLanguage() == null || locale.getLanguage().isEmpty()){
            locale = new Locale("vi");
        }
        return messageSource.getMessage(msgCode, null, locale);
    }

}
