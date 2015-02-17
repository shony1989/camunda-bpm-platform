/* Licensed under the Apache License, Version 2.0 (the "License");
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
package org.camunda.bpm.engine.impl.history.event;

import static org.camunda.bpm.engine.impl.util.JobExceptionUtil.getJobExceptionStacktrace;

import java.util.Date;

import org.camunda.bpm.engine.history.JobState;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.persistence.entity.ByteArrayEntity;

/**
 * @author Roman Smirnov
 *
 */
public class HistoricJobLogEvent extends HistoryEvent {

  private static final long serialVersionUID = 1L;

  protected Date timestamp;

  protected String jobId;

  protected String jobDefinitionId;

  protected String activityId;

  protected String jobType;

  protected String jobHandlerType;

  protected Date jobDueDate;

  protected int retries;

  protected String jobExceptionMessage;

  protected String exceptionByteArrayId;

  protected String processDefinitionKey;

  protected String deploymentId;

  protected int state;

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public String getJobId() {
    return jobId;
  }

  public void setJobId(String jobId) {
    this.jobId = jobId;
  }

  public String getJobDefinitionId() {
    return jobDefinitionId;
  }

  public void setJobDefinitionId(String jobDefinitionId) {
    this.jobDefinitionId = jobDefinitionId;
  }

  public String getActivityId() {
    return activityId;
  }

  public void setActivityId(String activityId) {
    this.activityId = activityId;
  }

  public String getJobType() {
    return jobType;
  }

  public void setJobType(String jobType) {
    this.jobType = jobType;
  }

  public String getJobHandlerType() {
    return jobHandlerType;
  }

  public void setJobHandlerType(String jobHandlerType) {
    this.jobHandlerType = jobHandlerType;
  }

  public Date getJobDueDate() {
    return jobDueDate;
  }

  public void setJobDueDate(Date jobDueDate) {
    this.jobDueDate = jobDueDate;
  }

  public int getJobRetries() {
    return retries;
  }

  public void setJobRetries(int jobRetries) {
    this.retries = jobRetries;
  }

  public String getJobExceptionMessage() {
    return jobExceptionMessage;
  }

  public void setJobExceptionMessage(String jobExceptionMessage) {
    this.jobExceptionMessage = jobExceptionMessage;
  }

  public String getExceptionByteArrayId() {
    return exceptionByteArrayId;
  }

  public void setExceptionByteArrayId(String exceptionByteArrayId) {
    this.exceptionByteArrayId = exceptionByteArrayId;
  }

  public String getExceptionStacktrace() {
    ByteArrayEntity byteArray = getExceptionByteArray();
    return getJobExceptionStacktrace(byteArray);
  }

  protected ByteArrayEntity getExceptionByteArray() {
    if (exceptionByteArrayId != null) {
      return Context
        .getCommandContext()
        .getDbEntityManager()
        .selectById(ByteArrayEntity.class, exceptionByteArrayId);
    }

    return null;
  }

  public String getProcessDefinitionKey() {
    return processDefinitionKey;
  }

  public void setProcessDefinitionKey(String processDefinitionKey) {
    this.processDefinitionKey = processDefinitionKey;
  }

  public String getDeploymentId() {
    return deploymentId;
  }

  public void setDeploymentId(String deploymentId) {
    this.deploymentId = deploymentId;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public boolean isCreated() {
    return state == JobState.CREATED.getStateCode();
  }

  public boolean isFailed() {
    return state == JobState.FAILED.getStateCode();
  }

  public boolean isSuccessful() {
    return state == JobState.SUCCESSFUL.getStateCode();
  }

  public boolean isDeleted() {
    return state == JobState.DELETED.getStateCode();
  }

}
