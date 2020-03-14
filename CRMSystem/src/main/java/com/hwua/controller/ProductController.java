package com.hwua.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwua.mapper.ProductMapper;
import com.hwua.pojo.Product;
import com.hwua.service.ProductService;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/product-list.do/{pageNo}/{pageSize}")
    public ModelAndView findAllproduct(@PathVariable("pageNo") String pageNo, @PathVariable("pageSize") String pageSize)throws Exception{
        ModelAndView mv=new ModelAndView();
        PageHelper.startPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        List<Product> list=productService.findAllProduct();
        PageInfo pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-list");
        return mv;
    }
    @RequestMapping("/product/save.do")
    public String addProduct(Product product)throws Exception{
        Integer rs=  productService.addProduct(product);
        if(rs==1){
            return "redirect:/product-list.do/1/3";
        }else {
            return null;
        }
    }
    @RequestMapping("/product-delete.do/{ids}")
    public String delProduct(@PathVariable("ids") String ids)throws Exception{
        String[] id=ids.split(",");
        int rs=0;
        for (int i=0;i<id.length;i++){
            rs=  productService.delProduct(id[i]);
        }
        if(rs==1){
            return "redirect:/product-list.do/1/3";
        }else {
            return null;
        }
    }
    @RequestMapping("/product-change.do/{ids}/{num}")
    public String changeStatus(@PathVariable("ids") String ids,@PathVariable("num") String num)throws Exception{
        String[] id=ids.split(",");
        int rs=0;
        for (int i=0;i<id.length;i++){
            rs=  productService.changeStatus(id[i],num);
        }
        if(rs==1){
            return "redirect:/product-list.do/1/3";
        }else {
            return null;
        }
    }
    @RequestMapping("/search.do/{find}")
    public ModelAndView search(@PathVariable("find")String find)throws Exception{
        ModelAndView mv=new ModelAndView();
        //指定索引库的路径
        Directory directory = FSDirectory.open(new File("d:\\index").toPath());
        //创建indexReader对象,以读的方式打开索引库(看做是一个连接对象)
        IndexReader indexReader= DirectoryReader.open(directory);
        //创建IndexSearcher对象来发送查询请求
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        System.out.println(find);
        //创建一个QueryParser对象
        //第一个参数: 当查询的字符串进行分词后到指定的域中查询文档
        //第二个参数: 指定使用分词器来对搜索的字符串进行分词
        QueryParser queryParser = new QueryParser("productDesc",new IKAnalyzer());
        Query query = queryParser.parse(find);

        TopDocs topDocs = indexSearcher.search(query, 10);
        System.out.println(topDocs.totalHits);
        List<Product> list=new ArrayList<>();
        for(ScoreDoc scoreDoc:topDocs.scoreDocs){
            Product product=new Product();
            int docid =scoreDoc.doc;//获取匹配到的文档id
            //根据docid就能获取指定的Document对象
            Document document =indexSearcher.doc(docid);
            //打印输出文档中对应域中所保存的数据
           /* System.out.println(document.get("productId"));
            System.out.println(document.get("productNum"));
            System.out.println(document.get("productName"));
            System.out.println(document.get("cityName"));
            System.out.println(document.get("departureTime"));
            System.out.println(document.get("productPrice"));
            System.out.println(document.get("productDesc"));
            System.out.println(document.get("ProductStatus"));*/
            SimpleDateFormat sfEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            product.setId(document.get("productId"));
            product.setProductNum(document.get("productNum"));
            product.setProductName(document.get("productName"));
            product.setCityName(document.get("cityName"));
            product.setDepartureTime(sfEnd.parse(document.get("departureTime")));
            product.setProductPrice(Double.parseDouble(document.get("productPrice")));
            product.setProductDesc(document.get("productDesc"));
            product.setProductStatus(Long.parseLong(document.get("ProductStatus")));
            list.add(product);
            System.out.println("--------------------------------------------");
        }
        indexReader.close();
        mv.addObject("list",list);
        mv.setViewName("product-list1");
        return mv;
    }

}
