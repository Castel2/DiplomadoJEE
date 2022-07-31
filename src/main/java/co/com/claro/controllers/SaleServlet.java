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
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
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
        Vendor vendor = new Vendor();
        vendor.setId(Long.valueOf(req.getParameter("id_vendor")));
        vendor = vendorService.findById(vendor.getId());

        Equipment equipment = new Equipment();
        equipment.setId(Long.valueOf(req.getParameter("id_equipment")));
        equipment = equipmentService.findById(equipment.getId());
        equipment.setQuantity(equipment.getQuantity() - 1);

        Client client = new Client();
        client.setId(Long.valueOf(req.getParameter("id_client")));
        client = clientService.findById(client.getId());

        Sale sale = new Sale();
        sale.setClient(client);
        sale.setEquipment(equipment);
        sale.setVendor(vendor);
        saleService.save(sale);
        equipmentService.save(equipment);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Sale> saleList = new ArrayList<>();
        saleList =  saleService.findAll();
    }


}
