package ke.co.turbosoft.compass.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by ktonym on 1/2/15.
 */
@Entity @IdClass(CategoryPrincipalId.class)
public class CategoryPrincipal extends AbstractEntity{

    private Boolean active;
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate wef;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "principal_id")
    private Principal principal;

    static final DateTimeFormatter DATE_FORMATTER_yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    public CategoryPrincipal() {
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDate getWef() {
        return wef;
    }

    public void setWef(LocalDate wef) {
        this.wef = wef;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        category.addJson(builder);
        principal.addJson(builder);
        builder.add("active", active ? "Y" : "N")
                .add("wef", wef == null ? "" : DATE_FORMATTER_yyyyMMdd.format(wef));

    }
}
