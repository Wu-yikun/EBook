package com.wyk.ebook.controller;

import com.wyk.ebook.entity.Book;
import com.wyk.ebook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    // 创建连接数据库的接口实例
    @Autowired
    private BookService bookService;

    // mongodb操作实例
    @Autowired
    private GridFsTemplate gridFsTemplate;

    // 监听 '/book/get' 返回所有书籍数据
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public List<Book> getBooks() {
        List<Book> books = bookService.getBooks();
        return books;
    }

    // 监听 '/book/search' 接受一个参数 返回过滤后的书籍数据
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public List<Book> searchBooks(@RequestParam("filter") String filter) {
        List<Book> books = bookService.searchBooks(filter);
        return books;
    }

    // 监听 '/book/add' 添加书籍，写入数据库
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addBook(@RequestBody Book book, HttpSession session) {
        InputStream inputStream = (InputStream) session.getAttribute("file");
        if (inputStream != null) {
            // 将图片写入MongoDB中
            try {
                gridFsTemplate.store(inputStream, book.getCover());
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        // 图片写入MongoDB、而其它数据写入MySQL数据库
        bookService.addBook(book);

        return "添加书籍成功";
    }

    // 监听 '/book/modify' 修改相应书籍的相应数据
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public String modifyBook(@RequestBody Book book, HttpSession session) {

        // 判断是否需要删除书籍封面图片
        String filename = (String) session.getAttribute("fileDelete");
        if (filename != null) {
            // mongodb 图片数据删除
            Query query = Query.query(Criteria.where("filename").is(filename));
            gridFsTemplate.delete(query);
            session.removeAttribute("fileDelete");
        }

        // 判断是否需要写入图片至数据库
        InputStream inputStream = (InputStream) session.getAttribute("file");
        if (inputStream != null) {
            // 将图片写入mongodb中
            try {
                gridFsTemplate.store(inputStream, book.getCover());
                session.removeAttribute("file");
            } catch (Exception e) {
                return e.getMessage();
            }
        }

        int row = bookService.modifyBook(book);
        if (row <= 0) {
            return "书籍信息修改失败";
        }
        return "书籍信息修改成功";
    }

    // 监听 '/book/detail' 返回相应书籍详细信息
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Book modifyBook(@RequestParam("ISBN") String ISBN) {
        return bookService.getDetail(ISBN);
    }

    // 监听 '/book/delete' 数据库中删除相应书籍
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public String deleteBook(@RequestParam("ISBN") String ISBN) {
        Book book = bookService.getBook(ISBN);
        // 删除 MongoDB 中的图书
        if (book.getCover() != null) {
            Query query = Query.query(Criteria.where("filename").is(book.getCover()));
            gridFsTemplate.delete(query);
        }

        // 删除 MySQL 数据库中的对应字段数据
        int result = bookService.deleteBook(ISBN);
        if (result > 0) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }
}
