package sgsits.cse.dis.administration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "library_book_category_count")
public class LibraryBookCategoryCount {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name="UUID",
			strategy="org.hibernate.id.UUIDGenerator"
		)
	@Column(name = "id", nullable = false, unique = true)
	private String id;
	
	@Column(name = "subject_category",unique = true, nullable = false)
	private String subjectCategory;
	
	@Column(name = "count",nullable = false)
	private Long count=1l;
	
	@Column(name = "subject_name",nullable = false)
	private String subjectName;
	
	public LibraryBookCategoryCount() {
		super();
	}
	
	public LibraryBookCategoryCount(String subjectCategory) {
		super();
		this.subjectCategory = subjectCategory;
	}

	public LibraryBookCategoryCount(String subjectCategory,String subjectName) {
		super();
		this.subjectCategory = subjectCategory;
		this.subjectName = subjectName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubjectCategory() {
		return subjectCategory;
	}

	public void setSubjectCategory(String subjectCategory) {
		this.subjectCategory = subjectCategory.toUpperCase();
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	
}
