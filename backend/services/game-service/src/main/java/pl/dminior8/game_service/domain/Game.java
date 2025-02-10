package pl.dminior8.game_service.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Game {
    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.UUID)
    @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID id;

    private UUID routeId;

    private boolean isCompleted;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp startTime;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @Version
    private Long version;
}