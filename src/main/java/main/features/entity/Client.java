package main.features.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;


@Entity
@Table(name = "Client")
@Data
public class Client {
    // name validation between 2 and 200
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name",
            columnDefinition = "VARCHAR(200) NOT NULL CHECK (LENGTH(name) >= 3) AND CHECK(LENGTH(name) <= 500")
    private String name;

    public Client() {

    }

    public Client (String name){
        this.name = name;
    }

    public Client(Long id, String name){
        this.id = id;
        this.name = name;

    }


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (!Objects.equals(id, client.id)) return false;
        return Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
