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

    @OneToOne
    private Equipment equipment;

    @OneToOne
    private Client client;

    @OneToOne
    private Vendor vendor;

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
