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

import java.util.List;

/**
 * Stores and exposes information about validation
 * errors for a specific object.
 * <p>
 * <p>Note: {@code Errors} objects are single-threaded.
 *
 * @author Ruslan Lazin
 */
public interface Errors {

    /**
     * Add error.
     *
     * @param error the error
     */
    void addError(ObjectError error);

    /**
     * Return if there were any errors.
     *
     * @return the boolean
     */
    boolean hasErrors();

    /**
     * Return the total number of errors.
     *
     * @return the error count
     */
    int getErrorCount();

    /**
     * Get all errors.
     *
     * @return List of {@link ObjectError} instances
     */
    List<ObjectError> getAllErrors();

}
