package co.com.claro.controllers;

import co.com.claro.entity.Equipment;
import co.com.claro.services.EquipmentServiceImpl;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/dispotivo")
public class EquipmentServlet extends HttpServlet {

    @Inject
    private EquipmentServiceImpl equipmentService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Equipment equipment = new Equipment();
        equipment.setName(req.getParameter("name"));
        equipment.setBrand(req.getParameter("brand"));
        equipment.setPrice(BigDecimal.valueOf(Long.valueOf(req.getParameter("price"))));
        equipment.setQuantity(Long.valueOf(req.getParameter("quantity")));
        equipmentService.save(equipment);

        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
            throw new IOException("Error parsing JSON request string");
        }

        JsonReader jsonReader = Json.createReader(new StringReader(jb.toString()));
        JsonObject jsonObject = jsonReader.readObject();


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Equipment> equipmentList = new ArrayList<>();
        equipmentList =  equipmentService.findAll();
    }
}
