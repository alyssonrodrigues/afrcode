export const OPERATION_STARTED = '@operationProgressActions/OPERATION_STARTED'
export const OPERATION_ENDED = '@operationProgressActions/OPERATION_ENDED'

export const startOperationProgress = () => {
  return {
    type: OPERATION_STARTED
  }
}

export const endOperationProgress = () => {
  return {
    type: OPERATION_ENDED
  }
}
