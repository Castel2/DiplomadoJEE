package co.com.claro.controllers;

import co.com.claro.entity.TypeVendor;
import co.com.claro.entity.Vendor;
import co.com.claro.services.TypeVendorServiceImpl;
import co.com.claro.services.VendorServiceImpl;
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
import java.io.PrintWriter;
import java.io.StringReader;

@WebServlet("/vendor")
public class VendorServlet extends HttpServlet {

    @Inject
    private VendorServiceImpl vendorService;

    @Inject
    private TypeVendorServiceImpl typeVendorService;

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

        String nombre = jsonObject.getString("name");
        String cedula = jsonObject.getString("dni");
        String codigo = jsonObject.getString("code");
        String ciudad = jsonObject.getString("city");
        String tipoVendedor = jsonObject.getString("id");

        Vendor vendor = new Vendor(nombre, cedula, codigo, ciudad, typeVendorService.findById(Long.valueOf(tipoVendedor)));
        Vendor vendorGuardado = vendorService.save(vendor);
        PrintWriter pw = resp.getWriter();
        pw.println(vendorGuardado.toString());

    }
}
