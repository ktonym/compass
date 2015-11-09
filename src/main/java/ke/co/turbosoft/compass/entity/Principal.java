package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by akipkoech on 12/8/14.
 */
@Entity
public class Principal extends AbstractEntity implements EntityItem<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPrincipal;
    private String familyNo;
    private String firstName;
    private String surname;
    private String otherNames;
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate dob;
    @OneToMany(mappedBy = "principal")
    private List<Member> dependants;
    @OneToMany(mappedBy = "principal")
    private List<CategoryPrincipal> categoryPrincipal;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCorporate",nullable = false)
    private Corporate corporate;

    static final DateTimeFormatter DATE_FORMATTER_yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    public Principal() {
    }

    public Integer getIdPrincipal() {
        return idPrincipal;
    }

    public void setIdPrincipal(Integer idPrincipal) {
        this.idPrincipal = idPrincipal;
    }

    public List<CategoryPrincipal> getCategoryPrincipal() {
        return categoryPrincipal;
    }

    public void setCategoryPrincipal(List<CategoryPrincipal> categoryPrincipal) {
        this.categoryPrincipal = categoryPrincipal;
    }

    public String getFamilyNo() {
        return familyNo;
    }

    public void setFamilyNo(String familyNo) {
        this.familyNo = familyNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public List<Member> getDependants() {
        return dependants;
    }

    public void setDependants(List<Member> dependants) {
        this.dependants = dependants;
    }

    public Corporate getCorporate() {
        return corporate;
    }

    public void setCorporate(Corporate corporate) {
        this.corporate = corporate;
    }

    @Override
    public Integer getId() {
        return idPrincipal;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {

        builder.add("idPrincipal", idPrincipal)
                .add("familyNo", familyNo)
                .add("firstName", firstName)
                .add("surname", surname)
                .add("otherNames", otherNames)
                .add("dob", dob == null ? "" : DATE_FORMATTER_yyyyMMdd.format(dob))
                .add("fullName", firstName.concat(" ").concat(surname).concat(" ").concat(otherNames));

        if(corporate!=null){
            corporate.addJson(builder);
        }
    }
}
