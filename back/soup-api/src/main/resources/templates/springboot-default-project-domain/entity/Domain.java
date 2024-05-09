package :domain-package-name.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = ":entity-table-name")
public class :domain-class-name {

    @Id
    @Column(name = ":entity-table-name_id")
    private :entity-id-type id;
}
