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
public class Member extends AbstractEntity implements EntityItem<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idMember;
    private String memberNo;
    private String firstName;
    private String surname;
    private String otherNames;
    private Sex sex;
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate dob;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="principal_id",nullable = false)
    private Principal principal;
    private MemberType memberType;
    @OneToMany(mappedBy = "member")
    private List<MemberAnniversary> memberAnniversaries;

    static final DateTimeFormatter DATE_FORMATTER_yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    public Member() {
    }

    public Integer getIdMember() {
        return idMember;
    }

    public void setIdMember(Integer idMember) {
        this.idMember = idMember;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public List<MemberAnniversary> getMemberAnniversaries() {
        return memberAnniversaries;
    }

    public void setMemberAnniversaries(List<MemberAnniversary> memberAnniversaries) {
        this.memberAnniversaries = memberAnniversaries;
    }

    @Override
    public Integer getId() {
        return idMember;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
         builder.add("idMember",idMember)
                 .add("memberNo",memberNo)
                 .add("firstName",firstName)
                 .add("surname",surname)
                 .add("otherNames",otherNames)
                 .add("sex",sex.toString())
                 .add("dob", dob == null ? "" : DATE_FORMATTER_yyyyMMdd.format(dob))
                 .add("memberType", memberType.toString());
        if(principal!=null) {
            principal.addJson(builder);
        }
    }
}
