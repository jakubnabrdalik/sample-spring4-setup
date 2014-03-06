package eu.solidcraft.starter.domain.some

import groovy.transform.TypeChecked

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.validation.constraints.NotNull

@TypeChecked
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
    private BigDecimal interestRate;

    @NotNull
    private Date someDate;

    private SomeEntity() {
    }

    SomeEntity(String username, BigDecimal someAmount, Date someDate) {
        this(username, someAmount, 0.10, someDate)
    }

    SomeEntity(String username, BigDecimal someAmount, BigDecimal interestRate, Date someDate) {
        this.username = username
        this.someAmount = someAmount
        this.interestRate = interestRate
        this.someDate = someDate
    }

    // TODO: round to one decimal place
    BigDecimal calculateCommission() {
        def commissionRate = someDate.after(new Date(2010, 1, 1)) ? interestRate * 0.22 : interestRate * 0.23
        def commission = commissionRate * someAmount
        return commission.setScale(2)

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
