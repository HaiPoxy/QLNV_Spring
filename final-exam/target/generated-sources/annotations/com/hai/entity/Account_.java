package com.hai.entity;

import com.hai.entity.Account.Role;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Account.class)
public abstract class Account_ {

	public static volatile SingularAttribute<Account, String> firstName;
	public static volatile SingularAttribute<Account, String> lastName;
	public static volatile SingularAttribute<Account, LocalDateTime> createdAt;
	public static volatile SingularAttribute<Account, String> password;
	public static volatile SingularAttribute<Account, Role> role;
	public static volatile SingularAttribute<Account, String> fullName;
	public static volatile SingularAttribute<Account, Integer> id;
	public static volatile SingularAttribute<Account, Department> department;
	public static volatile SingularAttribute<Account, String> username;
	public static volatile SingularAttribute<Account, LocalDateTime> updatedAt;

	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String CREATED_AT = "createdAt";
	public static final String PASSWORD = "password";
	public static final String ROLE = "role";
	public static final String FULL_NAME = "fullName";
	public static final String ID = "id";
	public static final String DEPARTMENT = "department";
	public static final String USERNAME = "username";
	public static final String UPDATED_AT = "updatedAt";

}

