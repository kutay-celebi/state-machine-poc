package tr.com.nekasoft.statemachinepoc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Order extends BaseEntity {

    private static final long serialVersionUID = -3679111365672819056L;

    @Column(name = "quantity")
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_order_product"), referencedColumnName = "id")
    private Product product;
}
