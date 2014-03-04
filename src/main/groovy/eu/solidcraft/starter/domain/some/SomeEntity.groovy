package eu.solidcraft.starter.domain.some

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.validation.constraints.NotNull

@Entity
class SomeEntity {
    @Id
    @SequenceGenerator(name = "SomeSequence", sequenceName = "SEQ_SOME_PK", initialValue=10000)
    @GeneratedValue(generator = "SomeSequence")
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private BigDecimal someAmount;

    @NotNull
    private Date someDate;

    private SomeEntity() {
    }

    SomeEntity(String username, BigDecimal someAmount, Date someDate) {
        this.username = username
        this.someAmount = someAmount
        this.someDate = someDate
    }

    Long getId() {
        return id
    }

    String getUsername() {
        return username
    }

    BigDecimal getSomeAmount() {
        return someAmount
    }

    Date getSomeDate() {
        return someDate
    }
}
