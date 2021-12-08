package tr.com.nekasoft.statemachinepoc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Product extends BaseEntity {

    private static final long serialVersionUID = 5290564464431930279L;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Long quantity;
}
