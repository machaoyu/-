package com.hwua;

import com.hwua.pojo.Product;
import com.hwua.pojo.Users;
import com.hwua.service.ProductService;
import com.hwua.service.UserService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.io.FileUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Test
    void contextLoads() throws Exception{
        Users user=new Users();
        user.setUsername("123");
        user.setPassword("123");
        user.setEmail("777888999@qq.com");
        userService.addUser(user);
    }

    @Test
    public void testCreateIndex() throws Exception {
        //指定索引库的路径
        Directory directory = FSDirectory.open(new File("d:\\index").toPath());
        //创建索引库写入对象,并告知写到哪里,使用指定的配置(分析器,默认使用的是标准分析器StandardAnalyzer)
        IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(new IKAnalyzer()));

        //得到存放数据源的目录
/*        File dir = new File("D:\\searchsource");*/
        List<Product> list=productService.findAllProduct();
        for (Product product : list) {
                //创建文档(Document)
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Document document = new Document();
                String productId=product.getId();//得到货物id
                String productNum=product.getProductNum();//得到货物编号
                String productName=product.getProductName();//得到货物名
                String cityName=product.getCityName();//得到城  String departure市
                String departureTime=sdf.format(product.getDepartureTime());//出发时间
                String productPrice=product.getProductPrice()+"";//得到价格
                String productDesc=product.getProductDesc();//得到详情
                String ProductStatus=String.valueOf(product.getProductStatus());//状态
                //给文档创建不同的域对象
                Field fileProductIdField = new StringField("productId",productId, Field.Store.YES);
                Field fileProductNumField = new StringField("productNum",productNum, Field.Store.YES);
                Field fileProductName = new TextField("productName",productName, Field.Store.YES);
                Field fileCityNameField = new StringField("cityName",cityName, Field.Store.YES);
                Field fileDepartureTimeField = new StringField("departureTime",departureTime, Field.Store.YES);
                Field fileProductPriceField = new StringField("productPrice",productPrice, Field.Store.YES);
                Field fileProductDescField = new TextField("productDesc", productDesc,Field.Store.YES);//不存储可以做运算
                Field fileProductStatusField = new StringField("ProductStatus",ProductStatus, Field.Store.YES);
                document.add(fileProductIdField);
                document.add(fileProductNumField);
                document.add(fileDepartureTimeField);
                document.add(fileProductStatusField);
                document.add(fileProductName);
                document.add(fileCityNameField);
                document.add(fileProductPriceField);
                document.add(fileProductDescField);
                //把文档写入到索引库
                indexWriter.addDocument(document);
            }
            indexWriter.close();



        }
}
