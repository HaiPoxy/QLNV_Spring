package com.hai.entity;

import com.hai.entity.Department.Type;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Department.class)
public abstract class Department_ {

	public static volatile SingularAttribute<Department, Integer> totalMembers;
	public static volatile SingularAttribute<Department, LocalDateTime> createdAt;
	public static volatile SingularAttribute<Department, String> name;
	public static volatile SingularAttribute<Department, Integer> id;
	public static volatile ListAttribute<Department, Account> accounts;
	public static volatile SingularAttribute<Department, Type> type;
	public static volatile SingularAttribute<Department, LocalDateTime> updatedAt;

	public static final String TOTAL_MEMBERS = "totalMembers";
	public static final String CREATED_AT = "createdAt";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String ACCOUNTS = "accounts";
	public static final String TYPE = "type";
	public static final String UPDATED_AT = "updatedAt";

}

