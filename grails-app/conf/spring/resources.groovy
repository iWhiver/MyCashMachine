package spring

import com.belorechev.cashmachine.computer.processors.ConverterStandardImplementation
import com.belorechev.cashmachine.computer.processors.ValidatorStandardImplementation
import com.belorechev.cashmachine.data.CashBankGORMService

// Place your Spring DSL code here
beans = {
    validator(ValidatorStandardImplementation) {}
    converter(ConverterStandardImplementation) {}
    cashBank(CashBankGORMService) {}
}
