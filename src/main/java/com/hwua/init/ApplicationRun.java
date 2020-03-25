package com.hwua.init;

import com.hwua.service.LuceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationRun implements ApplicationRunner {
    @Autowired
    private LuceneService luceneService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        luceneService.productInitLucene();
    }
}
