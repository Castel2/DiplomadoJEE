package co.com.claro.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long dni;

    @ManyToOne
    private Sale sale;

    public Client() {
    }

    public Client(String name, Long dni, Sale sale) {
        this.name = name;
        this.dni = dni;
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dni=" + dni +
                ", sale=" + sale.getId() +
                '}';
    }
}
