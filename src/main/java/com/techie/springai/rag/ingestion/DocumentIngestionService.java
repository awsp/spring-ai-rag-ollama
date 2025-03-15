package com.techie.springai.rag.ingestion;

import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class DocumentIngestionService implements CommandLineRunner {

    @Value("classpath:/pdf/spring-boot-reference.pdf")
    private Resource resource;
    private final VectorStore vectorStore;

    public DocumentIngestionService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @Override
    public void run(String... args) {
        TikaDocumentReader reader = new TikaDocumentReader(resource);
        TextSplitter textSplitter = new TokenTextSplitter();
        vectorStore.accept(textSplitter.split(reader.read()));
    }
}
