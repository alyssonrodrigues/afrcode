import { store } from '../Root'
import { startOperationProgress, endOperationProgress } from '../actions/operationProgressActions'

export const SLOW_REQUEST_HEADER = 'X-Slow-Request'

export const SLOW_REQUEST_CONFIG = {
  headers: {
    [SLOW_REQUEST_HEADER]: true
  }
}

export const showProgressDialog = () => {
  store.dispatch(startOperationProgress())
}

export const closeProgressDialog = () => {
  store.dispatch(endOperationProgress())
}
