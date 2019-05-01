package com.homework;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/fibonacci")
public class FibonacciServlet extends HttpServlet {

    @Inject
    TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Template template = templateProvider.getTemplate(getServletContext(), "form.ftlh");
        template.dump(resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String stringNumber = req.getParameter("number");
        Map<String, Object> model = new HashMap<>();
        List<Long> resultList;

        if(isTypeOfInteger(stringNumber)) {

            int number = Integer.parseInt(stringNumber);
            resultList = calculateFibonacci(number);

            if (!resultList.isEmpty()) {
                model.put("result", resultList.get(resultList.size()-1));
                model.put("n", String.valueOf(number));
                model.put("resultList", resultList);
                printResult(resp, model);
            }
            else{
                resp.getWriter().println("Number can't be negative or 0");
            }
        }
        else{
            resp.getWriter().println("Input valid number");
        }
    }

    private boolean isTypeOfInteger(String stringNumber){
        try{
            Integer.parseInt(stringNumber);
            return true;
        }
        catch(NumberFormatException e){
            return false;

        }
    }

    private void printResult(HttpServletResponse resp, Map<String, Object> model) throws IOException {
        Template template = templateProvider.getTemplate(getServletContext(), "fibonacci.ftlh");

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    private List<Long> calculateFibonacci(int number) {
        List<Long> resultList = new ArrayList<>();
        if(number >= 1){
            resultList.add(1L);
        }
        Long temp;
        Long a = 0L;
        Long b = 1L;
        for (int i = 1; i < number; i++) {
            temp = a;
            a = b;
            b += temp;
            resultList.add(b);
        }
        return resultList;
    }
}
