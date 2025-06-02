package run.moku.framework.jpa.entity

import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validation
import jakarta.validation.Validator
import run.moku.framework.adapter.validator.throwUnless

interface Validatable

private val validator: Validator by lazy {
    Validation.buildDefaultValidatorFactory().validator
}

fun <T : Validatable> T.validateSelf() {
    val violation = validator.validate(this)

    throwUnless(violation.isEmpty(), ConstraintViolationException(violation))
}