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
 * <p>
 * <p>This interface is totally divorced from any infrastructure
 * or context; that is to say it is not coupled to validating
 * only objects in the web tier, the data-access tier, or the
 * whatever-tier. As such it is amenable to being used in any layer
 * of an application.
 *
 * @param <T> the type parameter
 * @author Ruslan Lazin
 * @see Errors
 */
public interface Validator<T> {

    /**
     * Validate the supplied <T> object,
     * <p>The supplied {@link Errors errors} instance can be used to report
     * any resulting validation errors.
     *
     * @param target the object that is to be validated (never {@code null})
     * @param errors contextual state about the validation process (never {@code null})
     * @return the errors
     */
    Errors validate(T target, Errors errors);

    /**
     * Do next validation.
     *
     * @param target the target
     * @param errors the errors
     * @return the errors
     */
    public Errors doNext(T target, Errors errors);

    /**
     * Sets next validator.
     *
     * @param nextValidator the next validator
     * @return the next validator
     */
    public Validator<T> setNextValidator(Validator<T> nextValidator);
}
