/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.book;

import dao.utils.ConDB;
import dao.utils.Mapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Item.Item;
import model.book.Author;
import model.book.Book;
import model.book.BookItem;
import model.book.Publisher;

/**
 *
 * @author DELL
 */
public class BookDAOImpl implements BookDAO {

    private final Connection conn;
    private final String sql1 = "DELETE FROM shopbanhang.book where ID=? ;";
    private final String sql2 = "DELETE FROM book_author where BookID=? ;";
    private final String sql3 = "Update book SET Title=?,Summary=?,PublicationDate=?,NumberOfPage=?,RemainingQuantity=?,Status=?,Cost=?" + "where ID=?";
    private final String sql4 = "Update author SET Name=?,Biography=?,Email=?,Adress=?" + "where ID=?";
    private final String sql5 = "Update publisher SET Name=?,Adress=?" + "where ID=?";
    private final String sql7 = "SELECT * FROM book WHERE ID=?;";
    private final String sql8 = "SELECT * FROM publisher where ID=?;";
    private final String sql9 = "Select author.name,author.Biography,author.Email,author.address\n"
            + "from((book_author\n"
            + "inner join book on book.ID=book_author.BookID)\n"
            + "inner join author on author.ID=book_author.AuthorID) \n"
            + "where book.ID=?;";
    private final String sql10 = "Select *\n"
            + "from((bookitem\n"
            + "inner join item on item.ID=bookitem.ItemID)\n"
            + "inner join book on book.ID=bookitem.BookID) \n"
            + "where book.ID= ? ;";

    public BookDAOImpl() {
        conn = ConDB.getJDBCCOnection();
    }

    @Override
    public int deleteBook(int id) {
        PreparedStatement prestatement;
        PreparedStatement prestatement1;
        try {
            prestatement = conn.prepareStatement(sql2);
            prestatement.setInt(1, id);
            int rowDeleted = prestatement.executeUpdate();
            prestatement1 = conn.prepareStatement(sql1);
            prestatement1.setInt(1, id);
            int rowDeleted2 = prestatement1.executeUpdate();
            return rowDeleted2;
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int updateBook(Book Book) {

        try {
            PreparedStatement prestatement = conn.prepareStatement(sql3);
            prestatement = conn.prepareStatement(sql3);
            prestatement.setString(2, Book.getTitle());
            prestatement.setString(3, Book.getSummary());
            prestatement.setString(4, Book.getPublicationDate().toString());
            prestatement.setInt(5, Book.getNumberOfPage());
            prestatement.setInt(6, Book.getRemainingQuantity());
            prestatement.setBoolean(7, Book.getStatus());
            prestatement.setFloat(8, Book.getCost());
            prestatement.setInt(1, Book.getId());
            int rowcount1 = prestatement.executeUpdate();
            return rowcount1;
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    @Override
    public int UpdateAuthor(Author Author) {

        try {
            PreparedStatement prestatement = conn.prepareStatement(sql4);
            prestatement = conn.prepareStatement(sql4);
            prestatement.setString(1, Author.getName());
            prestatement.setString(2, Author.getBiography());
            prestatement.setString(3, Author.getEmail());
            prestatement.setString(4, Author.getAddress());
            prestatement.setInt(5, Author.getId());
            int rowcount2 = prestatement.executeUpdate();
            return rowcount2;
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int UpdateBookItem(BookItem bookitem) {
        return 0;
    }

    @Override
    public int UpdatePublisher(Publisher publisher) {

        try {
            PreparedStatement prestatement = conn.prepareStatement(sql5);
            prestatement = conn.prepareStatement(sql5);
            prestatement.setString(1, publisher.getName());
            prestatement.setString(2, publisher.getAddress());
            prestatement.setInt(3, publisher.getId());
            int rowcount3 = prestatement.executeUpdate();
            return rowcount3;
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public Book getBook(int id) {
        PreparedStatement prestatement;
        ResultSet rs;
        try {
            prestatement = conn.prepareStatement(sql7);
            prestatement.setInt(1, id);
            rs = prestatement.executeQuery();
            Book b = new Book();
            if (rs.next()) {
                b.setTitle(rs.getString(3));
                b.setSummary(rs.getString(4));
                b.setPublicationDate(rs.getDate(5));
                b.setNumberOfPage(rs.getInt(6));
                b.setRemainingQuantity(rs.getInt(7));
                b.setStatus(rs.getBoolean(8));
                b.setCost(rs.getFloat(9));
            }
            return b;
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Publisher getBookPUB(int id) {
        PreparedStatement prestatement;
        PreparedStatement prestatement1;
        ResultSet rs;
        try {
            prestatement = conn.prepareStatement(sql7);
            prestatement.setInt(1, id);
            rs = prestatement.executeQuery();
            int tmp = 0;
            if (rs.next()) {
                tmp = rs.getInt(2);
            }
            prestatement1 = conn.prepareStatement(sql8);
            prestatement1.setInt(1, tmp);
            rs = prestatement1.executeQuery();
            Publisher p = new Publisher();
            if (rs.next()) {
                p.setName(rs.getString(2));
                p.setAddress(rs.getString(3));
            }
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ArrayList<Author> getBookAU(int id) {
        PreparedStatement prestatement;
        ResultSet rs;
        try {
            prestatement = conn.prepareStatement(sql9);
            prestatement.setInt(1, id);
            rs = prestatement.executeQuery();
            ArrayList<Author> list = new ArrayList<>();
            while (rs.next()) {
                Author a = new Author();
                a.setName(rs.getString(1));
                a.setBiography(rs.getString(2));
                a.setEmail(rs.getString(3));
                a.setAddress(rs.getString(4));
                list.add(a);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public BookItem getBookIT(int id) {
        PreparedStatement prestatement;
        ResultSet rs;
        BookItem bi = null;

        try {
            prestatement = conn.prepareStatement(sql10);
            prestatement.setInt(1, id);
            rs = prestatement.executeQuery();
            if (rs.next()) {
                Item item = Mapper.mapItem(rs);
                bi = new BookItem(item.getID(), item.getName(),
                        item.getDescription(), item.getPrice(),
                        item.getDiscount(), item.getSellingStatus(),
                        item.getImage(), item.getCategory()
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return bi;
        }
    }

    @Override
    public Map<BookItem, Book> getBookAllStt(int id) {
        Map<BookItem, Book> tmp = new HashMap<BookItem, Book>();
        Book b = getBook(id);
        b.setPub(getBookPUB(id));
        b.setAut(getBookAU(id));
        tmp.put(getBookIT(id), b);
        return tmp;
    }

}
