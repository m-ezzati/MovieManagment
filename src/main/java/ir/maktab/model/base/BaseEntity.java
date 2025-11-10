package ir.maktab.model.base;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private ID id;
    private Instant createdAt;
    private Instant lastUpdate;

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = Instant.now();
        this.lastUpdate = Instant.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.lastUpdate = Instant.now();
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

}
