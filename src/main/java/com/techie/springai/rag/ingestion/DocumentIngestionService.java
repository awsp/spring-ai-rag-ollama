package com.techie.springai.rag.ingestion;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
@ConditionalOnProperty(name = "document.ingestion.enabled", havingValue = "true")
public class DocumentIngestionService implements CommandLineRunner {

    @Value("classpath:/pdf/spring-boot-reference.pdf")
    private Resource resource;

    private final VectorStore vectorStore;


    @Override
    public void run(String... args) {
        TikaDocumentReader reader = new TikaDocumentReader(resource);
        TextSplitter textSplitter = new TokenTextSplitter();
        log.info("Ingesting PDF file");
        vectorStore.accept(textSplitter.split(reader.read()));
        log.info("Completed Ingesting PDF file");
    }
}
