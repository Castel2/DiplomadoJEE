package co.com.claro.controllers;

import co.com.claro.entity.Client;
import co.com.claro.entity.Equipment;
import co.com.claro.entity.Sale;
import co.com.claro.entity.Vendor;
import co.com.claro.services.ClientServiceImpl;
import co.com.claro.services.EquipmentServiceImpl;
import co.com.claro.services.SaleServiceImpl;
import co.com.claro.services.VendorServiceImpl;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/sale")
public class SaleServlet extends HttpServlet {

    @Inject
    private SaleServiceImpl saleService;

    @Inject
    private ClientServiceImpl clientService;

    @Inject
    private EquipmentServiceImpl equipmentService;

    @Inject
    private VendorServiceImpl vendorService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        String vendedor = jsonObject.getString("id_vendor");
        String equipo = jsonObject.getString("id_equipment");
        String cliente = jsonObject.getString("id_client");

        Equipment equipment = new Equipment();
        equipment = equipmentService.findById(Long.valueOf(equipo));
        equipment.setQuantity(equipment.getQuantity() - 1);

        Sale sale = new Sale(equipment, clientService.findById(Long.valueOf(cliente)), vendorService.findById(Long.valueOf(vendedor)));
        Sale saleGuardado = saleService.save(sale);
        Equipment equipmentGuardado = equipmentService.save(equipment);
        PrintWriter pw = resp.getWriter();
        pw.println(saleGuardado.toString());
        pw.println(equipmentGuardado.toString());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Sale> saleList = saleService.findAll();
        PrintWriter pw = resp.getWriter();
        for (Sale iter: saleList) {
            pw.println(iter.getClient().getName());
            pw.println(iter.getClient().getDni());
            pw.println(iter.getEquipment().getName());
            pw.println(iter.getEquipment().getBrand());
            pw.println(iter.getEquipment().getPrice());
            pw.println(iter.getVendor().getName());
            pw.println(iter.getVendor().getTypeVendor().getType());
            pw.println(iter.getVendor().getCity());
        }
    }


}
