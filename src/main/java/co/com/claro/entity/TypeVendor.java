package co.com.claro.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "type_vendor")
@Data
public class TypeVendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Override
    public String toString() {
        return "TypeVendor{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
