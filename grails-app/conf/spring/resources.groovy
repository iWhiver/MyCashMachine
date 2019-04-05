package spring

import com.belorechev.cashmachine.computer.processors.ValidatorStandardImplementation
import com.belorechev.cashmachine.data.CashBankTreeMap

// Place your Spring DSL code here
beans = {
    validator(ValidatorStandardImplementation) {}
    cashBank(CashBankTreeMap) {}
}
