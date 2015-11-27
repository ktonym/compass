package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.util.List;

@Entity
public class Category extends AbstractEntity implements EntityItem<Integer>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCategory;
    @Column(nullable = false)
    private char cat;
	private String description;
	@OneToMany(mappedBy = "category")
	private List<CorpBenefit> corpBenefits;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "anniv_id",nullable = false)
    private CorpAnniv corpAnniv;
    @OneToMany(mappedBy = "category")
    private List<CategoryPrincipal> categoryPrincipal;

    public Category() {
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public List<CategoryPrincipal> getCategoryPrincipal() {
        return categoryPrincipal;
    }

    public void setCategoryPrincipal(List<CategoryPrincipal> categoryPrincipal) {
        this.categoryPrincipal = categoryPrincipal;
    }

    public char getCat() {
        return cat;
    }

    public void setCat(char cat) {
        this.cat = cat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CorpBenefit> getCorpBenefits() {
        return corpBenefits;
    }

    public void setCorpBenefits(List<CorpBenefit> corpBenefits) {
        this.corpBenefits = corpBenefits;
    }

    public CorpAnniv getCorpAnniv() {
        return corpAnniv;
    }

    public void setCorpAnniv(CorpAnniv corpAnniv) {
        this.corpAnniv = corpAnniv;
    }

    @Override
    public Integer getId() {
        return idCategory;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("idCategory", idCategory)
                .add("cat", cat)
                .add("description", description);
        if(corpAnniv !=null) {
            corpAnniv.addJson(builder);
        }
    }
}
