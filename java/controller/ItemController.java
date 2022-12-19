/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.book.BookDAOImpl;
import dao.item.ItemDAO;
import dao.item.ItemDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Item.Item;
import model.book.BookItem;

/**
 *
 * @author Admin
 */
public class ItemController extends HttpServlet {

    private final int limitItemPerPage = 15;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String route = request.getPathInfo();

        if (route == null) {
            String page = request.getParameter("page");
            String category = request.getParameter("category");
            int pageNumber = getPageNumber(page);

            List<Item> listItem = null;
            if (category == null) {
                listItem = getItems(pageNumber);
            } else {
                System.out.println(category);
                listItem = getItemsByCategory(pageNumber, category);
            }

            request.setAttribute("listItem", listItem);
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/list-item.jsp");
            rd.forward(request, response);
        } else {
            int itemId = 0;
            
            try {
                itemId = Integer.parseInt(route);
            } catch (NumberFormatException e) {
                System.err.println(e);
                response.sendRedirect(request.getContextPath() + "/not-found");
                return;
            }

            getItemDetail(itemId);
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/item-detail.jsp");
            rd.forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private List<Item> getItems(int page) {
        int from = page * limitItemPerPage + limitItemPerPage;
        List<Item> listItem = new ItemDAOImpl().getNewItems(limitItemPerPage, from);
        return listItem;
    }

    private List<Item> getItemsByCategory(int page, String category) {
        int from = page * limitItemPerPage + limitItemPerPage;
        List<Item> listItem = new ItemDAOImpl().getNewItemsByCategory(limitItemPerPage, from, category);
        return listItem;
    }

    private int getPageNumber(String page) {
        if (page != null) {
            return Integer.parseInt(page);
        }

        return 0;
    }
    
    private BookItem getBookItem(int bookItemId) {
        return new BookDAOImpl().getBookIT(bookItemId);
    }

    protected void shoeControl() {

    }

    protected void electronicControl() {

    }

    protected void bookControl() {

    }

    protected void clothesControl() {

    }
}
