package fu.talenthub.shared.common;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;


@MappedSuperclass
@Getter@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    // These fields are filled automatically by Spring Data JPA Auditing.
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private OffsetDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    @Setter(AccessLevel.NONE)
    private OffsetDateTime updatedAt;

    @CreatedBy
    @Column(name = "created_by", length = 255, updatable = false)
    @Setter(AccessLevel.NONE)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by", length = 255)
    @Setter(AccessLevel.NONE)
    private String updatedBy;

    @Column(name = "is_deleted", nullable = false)
    @Setter(AccessLevel.NONE)
    private boolean deleted = false;

    @Column(name = "deleted_at")
    @Setter(AccessLevel.NONE)
    private OffsetDateTime deletedAt;

    /**
     * Marks the entity as deleted without physically removing its database row.
     */
    public void markDeleted() {
        this.deleted = true;
        this.deletedAt = OffsetDateTime.now();
    }

    /**
     * Restores an entity previously marked as deleted.
     */
    public void restore() {
        this.deleted = false;
        this.deletedAt = null;
    }
}
