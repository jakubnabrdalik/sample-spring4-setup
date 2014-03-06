package eu.solidcraft.starter.domain.some

import spock.lang.Specification


class SomeEntitySpec extends Specification {
    def "calculate commission"() {
        given:
        def someEntity = new SomeEntity(someAmount: amount, interestRate: rate, someDate: new Date(year, 1, 2))

        expect:
        someEntity.calculateCommission() == commission

        where:
        year | amount | rate || commission
        2009 | 1000   | 0.11 || 25.30
        2011 | 1000   | 0.11 || 24.20
        2011 | 1500   | 0.11 || 36.30
    }
}
