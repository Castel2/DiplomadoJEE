package co.com.claro.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vendor")
@Data
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String dni;

    @Column(nullable = false)
    private String code;

    private String city;

    @OneToOne
    private TypeVendor typeVendor;

    public Vendor() {
    }

    public Vendor(String name, String dni, String code, String city, TypeVendor typeVendor) {
        this.name = name;
        this.dni = dni;
        this.code = code;
        this.city = city;
        this.typeVendor = typeVendor;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dni='" + dni + '\'' +
                ", code='" + code + '\'' +
                ", city='" + city + '\'' +
                ", typeVendor=" + typeVendor.getType() +
                '}';
    }
}
