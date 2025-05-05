package com.malise5.tickets.entity;

import com.malise5.tickets.enums.QrCodeStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "qr_codes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QrCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "code", nullable = false)
    private QrCodeStatusEnum code;

    @Column(name = "value", nullable = false)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;


    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        QrCode qrCode = (QrCode) o;
        return Objects.equals(id, qrCode.id) && code == qrCode.code && Objects.equals(value, qrCode.value) && Objects.equals(createdAt, qrCode.createdAt) && Objects.equals(updatedAt, qrCode.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, value, createdAt, updatedAt);
    }
}
