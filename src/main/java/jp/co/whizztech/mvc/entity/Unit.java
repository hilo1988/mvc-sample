package jp.co.whizztech.mvc.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by hilo on 2017/05/24.
 */
@Table(name = "unit")
@Entity
@Data
public class Unit {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;


}
