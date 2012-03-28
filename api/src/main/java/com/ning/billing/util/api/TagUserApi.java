/*
 * Copyright 2010-2011 Ning, Inc.
 *
 * Ning licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.ning.billing.util.api;

import java.util.List;

import org.joda.time.DateTime;

import com.ning.billing.util.tag.Tag;
import com.ning.billing.util.tag.TagDefinition;

public interface TagUserApi {
    /***
     *
     * @return
     */
    public List<TagDefinition> getTagDefinitions();

    /***
     *
     * @param name Identifies the definition.
     * @param description Describes the use of the definition.
     * @param createdBy The name of person who created the definition.
     * @return
     * @throws TagDefinitionApiException
     */
    public TagDefinition create(String name, String description, String createdBy) throws TagDefinitionApiException;

    /***
     *
     * @param definitionName Identifies the definition.
     * @throws TagDefinitionApiException
     */
    public void deleteAllTagsForDefinition(String definitionName) throws TagDefinitionApiException;

    /***
     *
     * @param definitionName Identifies the definition.
     * @throws TagDefinitionApiException
     */
    public void deleteTagDefinition(String definitionName) throws TagDefinitionApiException;

    
	/**
	 * 
	 * @param name
	 * @return the tag with this definition
     * @throws TagDefinitionApiException
	 */
	public TagDefinition getTagDefinition(String name) throws TagDefinitionApiException;
	
	/**
	 * @param addedBy
	 * @param addedDate
	 * @param controlTagType
	 * @throws TagApiException 
	 */
	public Tag createControlTag(String controlTagName, String addedBy, DateTime addedDate) throws TagDefinitionApiException;
	
	
	/**
	 * @param tagDefinitionName
	 * @param addedBy
	 * @param addedDate
	 * @return
	 */
	public Tag createDescriptiveTag(String tagDefinitionName, String addedBy, DateTime addedDate) throws TagDefinitionApiException;
	
}