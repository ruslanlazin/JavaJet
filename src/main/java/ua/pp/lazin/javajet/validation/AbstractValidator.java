/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ua.pp.lazin.javajet.validation;

/**
 * A validator for application-specific objects.
 *
 * @param <T> the type parameter
 * @see Validator
 * @see Errors  * @author Ruslan Lazin
 */
public abstract class AbstractValidator<T> implements Validator<T>{

    /**
     * The Next validator.
     */
    protected Validator<T> nextValidator;

    public abstract Errors validate(T target, Errors errors);

    public Errors doNext(T target, Errors errors) {
        if (nextValidator != null) {
            return nextValidator.validate(target, errors);
        }
        return errors;
    }

    public Validator<T> setNextValidator(Validator<T> nextValidator) {
        this.nextValidator = nextValidator;
        return nextValidator;
    }
}
