package com.wyk.ebook.config;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;

@Configuration
public class MongoConf {

    @Autowired
    private MongoDbFactory mongoDbFactory;

    /**
     * MongoDb GridFS是MongoDB的文件存储方案(支持大容量的存储), 主要用于存储和恢复那些超过16M（BSON文件限制）的文件(如：图片、音频等)，对大文件有着更好的性能。
     */
    @Bean
    public GridFSBucket getGridFSBuckets() {
        MongoDatabase db = mongoDbFactory.getDb();
        return GridFSBuckets.create(db);
    }
}
