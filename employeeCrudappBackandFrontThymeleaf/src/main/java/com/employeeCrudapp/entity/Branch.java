package com.employeeCrudapp.entity;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javassist.compiler.ast.NewExpr;

@Entity
@Table(name="Branch")
public class Branch {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;

	@OneToMany(mappedBy = "branch",fetch = FetchType.LAZY)
	private List<Employee> employees = new ArrayList<>();


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Branch{" +
				"id=" + id +
				", name='" + name + '\'' +
				", employees=" + employees +
				'}';
	}
}
