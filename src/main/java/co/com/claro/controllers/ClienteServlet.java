package co.com.claro.controllers;

import co.com.claro.entity.Client;
import co.com.claro.entity.Sale;
import co.com.claro.services.ClientServiceImpl;
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

@WebServlet("/cliente")
public class ClienteServlet  extends HttpServlet {

    @Inject
    private ClientServiceImpl clientService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Sale sale = new Sale();
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
            throw new IOException("Error parsing JSON request string");
        }

        JsonReader jsonReader =  Json.createReader(new StringReader(jb.toString()));
        JsonObject jsonObject = jsonReader.readObject();

        String nombre = jsonObject.getString("nombre");
        String cedula = jsonObject.getString("cedula");
        String celular = jsonObject.getString("celular");

        Client client = new Client(nombre,Long.valueOf(cedula),sale);
        Client cliente = clientService.save(client);
        PrintWriter pw = resp.getWriter();
        pw.println(cliente.toString());

    }

}
