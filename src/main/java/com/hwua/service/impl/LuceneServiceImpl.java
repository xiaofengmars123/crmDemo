package com.hwua.service.impl;

import com.hwua.pojo.Product;
import com.hwua.service.LuceneService;
import com.hwua.service.ProductService;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class LuceneServiceImpl implements LuceneService {

        @Autowired
        private ProductService productService;

    /**
     * 创建索引
     * @throws IOException
     */
    @Override
    public void productInitLucene() throws IOException {
        List<Product> allProduct = productService.findAllProduct();
        Directory directory= FSDirectory.open(new File("d:\\crm").toPath());
        IndexWriter indexWriter=new IndexWriter(directory,new IndexWriterConfig(new IKAnalyzer()));
        indexWriter.deleteAll();//删除所有
        for (Product product:
                allProduct) {
            Document document=new Document();
            String id = product.getId();
            String productName = product.getProductName();
            String productNum = product.getProductNum();
            double productPrice = product.getProductPrice();
            Integer productStatus = product.getProductStatus();
            String departureTime = product.getDepartureTime();
            String cityName = product.getCityName();
            document.add(new StringField("id",id, Field.Store.YES));
            document.add(new TextField("productName",productName, Field.Store.YES));
            document.add(new TextField("productNum",productNum, Field.Store.YES));
            document.add(new StringField("productPrice",productPrice+"",Field.Store.YES));
            document.add(new TextField("productStatus",productStatus+"", Field.Store.YES));
            document.add(new StoredField("departureTime",departureTime));
            document.add(new TextField("cityName",cityName, Field.Store.YES));
            indexWriter.addDocument(document);

        }
        indexWriter.close();
    }

    /**
     * 搜索
     * @param productName
     * @return
     * @throws IOException
     */
    @Override
    public List<Product> productSearchIndex(String productName) throws IOException {
        System.out.println("productSearchIndex==================启动");
        List<Product> productList=new ArrayList<>();
        Directory directory = FSDirectory.open(new File("d:\\crm").toPath());
        //创建indexReader对象,以读的方式打开索引库(看做是一个连接对象)
        IndexReader indexReader= DirectoryReader.open(directory);
        //创建IndexSearcher对象来发送查询请求
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        //执行关键字查询(精确查询),创建一个Query对象
         Query query = new TermQuery(new Term("productName",productName));
        //执行查询
        TopDocs topDocs = indexSearcher.search(query, 100);
        System.out.println(topDocs.totalHits);//实际查询到的记录数
        for(ScoreDoc scoreDoc:topDocs.scoreDocs){
            int docid =scoreDoc.doc;//获取匹配到的文档id
            //根据docid就能获取指定的Document对象
            Document document =indexSearcher.doc(docid);
            String id = document.get("id");
            String productName1 = document.get("productName");
            String productNum = document.get("productNum");
            String productPrice = document.get("productPrice");
            String productStatus = document.get("productStatus");
            String departureTime = document.get("departureTime");
            String cityName = document.get("cityName");
            Product product=new Product();
            product.setId(id);
            product.setProductName(productName1);
            product.setProductNum(productNum);
            product.setProductPrice(Double.valueOf(productPrice));
            product.setProductStatus(Integer.valueOf(productStatus));
            product.setDepartureTime(departureTime);
            product.setCityName(cityName);
            productList.add(product);
        }

        indexReader.close();

        for (Product p:
             productList) {
            System.out.println(p);
        }
        return productList;

    }
}


