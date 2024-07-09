package com.onlineshop.app.helpers;

import com.onlineshop.app.entities.Order;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class ExportService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void exportOrdersToCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"orders.csv\"");

        Writer writer = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
        CSVWriter csvWriter = new CSVWriter(writer, ';', CSVWriter.DEFAULT_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

        List<Order> orders = entityManager.createQuery("SELECT o FROM Order o", Order.class).getResultList();

        String[] header = {"Order ID", "Customer Name", "Product Names", "Total Products"};
        csvWriter.writeNext(header);

        for (Order order : orders) {
            StringBuilder productNames = new StringBuilder();
            order.getProducts().forEach(product -> productNames.append(product.getName()).append(", "));

            String[] data = {
                    String.valueOf(order.getId()),
                    order.getCustomer().getName(),
                    productNames.toString(),
                    String.valueOf(order.getProducts().size())
            };
            csvWriter.writeNext(data);
        }

        csvWriter.close();
        writer.close();
    }
}
