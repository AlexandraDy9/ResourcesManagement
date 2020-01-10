package com.tw.management.resources.persistence.roles;

import com.tw.management.resources.persistence.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity(name = "roles")
@Inheritance(strategy = InheritanceType.JOINED)
public class RolesEntity extends BaseEntity {
}
