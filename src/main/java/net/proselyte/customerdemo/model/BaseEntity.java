package net.proselyte.customerdemo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Basic class - class with property ID
 * @author Maxim Eliseev
 * @version 1.0
 */

@MappedSuperclass
@Entity
@Table(name = "customers")
@Getter
@Setter
@ToString
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
