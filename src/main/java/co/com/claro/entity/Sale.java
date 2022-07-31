package co.com.claro.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Equipment equipment;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Vendor vendor;

    public Sale() {
    }

    public Sale(Equipment equipment, Client client, Vendor vendor) {
        this.equipment = equipment;
        this.client = client;
        this.vendor = vendor;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", equipment=" + equipment.getId() +
                ", client=" + client.getId() +
                ", vendor=" + vendor.getId() +
                '}';
    }
}
