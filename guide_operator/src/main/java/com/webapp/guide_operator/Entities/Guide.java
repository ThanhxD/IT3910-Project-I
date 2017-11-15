package com.webapp.guide_operator.Entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "Guide")
public class Guide  implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;
	
	@OneToOne
	@JoinColumn(name = "userid", referencedColumnName = "id", unique = true)
    private User user;
    
	@Length(max = 50)
    @Column(name = "cardnumber", nullable = true)
    private String cardnumber;
	
	@Length(max = 50)
    @Column(name = "cardtype", nullable = true)
    private String cardtype;
    
	@Length(max = 50)
    @Column(name = "status", nullable = true)
	private  String status;
	
	@Column(name = "expirationdate", nullable = true)
    private Date expirationdate;
    
	@Length(max = 255)
    @Column(name = "experience", nullable = true)
	private String experience;
    
	@Length(max = 20)
    @Column(name = "gender", nullable = true)
    private String gender;
    
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(Date expirationdate) {
        this.expirationdate = expirationdate;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Guide(int id,User user,String  username, String password, String fullname, String adress, String phonenumber, String email, int is_guide, String cardnumber, String cardtype, String status, Date expirationdate, String experience, String gender) {
        this.id= id;
        this.user=user;
        this.cardnumber = cardnumber;
        this.cardtype = cardtype;
        this.status = status;
        this.expirationdate = expirationdate;
        this.experience = experience;
        this.gender = gender;
    }
    public Guide(){
    }
}
