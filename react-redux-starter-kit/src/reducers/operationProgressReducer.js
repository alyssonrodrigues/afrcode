import { OPERATION_STARTED, OPERATION_ENDED } from '../actions/operationProgressActions'

const INITIAL_STATE = { numOperationsInProgress: 0, operationInProgress: false }

export default (state = INITIAL_STATE, action) => {
  switch (action.type) {
    case OPERATION_STARTED: {
      const numOperationsInProgress = state.numOperationsInProgress + 1
      return {
        numOperationsInProgress,
        operationInProgress: Boolean(numOperationsInProgress)
      }
    }
    case OPERATION_ENDED: {
      const numOperationsInProgress = state.numOperationsInProgress - 1
      return {
        numOperationsInProgress,
        operationInProgress: Boolean(numOperationsInProgress)
      }
    }
    default:
      return state
  }
}
