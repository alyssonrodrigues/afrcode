import { OPERATION_STARTED, OPERATION_ENDED } from '../actions/operationProgressActions'

const INITIAL_STATE = { operationInProgress: false }

export default (state = INITIAL_STATE, action) => {
  switch (action.type) {
    case OPERATION_STARTED:
      return action.payload
    case OPERATION_ENDED:
      return action.payload
    default:
      return state
  }
}
