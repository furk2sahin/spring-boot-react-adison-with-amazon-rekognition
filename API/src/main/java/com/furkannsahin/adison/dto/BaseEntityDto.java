package com.furkannsahin.adison.dto;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;


@Data
@MappedSuperclass
public class BaseEntityDto implements Serializable {
    private Date createDate;
    private String image;
    private String description;
}
